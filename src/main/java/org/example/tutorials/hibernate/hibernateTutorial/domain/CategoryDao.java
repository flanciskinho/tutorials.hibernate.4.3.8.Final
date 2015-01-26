package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDao;

/**
 * @author flanciskinho
 *
 */
public interface CategoryDao extends GenericDao<Category, Long>{
	public List<Category> getCategories(String filter);
	public Category insertCategory(String desc);
}
