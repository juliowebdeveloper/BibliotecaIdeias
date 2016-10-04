package br.com.bibideais.service;

import java.util.List;

import br.com.bibideais.dao.CidadeDAO;
import br.com.bibideais.entity.Cidade;

public class CidadeBO {

	
	private CidadeDAO dao;
	
	public List<Cidade> buscarTodas(){
		dao = new CidadeDAO();
		return dao.localizarAll();
	}
	
	public Cidade cadastrar(Cidade c){
		dao = new CidadeDAO();
		return dao.inserir(c);
	}
	
	public Cidade buscarPorId(int id){
		dao = new CidadeDAO();
		return dao.localizar(id);
	}
	
	public int returnNextId(){
		dao = new CidadeDAO();
		return dao.returnLastId()+1;
	}
	
	public List<Cidade> buscarPorCodigoPais(String codPais){
		dao = new CidadeDAO();
		return dao.buscarPorCodigoPais(codPais);
	}
	
	public Cidade buscarPorNomeExato(String nome){
		dao = new CidadeDAO();
		return dao.buscarPorNomeExato(nome);
	}
	
	public Cidade buscarPorNomeDistrito(String nome, String distrito){
		dao = new CidadeDAO();
		return dao.buscarPorNomeExatoDistrito(nome, distrito);
	}
}
