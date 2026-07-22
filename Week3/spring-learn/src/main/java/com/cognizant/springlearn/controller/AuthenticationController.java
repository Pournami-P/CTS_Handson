package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Hands on: Create authentication service that returns JWT.
 *
 * Request: curl -s -u user:pwd http://localhost:8090/authenticate
 * Response: {"token":"<generated JWT>"}
 */
@RestController
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("START");
		LOGGER.debug("authHeader : {}", authHeader);

		String user = getUser(authHeader);
		String token = generateJwt(user);

		Map<String, String> map = new HashMap<>();
		map.put("token", token);

		LOGGER.info("END");
		return map;
	}

	/**
	 * Reads the Authorization header (Basic <base64(user:password)>),
	 * decodes it and returns just the username portion.
	 */
	private String getUser(String authHeader) {
		LOGGER.info("START");
		LOGGER.debug("authHeader : {}", authHeader);

		String encodedCredentials = authHeader.replace("Basic ", "");
		byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
		String decodedCredentials = new String(decodedBytes);
		String user = decodedCredentials.substring(0, decodedCredentials.indexOf(":"));

		LOGGER.debug("user : {}", user);
		LOGGER.info("END");
		return user;
	}

	/**
	 * Generates a JWT for the given user, valid for 20 minutes,
	 * signed using HS256 with the shared secret "secretkey".
	 */
	private String generateJwt(String user) {
		LOGGER.info("START");

		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();

		LOGGER.debug("token : {}", token);
		LOGGER.info("END");
		return token;
	}
}
