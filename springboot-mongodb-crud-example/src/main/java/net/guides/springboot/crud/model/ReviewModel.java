package net.guides.springboot.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Reviews")
public class ReviewModel {

	@Transient
	public static final String SEQUENCE_NAME = "reviews_sequence";
	@Id
	private long id;
	
	private String name;
	private String subject;
	private String feedback;
	

	@Override
	public String toString() {
		return "ReviewModel [id=" + id + ", name=" + name + ", subject=" + subject + ", feedback=" + feedback
				+ ", emailId=" + emailId + "]";
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	private String emailId;
	
	
	

	


}

