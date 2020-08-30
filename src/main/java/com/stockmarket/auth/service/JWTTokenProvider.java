package com.stockmarket.auth.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.stockmarket.auth.models.UserPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTTokenProvider {
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(UserPrincipal userPrincipal) {

		List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return Jwts.builder().setIssuer("Demo App").setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationInMs * 10000)).claim("Roles", roles)
				.claim("id", userPrincipal.getUserId()).claim("email", userPrincipal.getUserEmail())
				.claim("mobile", userPrincipal.getUserMobileNumber()).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public boolean validateToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
			return true;
		} catch (MalformedJwtException ex) {
//			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
//			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//			log.error("JWT claims string is empty.");
		}
		return false;

	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}
