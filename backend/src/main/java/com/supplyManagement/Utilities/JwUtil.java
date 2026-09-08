package com.supplyManagement.Utilities;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwUtil {

	private final String SECRET = "abcdjwoefhweifgevbfgbgfrbrbogrbtrpbrtjbrtj";
	private final long EXPIRATION = 1000 * 60 * 60;
	private Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

	public String generateToken(String email) {
		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}

	public boolean validToken(String token) {
		try {
			extractEmail(token);
			return true;
		} catch (JwtException e) {

			return false;
		}
	}

	public String extractEmail(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
	}

}
