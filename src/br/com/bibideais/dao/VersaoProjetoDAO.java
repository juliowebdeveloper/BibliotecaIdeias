package br.com.bibideais.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;

public class VersaoProjetoDAO extends GenericDAO<VersaoProjeto> implements Serializable {


	private static final long serialVersionUID = 1L;
	EntityManager em = null;
	
	

	
	
	public List<VersaoProjeto> buscarPeloProjeto(Projeto proj){
		em = factory.createEntityManager();
		List<VersaoProjeto> versoes =  new ArrayList<VersaoProjeto>();
		Query q = em.createQuery("Select v from VersaoProjeto v where v.projeto =:proj");
		q.setParameter("proj", proj);
		versoes = q.getResultList();
		em.close();
		return versoes;
	}
	
	public void excluirVersao(VersaoProjeto v){
		super.excluirLong(v.getIdVersaoProjeto());
	}

	public VersaoProjeto buscarPeloNomeEProjeto(Projeto proj, String nome) {
		em = factory.createEntityManager();
		VersaoProjeto versao =  new VersaoProjeto();
		Query q = em.createQuery("Select v from VersaoProjeto v where v.projeto =:proj and v.versao =:nome");
		q.setParameter("proj", proj);
		q.setParameter("nome", nome);
		
		q.setMaxResults(1);
		versao = (VersaoProjeto) q.getSingleResult();
		
		return versao;
		
	}
	
	public void atualizarNomeVersao(VersaoProjeto v, String nome){
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			Query q = em.createQuery("Update VersaoProjeto set versao =? where idVersaoProjeto =?");
			q.setParameter(1,nome);
			q.setParameter(2, v.getIdVersaoProjeto());
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		
	}
	
	
}
