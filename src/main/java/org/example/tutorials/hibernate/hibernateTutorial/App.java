package org.example.tutorials.hibernate.hibernateTutorial;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.example.tutorials.hibernate.hibernateTutorial.domain.address.Address;
import org.example.tutorials.hibernate.hibernateTutorial.domain.address.AddressDao;
import org.example.tutorials.hibernate.hibernateTutorial.domain.address.AddressDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.domain.category.Category;
import org.example.tutorials.hibernate.hibernateTutorial.domain.category.CategoryDao;
import org.example.tutorials.hibernate.hibernateTutorial.domain.category.CategoryDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.domain.event.Event;
import org.example.tutorials.hibernate.hibernateTutorial.domain.event.EventDao;
import org.example.tutorials.hibernate.hibernateTutorial.domain.event.EventDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.domain.user.User;
import org.example.tutorials.hibernate.hibernateTutorial.domain.user.UserDao;
import org.example.tutorials.hibernate.hibernateTutorial.domain.user.UserDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;
import org.hibernate.Session;

/**
 * @author flanciskinho
 *
 */
public class App 
{
	
 	private static void printSeparator(String str) {
 		System.err.flush();
 		
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
		
		System.out.flush();
	}
	private static void printSeparator(){
		printSeparator("");
	}
	
	private final static int ROWS_PER_PAGE = 10;
	
	private EventDao    eventDao;
	private CategoryDao categoryDao;
	private UserDao     userDao;
	private AddressDao  addressDao;
	
	private String categories[] = {
			"Deportes de agua",
			"Actividades al aire libre",
		};
	
	private String waterEvents[] = {
			"Natacion sincronizada", "Natacion individual", "Esqui acuatico",
			"Remo", "Piraguismo", "Esnorquel", "Jet Ski", "Kayak", "Waterpolo",
			"Windsurf", "Rafting", "Pesca deportiva", "Surf", "Vela", "Buceo",
			"Natacion en aguas abiertas", "Hidrospeed", "Bodyboarding", "Subwing"
		};
	
	private String names[] = {
			"John", "James", "Dylan", "Tyler", "Nick", "Aaron", "Abraham", 
			"Jane", "Mary", "Abigail", "Aleesha", "Alexa", "Alice", "Ally",
			"Lily", "Gwen", "Ann", "Claire", "Tess", "Natalie", "Lucy",
			"Lex", "Miles", "Ralph", "Ezio", "Scott", "Theo", "Flynn"
	};
	
	public App() {
		eventDao    = new EventDaoHibernate();
		categoryDao = new CategoryDaoHibernate();
		userDao     = new UserDaoHibernate();
		addressDao  = new AddressDaoHibernate();
	}
	
	private void insertInitialEvents() {
		printSeparator("anadir categorias");
		for (String str: categories) {
			categoryDao.insertCategory(str);
		}
		printSeparator();
		List<Category> list;
		list = categoryDao.getCategoriesByFilter("Agua", 0, ROWS_PER_PAGE);
		if (list.size() == 0) {
			exit("No hay categorias para agua");
		}
		printSeparator("anadir eventos");
		for (String str: waterEvents) {
			eventDao.insertEvent(str, list.get(0));
		}
	}
	
	private void insertInitialUsers() {
		printSeparator("anadir usuarios");
		//List<User> list = new ArrayList<User>();
		
		Random rnd = new Random();
		
		User user = null;
		Address address = null;
		for (String str: names) {
			user    = new User(str, str+"@example.org", Calendar.getInstance());
			if (rnd.nextBoolean()) {
				address = new Address(str + "'s street", user);
				user.setAddress(address);
			}
			userDao.save(user);
			System.out.println(user);
			if (user.getAddress() != null) {
				addressDao.save(address);
				System.out.println(address);
			}
			
			//list.add(user);
		}
		
		printSeparator();
		//userDao.insert(list);
		
		address = addressDao.find(address.getId());
		System.out.println(address);
		System.out.println(address.getUser());
	}
	
	private void showEventsByCategory(String filter) {
		List<Category> list = categoryDao.getCategoriesByFilter(filter, 0, ROWS_PER_PAGE);
		printSeparator("eventos de " + filter);
		long size = eventDao.getNumberOfEventsByCategory(list.get(0).getId());
		System.out.println("numero "+size);
		if (size != 0) {
			int start = 0;
			List<Event> events = eventDao.getEventsByCategory(list.get(0).getId(), start, ROWS_PER_PAGE);
			while (!events.isEmpty()) {
				for (Event event: events) {
					System.out.println(event.getTitle());
				}
				System.out.println(">>>>");
				start += ROWS_PER_PAGE;
				events = eventDao.getEventsByCategory(list.get(0).getId(), start, ROWS_PER_PAGE);
			}
		}
		printSeparator();
	}
	
	private void removeCategories() {
		printSeparator("eliminando las categorias");
		List<Category> list = categoryDao.getCategoriesByFilter(null, 0, ROWS_PER_PAGE);
		for (Category category:list) {
			System.out.println("Delete "+category.getId()+">"+category.getDescription());
			categoryDao.remove(category.getId());
		}
	}
	
	private static void exit(String msg) {
		HibernateUtil.stopConnectionProvider();
		if (msg != null)
			System.out.println(msg);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		App app = new App();
		
		app.insertInitialUsers();
		
		/*
		app.insertInitialEvents();
		app.showEventsByCategory("AgUA");
		app.removeCategories();
		*/
		
		HibernateUtil.stopConnectionProvider();
	}
    
}
