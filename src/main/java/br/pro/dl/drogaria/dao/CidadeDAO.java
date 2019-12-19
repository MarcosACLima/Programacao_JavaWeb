package br.pro.dl.drogaria.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.pro.dl.drogaria.domain.Cidade;
import br.pro.dl.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

/*
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
*/
	
	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Cidade> consulta = builder.createQuery(Cidade.class);
			Root<Cidade> root = consulta.from(Cidade.class);

			CriteriaQuery<Cidade> select = consulta.select(root);

			Predicate p1 = builder.equal(root.get("estado").get("codigo"), estadoCodigo);

			TypedQuery<Cidade> typedQuery = session.createQuery(select.where(p1));
			List<Cidade> resultado = typedQuery.getResultList();

			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
