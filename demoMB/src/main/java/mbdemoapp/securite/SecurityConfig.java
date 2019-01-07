package mbdemoapp.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userdetailservice;
	
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder ;
	
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
/*
 * 	auth.inMemoryAuthentication().withUser("log").password("pass").roles("ADMIN");
 */
	auth.userDetailsService(userdetailservice)
	.passwordEncoder(bCryptPasswordEncoder);
	
}

@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	    
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// TODO Auto-generated method stub
		//http.formLogin();
	    
		http.authorizeRequests().antMatchers("/**","/login/**","/register/**").permitAll();

		//http.authorizeRequests().antMatchers(HttpMethod.GET,"/clients/**").hasAuthority("ADMIN");
		/*
		 * Configuration des acces aux urls
		 */
	
		//http.authorizeRequests().antMatchers("/clients/**").permitAll();
		http.authorizeRequests().antMatchers("/comptesCourants/**").permitAll();
		http.authorizeRequests().antMatchers("/agences/**").permitAll();
		http.authorizeRequests().antMatchers("/credits/**").permitAll();
		http.authorizeRequests().antMatchers("/clients/client/**").authenticated();
		//http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/clients/client/").permitAll();
		//http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/home/clients/").permitAll();
		
		
		//http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}



}
