package br.com.bibideais.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Pais;

public class CidadeDAO extends GenericDAO<Cidade> {

	@Inject
	EntityManager em ;
	
	
	public List<String> buscarNomesDistritos(String codigoPais){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select distrito from Cidade where codigoPais like ? order by distrito");
		q.setParameter(1,  codigoPais);
		return q.getResultList();
	}
	

	public int returnLastId(){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select c.id_cidade from Cidade c order by c.id_cidade desc");
		q.setMaxResults(1);
		int id = (Integer) q.getSingleResult();
		
		return id;
		
	}
	
	
	public List<Cidade> buscarPorCodigoPais(String codigoPais){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select c from Cidade c where c.codigoPais like :codigoPais order by c.nome");
		q.setParameter("codigoPais", codigoPais);
		List<Cidade> cidades = q.getResultList();
		em.close();
		return cidades;
	}
	
	
	
	public List<Cidade> buscarCidades(String codigoPais){
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("Select e from Cidade e where e.codigoPais like :codigo order by e.nome");
		q.setParameter("codigo", codigoPais);
		List<Cidade> cidades = q.getResultList();
		
		Collections.sort(cidades);
		
		for (Cidade cidade : cidades) {
			String cdlower = StringUtils.lowerCase(cidade.getNome());
			String city = StringUtils.capitaliseAllWords(cdlower);
			cidade.setNome(city);
		}
		em.close();
		return cidades;
		
	}
	
	public Cidade buscarPorNomeExato(String nome){
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("Select e from Cidade e where e.nome like :nome ");
		q.setParameter("nome", nome);
		Cidade c = (Cidade) q.getSingleResult();
		em.close();
		return c;
		
	}
	
	
	public Cidade buscarPorNomeExatoDistrito(String nome, String distrito){
		EntityManager em = factory.createEntityManager();
		Cidade c = new Cidade();

		try{
			Query q = em.createQuery("Select e from Cidade e where e.nome like :nome and e.distrito like :distrito");
			q.setParameter("nome", nome);
			q.setParameter("distrito", distrito);
			c = (Cidade) q.getSingleResult();

		}catch(NoResultException nr){
			c = null;
		}finally{
			em.close();

		}
	
		return c;
		
	}
	
	public List<Cidade> localizarPorNome(String nome){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Cidade u where u.nome like :name order by u.nome" );
		
		q.setParameter("name", nome);
		q.setParameter("name", "%" + nome.toUpperCase() + "%");
	
		List<Cidade> cidades = q.getResultList();
		em.close();
		return cidades;
	}
	
	
	
	
}
