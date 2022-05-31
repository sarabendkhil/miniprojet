package com.sarra.gestion.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
	PasswordEncoder passwordEncoder = passwordEncoder ();
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(passwordEncoder);
	}

 @Override
 protected void configure(HttpSecurity http) throws Exception {
 http.authorizeRequests().antMatchers("/showCreate","/showCreateG").hasAnyRole("ADMIN","AGENT");
 http.authorizeRequests().antMatchers("/saveMusique","/saveGenre").hasAnyRole("ADMIN","AGENT");
 http.authorizeRequests().antMatchers("/ListeMusiques","/ListeGenres")
 .hasAnyRole("ADMIN","AGENT","USER");

 http.authorizeRequests()
 .antMatchers("/supprimerMusique","/modifierMusique","/updateMusique","/supprimerGenre","/modifierGenre","/updateGenre")
 .hasAnyRole("ADMIN");
 http.authorizeRequests().antMatchers("/login").permitAll();
	
	
 http.formLogin().loginPage("/login");
//pour faire fonctionner Bootstrap
 http.authorizeRequests().antMatchers("/webjars/**").permitAll();
 http.authorizeRequests().anyRequest().authenticated();
 http.exceptionHandling().accessDeniedPage("/accessDenied");
 }
 
 @Bean
 public PasswordEncoder passwordEncoder () {
 return new BCryptPasswordEncoder();
 }

 
 
}