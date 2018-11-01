package ua.edu.ukma.gpd.storage.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ua.edu.ukma.gpd.storage.configuration.auth.RestAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
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
    		.addFilterBefore(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        	.exceptionHandling()
        	.authenticationEntryPoint(authEntryPoint)
        	.and()
        	.authorizeRequests()
        	.antMatchers("/api/foos").authenticated()
        	.antMatchers("/api/admin/**").hasRole("ADMIN_ROLE");
    }

    private RestAuthenticationFilter restAuthenticationFilter() throws Exception {
    	RestAuthenticationFilter filter = new RestAuthenticationFilter();
    	filter.setAuthenticationManager(authenticationManagerBean());
    	filter.setAuthenticationSuccessHandler(authSuccessHandler);
    	filter.setAuthenticationFailureHandler(authFailureHandler);
    	filter.setUsernameParameter("email");
    	return filter;
    }
    
}
