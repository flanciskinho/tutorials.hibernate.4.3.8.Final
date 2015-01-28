package org.example.tutorials.hibernate.hibernateTutorial.domain.category;

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
public class CategoryDaoHibernate
	extends GenericDaoHibernate<Category, Long>
	implements CategoryDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoriesByFilter(String filter, int start, int size) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	List<Category> list = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		boolean doFilter = false;
    		String aux =
    				"SELECT c " +
    	    		"FROM Category c ";
    		if (filter != null) {
    			if (!filter.trim().isEmpty()) {
    				aux = aux + "WHERE UPPER(c.description) LIKE CONCAT('%', :descFilter, '%')";
    				doFilter = true;
    			}
    		}
    		aux = aux + " ORDER BY c.description";
    		Query query = session.createQuery(aux);
    		if (doFilter)
    			query.setString("descFilter", filter.toUpperCase());
    		
    		list = query.setFirstResult(start).setMaxResults(size).list();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    		list = new ArrayList<Category>();
    	} finally {
    		session.close();
    	}
    	
    	return list;
	}
	
	
	public long getNumberOfCategoriesByFilter(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	long size = 0;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		boolean doFilter = false;
    		String aux =
    				"SELECT COUNT(c) " +
    	    		"FROM Category c ";
    		if (filter != null) {
    			if (!filter.trim().isEmpty()) {
    				aux = aux + "WHERE UPPER(c.description) LIKE CONCAT('%', :descFilter, '%')";
    				doFilter = true;
    			}
    		}
    		Query query = session.createQuery(aux);
    		if (doFilter)
    			query.setString("descFilter", filter.toUpperCase());
    		
    		size = (long) query.uniqueResult();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
    	
    	return size;
	}

	@Override
	public Category insertCategory(String desc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	Category category = null;
    	try {
    		transaction = session.beginTransaction();
    		
    		category = new Category(desc);
    		session.saveOrUpdate(category);
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
    	return category;
	}
	
	

}
