package br.com.bibideais.service;

import java.util.LinkedList;
import java.util.List;

import br.com.bibideais.dao.PavilhaoDAO;
import br.com.bibideais.entity.Pavilhao;

public class PavilhaoBO {

	
	
private PavilhaoDAO dao;
	
	public Pavilhao cadastrar(Pavilhao ent){
		
		dao = new PavilhaoDAO();
		
		boolean achou = dao.localizarNomeExato(ent.getNome());
				
				if(achou){
					return null;
				}else{
					return dao.inserir(ent);

				}
			
	}
	
	
	public List<Pavilhao> buscarPorNome(String nome){
		dao = new PavilhaoDAO();
		return dao.localizarPorNome(nome);
	}
	
	public List<Pavilhao> localizarTodas(){
		dao = new PavilhaoDAO();
		return dao.localizarAll();
	}

	
	
	
	public Pavilhao buscarPorCodigoLazy(int codigo){
		dao = new PavilhaoDAO();
		return dao.findByLazyInteger(codigo);
		
		
	}
	
	public Pavilhao buscarPorCodigo(int codigo){
		dao = new PavilhaoDAO();
		return dao.localizar(codigo);
	}
	
	
	public LinkedList<Pavilhao> buscarUltimosCadastrados(){
		dao = new PavilhaoDAO();
		return dao.buscarUltimosCadastrados();
	}
	
	public Pavilhao alterarPavilhao(Pavilhao pav){
		dao = new PavilhaoDAO();
		return dao.alterarPavilhao(pav);
	}


	public List<Pavilhao> buscarTodosAlphabetic() {
		dao = new PavilhaoDAO();		
		return dao.buscarTodosAlphabetic();
	}
	
}
