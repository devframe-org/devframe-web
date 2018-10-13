package org.devframe.web.sample01.service.impl;

import java.util.List;

import org.devframe.web.sample01.mapper.Sample01Mapper;
import org.devframe.web.sample01.service.Sample01Service;
import org.devframe.web.sample01.vo.Sample01Grid1VO;
import org.devframe.web.sample01.vo.Sample01VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Sample01ServiceImpl implements Sample01Service {

	@Autowired
	private Sample01Mapper sample01Mapper;

	@Override
	public List<Sample01Grid1VO> selectSampleList(Sample01VO sample01VO) throws Exception {
		List<Sample01Grid1VO> sample01Grid1VOList = sample01Mapper.selectSampleList(sample01VO);

		return sample01Grid1VOList;
	}

	@Override
	public void saveSample(Sample01VO sample01VO) throws Exception {
		int insertCnt = sample01Mapper.insertSample(sample01VO);

		log.debug("insertCnt : " + insertCnt);
		log.debug("sn : " + sample01VO.getSn());
	}

}
