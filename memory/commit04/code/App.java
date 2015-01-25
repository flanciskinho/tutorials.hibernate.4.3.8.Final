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
	
	private EventDao    eventDao;
	private CategoryDao categoryDao;
	public App() {
		eventDao    = new EventDaoHibernate();
		categoryDao = new CategoryDaoHibernate();
	}
    
	public static void main(String[] args) {
		App app = new App();
		
		String categories[] = {
			"Deportes de agua",
			"Actividades al aire libre",
		};
		
		String waterEvents[] = {
			"Natacion sincronizada", "Natacion individual", "Waterpolo",
			"Remo", "Piraguismo", "Esqui acuatico",
			"Surf", "Vela", "Buceo", "Subwing",
			"Windsurf", "Rafting", "Pesca deportiva",
			"Esnorquel", "Jet Ski", "Kayak",
			"Natacion en aguas abiertas", "Hidrospeed", "Bodyboarding"
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
			app.categoryDao.removeCategory(category.getId());
		}
		
		HibernateUtil.stopConnectionProvider();
	}
    
}
