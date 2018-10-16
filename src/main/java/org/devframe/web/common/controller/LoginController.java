package org.devframe.web.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String login() {
		log.debug("login");

		return "common/login";
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied() {
		log.debug("accessDenied");

		return "common/accessDenied";
	}

}
