package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.EstadoDAO;
import br.pro.dl.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable{
	
	// Atributos do Modelo
	private Estado estado;
	private List<Estado> estados;
		
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	/**
	 * Metodo sem OmniFaces
	public void salvar() {
		String texto = "Programação Web com Java";
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, texto, texto);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, mensagem); // primeiro parametro geralmente nulo
	}
	*/ 
	
	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu erro ao tentar listar os estados");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		estado = new Estado();
	}
	
	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
					
			Messages.addGlobalInfo("Salvo com suceso! Estado: " + estado.getNome() + " Sigla: " + estado.getSigla());
			
			novo(); // reinstanciar novo
			estados = estadoDAO.listar(); // mostrar dados atualizadps
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu erro ao tentar salvar estado");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado"); // componente clicado e  retorna atributo selecionado
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
					
			Messages.addGlobalInfo("Removido Estado: " + estado.getNome() + " Sigla: " + estado.getSigla());
			
			estados = estadoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	}
		
}
