package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.ClienteDAO;
import br.pro.dl.drogaria.dao.FuncionarioDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.dao.VendaDAO;
import br.pro.dl.drogaria.domain.Cliente;
import br.pro.dl.drogaria.domain.Funcionario;
import br.pro.dl.drogaria.domain.ItemVenda;
import br.pro.dl.drogaria.domain.Produto;
import br.pro.dl.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	private Venda venda;
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Venda> vendas;

//	@PostConstruct
	public void listar() {
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));
			produtos = new ProdutoDAO().listar("descricao");

			itensVenda = new ArrayList<>(); // criar carrinho de compras vazio
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			e.printStackTrace();
		}
	}
	
	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int i = 0; i < itensVenda.size(); i++) {
			if (itensVenda.get(i).getProduto().equals(produto)) {
				achou = i;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade((short) (itemVenda.getQuantidade() + 1));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		calcular();
	}
	
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
		
		int achou = -1;
		for (int i = 0; i < itensVenda.size(); i++) {
			if (itensVenda.get(i).getProduto().equals(itemVenda.getProduto())) {
				achou = i;
			} 
		}
		
		if(achou > -1) {
//			itensVenda.remove(achou);
			ItemVenda iVenda = itensVenda.get(achou);
			iVenda.setQuantidade((short) (itemVenda.getQuantidade() - 1));
			iVenda.setPrecoParcial(iVenda.getProduto().getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
			if (iVenda.getQuantidade() == 0) {
				itensVenda.remove(achou);
			}
		}
		calcular();
	}
	
	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));
		
		for (int i = 0; i < itensVenda.size(); i++) {
			ItemVenda itemVenda = itensVenda.get(i);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}
	
	public void finalizar() {
		try {
			venda.setHorario(new Date());
			venda.setCliente(null);
			venda.setFuncionario(null);
			funcionarios = new FuncionarioDAO().listarOrdenado();
			
			clientes = new ClienteDAO().listarOrdenado();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalError("Informe um item para a venda");
				return;
			}
			new VendaDAO().salvar(venda, itensVenda);
			listar();
			Messages.addGlobalInfo("Venda realizada com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("ocorreu um erro ao tentar salvar a venda");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		vendas = new VendaDAO().listar();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

}
