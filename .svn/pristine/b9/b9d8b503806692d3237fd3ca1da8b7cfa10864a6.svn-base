package br.com.bibideais.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Authority;
import br.com.bibideais.entity.Funcionario;

public class AuthorityDAO extends GenericDAO<Authority> {

	
	private EntityManager em;
	
	
	//Retorna todas as authorities para criar um administrador
	public List<Authority> findAll(){
		em = factory.createEntityManager();
		
		Query q = em.createQuery("from Authority");
		List<Authority> auths = q.getResultList();
		em.close();
		return auths;
		
	}
	
	/**
	 * 
	 * @param userType
	 * @return
	 */
	//Retorna authority de funcionario ou admin - Parametros a serem passados : Role_Admin e Role_Func
	public Authority findUserAuthority(String userType){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select a from Authority a where a.name =:userType");
		q.setParameter("userType", userType);
		Authority a = (Authority) q.getSingleResult();
		em.close();
		return a;
		
	
	}
	
	public List<Authority> findByFunc(Funcionario func){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select b.auth_authority from func_auth b where b.func_id =?");
		q.setParameter(1, func.getId());
		q.setMaxResults(1);
		String s =  (String) q.getSingleResult();
		Authority a = this.findUserAuthority(s);
		List<Authority> list = new ArrayList<Authority>();
		list.add(a);
		
		return list;
		
	}
	
	
	
	

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
