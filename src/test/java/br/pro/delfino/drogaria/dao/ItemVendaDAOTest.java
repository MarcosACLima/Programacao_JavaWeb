package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Produto;

public class ItemVendaDAOTest {

	@Ignore
	@Test
	public void salvar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(2L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(1L);
		
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setQuantidade(new Short("4"));
		itemVenda.setPrecoParcial(new BigDecimal("6.74"));
		itemVenda.setProduto(produto);
		itemVenda.setFuncionario(funcionario);
		
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
							+ "\n Nome Funcionario: " + itemVenda.getFuncionario().getPessoa().getNome());
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
						+ "\n Nome Funcionario: " + itemVenda.getFuncionario().getPessoa().getNome());
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
