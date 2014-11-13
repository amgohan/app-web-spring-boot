package com.entreprise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Configuration to handle authentication by using form login.
 * 
 * @author Sidi Amine
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Value("${app.security.bcrypt.strength}")
    private int bcryptStrength;
    
    @Value("${app.security.token.secret}")
    private String tokenSecret;

    @Value("${app.security.token.cookieName}")
    private String cookieName;

    @Value("${app.security.token.tokenValiditySeconds}")
    private int tokenValiditySeconds;
    
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
	// @formatter:off
		http
		.csrf()
		.disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.NEVER)
		.enableSessionUrlRewriting(false)
		.and()
		.authorizeRequests()
		.antMatchers("/static/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(this.rememberMeAuthenticationFilter(), 
			BasicAuthenticationFilter.class)
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.deleteCookies(cookieName, "JSESSIONID")
		.permitAll()
		.and()
		.rememberMe().rememberMeServices(this.tokenBasedRememberMeService());
	// @formatter:on
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	// @formatter:off
		auth
		.userDetailsService(this.userDetailsServiceImpl)
		.passwordEncoder(this.bCryptPasswordEncoder());
	// @formatter:on
		auth.authenticationProvider(this.rememberMeAuthenticationProvider());
    }

    /**
     * @return BCryptPasswordEncoder algorithm for password
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder(this.bcryptStrength);
    }
    
    /**
     * @return RememberMeAuthenticationFilter
     * @throws Exception
     *             exception
     */
    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
    	return new RememberMeAuthenticationFilter(this.authenticationManager(), this.tokenBasedRememberMeService());
    }
    
    /**
     * @return RestTokenBasedRememberMeService
     */
    @Bean
    public TokenBasedRememberMeService tokenBasedRememberMeService() {
		TokenBasedRememberMeService service = new TokenBasedRememberMeService(this.tokenSecret,
			this.userDetailsServiceImpl);
		service.setAlwaysRemember(true);
		service.setCookieName(this.cookieName);
		service.setTokenValiditySeconds(this.tokenValiditySeconds);
		return service;
    }

    /**
     * @return RememberMeAuthenticationProvider
     */
    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
    	return new RememberMeAuthenticationProvider(this.tokenSecret);
    }
}
