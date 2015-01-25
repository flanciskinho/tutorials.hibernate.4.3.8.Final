package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author flanciskinho
 *
 */
@Entity
@Table(name="EVENTS")
public class Event {

	
	private Long id;
	private String title;
	private Calendar date;
	private Category category;

	public Event() {}
	
	public Event(String title, Category category) {
		this(title, Calendar.getInstance(), category);
	}
	
	public Event(String title, Calendar date, Category category) {
		this.title    = title;
		this.date     = date;
		this.category = category;
	}

	@Column(name = "EVENT_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}

	@Column(name = "EVENT_DATE")
	public Calendar getDate() {
		return this.date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	// Como tiene el mismo nombre la columna en la Tabla que el atributo en la clase no hace falta indicar como es el mapeo
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "category")
	public Category getCategory() {
		return this.category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		int day   = this.date.get(Calendar.DAY_OF_MONTH);
		int month = this.date.get(Calendar.MONTH);
		int year  = this.date.get(Calendar.YEAR);
		return String.format("%d\t(%d/%d/%d)\t %s", this.id, day, month, year, title);
	}
	
}
