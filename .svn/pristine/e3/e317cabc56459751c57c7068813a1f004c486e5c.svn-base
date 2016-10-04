package br.com.bibideais.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.bibideais.dao.FornecedorDAO;
import br.com.bibideais.entity.Fornecedor;

public class FornecedorBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private FornecedorDAO dao;
	
	
	public Fornecedor cadastrar(Fornecedor org){
		dao = new FornecedorDAO();
		return dao.inserir(org);
	}
	
	public List<Fornecedor> buscarTodosAlfabetic(){
		dao = new FornecedorDAO();
		return dao.buscarTodosAlfabetic();
	}
	
	public List<Fornecedor> buscarPorRazaoSocial(String razaoSocial){
		dao = new FornecedorDAO();
		return dao.localizarPorRazaoSocial(razaoSocial);
	}
	
	public List<Fornecedor> filteredSearch(HashMap<String, String> parametros, boolean associated){
		dao = new FornecedorDAO();
		return dao.filteredSearch(parametros, associated);
	}
	
	public Fornecedor buscarPorCodigoLazy(int codigo){
		dao = new FornecedorDAO();
		return dao.findByLazyInteger(codigo);
		
		
	}
	
	public BigInteger countFornecedores(){
		dao = new FornecedorDAO();
		return dao.countFornecedores();
	}
	
	public Fornecedor buscarPorCodigo(int codigo){
		dao = new FornecedorDAO();
		return dao.localizar(codigo);
	}
	
	public List<Fornecedor> buscarPorSegmento(String segmento){
		dao = new FornecedorDAO();
		return dao.buscarPorSegmento(segmento);
	}
	
	public List<Fornecedor> buscarPorSegmentoAndAssociated(String segmento){
		dao = new FornecedorDAO();
		return dao.buscarPorSegmentoAndAssociated(segmento);
	}
	
	public List<Fornecedor> buscarPorSegmentoAndNotAssociated(String segmento){
		dao = new FornecedorDAO();
		return dao.buscarPorSegmentoAndNotAssociated(segmento);
	}

	public Fornecedor alterarFornecedor(Fornecedor forne) {
		dao = new FornecedorDAO();
		return dao.alterarFornecedor(forne);
		
	}
	
	public LinkedList<Fornecedor> buscarUltimosCadastrados(){
		dao = new FornecedorDAO();
		return dao.buscarUltimosCadastrados();
	}
	
	
	public void excluirFornecedor(Fornecedor f){
		dao = new FornecedorDAO();
		dao.excluir(f.getIdFornecedor());
	}
	
}
