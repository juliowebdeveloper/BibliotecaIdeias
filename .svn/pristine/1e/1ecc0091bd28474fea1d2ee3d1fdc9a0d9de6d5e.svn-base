package br.com.bibideais.dao;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Organizadora;

public class OrganizadoraDAO extends GenericDAO<Organizadora> {

	@Inject
	EntityManager em = null;
	
	
	
	
	public List<Organizadora> localizarPorNomeFantasia(String nomeFantasia){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Organizadora u where u.nomeFantasia like :name u.nomeFantasia");
		
		q.setParameter("name", nomeFantasia);
		q.setParameter("name", "%" + nomeFantasia.toUpperCase() + "%");
		q.setMaxResults(100);
		
		List<Organizadora> orgs = q.getResultList();
		em.close();
		return orgs;
		
	}
	
	
	public List<Organizadora> localizarPorRazaoSocial(String razaoSocial){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Organizadora u where u.razaoSocial like :name order by u.razaoSocial");
		
		q.setParameter("name", "%" + razaoSocial.toUpperCase() + "%");
		q.setMaxResults(100);
		
		List<Organizadora> orgs = q.getResultList();
		em.close();
		return orgs;
	}
	
	public BigInteger countOrganizadoras(){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select Count(idOrganizadora) from Organizadora");
		BigInteger b = (BigInteger) q.getSingleResult();
		em.close();
		return b;

	}
	
	public Organizadora buscarPorFeira(Feira f){
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Select * from Organizadora where idOrganizadora =? ");
		q.setParameter(1, f.getOrganizadora().getIdOrganizadora());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public Organizadora alterarOrganizadora(Organizadora org){
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update Organizadora set nomeFantasia =?, endereco = ?,numero =?, complemento =?, cep =?, bairro = ?,telefone =?, skype =?, celular = ?, nomeContato = ?,  cargo =?, email = ?, id_cidade = ?, razaoSocial =? where idOrganizadora =?");
		q.setParameter(1,org.getNomeFantasia());
		q.setParameter(2,org.getEndereco());
		q.setParameter(3,org.getNumero());
		q.setParameter(4,org.getComplemento());
		q.setParameter(5,org.getCep());
		q.setParameter(6,org.getBairro());
		q.setParameter(7,org.getTelefone());
		q.setParameter(8,org.getSkype());
		q.setParameter(9,org.getCelular());
		q.setParameter(10,org.getNomeContato());
		q.setParameter(11,org.getCargo());
		q.setParameter(12,org.getEmail());
		q.setParameter(13, org.getCidade().getId());
		q.setParameter(14, org.getRazaoSocial());
		q.setParameter(15, org.getIdOrganizadora());

		q.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		
		return org;
	}


	public Organizadora alterarLogo(Organizadora org) {
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update Organizadora set logourl =? where idOrganizadora =?");
		q.setParameter(1,org.getLogourl());
		q.setParameter(2,org.getIdOrganizadora());
		q.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();


		}finally{
			em.close();
		}
		
		return org;
	}
	
	
	public List<Organizadora> buscarTodasAlfabetic() {
		em = factory.createEntityManager();
		Query q = em.createQuery("Select o from Organizadora o order by o.razaoSocial");

		List<Organizadora> orgs = q.getResultList();
		em.close();
		return orgs;
	}


	public List<Organizadora> buscarUltimasCadastradas() {
		em = factory.createEntityManager();
		Query q = em.createQuery("Select o from Organizadora o order by o.idOrganizadora desc");
		q.setMaxResults(5);
		List<Organizadora> orgs = q.getResultList();
		em.close();
		return orgs;
	}
	
	
}
