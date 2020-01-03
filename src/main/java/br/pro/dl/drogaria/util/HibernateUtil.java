/** Classe criada de outra maneira */
package br.pro.dl.drogaria.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes;
	
	public static Connection getConexao() {
		Session sessao = fabricaDeSessoes.openSession();
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection connection) throws SQLException {
				return connection;
			}
		});
		
		return conexao;
	}

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
