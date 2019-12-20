package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.ClienteDAO;
import br.pro.dl.drogaria.dao.PessoaDAO;
import br.pro.dl.drogaria.domain.Cliente;
import br.pro.dl.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void listar() {
		try {
			clientes = new ClienteDAO().listar("dataCadastro");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os clientes");
			e.printStackTrace();
		}

	}

	public void novo() {
		try {
			cliente = new Cliente();
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo cliente");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			clientes = clienteDAO.listar("dataCadastro");

			novo();

			Messages.addGlobalInfo("Cliente salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo Cliente");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o Cliente");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			new ClienteDAO().excluir(cliente);
			clientes = new ClienteDAO().listar("dataCadastro");
			Messages.addGlobalInfo("Cliente removido com sucesso!");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o cliente");
			e.printStackTrace();
		}
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
