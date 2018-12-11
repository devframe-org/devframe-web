package org.devframe.web.common.xss;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestBodyXSSFIleter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
 		HttpServletResponse response = (HttpServletResponse)res;

		String contentType = request.getContentType();

		System.out.println("contentType : " + contentType);

		//Json 문자열 파라미터
		//contentType : application/json;charset=UTF-8
		if(contentType != null && contentType.indexOf("json") >= 0) {
			RequestWrapper requestWrapper = new RequestWrapper(request);

			chain.doFilter(requestWrapper, response);
 		//Json 객체 파라미터
		//contentType : application/x-www-form-urlencoded; charset=UTF-8
		} else {
			RequestWrapper2 requestWrapper = new RequestWrapper2(request);

 			chain.doFilter(requestWrapper, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}