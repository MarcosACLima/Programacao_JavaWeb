package br.pro.dl.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.pro.dl.drogaria.domain.Usuario;
import br.pro.dl.drogaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			@SuppressWarnings("deprecation")
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
//	Fazer mais testes no metodo
	/*
	public Usuario autenticar(String cpf, String senha) {
			Session session = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				CriteriaBuilder builder = session.getCriteriaBuilder();

				CriteriaQuery<Usuario> consulta = builder.createQuery(Usuario.class);
				Root<Usuario> root = consulta.from(Usuario.class);

				CriteriaQuery<Usuario> select = consulta.select(root);
				
				SimpleHash hash = new SimpleHash("md5", senha);

				Predicate p1 = builder.equal(root.get("pessoa").get("cpf"), cpf);
				Predicate p2 = builder.equal(root.get("senha"), hash.toHex());

				TypedQuery<Usuario> typedQuery = session.createQuery(select.where(p1,p2));
				Usuario resultado = typedQuery.getSingleResult();

				return resultado;
			} catch (RuntimeException e) {
				throw e;
			} finally {
				session.close();
			}
		}
		*/
	
}