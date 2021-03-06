package br.pro.dl.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.ItemVendaDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.dao.VendaDAO;
import br.pro.dl.drogaria.domain.ItemVenda;
import br.pro.dl.drogaria.domain.Produto;
import br.pro.dl.drogaria.domain.Venda;

public class ItemVendaDAOTest {

	@Ignore
	@Test
	public void salvar() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(2L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(1L);
		
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setQuantidade(new Short("4"));
		itemVenda.setPrecoParcial(new BigDecimal("6.74"));
		itemVenda.setProduto(produto);
		itemVenda.setVenda(venda);
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itemVendaDAO.salvar(itemVenda);
	}
	
	@Ignore
	@Test
	public void listar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> resultado = itemVendaDAO.listar();
		
		for (ItemVenda itemVenda : resultado) {
			System.out.println("\n Codigo Item Venda: " + itemVenda.getCodigo()
							+ "\n Quantidade: " + itemVenda.getQuantidade()
							+ "\n Preco Parcial: " + itemVenda.getPrecoParcial()
							+ "\n Descricao Produto: " + itemVenda.getProduto().getDescricao()
							+ "\n Horario da Venda: " + itemVenda.getVenda().getHorario());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 3L;
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);
		
		System.out.println("\n Codigo Item Venda: " + itemVenda.getCodigo()
						+ "\n Quantidade: " + itemVenda.getQuantidade()
						+ "\n Preco Parcial: " + itemVenda.getPrecoParcial()
						+ "\n Descricao Produto: " + itemVenda.getProduto().getDescricao()
						+ "\n Horario da Venda: " + itemVenda.getVenda().getHorario());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 2L;
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);
		
		itemVendaDAO.excluir(itemVenda);
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigo = 3L;
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);
		
		itemVenda.setPrecoParcial(new BigDecimal("13.50"));
		itemVenda.setQuantidade(new Short("44"));
		
		itemVendaDAO.editar(itemVenda);
	}
	
	
	
	
	
}
