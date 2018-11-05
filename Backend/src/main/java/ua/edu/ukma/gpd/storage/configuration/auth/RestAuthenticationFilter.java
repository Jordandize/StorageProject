package ua.edu.ukma.gpd.storage.configuration.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response) {
		UsernamePasswordAuthenticationToken userAuth = jsonToAuthRequest(request);
		setDetails(request, userAuth);
		Authentication auth = this.getAuthenticationManager().authenticate(userAuth);
		if(auth != null && auth.getPrincipal() instanceof UserPrincipal) {
			((UserPrincipal) auth.getPrincipal())
					.setRawPassword(userAuth.getCredentials().toString());
		}
		return auth;
	}
	
	private UsernamePasswordAuthenticationToken jsonToAuthRequest(HttpServletRequest request) {
		String email = null, password = null;
		BufferedReader body = null;
		try {
			body = request.getReader();
			JsonNode json = 
					new ObjectMapper().readTree(body.lines().collect(Collectors.joining()));
			email = json.get(getUsernameParameter()).asText();
			password = json.get(getPasswordParameter()).asText();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				body.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
