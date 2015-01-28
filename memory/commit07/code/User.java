package org.example.tutorials.hibernate.hibernateTutorial.domain.user;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.example.tutorials.hibernate.hibernateTutorial.domain.address.Address;

/**
 * @author flanciskinho
 *
 */
@Entity
@Table(name="users")
public class User {

	private Long id;
	private String name;
	private String email;
	private Calendar dob;
	private Address address = null;
	
	public User() {}
	public User(String name, String email, Calendar dob) {
		this(name, email, dob, null);
	}
	public User(String name, String email, Calendar dob, Address address) {
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.address = address;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public Calendar getDob() { return dob; }
	public void setDob(Calendar dob) { this.dob = dob; }
	
	@OneToOne(mappedBy = "user")
	public Address getAddress() { return this.address; }
	public void setAddress(Address address) { this.address = address; }
	
	@Override
	public String toString() {
		String tmp = dob.get(Calendar.DAY_OF_MONTH)+"/"+dob.get(Calendar.MONTH)+"/"+dob.get(Calendar.YEAR);
		
		return id+","+name+","+email+","+tmp+(address==null?".":","+address.getStreet());
	}
	
}
