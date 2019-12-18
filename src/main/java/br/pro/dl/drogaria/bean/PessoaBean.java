package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.EstadoDAO;
import br.pro.dl.drogaria.dao.PessoaDAO;
import br.pro.dl.drogaria.domain.Cidade;
import br.pro.dl.drogaria.domain.Estado;
import br.pro.dl.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;
	private List<Estado> estados;
	
	@PostConstruct
	public void listar() {
		try {
			pessoas = new PessoaDAO().listar();
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			pessoa = new Pessoa();
			
			estados = new EstadoDAO().listar("nome");
			
			cidades = new ArrayList<Cidade>(); /// iniciar cidade vazia
		} catch (RuntimeException e) {
			Messages.addGlobalError("ocorreu um errp ao tentar gerar uma nova pessoa");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		
	}
	
	public void editar() {
		
	}
	
	public void excluir() {
		
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
