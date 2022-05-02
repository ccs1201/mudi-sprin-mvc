package br.com.ccs.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogginController {

	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	public String autenticate() {
		return null;
	}
}
