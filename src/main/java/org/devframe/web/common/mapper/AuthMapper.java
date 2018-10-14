package org.devframe.web.common.mapper;

import java.util.List;

import org.devframe.web.common.vo.AuthVO;

public interface AuthMapper {

	public AuthVO selectUser(String userId);
	public List<AuthVO> selectUserRoleList(String userId);
	public List<AuthVO> selectRscRoleAllList();
	public List<AuthVO> selectRscRoleList(String url);

}
