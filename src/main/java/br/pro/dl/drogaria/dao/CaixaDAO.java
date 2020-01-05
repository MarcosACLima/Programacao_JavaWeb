package br.pro.dl.drogaria.dao;

import java.util.Date;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.pro.dl.drogaria.domain.Caixa;
import br.pro.dl.drogaria.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa> {

	/* Metodo antigo
	public Caixa buscar(Date dataAbertura) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Caixa.class);
			consulta.add(Restrictions.eq("dataAbertura", dataAbertura));
			Caixa resultado = (Caixa) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	*/
	
	public Caixa buscar(Date dataAbertura) {
		Session session = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Caixa> consulta = builder.createQuery(Caixa.class);
			Root<Caixa> root = consulta.from(Caixa.class);

			CriteriaQuery<Caixa> select = consulta.select(root);

			Predicate p1 = builder.equal(root.get("dataAbertura"), dataAbertura);

			TypedQuery<Caixa> typedQuery = session.createQuery(select.where(p1));
			Caixa resultado = typedQuery.getSingleResult();

			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
}