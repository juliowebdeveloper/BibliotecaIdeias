package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Calendario;

public class CalendarioDAO extends GenericDAO<Calendario> implements Serializable {


	private static final long serialVersionUID = 1L;
	
	EntityManager em = null;

	//Os 4 primeiros parametros vem do evento antigo, e o cad é para o update.
	public Calendario update(String titulo, Calendar dataInicio, Calendar dataFim, boolean allday, Calendario cad){
		em = factory.createEntityManager();
		Calendario c = null;
		try{
			em.getTransaction().begin();
			Query q = em.createQuery("Select c from Calendario c where c.descricao =:titulo and c.dataInicio =:dataInicio and c.dataFim =:dataFim and diaTodo =:allday");
			q.setParameter("titulo", cad.getDescricao());
			q.setParameter("dataInicio", cad.getDataInicio());
			q.setParameter("dataFim", cad.getDataFim());
			q.setParameter("allday", cad.isDiaTodo());
			q.setMaxResults(1);
			c= (Calendario) q.getSingleResult();
			Query q1= em.createNativeQuery("Update Calendario set descricao =?, dataInicio =?, dataFim =?, diaTodo =? where idCalendario =?");
			q1.setParameter(1, titulo );
			q1.setParameter(2, dataInicio );
			q1.setParameter(3, dataFim);
			q1.setParameter(4, allday);
			q1.setParameter(5, c.getIdCalendario());
			q1.executeUpdate();
			em.getTransaction().commit();

		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}

		return c;
	}
	
	public void deletarEvento(String titulo, Calendar dataInicio, Calendar dataFim, boolean allday, Calendario cad){
		em = factory.createEntityManager();
		Calendario c = null;
		try{
			em.getTransaction().begin();
			Query q = em.createQuery("Select c from Calendario c where c.descricao =:titulo and c.dataInicio =:dataInicio and c.dataFim =:dataFim and diaTodo =:allday");
			q.setParameter("titulo", cad.getDescricao());
			q.setParameter("dataInicio", cad.getDataInicio());
			q.setParameter("dataFim", cad.getDataFim());
			q.setParameter("allday", cad.isDiaTodo());
			q.setMaxResults(1);
			c = (Calendario) q.getSingleResult();
			Query q1= em.createNativeQuery("Delete from Calendario where idCalendario =?");
			q1.setParameter(1, c.getIdCalendario());
			q1.executeUpdate();
			em.getTransaction().commit();

		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
	
		

	}
	
}
