package org.devframe.web.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/favicon.ico")
	public String favicon() {
		return "forward:/resources/images/favicon.ico";
	}

	@RequestMapping(value = "/login")
	public String login() {
		log.debug("login");

		log.debug("PW : " + bCryptPasswordEncoder.encode("1234"));

		return "common/login";
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied() {
		log.debug("accessDenied");

		return "common/accessDenied";
	}

}
