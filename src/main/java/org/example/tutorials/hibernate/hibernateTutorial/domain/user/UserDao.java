package org.example.tutorials.hibernate.hibernateTutorial.domain.user;

import java.util.List;

import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDao;

/**
 * @author flanciskinho
 *
 */
public interface UserDao extends GenericDao<User, Long> {
	public void insert(List<User> list);
}
