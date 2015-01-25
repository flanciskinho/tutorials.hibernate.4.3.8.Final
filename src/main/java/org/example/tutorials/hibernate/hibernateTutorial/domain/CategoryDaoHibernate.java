package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.ArrayList;
import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author flanciskinho
 *
 */
public class CategoryDaoHibernate implements CategoryDao {

	@Override
	public List<Category> getCategories(String filter) {
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
    		Query query = session.createQuery(aux);
    		if (doFilter)
    			query.setString("descFilter", filter.toUpperCase());
    		list = query.list();
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    		list = new ArrayList<Category>();
    	} finally {
    		session.close();
    	}
    	
    	return list;
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

	@Override
	public void removeCategory(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		Category category = (Category) session.get(Category.class, id);
    		session.delete(category);
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	
    	} finally {
    		session.close();
    	}
	}

}
