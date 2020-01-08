package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Fabricante;
import br.pro.dl.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable {
	
	private Produto produto;
	private Long codigoProduto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	private ProdutoDAO produtoDAO;
	private FabricanteDAO fabricanteDAO;
	
	@PostConstruct
	public void iniciar() {
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();
	}
	
	public void listar() {
		try {
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar lista os produtos");
			e.printStackTrace();
		}
	}
	
	public void carregarEdicao() {
		try {
			produto = produtoDAO.buscar(codigoProduto);
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar carregar os dados para edição");
			e.printStackTrace();
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
