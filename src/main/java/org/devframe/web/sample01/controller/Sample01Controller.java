package org.devframe.web.sample01.controller;

import java.util.List;

import org.devframe.web.sample01.service.Sample01Service;
import org.devframe.web.sample01.vo.Sample01Grid1VO;
import org.devframe.web.sample01.vo.Sample01VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample01")
public class Sample01Controller {

	@Autowired
	private Sample01Service sample01Service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Sample01VO sample01VO) throws Exception {
		List<Sample01Grid1VO> sample01Grid1VOList = sample01Service.selectSampleList(sample01VO);

		log.debug("sample01Grid1VOList.size() : " + sample01Grid1VOList.size());

		return "sample01/sample01List";
	}

}
