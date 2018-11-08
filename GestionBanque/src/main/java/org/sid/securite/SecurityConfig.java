package org.sid.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		/*
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
        .withUser("admin").password("admin").roles("ADMIN","USER").and()
        .withUser("user").password("user").roles("USER");
		*/
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username as principal,password as credentials, active from users where username=?")
		.authoritiesByUsernameQuery("select usrname as principal,role as role from userRole where usrname=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/compte","/consulterCompte").hasRole("USER");
		http.authorizeRequests().antMatchers("/saveOperation","/confirmation","/clients","/infoClient","/enregistrement","/saveClient").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	} 
	
}
