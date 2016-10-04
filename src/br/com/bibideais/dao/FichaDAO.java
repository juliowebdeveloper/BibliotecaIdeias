package br.com.bibideais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Ficha;


public class FichaDAO extends GenericDAO<Ficha> {

	EntityManager em = null;
	
	public void inserirFichas(List<Ficha> fichas){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		try{
			for (Ficha ficha : fichas) {
				em.persist(ficha);
			
			}	
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		
		
		
	}
	
	
	public Ficha atualizarUrlFicha(Ficha f){
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		
			Query q= em.createNativeQuery("Update Ficha set urlimagem =? where idFicha =?");
			q.setParameter(1, f.getUrlimagem());
			q.setParameter(2, f.getIdFicha());
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		return f;
	}
	
	

	public Ficha atualizarNomeFichaQuery(Ficha f){
		EntityManager em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q= em.createNativeQuery("Update Ficha set nomeImagem =? where idFicha =?");
		q.setParameter(1, f.getNomeImagem());
		q.setParameter(2, f.getIdFicha());
		q.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		return f;
	}
	
	

	public void excluirFichaQuery(Ficha f){
		EntityManager em = factory.createEntityManager();

		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Delete from Ficha where idficha =?");
			q.setParameter(1, f.getIdFicha());
			q.executeUpdate();
			em.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
	}

	
	
	
	
	
	
	
	
}
