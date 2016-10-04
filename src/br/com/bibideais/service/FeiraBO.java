package br.com.bibideais.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.PersistenceException;

import org.hibernate.exception.DataException;

import br.com.bibideais.dao.FeiraDAO;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ClienteFeira;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pavilhao;

public class FeiraBO implements Serializable {


	private static final long serialVersionUID = 1L;
	
private FeiraDAO dao;
	
	public Feira cadastrar(Feira f) {
		dao = new FeiraDAO();
		Feira newfeira = null;
		try{
			newfeira = dao.inserirFeira(f);
			
		}catch(Exception pe){
		pe.printStackTrace();
		newfeira = null;
		}
		
		return newfeira;
	}
	
	public Feira updateFeira(Feira feira){
		dao = new FeiraDAO();
		return dao.updateFeira(feira);
	}
	
	public Feira atualizarLogo(Feira f){
		dao = new FeiraDAO();
		return dao.atualizarLogo(f);
	}
	
	public Feira atualizarManual(Feira f){
		dao = new FeiraDAO();
		return dao.atualizarManual(f);
	}
	public Feira atualizarMapa(Feira f){
		dao = new FeiraDAO();
		return dao.atualizarMapa(f);
	}
	
	public Feira atualizarFeira(Feira f){
		dao = new FeiraDAO();
		return dao.alterarFeira(f);
	}
	

	public List<Feira> buscarPorNome(String nome){
		dao = new FeiraDAO();
		return dao.localizarPorNome(nome);
	}
	
	public List<Feira> buscarPorConstruiu(boolean construiu){
		dao = new FeiraDAO();
		return dao.localizarPorConstruiu(construiu);
	}
	
	public List<Feira> localizarTodas(){
		dao = new FeiraDAO();
		return dao.buscarTodasAlpha();
	}
	
	public Feira buscarPorCodigoLazy(int codigo){
		dao = new FeiraDAO();
		return dao.findByLazyInteger(codigo);
	}
	
	public Feira buscarPorCodigo(int codigo){
		dao = new FeiraDAO();
		return dao.localizar(codigo);
	}
	
	
	public List<Feira> buscarPorOrganizadora(Organizadora org){
		dao = new FeiraDAO();
		return dao.buscarPorOrganizadora(org);
	}
	
	
	public List<Feira> buscarPorLocal(Pavilhao pav){
		dao = new FeiraDAO();
		return dao.buscarPorLocal(pav);
	}
	public List<Feira> buscarPorBiConstruiu(){
		dao = new FeiraDAO();
		return dao.buscarPorBiConstruiu();
	}
	public List<Feira> buscarPorBiNaoConstruiu(){
		dao = new FeiraDAO();
		return dao.buscarPorBiNaoConstruiu();
	}
	public List<Feira> buscarPorAno(String ano){
		dao = new FeiraDAO();
		return dao.buscarPorAno(ano);
	}
	
	public LinkedList<Feira> buscarUltimasCadastras(){
		dao = new FeiraDAO();
		return dao.buscarUltimas();
	}
	
	
	public List<Feira> filteredSearch(HashMap <String, String> parametros, boolean construiu){
		dao = new FeiraDAO();
		return dao.filteredSearch(parametros, construiu);
	}
	
	public void relacionarFeirasAoCliente(Cliente c, List<Feira> feiras){
		dao = new FeiraDAO();
		dao.relacionarFeiraAoCliente(c, feiras);
	}
	
	
	public void desrelacionarFeirasAoCliente(Cliente c, Feira feira){
		dao = new FeiraDAO();
		dao.desrelacionarFeiraAoCliente(c, feira);
	}
	
	
	
	public List<Feira> buscarFeiraPorCliente(Cliente c){
		dao = new FeiraDAO();
		List<ClienteFeira> cfs = dao.buscarPorCliente(c);
		List<Feira> feiras = new ArrayList<Feira>();
		for (ClienteFeira clienteFeira : cfs) {
			feiras.add(clienteFeira.getId().getIdFeira());
		}
		return feiras;
	}
	
	public BigInteger countFeiras(){
		dao = new FeiraDAO();
		return dao.countFeiras();
	}
	
	
	public List<Feira> subtrairFeirasCliente(Cliente c){
		List<Feira> fclientes = this.buscarFeiraPorCliente(c);
		List<Feira> feirasTotais = new FeiraDAO().buscarTodasAlpha();
		
		List<Feira> feirasClean = new ArrayList<Feira>();

		feirasClean.addAll(feirasTotais);
		for (Feira feiraA : feirasTotais) {
			
			for (Feira feiraB : fclientes) {
				if(feiraA.getIdFeira() == feiraB.getIdFeira()){
					feirasClean.remove(feiraA);
				}
			}
			
			
			
		}

		return feirasClean;
	}	
	
}
