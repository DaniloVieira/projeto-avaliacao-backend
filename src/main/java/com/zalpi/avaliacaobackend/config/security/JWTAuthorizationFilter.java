package com.zalpi.avaliacaobackend.config.security;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Deprecated
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


	private final UserDetailsService userDetailsService;

	private SecurityConfigInfoConstants config;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, SecurityConfigInfoConstants config) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
		this.config = config;
	}

	@Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader(config.getHeaderString());
		if(Objects.nonNull(header) && header.startsWith(config.getTokenPefix())){
			UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(request);
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
		String token = request.getHeader(config.getHeaderString());
		if(Objects.isNull(token)) return null;
		String username = Jwts.parser()
			.setSigningKey(config.getSecret())
			.parseClaimsJws(StringUtils.remove(token, config.getTokenPefix()))
			.getBody().getSubject();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return Objects.nonNull(username) ? new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()) : null;
	}
}
