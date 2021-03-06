package com.easyrun.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.easyrun.auth.oauth2.security.ClientGrantAuthentitationProvider;
import com.easyrun.auth.oauth2.security.ClientGrantFilter;
import com.easyrun.auth.security.UsernamePasswordAuthentitationProvider;
import com.easyrun.auth.security.UsernamePasswordFilter;
import com.easyrun.commons.security.AuthenticationTokenFilter;
import com.easyrun.commons.security.AuthenticationTokenProvider;
@Order(2)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
@ComponentScan({"com.easyrun.commons"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsernamePasswordAuthentitationProvider usernamePasswordAuthentitationProvider;
	
	@Autowired
	private AuthenticationTokenProvider authenticationTokenProvider;
	
	@Autowired
	private ClientGrantAuthentitationProvider clientGrantAuthentitationProvider;

	

	@Override
	public void configure(WebSecurity web) throws Exception {

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(usernamePasswordAuthentitationProvider);
		auth.authenticationProvider(clientGrantAuthentitationProvider);
		auth.authenticationProvider(authenticationTokenProvider);
		
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .authorizeRequests().antMatchers("/public/**").permitAll();
		http.csrf().disable();
	}

	@Bean
	public UsernamePasswordFilter getUsernamePasswordFilter() throws Exception {
		return new UsernamePasswordFilter(authenticationManager());
	}
	
	@Bean
	public ClientGrantFilter getClientGrantFilter() throws Exception {
		return new ClientGrantFilter(authenticationManager());
	}
	
	@Bean
	public AuthenticationTokenFilter getAuthenticationTokenFilter() throws Exception {
		return new AuthenticationTokenFilter(authenticationManager(), new String[] {"/public/**", "/JWK/**", "/mappings/**"}, new String[] {"/user/**", "/role/**", "/oauth2/client/**"});
	}

	
}
