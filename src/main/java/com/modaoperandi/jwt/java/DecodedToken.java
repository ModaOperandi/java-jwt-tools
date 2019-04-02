package com.modaoperandi.jwt.java;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class DecodedToken {

	private static final String namespace = "https://perms.moda.com/";
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
    
    public DecodedToken(HttpServletRequest request) {
    	helper(request);
    	decodeClaims(this.authToken);
    }
    
    public DecodedToken(String token) {
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
	
	private void helper(HttpServletRequest request) {
		try {
			String header = request.getHeader("Authorization");
			this.authToken = header != null ? header.substring(7) : null; 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void decodeClaims(String token) {	
    	try {
    	    DecodedJWT jwt = JWT.decode(token);
    	    this.scopes = jwt.getClaim("scope").asString();
    	    this.groups = jwt.getClaim(namespace + "groups").asArray(String.class);
    	    this.roles = jwt.getClaim(namespace + "roles").asArray(String.class);
    	    this.permissions = jwt.getClaim(namespace + "permissions").asArray(String.class);	    
    	} catch (NullPointerException e) {
    		System.out.println("Token is null.");
    	} catch (JWTDecodeException e){
    		System.out.println("Invalid token.");
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