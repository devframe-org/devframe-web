package org.devframe.web.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.devframe.web.common.service.SecuredObjectService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private final Map<RequestMatcher, List<ConfigAttribute>> requestMap;

	private SecuredObjectService securedObjectService;

	public CustomFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap) {
		this.requestMap = requestMap;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		HttpServletRequest request = ((FilterInvocation)object).getRequest();

		Collection<ConfigAttribute> configAttributeList = null;

		for(Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
			if(entry.getKey().matches(request)) {
				configAttributeList = entry.getValue();

				break;
			}
		}

		return configAttributeList;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();

		for(Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
			configAttributes.addAll(entry.getValue());
		}

		return configAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public void reload() throws Exception {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadMap = securedObjectService.selectResourceAuthAll();
		Iterator<Entry<RequestMatcher, List<ConfigAttribute>>> iterator = reloadMap.entrySet().iterator();

		requestMap.clear();

		while(iterator.hasNext()) {
			Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();

			requestMap.put(entry.getKey(), entry.getValue());
		}
	}

}