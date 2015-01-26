package org.example.tutorials.hibernate.hibernateTutorial.utils;

import java.io.Serializable;

/**
 * @author flanciskinho
 *
 */
public interface GenericDao <E, PK extends Serializable>{
	void save(E entity);
	E find(PK id);
	void remove(PK id);
}
