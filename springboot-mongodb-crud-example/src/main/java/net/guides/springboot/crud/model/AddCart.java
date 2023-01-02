package net.guides.springboot.crud.model;





import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Cart")
public class AddCart {

	@Transient
	public static final String SEQUENCE_NAME = "carts_sequence";
	@Id
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String street;
	
	private String city;
	
	private String country;
	
	private String state;
	
	private int zip;
	
	private String cardtype;
	
	private String nameoncard;
	
	private int cardnumber;
	
	private int expmonth;
	
	private int expyear;
	
	private int cvv;
	
	private List<String> products;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getNameoncard() {
		return nameoncard;
	}

	public void setNameoncard(String nameoncard) {
		this.nameoncard = nameoncard;
	}

	public int getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(int cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getExpmonth() {
		return expmonth;
	}

	public void setExpmonth(int expmonth) {
		this.expmonth = expmonth;
	}

	public int getExpyear() {
		return expyear;
	}

	public void setExpyear(int expyear) {
		this.expyear = expyear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "AddCart [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", street=" + street + ", city=" + city + ", country=" + country + ", state=" + state + ", zip=" + zip
				+ ", cardtype=" + cardtype + ", nameoncard=" + nameoncard + ", cardnumber=" + cardnumber + ", expmonth="
				+ expmonth + ", expyear=" + expyear + ", cvv=" + cvv + ", products=" + products + "]";
	}

	
	
	
	

	

	

	


}

