package br.com.bibideais.service;

import java.util.List;

import br.com.bibideais.dao.AnoDAO;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Cliente;

public class AnoBO {

	
	private AnoDAO dao;
	
	
	
	public void relacionarAnosCliente(Cliente c, List<Ano> anos){
		dao = new AnoDAO();
		dao.relacionarAnosCliente(c, anos);
	}
	
	
	public void desrelacionarAnosCliente(Cliente c){
		dao = new AnoDAO();
		dao.desrelacionarAnosCliente(c);
		}

	public void relacionarAnoCliente(Cliente c, Ano ano){
		dao = new AnoDAO();
		dao.relacionarAnosCliente(c, ano);
	}
	
	public List<Ano> buscarTodosAnos(){
		dao = new AnoDAO();
		return dao.buscarTodosDesc();
	}
	
	public Ano selectAno(int ano){
		dao = new AnoDAO();
		return dao.selectAno(ano);
	}
	
	public List<Integer> buscarAnosPorCliente(Cliente c){
		dao = new AnoDAO();
		return dao.buscarPorCliente(c);
	}
	
	
	
}
