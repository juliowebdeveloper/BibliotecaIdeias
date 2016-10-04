package br.com.bibideais.service;

import java.util.List;

import br.com.bibideais.dao.FotoClienteDAO;
import br.com.bibideais.entity.FotoCliente;

public class FotoBO {



	
	public FotoCliente atualizarLegendaQuery(FotoCliente f){
		return new FotoClienteDAO().atualizarLegendaQuery(f);
	}
	
	public FotoCliente inserirFotoCliente(FotoCliente f){
		return new FotoClienteDAO().inserir(f);
	}
	
	public void inserirFotos(List<FotoCliente> fotos){
		new FotoClienteDAO().inserirFotos(fotos);
	}
	
	public void excluirFotoCliente(FotoCliente f){
		new FotoClienteDAO().excluirFotoQuery(f);
	}
	
}
