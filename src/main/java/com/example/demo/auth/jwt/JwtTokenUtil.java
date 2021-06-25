package com.example.demo.auth.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.auth.model.UserBridge;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil {

	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	public JwtTokenUtil(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}


	public String generateToken(UserBridge userDetails) {

		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setClaims(setClaims(userDetails))
				.setIssuedAt(new Date())
				.setExpiration(Date.from(Instant.now().plus(Duration.ofDays(jwtConfig.getTokenExpirationAfterDays()))))
				.signWith(secretKey).compact();

	}

	private Map<String, Object> setClaims(UserBridge userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userDetails.getId());
		claims.put("tenantId", userDetails.getTenant());
		claims.put("permissions", userDetails.getAuthorities());  // can be like getPermissions(permissionRepository.getPermissionByuserId(userDetails.getuserId())
		return claims;
	}

}
