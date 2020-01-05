package br.pro.dl.drogaria.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.pro.dl.drogaria.domain.ItemVenda;
import br.pro.dl.drogaria.domain.Produto;
import br.pro.dl.drogaria.domain.Venda;
import br.pro.dl.drogaria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{

	public void salvar(Venda venda, List<ItemVenda> itensVenda){
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); 
			Transaction transacao = null;
			try {
				transacao = sessao.beginTransaction(); 
				
				sessao.save(venda);
				
				for (int i = 0; i < itensVenda.size(); i++) {
					ItemVenda itemVenda = itensVenda.get(i);
					itemVenda.setVenda(venda);
					
					sessao.save(itemVenda);
					
					Produto produto = itemVenda.getProduto();
					int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
					
					if (quantidade >= 0) {
						produto.setQuantidade(new Short((produto.getQuantidade() - itemVenda.getQuantidade()) + ""));
						sessao.update(produto);
					} else {
						throw new RuntimeException("Quantidade insuficiente em estoque");
					}
					
				}
				
				transacao.commit(); 
			} catch (RuntimeException e) {
				if(transacao != null) {
					transacao.rollback(); 
				}
				throw e;
			} finally {
				sessao.close();
			}
		}
	
}