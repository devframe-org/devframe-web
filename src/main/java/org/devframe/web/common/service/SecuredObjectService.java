package org.devframe.web.common.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

public interface SecuredObjectService {

	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> selectResourceAuthAll();
	public List<ConfigAttribute> selectResourceAuth(String url);

}