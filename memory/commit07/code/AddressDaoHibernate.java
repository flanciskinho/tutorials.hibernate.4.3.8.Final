package org.example.tutorials.hibernate.hibernateTutorial.domain.address;

import org.example.tutorials.hibernate.hibernateTutorial.utils.GenericDaoHibernate;

/**
 * @author flanciskinho
 *
 */
public class AddressDaoHibernate 
	extends GenericDaoHibernate<Address, Long>
	implements AddressDao{

	public void save(Address entity) {
		entity.setId(entity.getUser().getId());
		super.save(entity);
	}
}
