package org.example.tutorials.hibernate.hibernateTutorial.domain.event;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.domain.category.Category;
import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDao;

/**
 * @author flanciskinho
 *
 */
public interface EventDao extends GenericDao<Event, Long>{
	public void removeByFilter(String filter);
	public Event insertEvent(String title, Category category);
	
	public List<Event> getEventsByFilter(String filter, int start, int size);
	public long getNumberOfEventsByFilter(String filter);
	
	public long getNumberOfEventsByCategory(long categoryId);
	public List<Event> getEventsByCategory(long categoryId, int start, int size);
}
