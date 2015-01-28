package org.example.tutorials.hibernate.hibernateTutorial.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author flanciskinho
 *
 */
public class GenericDaoHibernate<E, PK extends Serializable> implements GenericDao<E, PK> {
	private Class<E> entityType;
	
	
	@SuppressWarnings("unchecked")
	public GenericDaoHibernate() {
		this.entityType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public void save(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	try {
    		transaction = session.beginTransaction();
		
			session.saveOrUpdate(entity);
		
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			System.out.println("\n\n\t"+e.getMessage()+"\n\n");
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E find(PK id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	E entity = null;
    	try {
    		transaction = session.beginTransaction();
		
			entity = (E) session.get(this.entityType, id);
		
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return entity;
	}

	@Override
	public void remove(PK id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    		
    		session.delete(find(id));
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
	}

}
