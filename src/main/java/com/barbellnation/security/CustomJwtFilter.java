package com.barbellnation.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomJwtFilter  extends OncePerRequestFilter {
	private final JwtUtils jwtUtils;
	
	public CustomJwtFilter(JwtUtils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	

		String headerValue = request.getHeader("Authorization");
		if (headerValue != null && headerValue.startsWith("Bearer ")) {
			// 2. extract JWT .
			String jwt = headerValue.substring(7);
			// 3. validate token n 
			//get populated authentication object from the token
			Authentication authentication 
			= jwtUtils.populateAuthenticationTokenFromJWT(jwt);
			//4. store this auth obj under spring sec context holder
			SecurityContextHolder
			.getContext().setAuthentication(authentication);
		}
		//pass the control to the next filter in the filter chain
		filterChain.doFilter(request, response);

	}
}
