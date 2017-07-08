package com.onlineshop.test.controller;

import com.onlineshop.dto.UserDto;
import com.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class TestController {

	@Autowired
	UserService service;


	@GetMapping(value = "/")
	public String home() {
		return "test";
	}

	@GetMapping(value = "/admin/**")
	public String admin() {
		return "testAdmin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "registration";
	}

	@RequestMapping(value = "/create_user", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto, BindingResult result){
		if (!result.hasErrors()) {
			service.registerNewUserAccount(accountDto);
			return new ModelAndView("successRegister", "user", accountDto);
		} else {
			return new ModelAndView("registration", "user", accountDto);
		}
	}




}