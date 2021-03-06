package com.modaoperandi.jwt.java;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPublicKey;

public class DecodedToken {
	private String namespace;
	private String jwkUrl;
	private String authToken;
	private String scopes;
	private String[] groups;
	private String[] roles;
	private String[] permissions;

	public DecodedToken() {
		this.authToken = null;
		this.scopes = null;
		this.groups = null;
		this.roles = null;
		this.permissions = null;
	}

	public DecodedToken(HttpServletRequest request, String namespace, String jwkUrl) {
		this.namespace = namespace;
		this.jwkUrl = jwkUrl;
		helper(request);
		decodeClaims(this.authToken);
	}

	public DecodedToken(String token,  String namespace, String jwkUrl) {
		this.namespace = namespace;
		this.jwkUrl = jwkUrl;
		this.authToken = token;
		decodeClaims(token);
	}

	public String getToken() {
		return this.authToken;
	}

	public String getScopes() {
		return this.scopes;
	}

	public String[] getGroups() {
		return this.groups;
	}

	public String[] getRoles() {
		return this.roles;
	}

	public String[] getPermissions() {
		return this.permissions;
	}

	public void printToken() {
		System.out.println("-------------ACCESS TOKEN-------------");
		System.out.println(this.authToken);
		System.out.println("-------------ACCESS TOKEN-------------");
	}

	public void printScopes() {
		System.out.println("Scopes: " + this.scopes);
	}

	public void printGroups() {
		System.out.println("Groups: " + arrayToString(this.groups));
	}

	public void printRoles() {
		System.out.println("Roles: " + arrayToString(this.roles));
	}

	public void printPermissions() {
		System.out.println("Permissions: " + arrayToString(this.permissions));
	}

	public void printUserInfo() {
		try {
			printScopes();
			printGroups();
			printRoles();
			printPermissions();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String getNamespace() {
		return namespace;
	}

	private void helper(HttpServletRequest request) {
		try {
			String header = request.getHeader("Authorization");
			this.authToken = header != null ? header.substring(7) : null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void verify(DecodedJWT jwt) throws JwkException {
		JwkProvider provider = new UrlJwkProvider(this.jwkUrl);
		Jwk jwk = provider.get(jwt.getKeyId());
		Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(),null);
		algorithm.verify(jwt);
	}

	private void decodeClaims(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			verify(jwt);
			this.scopes = jwt.getClaim("scope").asString();
			this.groups = jwt.getClaim(namespace + "groups").asArray(String.class);
			this.roles = jwt.getClaim(namespace + "roles").asArray(String.class);
			this.permissions = jwt.getClaim(namespace + "permissions").asArray(String.class);
		} catch (NullPointerException e) {
			System.out.println("Token is null.");
		} catch (JWTDecodeException e){
			System.out.println("Invalid token.");
		} catch (JwkException e) {
			System.out.println("Cannot verify the token.");
		}
	}

	private String arrayToString(String[] array) {
		StringBuilder sb = new StringBuilder();
		for (String str : array) {
			sb.append(str).append(" ");
		}
		return sb.toString().trim();
	}
}
