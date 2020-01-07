package br.pro.dl.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.domain.Fabricante;

//http://localhost:8080/Drogaria/rest/fabricante
@Path("fabricante")
public class FabricanteService {
	
	@GET
	public String listar() {
		String json = null;
		try {
			List<Fabricante> fabricantes = new FabricanteDAO().listar("descricao");
			
			Gson gson = new Gson();
			json = gson.toJson(fabricantes);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//http://localhost:8080/Drogaria/rest/fabricante/codigo
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		String json = null;
		try {
			Fabricante fabricante = new FabricanteDAO().buscar(codigo);
			Gson gson = new Gson();
			json = gson.toJson(fabricante);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//http://localhost:8080/Drogaria/rest/fabricante
	/* metodo salva e edita
	@POST
	public String salvar(String json) {
		String jsonRetorno = null;
		try {
			Gson gson = new Gson();
			Fabricante fabricante = gson.fromJson(json, Fabricante.class);
			
			new FabricanteDAO().merge(fabricante);
			
			jsonRetorno = gson.toJson(fabricante);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return jsonRetorno;
	}
	*/
	
	@POST
	public String salvar(String json) {
		String jsonRetorno = null;
		try {
			Gson gson = new Gson();
			Fabricante fabricante = gson.fromJson(json, Fabricante.class);
			
			new FabricanteDAO().salvar(fabricante);
			
			jsonRetorno = gson.toJson(fabricante);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return jsonRetorno;
	}
	
	@PUT
	public String editar(String json) {
		String jsonRetorno = null;
		try {
			Gson gson = new Gson();
			Fabricante fabricante = gson.fromJson(json, Fabricante.class);
			
			new FabricanteDAO().editar(fabricante);
			
			jsonRetorno = gson.toJson(fabricante);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return jsonRetorno;
	}
	
	//http://localhost:8080/Drogaria/rest/fabricante/codigo
	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {
		String jsonRetorno = null;
		try {
			Fabricante fabricante = new FabricanteDAO().buscar(codigo); // buscar codigo
			new FabricanteDAO().excluir(fabricante);
			Gson gson = new Gson();
			jsonRetorno = gson.toJson(fabricante);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return jsonRetorno;
	}
	
}
