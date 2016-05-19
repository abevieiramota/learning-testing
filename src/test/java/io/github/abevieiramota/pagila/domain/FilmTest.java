package io.github.abevieiramota.pagila.domain;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.github.abevieiramota.pagila.infra.HibernateUtil;

@RunWith(JUnit4.class)
public class FilmTest {
	
	@Test
	public void testFindById() {
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Query q = sess.createQuery("FROM Film f WHERE f.id = :id");
		
		q.setParameter("id", 2);
		
		Film film = (Film) q.uniqueResult();
		
		System.out.println(film);
	}

}
