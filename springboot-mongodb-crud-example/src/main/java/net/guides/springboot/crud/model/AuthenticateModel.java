package net.guides.springboot.crud.model;

import org.springframework.stereotype.Service;

@Service
public class AuthenticateModel {

	private String username;
	private String role;
	private String token;
	private String emailId;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "AuthenticateModel [username=" + username + ", role=" + role + ", token=" + token + ", emailId="
				+ emailId + "]";
	}

	
}
