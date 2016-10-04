package br.com.bibideais.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import br.com.bibideais.dao.EntidadeDAO;
import br.com.bibideais.entity.Entidade;

public class EntidadeBO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private EntidadeDAO dao;
	
	public Entidade cadastrar(Entidade ent){
		dao = new EntidadeDAO();
		return dao.inserir(ent);
	}
	
	
	public List<Entidade> buscarPorNome(String nome){
		dao = new EntidadeDAO();
		return dao.localizarPorNome(nome);
	}
	
	public List<Entidade> localizarTodasAlfabetic(){
		dao = new EntidadeDAO();
		return dao.localizarAllAlfabetic();
	}
	
	public List<Entidade> localizarPorNomeNotAssociated(String nome){
		dao = new EntidadeDAO();
		return dao.localizarPorNomeNotAssociated(nome);
	}
	
	public List<Entidade> localizarPorNomeAssociated(String nome){
		dao = new EntidadeDAO();
		return dao.localizarPorNomeAssociated(nome);
	}
	
	public Entidade alterarEntidade(Entidade ent){
		dao = new EntidadeDAO();
		return dao.alterarEntidade(ent);
	}


	public LinkedList<Entidade> localizarUltimasCadastradas() {
		dao = new EntidadeDAO();
		return dao.localizarUltimasCadastradas();
		
	}
	
	
}
