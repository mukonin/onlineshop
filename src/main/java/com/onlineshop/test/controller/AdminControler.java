package com.onlineshop.test.controller;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.UserStatus;
import com.onlineshop.service.MailService;
import com.onlineshop.service.UserService;
import com.onlineshop.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;


@Controller
public class AdminControler {

	public static final Integer MANAGERS_PER_PAGE = 3;

	@Autowired
	private MailService mailService;

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/managers", method = RequestMethod.GET)
	public String getManagersPage(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		int countPages = userService.pageCount(userService.getCountByRole("ROLE_MANAGER"), MANAGERS_PER_PAGE);
		model.addAttribute("UserStatus", UserStatus.class);
		model.addAttribute("countPages", countPages);
		model.addAttribute("page", page);
		model.addAttribute("managers", userService.findByRole(page, MANAGERS_PER_PAGE, "ROLE_MANAGER"));
		return "admin/managersList";
	}

	@RequestMapping(value = "/admin/invitemanager", method = RequestMethod.GET)
	public String inviteManagerPage(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		return "admin/inviteManager";
	}

	@RequestMapping(value = "/admin/invitemanager/submit", method = RequestMethod.POST)
	public String inviteManager(@Valid @ModelAttribute("user") UserRegistrationDTO user,
								BindingResult result,
								HttpServletRequest request,
								RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "admin/inviteManager";
		}

		String appURL = String.format("%s://%s:%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath());
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		verificationTokenService.createVerificationToken(token, user.getEmail());
		mailService.sendInviteManagerMail(user.getEmail(), token, appURL);
		redirectAttributes.addFlashAttribute("message", "Manager successfu was invited");
		return "redirect:/admin/managers";
	}

	@RequestMapping(value = "/admin/manager/setstatus", method = RequestMethod.POST)
	public String setStatus(@RequestParam(value = "page", defaultValue = "1") Integer page,
							@RequestParam("id") Long id,
							@RequestParam("status") UserStatus status) {
		userService.setStatus(id, status);
		return "redirect:/admin/managers?page=" + page;
	}

}
