package org.devframe.web.sample02.mapper;

import java.util.List;

import org.devframe.web.sample02.vo.Sample02Grid1VO;
import org.devframe.web.sample02.vo.Sample02VO;

public interface Sample02Mapper {

	public List<Sample02Grid1VO> selectSampleList(Sample02VO sample02VO);
	public Sample02VO selectSample(Sample02VO sample02VO);
	public int insertSample(Sample02VO sample02VO);
	public int updateSample(Sample02VO sample02VO);
	public int deleteSample(Sample02VO sample02VO);

}
