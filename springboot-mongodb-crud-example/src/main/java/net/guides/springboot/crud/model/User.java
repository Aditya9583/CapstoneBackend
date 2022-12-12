package net.guides.springboot.crud.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "User")
public class User {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private long id;
	
	
	

	private String password;
	
	@Indexed(unique=true)
	private String username;

	
    @Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", username=" + username + ", emailId=" + emailId
				+ ", role=" + role + "]";
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Indexed(unique=true)
	private String emailId;
	
	
	private String role;

	


}
