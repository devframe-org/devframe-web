package org.devframe.web.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private String targetUrlParameter = null;
	private String defaultTargetUrl = "/";
	private boolean alwaysUseDefaultTargetUrl = false;
	private boolean useReferer = false;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response);

		if(response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to " + targetUrl);

            return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);

		HttpSession session = request.getSession(false);

		if(session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		if(isAlwaysUseDefaultTargetUrl()) {
			return defaultTargetUrl;
		}

		String targetUrl = null;

		if(StringUtils.isNotEmpty(targetUrlParameter)) {
			targetUrl = request.getParameter(targetUrlParameter);

			if(StringUtils.isNotEmpty(targetUrl)) {
				log.debug("Found targetUrlParameter in request : " + targetUrl);

				return targetUrl;
			}
		}

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if(savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();

			if(StringUtils.isNotEmpty(targetUrl)) {
				log.debug("savedRequest : " + targetUrl);

				return targetUrl;
			}
		}

		if(useReferer) {
			targetUrl = request.getHeader("Referer");

			if(StringUtils.isNotEmpty(targetUrl)) {
				log.debug("useReferer : " + targetUrl);

				return targetUrl;
			}
		}

		targetUrl = defaultTargetUrl;

		log.debug("defaultTargetUrl : " + targetUrl);

		return targetUrl;
	}

}