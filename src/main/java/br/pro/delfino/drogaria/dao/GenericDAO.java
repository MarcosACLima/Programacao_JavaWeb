package br.pro.delfino.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
	
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // criar sessao
		try {
			/* Metodo modificado */
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Entidade> consulta =construtor.createQuery(classe);
			consulta.from(classe);
			List<Entidade> resultado = sessao.createQuery(consulta).getResultList();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // criar sessao
		try {
			/* Metodo modificado */
			Entidade resultado = sessao.find(classe, codigo); // buscar
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	} 
	
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // capturar sessao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction(); // iniciar transacao
			sessao.delete(entidade); // deletar
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
	
	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // capturar sessao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction(); // iniciar transacao
			sessao.update(entidade); // alterar
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
	
	public void merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // capturar sessao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction(); // iniciar transacao
			sessao.merge(entidade); // salvar ou editar
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
