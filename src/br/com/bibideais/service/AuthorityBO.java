package br.com.bibideais.service;

import java.util.List;

import br.com.bibideais.dao.AuthorityDAO;
import br.com.bibideais.entity.Authority;
import br.com.bibideais.entity.Funcionario;

public class AuthorityBO {

	private AuthorityDAO dao; 
	
	
	public Authority findUserAuthority(String userType){
		Authority a = null ;
		if(!userType.equalsIgnoreCase("Role_ADMIN") && !userType.equalsIgnoreCase("Role_Func")){
			throw new NullPointerException();
		}else{
			dao = new AuthorityDAO();
			a = dao.findUserAuthority(userType);
		}
		return a;
		
	}
	
	public List<Authority> buscarPorFuncionario(Funcionario f){
		dao = new AuthorityDAO();
		return dao.findByFunc(f);
	}
	
	

	public List<Authority> findAll(){
		dao = new AuthorityDAO();
		return dao.findAll();
	}
	
	
	
	

	public AuthorityDAO getDao() {
		return dao;
	}


	public void setDao(AuthorityDAO dao) {
		this.dao = dao;
	}
	
	
	
	
}
