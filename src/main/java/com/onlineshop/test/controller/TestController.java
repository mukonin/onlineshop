package com.onlineshop.test.controller;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.VerificationToken;
import com.onlineshop.service.UserService;
import com.onlineshop.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class TestController {

	@Autowired
	private UserService userService;

	@Autowired
	private VerificationTokenService verificationTokenService;

	@GetMapping(value = "/")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@GetMapping(value = "/registr")
	public String getRegistr(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		return "registr";
	}

	@PostMapping(value = "/registr/submit")
	public String registrUser(@Valid @ModelAttribute("user") UserRegistrationDTO user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registr";
		}
		userService.registrUser(user);
		model.addAttribute("success", true);
		return "/user/endRegistration";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String getAccessDenied() {
		System.out.println("Access denied");
		return "accessDenied";
	}

	@RequestMapping(value = "/user/confirmmanager", method = RequestMethod.GET)
	public String getRegistrManager(@RequestParam("token") String token, Model model) {
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		if (verificationToken == null) {
			model.addAttribute("errmsg", "Invalid token");
			return "manager/confirmResult";
		}
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("errmsg", "Your invitation token has expired");
			return "manager/confirmResult";
		}
		model.addAttribute("user", new UserRegistrationDTO());
		model.addAttribute("veriftoken", verificationToken);
		return "manager/registrManager";
	}

	@RequestMapping(value = "/user/confirmmanager/submit", method = RequestMethod.POST)
	public String registrManager(@Valid @ModelAttribute("user") UserRegistrationDTO user,
								 BindingResult result,
								 @RequestParam("token") String token,
								 Model model) {
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		if (verificationToken == null) {
			model.addAttribute("errmsg", "Invalid token");
			return "manager/confirmResult";
		}
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("errmsg", "Your invitation token has expired");
			return "manager/confirmResult";
		}

		if (result.hasErrors()) {
			System.out.println("Has error");
			model.addAttribute("veriftoken", verificationToken);
			return "manager/registrManager";
		}
		user.setEmail(verificationToken.getEmail());
		userService.registrManager(user);
		verificationTokenService.deleteVerificationToken(verificationToken);
		model.addAttribute("errmsg", "Registration success");
		return "manager/confirmResult";
	}
}