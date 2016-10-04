package br.com.bibideais.service;

import java.math.BigInteger;
import java.util.List;

import br.com.bibideais.dao.OrganizadoraDAO;
import br.com.bibideais.entity.Organizadora;

public class OrganizadoraBO {

	private OrganizadoraDAO dao;
	
	
	public Organizadora cadastrar(Organizadora org){
		dao = new OrganizadoraDAO();
		return dao.inserir(org);
	}
	
	public List<Organizadora> buscarTodas(){
		dao = new OrganizadoraDAO();
		return dao.localizarAll();
	}
	
	public Organizadora buscarPorCodigoLazy(int codigo){
		dao = new OrganizadoraDAO();
		return dao.findByLazyInteger(codigo);
		
		
	}
	
	public Organizadora buscarPorCodigo(int codigo){
		dao = new OrganizadoraDAO();
		return dao.localizar(codigo);
	}
	
	public List<Organizadora> localizarRazaoSocial(String razao){
		dao = new OrganizadoraDAO();
		return dao.localizarPorRazaoSocial(razao);
	}
	
	
	public List<Organizadora> localizarNomeFantasia(String nome){
		dao = new OrganizadoraDAO();
		return dao.localizarPorNomeFantasia(nome);
	}
	
	
	public BigInteger countOrganizadoras(){
		dao = new OrganizadoraDAO();
		return dao.countOrganizadoras();
	}

	public Organizadora alterarOrganizadora(Organizadora org) {
		dao = new OrganizadoraDAO();
		return dao.alterarOrganizadora(org);
		
	}

	public Organizadora atualizarLogo(Organizadora org) {
		dao = new OrganizadoraDAO();
		return dao.alterarLogo(org);
		
	}
	
	public List<Organizadora> buscarTodasAlfabetic() {
		dao = new OrganizadoraDAO();
		return dao.buscarTodasAlfabetic();
		
	}
	
	public List<Organizadora> buscarUltimasCadastradas(){
		dao = new OrganizadoraDAO();
		return dao.buscarUltimasCadastradas();
	}

	
	
}
