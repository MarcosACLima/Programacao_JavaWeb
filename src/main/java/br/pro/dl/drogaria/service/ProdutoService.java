package br.pro.dl.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Produto;

//http://localhost:8080/Drogaria/rest/produto
@Path("produto")
public class ProdutoService {

	@GET
	public String listar() {
		String json = null;
		try {
			List<Produto> produtos = new ProdutoDAO().listar("descricao");
			Gson gson = new Gson();
			json = gson.toJson(produtos);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return json;
	}

	@POST
	public String salvar(String json) {
		String jsonResultado = null;
		try {
			Gson gson = new Gson();
			Produto produto = gson.fromJson(json, Produto.class);
			produto = new ProdutoDAO().merge(produto);
			jsonResultado = gson.toJson(produto);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return jsonResultado;
	}

}
