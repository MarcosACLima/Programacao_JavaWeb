package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;
	
	@PostConstruct
	public void listar() {
		try {
			fabricantes = new FabricanteDAO().listar();
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Fabricantes");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		fabricante = new Fabricante();
	}
	
	public void salvar() {
		try {
			new FabricanteDAO().merge(fabricante);
			Messages.addGlobalInfo("Salvo com suceso! Fabricante: " + fabricante.getDescricao());
			novo();
			fabricantes = new FabricanteDAO().listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu erro ao tentar salvar Fabricante");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}
	
	public void excluir(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);
			
			Messages.addGlobalInfo("Removido Fabricante: " + fabricante.getDescricao());
			
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Fabricante");
			e.printStackTrace();
		}
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

}
