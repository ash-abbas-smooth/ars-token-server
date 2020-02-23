package com.smoothstack.avalance.lms.security.config;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

	//The path
	@Value("${security.jwt.uri:/login}")
    private String Uri;

	//header: Authorization
    @Value("${security.jwt.header:Authorization}")
    private String header;

    //prefix of token
    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;

	public String getUri() {
		return Uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public String getSecret() {
		return secret;
	}
    
}
