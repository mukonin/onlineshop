package com.onlineshop.test.controller;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TestController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String home() {
		return "test";
	}

	@GetMapping(value = "/admin/**")
	public String admin() {
		return "testAdmin";
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@GetMapping(value = "/registr")
	public String getRegistr(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		return "registr";
	}

	@PostMapping(value = "/registr/submit")
	public String registrUser(@Valid @ModelAttribute("user") UserRegistrationDTO user, BindingResult result, Model model) {
		if(result.hasErrors()){
			return "registr";
		}
		userService.registrUser(user);
		model.addAttribute("success", true);
		return "/user/endRegistration";
	}
}