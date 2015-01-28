package org.example.tutorials.hibernate.hibernateTutorial.domain.address;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.example.tutorials.hibernate.hibernateTutorial.domain.user.User;

/**
 * @author flanciskinho
 *
 */
@Entity
@Table(name = "addresses")
public class Address {

	private Long id;
	private String street;
	private User user;
	
	public Address() {}
	public Address(String street) {
		this(street, null);
	}
	public Address(String street, User user) {
		this.street = street;
		this.user = user;
	}
	
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@OneToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name = "id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return id+","+street+","+user.getName();
	}
	
}
