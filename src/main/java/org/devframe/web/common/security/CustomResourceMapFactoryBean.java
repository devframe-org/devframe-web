package org.devframe.web.common.security;

import java.util.LinkedHashMap;
import java.util.List;

import org.devframe.web.common.service.SecuredObjectService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.Data;

@Data
public class CustomResourceMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

	private SecuredObjectService securedObjectService;
	private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap;

	public void init() throws Exception {
		requestMap = securedObjectService.selectRscRoleAllList();
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
		return requestMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}