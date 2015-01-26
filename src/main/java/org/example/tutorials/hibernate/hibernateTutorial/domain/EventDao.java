package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDao;

/**
 * @author flanciskinho
 *
 */
public interface EventDao extends GenericDao<Event, Long>{
	public void removeByFilter(String filter);
	public List<Event> getEventsByFilter(String filter);
	public Event insertEvent(String title, Category category);
}
