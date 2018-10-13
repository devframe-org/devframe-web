//package org.devframe.web.common.service.impl;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import org.devframe.web.common.service.SecuredObjectService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Data
//public class SecuredObjectServiceImpl implements SecuredObjectService {
//
//	private AuthDao authDao;
//
//	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> selectResourceAuthAll() {
//		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
//
//		List<Map<String, Object>> resourceAuthList = authDao.selectResourceAuthAll(null);
//
//		String preResource = null;
//
//		for(Map<String, Object> resourceAuthMap : resourceAuthList) {
//			String url = (String)resourceAuthMap.get("URL");
//			String authRole = (String)resourceAuthMap.get("ATH");
//
//			AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(url);
//			List<ConfigAttribute> configAttributeList = new LinkedList<ConfigAttribute>();
//
//			if(preResource != null && url.equals(preResource)) {
//				List<ConfigAttribute> preConfigAttributeList = requestMap.get(antPathRequestMatcher);
//
//				configAttributeList.addAll(preConfigAttributeList);
//			}
//
//			configAttributeList.add(new SecurityConfig(authRole));
//
//			requestMap.put(antPathRequestMatcher, configAttributeList);
//
//			preResource = url;
//		}
//
//		return requestMap;
//	}
//
//	public List<ConfigAttribute> selectResourceAuth(String url) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("url", url);
//
//		List<Map<String, Object>> resourceAuthList = authDao.selectResourceAuth(paramMap);
//
//		List<ConfigAttribute> configAttributeList = new LinkedList<ConfigAttribute>();
//
//		for(Map<String, Object> resourceAuthMap : resourceAuthList) {
//			String authRole = (String)resourceAuthMap.get("ATH");
//
//			configAttributeList.add(new SecurityConfig(authRole));
//		}
//
//		return configAttributeList;
//	}
//
//}