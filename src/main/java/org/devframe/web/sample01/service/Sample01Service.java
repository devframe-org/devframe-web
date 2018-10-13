package org.devframe.web.sample01.service;

import java.util.List;

import org.devframe.web.sample01.vo.Sample01Grid1VO;
import org.devframe.web.sample01.vo.Sample01VO;

public interface Sample01Service {

	public List<Sample01Grid1VO> selectSampleList(Sample01VO sample01VO) throws Exception;

}
