package br.com.bibideais.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Fornecedor;


public class FornecedorDAO extends GenericDAO<Fornecedor> implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	@Inject
	EntityManager em = null;
	

	public BigInteger countFornecedores(){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select Count(idFornecedor) from Fornecedor");
		BigInteger b = (BigInteger) q.getSingleResult();
		em.close();
		return b;

	}
	
	
	public List<Fornecedor> filteredSearch(HashMap <String, String> parametros, boolean associated){
		int numerosand = 1; //começa com 1 and pelo associated
		
		em = factory.createEntityManager();
		
		//Maximo numero de ands = 2 um para segmento e outro para associated
		//Se vierem dois parametros fica só 1 and a mais
		numerosand = numerosand + (parametros.size() -1);
		
		Query q = null;
		
		if(numerosand > 1){
			
			 q = em.createQuery("Select f from Fornecedor f where f.razaoSocial like :razaosocial and f.segmento like :segmento and f.isFornecedorBi =:associated order by f.razaoSocial");
			 q.setParameter("associated", associated);
			 for (Map.Entry<String, String> entrada : parametros.entrySet()) {  
				  if(entrada.getKey().equalsIgnoreCase("razaosocial")){
					
				 q.setParameter("razaosocial","%" + entrada.getValue().toUpperCase() + "%");

				  }
				   if(entrada.getKey().equalsIgnoreCase("segmento")){

					q.setParameter("segmento", "%" + entrada.getValue().toUpperCase() + "%");
					
				   }
				   
				   
			 }
			 
			 
		}else{
			for (Map.Entry<String, String> entrada : parametros.entrySet()) {  
			   
			   if(entrada.getKey().equalsIgnoreCase("razaosocial")){
				q = em.createQuery("Select f from Fornecedor f where f.razaoSocial like :razaosocial and f.isFornecedorBi =:associated order by f.razaoSocial");
				q.setParameter("razaosocial", "%" + entrada.getValue().toUpperCase() + "%");
				q.setParameter("associated", associated);
					
			   }
			   
			   if(entrada.getKey().equalsIgnoreCase("segmento")){
					q = em.createQuery("Select f from Fornecedor f where f.segmento like :segmento and f.isFornecedorBi =:associated order by f.razaoSocial");
					q.setParameter("segmento", "%" + entrada.getValue().toUpperCase() + "%");
					q.setParameter("associated", associated);
			   }
			   
			}
			
			

		}
		
			List<Fornecedor> forns = q.getResultList();
		
			em.close();
			return forns;

	}
	
	

	
	
	
	public List<Fornecedor> localizarPorRazaoSocial(String razaoSocial){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where u.razaoSocial like :name order by u.razaoSocial");
		
		q.setParameter("name", "%" + razaoSocial.toUpperCase() + "%");
	

		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> localizarPorRazaoSocialAssociated(String razaoSocial){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where u.razaoSocial like :name and isFornecedorBi = true order by u.razaoSocial");
		
		q.setParameter("name", "%" + razaoSocial.toUpperCase() + "%");
	

		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> localizarPorRazaoSocialNotAssociated(String razaoSocial){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where u.razaoSocial like :name and isFornecedorBi = false order by u.razaoSocial");
		
		q.setParameter("name", "%" + razaoSocial.toUpperCase() + "%");
	

		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	
	
	public List<Fornecedor> buscarPorSegmento(String segmento){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where u.segmento like :segmento order by u.razaoSocial");
		
		q.setParameter("segmento", "%" + segmento.toUpperCase() + "%");
	

		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> buscarPorSegmentoAndAssociated(String segmento){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where u.segmento like :segmento and isFornecedorBi = true order by u.segmento");
		
		q.setParameter("segmento", "%" + segmento.toUpperCase() + "%");
		

		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> buscarPorSegmentoAndNotAssociated(String segmento){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where u.segmento like :segmento and isFornecedorBi = false order by u.segmento");
		
		q.setParameter("segmento", "%" + segmento.toUpperCase() + "%");
		
		

		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> buscarAssociated(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where isFornecedorBi = true order by u.razaoSocial");


		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> buscarNotAssociated(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u where isFornecedorBi = false order by u.razaoSocial");


		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}
	
	public List<Fornecedor> buscarTodosAlfabetic(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Fornecedor u order by u.razaoSocial");


		List<Fornecedor> forns = q.getResultList();
		em.close();
		return forns;
	}







	public Fornecedor alterarFornecedor(Fornecedor forne) {

		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		em.merge(forne);
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		return null;
	}
	
	
	public LinkedList<Fornecedor> buscarUltimosCadastrados(){
		em = factory.createEntityManager();
		LinkedList<Fornecedor> lista = new LinkedList<Fornecedor>();
		List<Fornecedor> banco = new ArrayList<Fornecedor>();
		Query q = em.createQuery("From Fornecedor f order by f.idFornecedor desc");
		q.setMaxResults(5);
		banco = q.getResultList();
		
		for (Fornecedor fornecedor : banco) {
			lista.add(fornecedor);
		}
		return lista;
		
	}
	
}
