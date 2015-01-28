/**
 * 
 */
package org.example.tutorials.hibernate.hibernateTutorial.domain.user;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.domain.address.Address;
import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author flanciskinho
 *
 */
public class UserDaoHibernate
	extends GenericDaoHibernate<User, Long>
	implements UserDao {

	public void insert(List<User> list) {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		for (User user: list) {
    			System.out.println("Insert > " + user.getName() + " > " + user.getAddress().getStreet());
    			session.saveOrUpdate(user);
    			session.flush();
    		}
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		System.out.println("\n\n"+e.getMessage()+"\n\n");
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
	}
}
