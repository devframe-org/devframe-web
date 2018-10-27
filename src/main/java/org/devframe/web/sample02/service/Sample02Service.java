package org.devframe.web.sample02.service;

import java.util.List;

import org.devframe.web.sample02.vo.Sample02Grid1VO;
import org.devframe.web.sample02.vo.Sample02VO;

public interface Sample02Service {

	public List<Sample02Grid1VO> selectSampleList(Sample02VO sample02VO) throws Exception;
	public void saveSample(Sample02VO sample02VO) throws Exception;

}
