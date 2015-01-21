package org.example.tutorials.hibernate.hibernateTutorial;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.domain.*;
import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;

/**
 * @author flanciskinho
 *
 */
public class App 
{
	
 	private static void printSeparator(String str) {...}
	private static void printSeparator(){...}
	
	private EventDao eventDao;
	public App() {
		eventDao = new EventDaoHibernate();
	}
    
    
    public static void main(String[] args) {
    	App app = new App();
    	
    	printSeparator();
    	app.eventDao.insertEvent("Natacion Sincronizada");
    	app.eventDao.insertEvent("Natacion individual");
    	app.eventDao.insertEvent("Risoterapia");
    	app.eventDao.insertEvent("Paracaidismo");
    	
    	List<Event> list;
    	
    	printSeparator();
    	list = app.eventDao.getEventsByFilter(null);
    	for (Event e: list)
    		System.out.println(e);
    	
    	printSeparator();
    	list = app.eventDao.getEventsByFilter("NaTaCiON");
    	for (Event e: list)
    		System.out.println(e);
    	
    	printSeparator();
    	app.eventDao.removeByFilter("NaTaCiON");
    	
    	printSeparator();
    	app.eventDao.removeByFilter(null);
    	
    	HibernateUtil.stopConnectionProvider();
    }
    
    
}
