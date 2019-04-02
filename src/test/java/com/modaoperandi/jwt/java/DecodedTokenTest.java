package com.modaoperandi.jwt.java;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecodedTokenTest {
	
	private String token1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwczovL3Blcm1zLm1vZGEuY29tL2dyb3VwcyI6WyJBZG1pbnMiLCJCdXllcnMiXSwiaHR0cHM6Ly9wZXJtcy5tb2RhLmNvbS9yb2xlcyI6WyJBZG1pbiIsIkJ1eWVyIl0sImh0dHBzOi8vcGVybXMubW9kYS5jb20vcGVybWlzc2lvbnMiOlsicmVhZDpwYWNrZXRzIiwiY3JlYXRlOnBhY2tldHMiLCJ1cGRhdGU6cGFja2V0cyIsImRlbGV0ZTpwYWNrZXRzIl0sImlzcyI6Imh0dHBzOi8vbW9kYW9wZXJhbmRpLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw1YzdlZjNlNDNmODUxYzE4ZTNkZWQ1MWQiLCJhdWQiOiJodHRwczovL3JvdWdlLm1vZGFhcHBzLmNvbS9hcGkiLCJpYXQiOjE1NTM4MDA3NDcsImV4cCI6MTU1MzgwNzk0NywiYXpwIjoiWFdCSndmVVdPdjNoRlU0YjdoeXpqbFYzclBuNGhzWmEiLCJzY29wZSI6InJlYWQ6cGFja2V0cyBjcmVhdGU6cGFja2V0cyB1cGRhdGU6cGFja2V0cyBkZWxldGU6cGFja2V0cyJ9.ni3ea7ZXKbSitb1hO-sYEjhDh48WghB-pRM1uj764kA"; 
	private String token2 = "";
	private String token3 = null;
	
	DecodedToken decodedToken1 = new DecodedToken(token1);
	DecodedToken decodedToken2 = new DecodedToken(token2);
	DecodedToken decodedToken3 = new DecodedToken(token3);
	
	@Test
	public void getToken() throws Exception {
		assertEquals(token1, decodedToken1.getToken());
		assertEquals(token2, decodedToken2.getToken());
		assertEquals(token3, decodedToken3.getToken());
	}
	
	@Test
	public void getScopes() throws Exception {
		assertEquals("read:packets create:packets update:packets delete:packets", decodedToken1.getScopes());
		assertEquals(null, decodedToken2.getScopes());
		assertEquals(null, decodedToken3.getScopes());
	}
	
	@Test
	public void getGroups() throws Exception {
		assertEquals("Admins Buyers", arrayToString(decodedToken1.getGroups()));
		assertNull(decodedToken2.getGroups());
		assertNull(decodedToken3.getGroups());
	}
	
	@Test
	public void getRoles() throws Exception {
		assertEquals("Admin Buyer", arrayToString(decodedToken1.getRoles()));
		assertNull(decodedToken2.getRoles());
		assertNull(decodedToken3.getRoles());
	}
	
	@Test
	public void getPermissions() throws Exception {
		assertEquals("read:packets create:packets update:packets delete:packets", arrayToString(decodedToken1.getPermissions()));
		assertNull(decodedToken2.getPermissions());
		assertNull(decodedToken3.getPermissions());
	}
	
	private String arrayToString(String[] array) {
		StringBuilder sb = new StringBuilder();
		for (String str : array) {
			sb.append(str).append(" ");
		}
		return sb.toString().trim();
	}
}
