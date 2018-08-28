package com.mihailstefanov.photoalbum.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.mihailstefanov.photoalbum.services.UserService;
import com.mihailstefanov.photoalbum.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserServiceImpl userService;
	
	@Autowired
	public ApplicationSecurityConfiguration(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().disable()
			.csrf().csrfTokenRepository(csrfTokenRepository())
			.and().authorizeRequests()
			.antMatchers("/", "/register", "/login", "/browse").permitAll()
//			.antMatchers("/css/**", "/js/**").permitAll()
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.loginProcessingUrl("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/home")
			.and().rememberMe()
			.rememberMeParameter("rememberMe").rememberMeCookieName("rememberMeCookie").key("encryptionKeyTemp")
			.tokenValiditySeconds(10000)
			.userDetailsService(this.userService)
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
			.and().exceptionHandling().accessDeniedPage("/unauthorized");
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setSessionAttributeName("_csrf");
		return repository;
	}

}
