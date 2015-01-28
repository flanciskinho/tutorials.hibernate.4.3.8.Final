package org.example.tutorials.hibernate.hibernateTutorial.domain.event;

import java.util.ArrayList;
import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.domain.category.Category;
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
	public List<Event> getEventsByFilter(String filter, int start, int size) {
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
    		aux = aux + " ORDER BY e.title";
    		
    		Query query = session.createQuery(aux);
    		if (doFilter)
    			query.setString("titleFilter", filter.toUpperCase());
    		
    		list = query.setFirstResult(start).setMaxResults(size).list();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    		list = new ArrayList<Event>();
    	} finally {
    		session.close();
    	}
    	
    	return list;
	}
	
	public long getNumberOfEventsByFilter(String filter) {

		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	long size = 0;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		boolean doFilter = false;
    		String aux =
    				"SELECT COUNT(e) " +
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
    		
    		size = (long) query.uniqueResult();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
    	
    	return size;
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
	
	@SuppressWarnings("unchecked")
	public List<Event> getEventsByCategory(long categoryId, int start, int size) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	List<Event> list = null;
    	try {
    		transaction = session.beginTransaction();
    		
    		Query query = session.createQuery(
    				"SELECT e " +
    				"FROM Event e " +
    				"WHERE e.category.id = :categoryId "+
    				"ORDER BY e.category.description"
    		);
    		
    		list = query
    					.setParameter("categoryId", categoryId)
    					.setFirstResult(start)
    					.setMaxResults(size)
    					.list();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
    	
    	return list;
	}
	
	public long getNumberOfEventsByCategory(long categoryId){
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	long size = 0;
    	try {
    		transaction = session.beginTransaction();
    		
    		Query query = session.createQuery(
    				"SELECT COUNT(e) " +
    				"FROM Event e " +
    				"WHERE e.category.id = :categoryId"
    		);
    		
    		size = (long) query.setParameter("categoryId", categoryId).uniqueResult();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
    	
    	return size;
	}
	
}
