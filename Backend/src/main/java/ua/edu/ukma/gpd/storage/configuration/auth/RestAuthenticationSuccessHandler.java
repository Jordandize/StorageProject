package ua.edu.ukma.gpd.storage.configuration.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ua.edu.ukma.gpd.storage.configuration.SecurityConfiguration;
import ua.edu.ukma.gpd.storage.service.CryptService;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private CryptService cryptService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
    		Authentication authentication) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectNode responseUser = mapper.createObjectNode();
    	responseUser.put("email", authentication.getName());
    	ArrayNode roles = mapper.createArrayNode();
    	for(GrantedAuthority auth: authentication.getAuthorities())
    		roles.add(auth.toString());
    	responseUser.putPOJO("roles", roles);
    	response.getWriter().write(responseUser.toString());
    	UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    	response.setHeader(SecurityConfiguration.HEADER_SECURITY_TOKEN,
    			cryptService.encrypt(
    					mapper.writeValueAsString(new Token(userPrincipal.getUsername(), userPrincipal.getRawPassword()))));
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        if(isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null 
        		&& StringUtils.hasText(request.getParameter(targetUrlParam)))) {
        	requestCache.removeRequest(request, response);
        	clearAuthenticationAttributes(request);
            return;
        }
        clearAuthenticationAttributes(request);
    }
 
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
    
}
