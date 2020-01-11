package br.pro.dl.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.dl.drogaria.dao.CidadeDAO;
import br.pro.dl.drogaria.domain.Cidade;

//http://localhost:8080/Drogaria/rest/cidade
@Path("cidade")
public class CidadeService {

	@GET
	@Path("{estadoCodigo}")
	public String buscarPorEstado(@PathParam("estadoCodigo") Long estadoCodigo) {
		String json = null;
		try {
			List<Cidade> cidades = new CidadeDAO().buscarPorEstado(estadoCodigo);
			
			Gson gson = new Gson();
			json = gson.toJson(cidades);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
