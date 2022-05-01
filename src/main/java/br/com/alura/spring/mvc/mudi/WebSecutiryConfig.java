package br.com.alura.spring.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/home", true))
				.logout(logout -> logout.logoutUrl("/logout"))
				.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * UserDetails user =
		 * User.builder().username("user").password(passwordEncoder().encode("user")).
		 * roles("USER") .build();
		 */
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
