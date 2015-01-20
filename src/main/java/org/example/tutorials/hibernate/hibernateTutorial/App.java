package org.example.tutorials.hibernate.hibernateTutorial;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.domain.Event;
import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{   
	public void insertData() {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    		
    		String[] events = {
        			"Natacion sincronizada", 
        			"Natacion libre",
        			"Risoterapia",
        			"Paracaidismo"
        		};
    		printSeparator();
    		for (String str: events){
        		System.out.println("\tAnadiendo evento>("+str+")");
        		session.saveOrUpdate(new Event(str));
        	}
    		
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
	}
	
	public void listEvents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		printSeparator();
    		Query query = session.createQuery("FROM Event e");
    		List<Event> list = query.list();
    		for (Event event:list) {
    			System.out.println(event);
    		}
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
		
	}
	
	public void listFilter() {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		printSeparator();
    		String filter = "NATACION";
    		Query query = session.createQuery(
    				"SELECT e " +
    				"FROM Event e " +
    				"WHERE UPPER(e.title) LIKE CONCAT('%', :titleFilter, '%')");
    		query.setString("titleFilter", filter.toUpperCase());
    		List<Event> list = query.list();

    		for (Event event:list) {
    			System.out.println(event);
    		}
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
		
	}
	
	public void listRemove() {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	
    	try {
    		transaction = session.beginTransaction();
    	
    		printSeparator();
    		String filter = "NATACION";
    		Query query = session.createQuery(
    				"SELECT e " +
    				"FROM Event e " +
    				"WHERE UPPER(e.title) LIKE CONCAT('%', :titleFilter, '%')");
    		query.setString("titleFilter", filter.toUpperCase());
    		List<Event> list = query.list();
    		for (Event event:list) {
    			System.out.println("\tEliminando el evento>("+event.getTitle()+")");
    			session.delete(event);
    		}
    		
    		transaction.commit();
    	} catch (HibernateException e) {
    		transaction.rollback();
    	} finally {
    		session.close();
    	}
		
	}
	
 	private static void printSeparator(String str) {
		int size = 80;
		int cnt = 0;
		if (!str.isEmpty()) {
			size = size - 2 -str.length();
			if (size < 4)
				size = 4;
		}
		for (; cnt < size/2; cnt++) {
			System.out.print("-");
		}
		if (!str.isEmpty())
			System.out.print(" "+str+" ");
		for (; cnt < size; cnt++) {
			System.out.print("-");
		}
		System.out.println();
	}
	private static void printSeparator(){
		printSeparator("");
	}
	
    public static void main(String[] args) {
    	App app = new App();
    	
    	printSeparator("Call insertData");
    	app.insertData();
    	printSeparator("Finish");
    	
    	printSeparator("Call listElements");
    	app.listEvents();
    	printSeparator("Finish");
    	
    	printSeparator("Call listFilter");
    	app.listFilter();
    	printSeparator("Finish");
    	
    	printSeparator("Call listRemove");
    	app.listRemove();
    	printSeparator("Finish");
    	
    	HibernateUtil.stopConnectionProvider();
    }
    
    
}
