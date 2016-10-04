package br.com.bibideais.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Briefing;

public class BriefingDAO extends GenericDAO<Briefing> {

	EntityManager em = null;
	
	
	
	public Briefing updateBriefing(Briefing b){
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			Query q= em.createNativeQuery("Update Briefing set codigo =?, status =?, anotacoes =? where idBriefing =?");
			q.setParameter(1, b.getCodigo());
			q.setParameter(2, b.getStatus());
			q.setParameter(3, b.getAnotacoes());
			q.setParameter(4, b.getIdBriefing());
			q.executeUpdate();
			em.getTransaction().commit();

			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
				
			}finally{
				em.close();
			}
			
		return b;
		
	}
	
	
	public Briefing alterarPlanilhaCustos(Briefing b){
		em = factory.createEntityManager();
		try{




			em.getTransaction().begin();
			Query q= em.createNativeQuery("Update Briefing set urlPlanilhaCustos = ? where idBriefing =?");
			q.setParameter(1, b.getUrlPlanilhaCustos());
			q.setParameter(2, b.getIdBriefing());
			q.executeUpdate();
			em.getTransaction().commit();
	
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}

		return b;
		
	}
	
	
	public Briefing atualizarBriefing(Briefing b){
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(b);
			em.getTransaction().commit();
			
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
	
		return b;
	}
	
	public Briefing buscarPorCodigo(Briefing b){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select b from Briefing b where b.codigo like:codigo");
		q.setParameter("codigo", b.getCodigo());
		Briefing br = (Briefing) q.getSingleResult();
		return br;
	}
	
	
	
}
