package net.guides.springboot.crud.model;

public class ProductDetailsModel {

	private long id;

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ProductDetailsModel [id=" + id + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
}
