package io.sunuh.springboot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.sunuh.springboot.utility.JWTUtility;

@Component
public class JwtFilter extends OncePerRequestFilter{

	private JWTUtility jwtUtility;

	private UserDetailsService userDetailsService;
	
	public JwtFilter(UserDetailsService userDetailsService, JWTUtility jwtUtility) {
		this.userDetailsService = userDetailsService;
		this.jwtUtility = jwtUtility;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authToken = jwtUtility.getToken(httpServletRequest);
		
		if(null != authToken) {
			
			String userName = jwtUtility.getUsernameFromToken(authToken);
			
			if(null != userName) {
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
					
				if(jwtUtility.validateToken(authToken, userDetails)) {
					
					UsernamePasswordAuthenticationToken authentication = 
							new UsernamePasswordAuthenticationToken(
									userDetails,
									null,
									userDetails.getAuthorities()
									);
					
					SecurityContextHolder.getContext()
										 .setAuthentication(authentication);
					
				}
			}
		}
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
