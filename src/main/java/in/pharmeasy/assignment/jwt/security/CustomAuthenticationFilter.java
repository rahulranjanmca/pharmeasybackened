package in.pharmeasy.assignment.jwt.security;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final String LOGIN_SUCCESS_URL = "/oauth/authorize?client_id={0}&response_type=code&redirect_uri={1}"; 
	private static final String LOGIN_ERROR_URL = "/oauth/login?client_id={0}&&redirect_uri={1}&error=true";
	
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		return super.attemptAuthentication(request, response);
	}


	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain , Authentication authResult) throws IOException, ServletException {
	    String url = MessageFormat.format(LOGIN_SUCCESS_URL,  request.getParameter("client_id"),request.getParameter("redirect_uri") );
	    setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(url));
	    super.successfulAuthentication(request, response, chain, authResult);

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
	    String url = MessageFormat.format(LOGIN_ERROR_URL,    request.getParameter("client_id"),request.getParameter("redirect_uri") );
	    setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(url));
	    super.unsuccessfulAuthentication(request, response, failed);
	}
	
	
}
