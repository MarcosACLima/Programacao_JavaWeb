/** Classe criada de outra maneira */
package br.pro.dl.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes;

	public static SessionFactory getFabricaDeSessoes() {
		try {
			if(fabricaDeSessoes == null) {
			Configuration configuracao = new Configuration().configure();
			@SuppressWarnings("unused")
			ServiceRegistry registro = new StandardServiceRegistryBuilder()
					.applySettings(configuracao.getProperties()).build();
			fabricaDeSessoes = configuracao.buildSessionFactory(); // removido resgistro
			}
			return fabricaDeSessoes;
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
