package com.onlineshop.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping(value = "/")
	public String home() {
		return "test";
	}

	@GetMapping(value = "/admin/**")
	public String admin() {
		return "testAdmin";
	}
}