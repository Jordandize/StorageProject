package ua.edu.ukma.gpd.storage.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ua.edu.ukma.gpd.storage.configuration.auth.RestAuthenticationFilter;
import ua.edu.ukma.gpd.storage.configuration.auth.TokenAuthenticationProcessingFilter;
import ua.edu.ukma.gpd.storage.service.CryptService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	public static final String HEADER_SECURITY_TOKEN = "X-Auth-Token";

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CryptService cryptService;
	
	@Autowired
	public PasswordEncoder encoder;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService)
    		.passwordEncoder(encoder)
    		.and()
    		.inMemoryAuthentication()
    		.withUser("admin").password(encoder.encode("adminPass")).roles("ADMIN")
    		.and()
    		.withUser("user").password(encoder.encode("userPass")).roles("USER)");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.addFilterAfter(tokenAuthenticationFilter("/api/**"), UsernamePasswordAuthenticationFilter.class)
    		.addFilterBefore(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        	.exceptionHandling()
        	.authenticationEntryPoint(authEntryPoint)
        	.and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        	.authorizeRequests()
        	.antMatchers("/api/foos").authenticated()
        	.antMatchers("/api/admin/**").hasRole("ADMIN_ROLE")
        	.antMatchers("/login").anonymous()
        	.anyRequest().authenticated();
    }

    private RestAuthenticationFilter restAuthenticationFilter() throws Exception {
    	RestAuthenticationFilter filter = new RestAuthenticationFilter();
    	filter.setAuthenticationManager(authenticationManagerBean());
    	filter.setAuthenticationSuccessHandler(authSuccessHandler);
    	filter.setAuthenticationFailureHandler(authFailureHandler);
    	filter.setUsernameParameter("email");
    	return filter;
    }
    
    private TokenAuthenticationProcessingFilter tokenAuthenticationFilter(String defaultFilterProcessesUrl) throws Exception {
    	TokenAuthenticationProcessingFilter filter =
    			new TokenAuthenticationProcessingFilter(
    					defaultFilterProcessesUrl);
    	filter.setAuthenticationManager(authenticationManagerBean());
    	filter.setCryptService(cryptService);
    	return filter;
    }
    
}
