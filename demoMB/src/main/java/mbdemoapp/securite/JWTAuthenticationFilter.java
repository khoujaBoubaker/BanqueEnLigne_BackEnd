package mbdemoapp.securite;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import mbdemoapp.domain.Administrateur;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	
	private  AuthenticationManager authenticationManager;
	
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		Administrateur appUser=null;
		// si je renvoie les données au format www.urlencoded
		// alors 
		//String username=request.getParameter("username");
	//	String password=request.getParameter("password");
		try {
			appUser=new ObjectMapper().readValue(request.getInputStream(),Administrateur.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		System.out.println("*******");
		System.out.println("username :"+appUser.getUsername());
		System.out.println("password :"+appUser.getPassword());
		
		// TODO Auto-generated method stub
	
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),
					appUser.getPassword()));
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		User springUser=(User) authResult.getPrincipal();
		System.out.println("sprkuer :"+springUser);
		String jwt=Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
				.claim("roles",springUser.getAuthorities())
				.compact();
		System.out.println(jwt);
		
		response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+jwt);
		
		
		
		
		
		
		
		
		
	}

}
