package org.devframe.web.sample02.service.impl;

import java.util.List;

import org.devframe.web.sample02.mapper.Sample02Mapper;
import org.devframe.web.sample02.service.Sample02Service;
import org.devframe.web.sample02.vo.Sample02Grid1VO;
import org.devframe.web.sample02.vo.Sample02VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Sample02ServiceImpl implements Sample02Service {

	@Autowired
	private Sample02Mapper sample02Mapper;

	@Override
	public List<Sample02Grid1VO> selectSampleList(Sample02VO sample02VO) throws Exception {
		List<Sample02Grid1VO> sample02Grid1VOList = sample02Mapper.selectSampleList(sample02VO);

		return sample02Grid1VOList;
	}

	@Override
	public void saveSample(Sample02VO sample02VO) throws Exception {
		int insertCnt = sample02Mapper.insertSample(sample02VO);

		log.debug("insertCnt : " + insertCnt);
		log.debug("sn : " + sample02VO.getSn());
	}

}
