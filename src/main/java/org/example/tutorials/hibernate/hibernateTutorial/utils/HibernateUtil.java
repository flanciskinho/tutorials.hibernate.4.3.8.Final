/**
 * 
 */
package org.example.tutorials.hibernate.hibernateTutorial.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.Stoppable;

/**
 * @author flanciskinho
 *
 */
public class HibernateUtil {
	//XML based configuration
		private static SessionFactory sessionFactory = buildSessionFactory();
		private static ServiceRegistry serviceRegistry;
		
		private static SessionFactory buildSessionFactory() {
			try {
				// Create the SessionFactory from hibernate.cfg.xml
				Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
	System.out.println("Hibernate Configuration loaded");

				 serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
	System.out.println("Hibernate serviceRegistry created");

				SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

				return sessionFactory;
			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}

		public static SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
		public static void stopConnectionProvider() {
			if (serviceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(serviceRegistry);
			}
		}
}
