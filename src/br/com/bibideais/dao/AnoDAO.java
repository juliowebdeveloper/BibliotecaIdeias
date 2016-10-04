package br.com.bibideais.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Cliente;

public class AnoDAO extends GenericDAO<Ano> {

	@Inject
	EntityManager em = null;
	
	public Ano selectAno(int ano){
		em = factory.createEntityManager();
		Query q = em.createQuery("From Ano a where a.ano =:ano");
		q.setParameter("ano", ano);
		q.setMaxResults(1);
		Ano ano1 = (Ano) q.getSingleResult();
		em.close();
		return ano1;
		
		
	}
	
	
	
	public void relacionarAnosCliente(Cliente cliente, Ano ano){
		em = factory.createEntityManager();
		Cliente c1 = em.find(Cliente.class, cliente.getIdCliente());
		Ano a1 = em.find(Ano.class, ano.getIdAno());
		
		
		try{
			em.getTransaction().begin();
			Query q1 = em.createNativeQuery("Delete from cliente_ano where id_cliente =?");
			q1.setParameter(1, c1.getIdCliente());
			q1.executeUpdate();
			
			Query q2 = em.createNativeQuery("Insert into Cliente_ano (id_cliente, id_ano) values (?, ?)");
			q2.setParameter(1, c1.getIdCliente());
			q2.setParameter(2, a1.getIdAno());
			q2.executeUpdate();
			
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
	
		
		
	}
	
	public void relacionarAnosCliente(Cliente cliente, List<Ano> anos){
		em = factory.createEntityManager();
		Cliente c1 = em.find(Cliente.class, cliente.getIdCliente());		
		
		try{
			em.getTransaction().begin();
			Query q1 = em.createNativeQuery("Delete from cliente_ano where id_cliente =?");
			q1.setParameter(1, c1.getIdCliente());
			
			q1.executeUpdate();
			
			for (Ano ano2 : anos) {
				Query q2 = em.createNativeQuery("Insert into Cliente_ano (id_cliente, id_ano) values (?, ?)");
				q2.setParameter(1, c1.getIdCliente());
				q2.setParameter(2, ano2.getIdAno());
				q2.executeUpdate();
			}
			
			
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
	
		
		
	}
	
	public void desrelacionarAnosCliente(Cliente cliente){
		em = factory.createEntityManager();
		Cliente c1 = em.find(Cliente.class, cliente.getIdCliente());		
		
		try{
			em.getTransaction().begin();
			Query q1 = em.createNativeQuery("Delete from cliente_ano where id_cliente =?");
			q1.setParameter(1, c1.getIdCliente());
			
			q1.executeUpdate();
			
			
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
	
		
		
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Ano> buscarTodosDesc(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select a from Ano a order by a.idAno desc");
		List<Ano> anos = q.getResultList();
		em.close();
		return anos;
	}
	

	public List<Integer> buscarPorCliente(Cliente c){
		em = factory.createEntityManager();
		//List<Ano> anos = null;
		Query q = em.createNativeQuery("Select a.ano from cliente_ano ca, ano a where a.idano = ca.id_ano and id_cliente =? order by a.idAno asc");
		q.setParameter(1, c.getIdCliente());
		
		List<Integer> anos = q.getResultList();
		
	
		return anos;
	}
	
	
	
}
