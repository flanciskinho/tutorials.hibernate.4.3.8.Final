package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.Calendar;

/**
 * @author flanciskinho
 *
 */
public class Event {

	private Long id;

	private String title;
	private Calendar date;

	public Event() {}
	
	public Event(String title) {
		this(title, Calendar.getInstance());
	}
	
	public Event(String title, Calendar date) {
		this.title = title;
		this.date = date;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Calendar getDate() {
		return this.date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		int day   = this.date.get(Calendar.DAY_OF_MONTH);
		int month = this.date.get(Calendar.MONTH);
		int year  = this.date.get(Calendar.YEAR);
		return String.format("%d\t(%d/%d/%d)\t %s", this.id, day, month, year, title);
	}
	
}
