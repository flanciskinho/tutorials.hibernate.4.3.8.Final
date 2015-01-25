package org.example.tutorials.hibernate.hibernateTutorial.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
		
		/**
		 * Este metodo cierra la instancia unica de serviceRegister y libera todos sus recuros
		 * (informacion de mapping, pool de conexiones ...)
		 */
		public static void stopConnectionProvider() {
			if (serviceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(serviceRegistry);
			}
		}
}
