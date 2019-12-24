package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.CidadeDAO;
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
	
	private Estado estado; // variavel temporaria
	
	@PostConstruct
	public void listar() {
		try {
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			pessoa = new Pessoa();
			estado = new Estado();
			
			estados = new EstadoDAO().listar("nome");
			
			cidades = new ArrayList<Cidade>(); /// iniciar cidade vazia
		} catch (RuntimeException e) {
			Messages.addGlobalError("ocorreu um erro ao tentar gerar uma nova pessoa");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			pessoas = pessoaDAO.listar("nome");
			
			novo();
			
			Messages.addGlobalInfo("Pessoa salva com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("ocorreu um erro ao tentar salvar a pessoa");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			estado = pessoa.getCidade().getEstado(); // retorna estado do objeto
			estados = new  EstadoDAO().listar("nome");
			
			popular(); 
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar a Pessoa");
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Pessoa removida com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a pessoa");
			e.printStackTrace();
		}
	}
	
	public void popular() {
		try {
			if(estado != null) {
				cidades = new CidadeDAO().buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorrey um erro ao tentar filtrar as Cidades");
		}
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
