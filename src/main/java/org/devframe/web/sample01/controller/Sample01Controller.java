package org.devframe.web.sample01.controller;

import org.devframe.web.sample01.vo.Sample01VO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sample01")
public class Sample01Controller {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Sample01VO sample01VO) {
		return "sample01/sample01List";
	}

}
