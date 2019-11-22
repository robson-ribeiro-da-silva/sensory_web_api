package br.edu.ifrn.projetosensoryweb.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/analisesensorial/**").hasAnyAuthority("ADM", "COR")
				.antMatchers("/avaliador/**").hasAnyAuthority("ADM")
				.antMatchers("/produto/**").hasAnyAuthority("ADM", "COR")
				.antMatchers("/").hasAnyAuthority("ADM", "COR")
				.antMatchers("/usuario/**").hasAnyAuthority("ADM")
				.antMatchers("/avaliacaohedonica/**").hasAnyAuthority("ADM")
				.antMatchers("/api/avaliador/**").permitAll()
				.antMatchers("/api/analisesensorial/**").permitAll()
				.antMatchers("http://**").permitAll()
				.antMatchers("https://**").permitAll()
				.antMatchers("layout").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/vendor/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/scss/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/entrar").permitAll()
				.successForwardUrl("/index").and().logout().permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/entrar");
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	
	}
	
	
	
	 @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	  }
	 
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**",  "/vendor/**",  "/img/**",  "/js/**",  "/scss/**", "/h2/**");
		web.ignoring().antMatchers("/layout", "http::/**", "https::/**");
	}


}
