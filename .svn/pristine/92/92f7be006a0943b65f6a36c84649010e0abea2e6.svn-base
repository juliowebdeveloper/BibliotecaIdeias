package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Projeto;

public class ProjetoDAO extends GenericDAO<Projeto> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em = null;


	public Projeto alterarProjeto(Projeto p) {
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		
		Query q = em.createNativeQuery("Update Projeto set arquiteto =?, metragem =?, ano =?, segmento =?, nome =?, evento =?, codigo =? where idProjeto =?" );
		q.setParameter(1, p.getArquiteto());
		q.setParameter(2, p.getMetragem());
		q.setParameter(3, p.getAno());
		q.setParameter(4, p.getSegmento());
		q.setParameter(5, p.getNome());
		q.setParameter(6, p.getEvento());
		q.setParameter(7, p.getCodigo());
		
		q.executeUpdate();
		
		em.getTransaction().commit();
		
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		
		return p;
		
	}
	
	public Projeto updateProjeto(Projeto p) {
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		em.merge(p);		
		em.getTransaction().commit();
		
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		
		return p;
		
	}
	
	
	public List<Projeto> buscarPorMetragem(String metragem){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createQuery("Select p from Projeto p where p.metragem like :metragem" );
		q.setParameter("metragem", metragem);
		List<Projeto> lista = new ArrayList<Projeto>();
		lista = q.getResultList();
		
		em.close();
		return lista;
	}

	public List<Projeto> buscarPorAno(String ano) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createQuery("Select p from Projeto p where p.ano like :ano" );
		q.setParameter("ano", ano);
		List<Projeto> lista = new ArrayList<Projeto>();
		lista = q.getResultList();
		
		em.close();
		return lista;
	}
	
}
