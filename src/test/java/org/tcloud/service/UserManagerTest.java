package org.tcloud.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.Test;

public class UserManagerTest {
	
	private static SessionFactory sessionFactory;
	
	@BeforeClass
	public static void beforeClass() {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}
	
	@Test
	public void testSchemaExport() {
		new SchemaExport(new Configuration().configure()).create(true, true);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test111() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Article as a inner join a.tags as t where a.id!=2 and t.id in (select t.id from Tag as t inner join t.articles a where a.id=2)");

		List<Object[]> l = q.list();
		for(Object[] a : l){
			System.out.println(a[0]);
		}
	}
}
