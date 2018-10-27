package org.devframe.web.sample02.controller;

import java.util.List;

import org.devframe.web.sample02.service.Sample02Service;
import org.devframe.web.sample02.vo.Sample02Grid1VO;
import org.devframe.web.sample02.vo.Sample02VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample02")
public class Sample02Controller {

	@Autowired
	private Sample02Service sample02Service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Sample02VO sample02VO) throws Exception {
		List<Sample02Grid1VO> sample02Grid1VOList = sample02Service.selectSampleList(sample02VO);

		log.debug("sample02Grid1VOList.size() : " + sample02Grid1VOList.size());

		sample02VO.setNm("1");
		sample02VO.setCnt(1);
		sample02VO.setCtx("1");

		sample02Service.saveSample(sample02VO);

		return "sample02/sample02List";
	}

}
