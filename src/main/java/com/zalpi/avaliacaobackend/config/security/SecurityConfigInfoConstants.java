package com.zalpi.avaliacaobackend.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SecurityConfigInfoConstants {

	public static final String publicUrl = "/api/public/";

	@Value("${ZALPI_APPLICATION_SECRET:qAwSeD}")
	private String secret;

	@Value("${ZALPI_APPLICATION_TOKEN_PREFIX:Bearer}")
	private String tokenPefix;

	@Value("${ZALPI_APPLICATION_HEADER_STRING:authorization}")
	private String headerString;

	@Value("${ZALPI_APPLICATION_EXPIRATION_TIME:86400000}")
	private long expirationTime;
}
