package io.github.abevieiramota.xunit.transaction_rollback_teardown;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertThat;

import io.github.abevieiramota.pagila.domain.Film;
import io.github.abevieiramota.pagila.domain.Language;
import io.github.abevieiramota.pagila.infra.HibernateUtil;

@RunWith(JUnit4.class)
public class TransactionRollbackTeardown {

	private Session sess;
	private SessionFactory sf;

	private static final String NOME_ESCROTO = "______o nome mais escroto do universo_____";

	private static Long qtdFilmsComNomeEscroto = null;

	public TransactionRollbackTeardown() {

		this.sf = HibernateUtil.getSessionFactory();
		this.sess = this.sf.openSession();
	}

	@BeforeClass
	public static void beforeClass() {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sess = sf.openSession();

		Query qtdFilmsEscrotos = sess.createQuery("SELECT count(*) FROM Film f WHERE f.title = :title");

		qtdFilmsEscrotos.setParameter("title", NOME_ESCROTO);

		qtdFilmsComNomeEscroto = (Long) qtdFilmsEscrotos.uniqueResult();
		
		sess.close();
	}
	
	@AfterClass
	public static void afterClass() {
		
		HibernateUtil.shutdown();
	}

	@Before
	public void setUp() {

		this.sess.beginTransaction();
	}

	@Test
	public void addFilm() {

		// TODO: pode acreditar que é!
		Language languageEnglish = this.sess.get(Language.class, 1);

		Query qtdFilmsEscrotos = this.sess.createQuery("SELECT count(*) FROM Film f WHERE f.title = :title");

		qtdFilmsEscrotos.setParameter("title", NOME_ESCROTO);

		long sizeBeforeSave = (Long) qtdFilmsEscrotos.uniqueResult();

		assumeThat(sizeBeforeSave, is(qtdFilmsComNomeEscroto));

		Film film = new Film();

		film.setTitle(NOME_ESCROTO);
		film.setLanguage(languageEnglish);

		this.sess.save(film);

		long sizeAfterSave = (Long) qtdFilmsEscrotos.uniqueResult();

		assertThat(sizeAfterSave, is(sizeBeforeSave + 1));
	}

	@After
	public void tearDown() {

		this.sess.getTransaction().rollback();
	}
}
