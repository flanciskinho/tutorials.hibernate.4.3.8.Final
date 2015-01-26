package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.ArrayList;
import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author flanciskinho
 *
 */
public class EventDaoHibernate
	extends GenericDaoHibernate<Event, Long>
	implements EventDao{

	@SuppressWarnings("unchecked")
	public List<Event> getEventsByFilter(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	List<Event> list;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		boolean doFilter = false;
    		String aux =
    				"SELECT e " +
    	    		"FROM Event e ";
    		if (filter != null) {
    			if (!filter.trim().isEmpty()) {
    				aux = aux + "WHERE UPPER(e.title) LIKE CONCAT('%', :titleFilter, '%')";
    				doFilter = true;
    			}
    		}
    		Query query = session.createQuery(aux);
    		if (doFilter)
    			query.setString("titleFilter", filter.toUpperCase());
    		list = query.list();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    		list = new ArrayList<Event>();
    	} finally {
    		session.close();
    	}
    	
    	return list;
	}

	public Event insertEvent(String title, Category category) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	Event event = null;
    	
    	try {
    		transaction = session.beginTransaction();
    		
    		event = new Event(title, category);
        	session.saveOrUpdate(event);
        	
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
    	
    	return event;
	}
	
	@SuppressWarnings("unchecked")
	public void removeByFilter(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		boolean doFilter = false;
    		String aux =
    				"SELECT e " +
    	    		"FROM Event e ";
    		if (filter != null) {
    			if (!filter.trim().isEmpty()) {
    				aux = aux + "WHERE UPPER(e.title) LIKE CONCAT('%', :titleFilter, '%')";
    				doFilter = true;
    			}
    		}
    		Query query = session.createQuery(aux);
    		if (doFilter)
    			query.setString("titleFilter", filter.toUpperCase());
			List<Event> list = query.list();
    		for (Event event:list) {
    			session.delete(event);
    		}
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
		
	}
}
