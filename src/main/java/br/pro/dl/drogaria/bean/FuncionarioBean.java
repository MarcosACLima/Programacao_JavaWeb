package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.FuncionarioDAO;
import br.pro.dl.drogaria.dao.PessoaDAO;
import br.pro.dl.drogaria.domain.Funcionario;
import br.pro.dl.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void listar() {
		try {
			funcionarios = new FuncionarioDAO().listar("dataAdmissao");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os funcionários");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			funcionario = new Funcionario();
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo funcionário");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
			funcionarios = funcionarioDAO.listar("dataAdmissao");
			novo();
			Messages.addGlobalInfo("Funcionário salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo funcionário");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o Funcionário");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			new FuncionarioDAO().excluir(funcionario);
			funcionarios = new FuncionarioDAO().listar("dataAdmissao");
			Messages.addGlobalInfo("Funcionário removido com sucesso!");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Funcionário");
			e.printStackTrace();
		}
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
