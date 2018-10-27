package org.devframe.web.common.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.devframe.web.common.mapper.AuthMapper;
import org.devframe.web.common.service.SecuredObjectService;
import org.devframe.web.common.vo.AuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service("securedObjectService")
public class SecuredObjectServiceImpl implements SecuredObjectService {

	@Autowired
	private AuthMapper authMapper;

	//주소 순으로 롤 권한을 체크한다. /** 가 맨 먼저 조회되면 모든 주소 호출은  ROLE_USER 이 없으면 계속 로그인 페이지만 호출되는 무한 루프에 빠진다.(로그인 주소도 ROLE_USER 로 처리되므로)
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> selectRscRoleAllList() {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();

		List<AuthVO> rscRoleAllList = authMapper.selectRscRoleAllList();

		String preResource = null;

		//주소 순으로 정렬되어야 한다.
		//url 1, ROLE_USER
		//url 1, ROLE_AGENT01
		//url 2, ROLE_USER
		//url 2, ROLE_AGENT02
		///**, ROLE_ANONYMOUS
		///**, ROLE_USER
		for(AuthVO _authVO : rscRoleAllList) {
			String url = _authVO.getUrl();
			String roleId = _authVO.getRoleId();

			AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(url);
			List<ConfigAttribute> configAttributeList = new LinkedList<ConfigAttribute>();

			if(preResource != null && url.equals(preResource)) {
				List<ConfigAttribute> preConfigAttributeList = requestMap.get(antPathRequestMatcher);

				configAttributeList.addAll(preConfigAttributeList);
			}

			configAttributeList.add(new SecurityConfig(roleId));

			requestMap.put(antPathRequestMatcher, configAttributeList);

			preResource = url;
		}

		return requestMap;
	}

	public List<ConfigAttribute> selectRscRoleList(String url) {
		List<AuthVO> rscRoleList = authMapper.selectRscRoleList(url);

		List<ConfigAttribute> configAttributeList = new LinkedList<ConfigAttribute>();

		for(AuthVO _authVO : rscRoleList) {
			String roleId = _authVO.getRoleId();

			configAttributeList.add(new SecurityConfig(roleId));
		}

		return configAttributeList;
	}

}