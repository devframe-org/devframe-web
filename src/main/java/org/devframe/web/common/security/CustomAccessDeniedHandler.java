package org.devframe.web.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Slf4j
@Data
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private String errorPage;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		boolean isAjaxCall = StringUtils.isNotEmpty(request.getHeader("X-Ajax-Call")) && Boolean.parseBoolean(request.getHeader("X-Ajax-Call")) ? true : false;

		log.debug("Access is denied. isAjaxCall : " + isAjaxCall);

		if(!response.isCommitted()) {
			if(isAjaxCall) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", exception.getMessage());

				response.getWriter().print(jsonObject.toString());
				response.getWriter().flush();
			} else {
				if(StringUtils.isEmpty(errorPage)) {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
				} else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);

					request.setAttribute(WebAttributes.ACCESS_DENIED_403, exception);
					request.getRequestDispatcher(errorPage).forward(request, response);
				}
			}
        }
	}

}