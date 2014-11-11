package com.entreprise.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * a TokenBasedRememberMeServices that override the way to extract remember me
 * token and add it to response header.
 * 
 * @author Sidi Amine
 *
 */
public final class TokenBasedRememberMeService extends TokenBasedRememberMeServices {
	
	@Value("${app.security.token.cookieName}")
    private String cookieName;
    
    @Value("${app.security.token.defaultTokenValiditySeconds}")
    private int defaultTokenValiditySeconds;

    /**
     * @param key
     *            secret key
     * @param userDetailsService
     *            service userDetailsService
     */
    public TokenBasedRememberMeService(final String key, final UserDetailsService userDetailsService) {
    	super(key, userDetailsService);
    }

    /**
     * Locates the Spring Security remember me token in the request and returns
     * its value.
     *
     * @param request
     *            the submitted request which is to be authenticated
     * @return the value of the request header (which was originally provided by
     *         the cookie - API expects it in header)
     */
    @Override
    protected String extractRememberMeCookie(final HttpServletRequest request) {
    	if(request.getRequestURI().contains("/static/")) {
    		return null;
    	}
	    String token = null;
	    if (request.getCookies() == null) {
	    	return null;
	    }
		for (Cookie cookie : request.getCookies()) {
			if (cookieName.equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		if ((token == null) || (token.length() == 0)) {
		    return null;
		}
		
		return token;
    }

    @Override
    protected int calculateLoginLifetime(HttpServletRequest request, Authentication authentication) {
    	if (rememberMeRequested(request)) {
    		return getTokenValiditySeconds();
    	} else {
    		return defaultTokenValiditySeconds;
    	}
    }

    /**
     * true if remember-me requested.
     * @param request HttpServletRequest
     * @return true if remember-me requested
     */
    private boolean rememberMeRequested(final HttpServletRequest request) {
		String parameter = this.getParameter();
		String paramValue = request.getParameter(parameter);
	
		if (paramValue != null) {
		    if (paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on")
			    || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1")) {
			return true;
		    }
		}
	
		return false;
    }

}
