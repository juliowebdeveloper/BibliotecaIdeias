package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Entidade;
import br.com.bibideais.entity.Fornecedor;
import br.com.bibideais.entity.Pavilhao;

public class EntidadeDAO extends GenericDAO<Entidade> implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em = null;
	
	
	
	
	public List<Entidade> localizarPorNome(String nome){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Entidade u where u.nome like :name order by nome");
		
		q.setParameter("name", nome);
		q.setParameter("name", "%" + nome.toUpperCase() + "%");
		
		List<Entidade> ents = q.getResultList();
		em.close();
		return ents;
		
		
	}
	
	public List<Entidade> localizarPorNomeAssociated(String nome){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Entidade u where u.nome like :name u.isAssociated = true order by nome");
		
		q.setParameter("name", nome);
		q.setParameter("name", "%" + nome.toUpperCase() + "%");
		List<Entidade> ents = q.getResultList();
		em.close();
		return ents;
		
	}
	
	public List<Entidade> localizarPorNomeNotAssociated(String nome){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Entidade u where u.nome like :name and u.isAssociated = false order by nome");
		
		q.setParameter("name", nome);
		q.setParameter("name", "%" + nome.toUpperCase() + "%");
		List<Entidade> ents = q.getResultList();
		em.close();
		return ents;
		
	}
	
	
	public List<Entidade> localizarAllAlfabetic(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Entidade u order by nome");
		List<Entidade> ents = q.getResultList();
		em.close();
		return ents;
		
	}
	
	
	
	
	public Entidade alterarEntidade(Entidade ent) {

		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update Entidade set endereco = ?, numero =?, complemento =?, bairro = ?, cep =?, email1 = ?, email2 = ?, email3 = ?, email4 = ?, telefone =?, id_cidade = ?, isAssociada =? where idEntidade =?");
		q.setParameter(1,ent.getEndereco());
		q.setParameter(2,ent.getNumero());
		q.setParameter(3,ent.getComplemento());
		q.setParameter(4,ent.getBairro());
		q.setParameter(5,ent.getCep());
		q.setParameter(6,ent.getEmail1());
		q.setParameter(7,ent.getEmail2());
		q.setParameter(8,ent.getEmail3());
		q.setParameter(9,ent.getEmail4());
		q.setParameter(10,ent.getTelefone());
		q.setParameter(11,ent.getCidade());
		q.setParameter(12,ent.isAssociada());
		q.setParameter(13, ent.getIdEntidade());
		
		q.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}

		return null;
	}
	

	public List<Entidade> buscarTodasAlphabetic() {
		em = factory.createEntityManager();
		Query q = em.createQuery("Select e from Entidade e order by e.nome");

		List<Entidade> ents = q.getResultList();
		em.close();
		return ents;
		
	}

	public LinkedList<Entidade> localizarUltimasCadastradas() {
		
		
		em = factory.createEntityManager();
		LinkedList<Entidade>  lista = new LinkedList<Entidade> ();
		List<Entidade>  banco = new ArrayList<Entidade> ();
		Query q = em.createQuery("Select c from Entidade c order by idEntidade desc");
		q.setMaxResults(5);
		banco = q.getResultList();
		
		for (Entidade Entidade : banco) {
			lista.add(Entidade);
		}
		return lista;
	}
	

}
