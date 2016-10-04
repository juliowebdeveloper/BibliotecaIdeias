package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;

public class BriefingFilesDAO extends GenericDAO<BriefingFiles> implements Serializable {

	EntityManager em = null;
	
	

	
	public BriefingFiles atualizarUrlBriefing(BriefingFiles bri){
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Update BriefingFiles set urlFile =? where idFile =?");
			q.setParameter(1, bri.getUrlFile());
			q.setParameter(2, bri.getIdFile());
			q.executeUpdate();

			em.getTransaction().commit();

		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}

		return bri;

	}

	
	public List<BriefingFiles> buscarFilesPorBriefing(Briefing bri){
		em = factory.createEntityManager();
		
		Query q = em.createQuery("Select b from BriefingFiles b where b.briefing =:bri");
		q.setParameter("bri", bri);
		List<BriefingFiles> briefs = new ArrayList<BriefingFiles>();
		briefs = q.getResultList();
		em.close();
		
		return briefs;
	}

	public BriefingFiles updateFile(BriefingFiles bf){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(bf);
		em.getTransaction().commit();
		em.close();
		
		return bf;
	}
	
	
	


	public EntityManager getEm() {
		return em;
	}




	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
}
