package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.VersaoProjeto;


public class FotoProjetoDAO extends GenericDAO<FotoProjeto> implements Serializable  {


	private static final long serialVersionUID = 1L;
	
	
	EntityManager em = null;
	
	
	public void adicionarFotosVersao(VersaoProjeto v, List<FotoProjeto> fotos){
		for (FotoProjeto fotoProjeto : fotos) {
			fotoProjeto.setVersaoprojeto(v);
			super.inserir(fotoProjeto);
		}
	}

	
	public FotoProjeto atualizarUrlFoto(FotoProjeto foto){
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update FotoProjeto set urlImagem =? where idFotoProjeto =?");
		q.setParameter(1, foto.getUrlImagem());
		q.setParameter(2, foto.getIdFotoProjeto());
		q.executeUpdate();
		
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		return foto;
		
	}
	
	
	public List<FotoProjeto> buscarFotosPorVersao(VersaoProjeto ver){
		em = factory.createEntityManager();
		List<FotoProjeto> fotos = new ArrayList<FotoProjeto>();
		Query q = em.createQuery("Select f from FotoProjeto f where f.versaoprojeto =:ver");
		
		q.setParameter("ver", ver);
		fotos = q.getResultList();
		
		em.close();
		
		return fotos;
		
	}
	
	
	
	

	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
	
	
}
