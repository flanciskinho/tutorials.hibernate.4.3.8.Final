package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDao;

/**
 * @author flanciskinho
 *
 */
public interface CategoryDao extends GenericDao<Category, Long>{
	public Category insertCategory(String desc);
	
	public List<Category> getCategoriesByFilter(String filter, int start, int size);
	public long getNumberOfCategoriesByFilter(String filter);
}
