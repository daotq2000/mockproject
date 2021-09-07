package fa.appcode.web.security;

import fa.appcode.web.commons.utility.CONSTANT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

 	@Qualifier("UserDetailsServiceImpl")
	@Autowired
	private UserDetailsService userService;

	@Autowired
	private JwtConfig jwtConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		    .csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	        .and()
		    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
				.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		    .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
				.antMatchers(HttpMethod.POST,"/auth").permitAll()
//				.antMatchers("/api/movies").hasRole("ADMIN")
//				.antMatchers("/api/invoice/**").hasRole(CONSTANT.ROLE_ADMIN)
//				.antMatchers("/api/member/**").hasRole(CONSTANT.ROLE_ADMIN)
//				.antMatchers("/api/promotion").hasRole(CONSTANT.ROLE_ADMIN)
//				.antMatchers("/api/cinemaRoom/**").hasRole(CONSTANT.ROLE_ADMIN)
//				.antMatchers("/api/scheduleSeat/**").hasRole(CONSTANT.ROLE_ADMIN)
		    .anyRequest().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
