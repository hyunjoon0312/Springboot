package org.bigdatacenter.datalink.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/**");
		
	
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("joon").password("1234qwer").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.httpBasic().disable();
		
	  http.authorizeRequests() 
		.antMatchers("/R_linkedData/**").access("hasRole('USER')")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.usernameParameter("ssoId")
		.passwordParameter("password");
	  
	  http.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout");
		
	}
}