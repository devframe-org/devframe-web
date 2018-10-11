package org.devframe.web.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private String defaultFailureUrl;
	private boolean forwardToDestination = false;
	private boolean allowSessionCreation = true;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if(StringUtils.isEmpty(defaultFailureUrl)) {
			log.debug("No failure URL set, sending 401 Unauthorized error");

			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed : " + exception.getMessage());
		} else {
			saveException(request, exception);

			if(forwardToDestination) {
				log.debug("Forwarding to " + defaultFailureUrl);

				request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
			} else {
				log.debug("Redirecting to " + defaultFailureUrl);

				redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
			}
		}
	}

	protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
		if(forwardToDestination) {
			request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
		} else {
			HttpSession session = request.getSession(false);

			if(session != null || allowSessionCreation) {
				request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
			}
		}
    }

}