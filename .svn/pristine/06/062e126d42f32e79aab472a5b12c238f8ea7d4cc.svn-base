package br.com.bibideais.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Fornecedor;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pavilhao;

public class PavilhaoDAO extends GenericDAO<Pavilhao> {

	
	@Inject
	EntityManager em = null;
	
	
	
	
	public List<Pavilhao> localizarPorNome(String nome){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Pavilhao u where u.nome like :name order by u.nome");
		
		
		q.setParameter("name", "%" + nome.toUpperCase() + "%");
		
		List<Pavilhao> pavs = q.getResultList();
		em.close();
		return pavs;
	}
	
	public boolean localizarNomeExato(String nome){
		em = factory.createEntityManager();
		boolean achou = false;
		
		
		
		try{
			Query q = em.createQuery("Select u from Pavilhao u where u.nome like :name order by u.nome");
			q.setParameter("name", nome.toUpperCase());
			
			Pavilhao pa = (Pavilhao) q.getSingleResult();
			achou = true;
		}catch (NoResultException e){
			//e.printStackTrace();
			achou = false;
		}finally{
			em.close();
		}
		
		
		
		return achou;
	}
	


	
	public Pavilhao alterarPavilhao(Pavilhao pav){
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update Pavilhao set endereco = ?,numero =?, complemento =?, bairro = ?, cep =?, email = ?, telefone =?, nomeContato = ?, site = ?, cargo =?, id_cidade = ? where idPavilhao =?");
		q.setParameter(1,pav.getEndereco());
		q.setParameter(2,pav.getNumero());
		q.setParameter(3,pav.getComplemento());
		q.setParameter(4,pav.getBairro());
		q.setParameter(5,pav.getCep());
		q.setParameter(6,pav.getEmail());
		q.setParameter(7,pav.getTelefone());
		q.setParameter(8,pav.getNomeContato());
		q.setParameter(9,pav.getSite());
		q.setParameter(10,pav.getCargo());
		q.setParameter(11,pav.getCidade().getId());
		q.setParameter(12,pav.getIdPavilhao());
		
		q.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		
		return pav;
	}
	
	

	public LinkedList<Pavilhao> buscarUltimosCadastrados(){
		em = factory.createEntityManager();
		LinkedList<Pavilhao> lista = new LinkedList<Pavilhao>();
		List<Pavilhao> banco = new ArrayList<Pavilhao>();
		Query q = em.createQuery("Select c from Pavilhao c order by idPavilhao desc");
		q.setMaxResults(5);
		banco = q.getResultList();
		
		for (Pavilhao p : banco) {
			lista.add(p);
		}
		return lista;
	
	}
	
	
	
	public List<Pavilhao> buscarTodosAlphabetic() {
		em = factory.createEntityManager();
		Query q = em.createQuery("Select p from Pavilhao p order by p.nome");

		List<Pavilhao> pavs = q.getResultList();
		em.close();
		return pavs;
		
	}
	
	
	
	
}
