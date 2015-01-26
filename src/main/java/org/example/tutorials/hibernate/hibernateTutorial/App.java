package org.example.tutorials.hibernate.hibernateTutorial;

import java.util.List;

import javax.management.InstanceNotFoundException;

import org.example.tutorials.hibernate.hibernateTutorial.domain.Category;
import org.example.tutorials.hibernate.hibernateTutorial.domain.CategoryDao;
import org.example.tutorials.hibernate.hibernateTutorial.domain.CategoryDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.domain.EventDao;
import org.example.tutorials.hibernate.hibernateTutorial.domain.EventDaoHibernate;
import org.example.tutorials.hibernate.hibernateTutorial.utils.HibernateUtil;

/**
 * @author flanciskinho
 *
 */
public class App 
{
	
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
	
	private EventDao    eventDao;
	private CategoryDao categoryDao;
	public App() {
		eventDao    = new EventDaoHibernate();
		categoryDao = new CategoryDaoHibernate();
	}
    
	public static void main(String[] args) throws InstanceNotFoundException {
		
		App app = new App();
		
		String categories[] = {
			"Deportes de agua",
			"Actividades al aire libre",
		};
		
		String waterEvents[] = {
			"Natacion sincronizada",
			"Natacion individual",
			"Waterpolo",
			"Remo",
			"Piraguismo",
			"Esqui acuatico",
			"Surf",
			"Vela",
			"Buceo",
			"Windsurf",
			"Rafting",
			"Pesca deportiva",
			"Esnorquel",
			"Jet Ski",
			"Kayak",
			"Natacion en aguas abiertas",
			"Hidrospeed",
			"Bodyboarding",
			"Subwing"
		};
		
		printSeparator("anadir categorias");
		for (String str: categories) {
			app.categoryDao.insertCategory(str);
		}
		
		printSeparator();
		List<Category> list;
		list = app.categoryDao.getCategories("Agua");
		if (list.size() == 0) {
			HibernateUtil.stopConnectionProvider();
			System.out.println("No hay categorias para agua");
			System.exit(0);
		}
		
		printSeparator("anadir eventos");
		for (String str: waterEvents) {
			app.eventDao.insertEvent(str, list.get(0));
		}
		
		printSeparator("eliminando las categorias");
		list = app.categoryDao.getCategories(null);
		for (Category category:list) {
			System.out.println("Delete "+category.getId()+">"+category.getDescription());
			app.categoryDao.remove(category.getId());
		}
		
		HibernateUtil.stopConnectionProvider();
	}
    
}
