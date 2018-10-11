package org.devframe.web.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private String targetUrlParameter = null;
    private String defaultTargetUrl = "/";
    private boolean alwaysUseDefaultTargetUrl = false;
    private boolean useReferer = false;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response);

		if(response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to " + targetUrl);

			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		if(isAlwaysUseDefaultTargetUrl()) {
			return defaultTargetUrl;
		}

		String targetUrl = null;

		if(StringUtils.isNotEmpty(targetUrlParameter)) {
			targetUrl = request.getParameter(targetUrlParameter);

			if(StringUtils.isNotEmpty(targetUrl)) {
				log.debug("Found targetUrlParameter in request: " + targetUrl);

				return targetUrl;
			}
		}

		if(useReferer && StringUtils.isEmpty(targetUrl)) {
			targetUrl = request.getHeader("Referer");

			log.debug("useReferer : " + targetUrl);
		}

		if(StringUtils.isEmpty(targetUrl)) {
			targetUrl = defaultTargetUrl;

			log.debug("defaultTargetUrl : " + targetUrl);
		}

        return targetUrl;
    }

}