package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.CidadeDAO;
import br.pro.dl.drogaria.dao.EstadoDAO;
import br.pro.dl.drogaria.domain.Cidade;
import br.pro.dl.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	
	private List<Cidade> cidades;
	private Cidade cidade;
	private List<Estado> estados;

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct 
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			cidade = new Cidade(); // criar
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova Cidade");
			e.printStackTrace();		
		}
	}
	
	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			
			cidade = new Cidade();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			
			cidades = cidadeDAO.listar();
			
			Messages.addGlobalInfo("Cidade salva com sucesso!");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova Cidade");
			e.printStackTrace();		
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada"); // componente clicado e  retorna atributo selecionado
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);
			
			cidades = cidadeDAO.listar(); // recarregar cidadees
					
			Messages.addGlobalInfo("Cidade removida com sucesso!");
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a cidade");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada"); // carregar
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar a Cidade");
			e.printStackTrace();	
		}
	}
	
}