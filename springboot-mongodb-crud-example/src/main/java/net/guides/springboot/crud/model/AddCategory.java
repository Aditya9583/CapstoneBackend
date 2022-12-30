package net.guides.springboot.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Category")
public class AddCategory {

	@Transient
	public static final String SEQUENCE_NAME = "category_sequence";
	@Id
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AddCategory [id=" + id + ", category=" + category + "]";
	}

	@Indexed(unique=true)
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
