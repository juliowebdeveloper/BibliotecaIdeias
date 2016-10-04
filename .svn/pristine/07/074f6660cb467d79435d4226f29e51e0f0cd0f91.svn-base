package br.com.bibideais.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.exception.DataException;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ClienteFeira;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.service.OrganizadoraBO;
import br.com.bibideais.service.PavilhaoBO;

public class FeiraDAO extends GenericDAO<Feira> implements Serializable{

	

	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em = null;
	
	public BigInteger countFeiras(){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select Count(idFeira) from Feira");
		BigInteger b = (BigInteger) q.getSingleResult();
		em.close();
		return b;

	}
	
	public Feira atualizarLogo(Feira f){
		
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Update Feira set urlLogo =? where idFeira =?");
			q.setParameter(1,f.getUrlLogo());
			q.setParameter(2,f.getIdFeira());
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
	
	public Feira atualizarMapa(Feira f){
		
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update Feira set urlMapa =? where idFeira =?");
		q.setParameter(1,f.getUrlMapa());
		q.setParameter(2,f.getIdFeira());
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
	
	
	public Feira updateFeira(Feira feira){
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		em.merge(feira);
		em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
		
		
		return feira;
	}
	
	public Feira atualizarManual(Feira f){
		
		em = factory.createEntityManager();
		try{
		em.getTransaction().begin();
		Query q = em.createNativeQuery("Update Feira set urlManual =? where idFeira =?");
		q.setParameter(1,f.getUrlManual());
		q.setParameter(2,f.getIdFeira());
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
	
	
	public Feira alterarFeira(Feira f){
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery
			("Update Feira set ano =?, periodo = ?, edicao =?, frequencia =?, expositoresNac =?, expositoresInt = ?, totalExpositores =?, numeroVisitantes =?, site = ?, telefone = ?,  email =?, metroConstruido = ?, biConstruiu = ?, anotacoes =?, nomeFeira =? where idFeira =?");
			q.setParameter(1,f.getAno());
			q.setParameter(2,f.getPeriodo());
			q.setParameter(3,f.getEdicao());
			q.setParameter(4,f.getFrequencia());
			q.setParameter(5,f.getExpositoresNac());
			q.setParameter(6,f.getExpositoresInt());
			q.setParameter(7,f.getTotalExpositores());
			q.setParameter(8,f.getNumeroVisitantes());
			q.setParameter(9,f.getSite());
			q.setParameter(10,f.getTelefone());
			q.setParameter(11,f.getEmail());
			q.setParameter(12,f.getMetroConstruido());
			q.setParameter(13, f.isBiConstruiu());
			q.setParameter(14, f.getAnotacoes());
			q.setParameter(15, f.getNomeFeira());
			q.setParameter(16, f.getIdFeira());

			q.executeUpdate();
			em.getTransaction().commit();
			
		}catch(Exception e){
			
			e.printStackTrace();
			em.getTransaction().rollback();
			
			em.close();
		}finally{
			em.close();
			
		}
		
		
		return f;
	}
	
	
	
	
	
	/********************** Buscas *****************************************************************/
	
	
	public List<Feira> filteredSearch(HashMap <String, String> parametros, boolean construiu){
		int numerosand = 1; //começa com 1 and pelo associated
		
		em = factory.createEntityManager();
		numerosand = numerosand + (parametros.size() -1);
		
		Query q = null;
		
		//Se for maior que 3, faz a busca com todos os parametros.
		if(parametros.size() > 3){
			q = em.createQuery("Select f from Feira f where f.nomeFeira like :nome and f.ano like :ano and f.organizadora =:organizadora and f.local =:local and f.biConstruiu =:construiu");
			q.setParameter("construiu", construiu);
			 for (Map.Entry<String, String> entrada : parametros.entrySet()) {  
				 if(entrada.getKey().equalsIgnoreCase("nome")){
						
					 q.setParameter("nome","%" + entrada.getValue().toUpperCase() + "%");

					  }
				 if(entrada.getKey().equalsIgnoreCase("ano")){
						
					 q.setParameter("ano",entrada.getValue().toUpperCase());

					  }
				 
				 if(entrada.getKey().equalsIgnoreCase("organizadora")){
						//Busca a organizadora que vem um id string no map
					 Organizadora o = new OrganizadoraBO().buscarPorCodigo(Integer.parseInt(entrada.getValue()));
					 q.setParameter("organizadora",o);

					  }
				 
				 if(entrada.getKey().equalsIgnoreCase("local")){
						//Busca o pavilhao que vem um id string no map
					 Pavilhao p = new PavilhaoBO().buscarPorCodigo(Integer.parseInt(entrada.getValue()));
					 q.setParameter("local",p);

					  }

				 
			 }
			
			 //Só vieram 3 parametros, analisar quais
			}else if(parametros.size() <= 3){
				boolean temnome = false;
				String nome = null;
				boolean temorg = false;
				String org = null;
				boolean temlocal = false;
				String local = null;
				boolean temano = false;
				String ano = null;
				
				
				for (Map.Entry<String, String> entrada : parametros.entrySet()) {
					 if(entrada.getKey().equalsIgnoreCase("nome")){
						 temnome = true;
						 nome = entrada.getValue();
					 }else if(entrada.getKey().equalsIgnoreCase("ano")){
					 
						 temano = true;
						 ano = entrada.getValue();
					 }else if(entrada.getKey().equalsIgnoreCase("organizadora")){
						 temorg = true;
						 org = entrada.getValue();
					 }else if(entrada.getKey().equalsIgnoreCase("local")){
						 temlocal = true;
						 local = entrada.getValue();
					 }
	
				}
				
				
				
				//Checar quais parametros existem e quais não para montar as queries
		

			String query;
			query = "Select f from Feira f where f.biConstruiu =:construiu ";
			if(temnome ==true){
				query = query + " and f.nomeFeira like :nome ";
				
			}
			if(temano == true){
				query = query + " and f.ano like :ano ";
			}
			if(temlocal == true){
				query = query + " and f.local =:local ";
			}
				
			if(temorg == true){
				query = query + " and f.organizadora =:organizadora ";
			}
			
				q = em.createQuery(query);
				q.setParameter("construiu", construiu);
				if(temnome ==true){
					q.setParameter("nome", "%" + nome.toUpperCase() + "%");
					
				}
				if(temano == true){
					q.setParameter("ano", ano);;
				}
				if(temlocal == true){
					//Busca o pavilhao que vem um id string no map
					 Pavilhao p = new PavilhaoBO().buscarPorCodigo(Integer.parseInt(local));
					 q.setParameter("local",p);
				}
					
				if(temorg == true){
					
					//Busca a organizadora que vem um id string no map
					 Organizadora o = new OrganizadoraBO().buscarPorCodigo(Integer.parseInt(org));
					q.setParameter("organizadora", o);
				
				}
			
			}
		
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
		
	}

	
	//Busca por locais, por biconstruiu, por nome, por ano, por organizadora
	public List<Feira> localizarPorNome(String nome){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Feira u where u.nomeFeira like :name order by u.nomeFeira");
		
		q.setParameter("name", "%" + nome.toUpperCase() + "%");
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	
	public List<Feira> localizarPorConstruiu(boolean construiu){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select u from Feira u where u.biConstruiu =:construiu order by u.nomeFeira");
		
		q.setParameter("construiu", construiu);
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	

	
	//Busca todas as feiras daquela organizadora
	public List<Feira> buscarPorOrganizadora(Organizadora org){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f where f.organizadora =:organizadora order by f.nomeFeira");
		q.setParameter("organizadora", org);
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	
	
	
	public List<Feira> buscarPorLocal(Pavilhao pav){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f where f.local =:local  order by f.nomeFeira");
		q.setParameter("local", pav);
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	
	public List<Feira> buscarPorBiConstruiu(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f where f.biConstruiu = true order by f.nomeFeira");
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	
	public List<Feira> buscarPorBiNaoConstruiu(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f where f.biConstruiu = false order by f.nomeFeira");
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	
	
	public List<Feira> buscarPorAno(String ano){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f where f.ano =:ano  order by f.nomeFeira");
		q.setParameter("ano", ano);
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	

	public List<ClienteFeira> buscarPorCliente(Cliente c){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q1 = em.createQuery("Select c from ClienteFeira c where c.id.idCliente =:cliente");
		q1.setParameter("cliente", c);
		
		 List<ClienteFeira> feiras = q1.getResultList();
		 em.close();
		 return feiras;
	}
	
	
	
	
	
	
	
	public List<Feira> buscarTodasAlpha(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f order by f.nomeFeira");
		 List<Feira> feiras = q.getResultList();
		 em.close();
		 return feiras;
	}
	
	
	
	public LinkedList<Feira> buscarUltimas(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select f from Feira f order by f.idFeira desc");
		q.setMaxResults(5);
		List<Feira> feiras = q.getResultList();
		LinkedList<Feira> lfeiras = new LinkedList<Feira>();
		for (Feira feira : feiras) {
			lfeiras.add(feira);
		}
		em.close();
		return lfeiras;
		
	}
	
	
	
	
	/**********************************Inserir*************************************************************/	


	
	
	public void relacionarFeiraAoCliente(Cliente cliente, List<Feira> feiras){
			em = factory.createEntityManager();
			Cliente c1 = em.find(Cliente.class, cliente.getIdCliente());
			
			
			
			try{
				em.getTransaction().begin();
				Query q1 = em.createNativeQuery("Delete from clientefeira where id_cliente =?");
				q1.setParameter(1, c1.getIdCliente());
				
				q1.executeUpdate();
				
				for (Feira f : feiras) {
					Query q2 = em.createNativeQuery("Insert into clientefeira (id_cliente, id_feira) values (?, ?)");
					q2.setParameter(1, c1.getIdCliente());
					q2.setParameter(2, f.getIdFeira());
					q2.executeUpdate();
				}
				
			
				
				em.getTransaction().commit();
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
			}finally{
				em.close();
			}
		
		
	}
	
	
	public void desrelacionarFeiraAoCliente(Cliente cliente, Feira feira){
		em = factory.createEntityManager();
		Cliente c1 = em.find(Cliente.class, cliente.getIdCliente());
		
		
		
		try{
			em.getTransaction().begin();
			Query q1 = em.createNativeQuery("Delete from clientefeira where id_cliente =? and id_feira =?");
			
			q1.setParameter(1, c1.getIdCliente());
			q1.setParameter(2, feira.getIdFeira());
			q1.executeUpdate();

			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
	
	
}
	
	
public Feira inserirFeira(Feira feira){
	Feira newfeira = null;
	try{
		newfeira = super.inserir(feira);
		
	}catch(Exception e){
		e.printStackTrace();
		feira = null;
		
	}
	
	return newfeira;
}
	
	
}
