package br.pro.dl.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

// http://localhost:8080/Drogaria/rest
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		packages("br.pro.dl.drogaria.service");
	}
}
