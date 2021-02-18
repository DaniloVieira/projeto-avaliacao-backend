package com.zalpi.avaliacaobackend.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zalpi.avaliacaobackend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@NonNull
	private AuthenticationManager authenticationManager;

	@NonNull
	private SecurityConfigInfoConstants config;

	@SneakyThrows
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String usernanme = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
		String token = Jwts
			.builder()
			.setSubject(usernanme)
			.setExpiration(new Date(System.currentTimeMillis() + config.getExpirationTime()))
			.signWith(SignatureAlgorithm.HS512, config.getSecret())
			.compact();
		response.addHeader(config.getHeaderString(), config.getTokenPefix() + token);
	}
}
