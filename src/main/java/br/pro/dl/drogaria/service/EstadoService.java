package br.pro.dl.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.pro.dl.drogaria.dao.EstadoDAO;
import br.pro.dl.drogaria.domain.Estado;

//http://localhost:8080/Drogaria/rest/estado
@Path("estado")
public class EstadoService {
	
	@GET
	public String listar() {
		String json = null;
		try {
			List<Estado> estados = new EstadoDAO().listar("nome");
			
			Gson gson = new Gson();
			json = gson.toJson(estados);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	
}
