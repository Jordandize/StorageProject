package ua.edu.ukma.gpd.storage.configuration.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import ua.edu.ukma.gpd.storage.configuration.SecurityConfiguration;
import ua.edu.ukma.gpd.storage.service.CryptService;


public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private CryptService cryptService;

	public TokenAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		setAuthenticationSuccessHandler((request, response, authentication) ->
			SecurityContextHolder.getContext().setAuthentication(authentication)
		);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String token = request.getHeader(SecurityConfiguration.HEADER_SECURITY_TOKEN);
		return tokenToAuth(token);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authentication);
		chain.doFilter(request, response);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
	

	private Authentication tokenToAuth(String encodedToken) {
		Token token;
		Authentication auth;
		try {
			if(encodedToken != null) {
				String decodedToken = cryptService.decrypt(encodedToken);
				token = new ObjectMapper().readValue(decodedToken, Token.class);
				auth = this.getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(token.getEmail(), token.getPassword()));
			} else {
				auth = null;
			}
		} catch (Exception e) {
			auth = null;
		}
		return auth;
	}

	public void setCryptService(CryptService cryptService) {
		this.cryptService = cryptService;
	}
	
}
