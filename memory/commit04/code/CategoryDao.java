package org.example.tutorials.hibernate.hibernateTutorial.domain;

import java.util.List;

/**
 * @author flanciskinho
 *
 */
public interface CategoryDao {
	public List<Category> getCategories(String filter);
	public Category insertCategory(String desc);
	public void removeCategory(long id);

}
