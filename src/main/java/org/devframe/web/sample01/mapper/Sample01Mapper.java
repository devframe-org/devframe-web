package org.devframe.web.sample01.mapper;

import java.util.List;

import org.devframe.web.sample01.vo.Sample01Grid1VO;
import org.devframe.web.sample01.vo.Sample01VO;

public interface Sample01Mapper {

	public List<Sample01Grid1VO> selectSampleList(Sample01VO sample01VO);
	public Sample01VO selectSample(Sample01VO sample01VO);
	public int insertSample(Sample01VO sample01VO);
	public int updateSample(Sample01VO sample01VO);
	public int deleteSample(Sample01VO sample01VO);

}
