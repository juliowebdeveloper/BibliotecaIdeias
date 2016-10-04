package br.com.bibideais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.FotoCliente;

public class FotoClienteDAO extends GenericDAO<FotoCliente> {

	
	
	//Recebe o objeto pronto, sem fazer select no banco.
		public FotoCliente atualizarFoto(FotoCliente f){
			EntityManager em = factory.createEntityManager();
			try{
			em.getTransaction().begin();
			em.merge(f);
			em.getTransaction().commit();
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();


			}finally{
				em.close();
			}
			return f;
			
		}

		public FotoCliente atualizarLegendaQuery(FotoCliente f){
			EntityManager em = factory.createEntityManager();
			try{
			em.getTransaction().begin();
			Query q= em.createNativeQuery("Update FotoCliente set legenda =? where idFoto =?");
			q.setParameter(1, f.getLegenda());
			q.setParameter(2, f.getIdFoto());
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
		
		
		
		public void inserirFotos(List<FotoCliente> fotos){
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			
			try{
				for (FotoCliente foto : fotos) {
					em.persist(foto);
				
				}	
				em.getTransaction().commit();
			}catch (Exception e) {
				em.getTransaction().rollback();
			}finally{
				em.close();
			}
			
			
			
		}
		

		public void excluirFotoQuery(FotoCliente f){
			EntityManager em = factory.createEntityManager();

			try{
				em.getTransaction().begin();
				Query q = em.createNativeQuery("Delete from FotoCliente where idfoto =?");
				q.setParameter(1, f.getIdFoto());
				q.executeUpdate();
				em.getTransaction().commit();
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				em.close();
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
}
