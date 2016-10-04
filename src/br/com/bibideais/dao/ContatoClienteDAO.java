package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.exception.DataException;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;

public class ContatoClienteDAO extends GenericDAO<ContatoCliente> implements Serializable {

	

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	
	
	
	public List<ContatoCliente> inserirContatos(List<ContatoCliente> contatos) throws Exception{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		try{
			for (ContatoCliente c : contatos) {
				em.persist(c);
			
			}	
			em.getTransaction().commit();
			
		}catch(DataException de){
			throw new Exception("Valor muito grande para uma das colunas");
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			em.close();
		}
		return contatos;

	}
	
	
	public ContatoCliente inserirContato(ContatoCliente contato){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		try{
			em.persist(contato);
		
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		return contato;

	}
	
	
	
	
	public ContatoCliente atualizarContato(ContatoCliente contato){
		em = factory.createEntityManager();
		try{

			em.getTransaction().begin();

			Query q = em.createNativeQuery("Update ContatoCliente set skype =?, cargo =?, nome =?, telefone =?, celular =?, email =?, redeSocial =? where idContatoCliente =? ");
			q.setParameter(1, contato.getSkype());
			q.setParameter(2, contato.getCargo());
			q.setParameter(3, contato.getNome());
			q.setParameter(4, contato.getTelefone());
			q.setParameter(5, contato.getCelular());
			q.setParameter(6, contato.getEmail());
			q.setParameter(7, contato.getRedeSocial());
			q.setParameter(8, contato.getIdContatoCliente());
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}

		return contato;
		
	}
	
	public List<ContatoCliente> buscarContatosPorCliente(Cliente cliente){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from ContatoCliente c where c.cliente =:cliente");
		q.setParameter("cliente", cliente);
		List<ContatoCliente> contatos = q.getResultList();
		return contatos;
	}
	
	
	
	
	
	
	public ContatoCliente update(ContatoCliente cont){
		 em = factory.createEntityManager();
		 ContatoCliente c = null;
		 try{
			 em.getTransaction().begin();
			 c = em.merge(cont);
		 }catch(Exception e){
			 em.getTransaction().rollback();
			 e.printStackTrace();


		 }finally{
			 em.close();
		 }

		return c;
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	public List<String> buscarTodosEmails() {
		 em = factory.createEntityManager();
		 List<String> listaEmails = new ArrayList<String>();
		 try{
			 Query q = em.createQuery("Select email from ContatoCliente");
			listaEmails = q.getResultList();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return listaEmails;
		 
	}
	
	
	
	
}
