package br.pro.delfino.drogaria.dao;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.pro.delfino.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {
	
	private Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() { 
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // capturar sessao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction(); // iniciar transacao
			sessao.save(entidade); // salvar
			transacao.commit(); // confirmar
		} catch (RuntimeException e) {
			if(transacao != null) {
				transacao.rollback(); // desfazer
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
}
