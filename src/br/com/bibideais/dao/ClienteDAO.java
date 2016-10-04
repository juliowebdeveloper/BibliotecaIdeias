package br.com.bibideais.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.axis.NoEndPointException;

import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ClienteFeira;
import br.com.bibideais.entity.ClienteFeiraID;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Funcionario;

public class ClienteDAO extends GenericDAO<Cliente> implements Serializable {


	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em = null;
	
	
	
	//Relativo a feiras e clientes
	//Esse monta o objeto ClienteFeiraID
	public boolean relacionarClienteFeira(Cliente cliente, Feira feira){
		ClienteFeiraID id = new ClienteFeiraID();
		id.setIdCliente(cliente);
		id.setIdFeira(feira);
		ClienteFeira cfeira = new ClienteFeira();
		cfeira.setId(id);
		
		em = factory.createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(cfeira);
			em.getTransaction().commit();
		
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			em.close();
		}
		
	}
	//Esse recebe o objeto pronto
	public boolean relacionarClienteFeira(ClienteFeira cf){
		em = factory.createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(cf);
			em.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			em.close();
		}
		
		
		
	}
	
	
	
	public BigInteger countCliente(){
		em = factory.createEntityManager();
		Query q = em.createNativeQuery("Select Count(idCliente) from Cliente");
		BigInteger b = (BigInteger) q.getSingleResult();
		em.close();
		return b;

	}
	

//******************************Updates *****************************************************************************************//



		public Cliente updateCliente(Cliente cliente) {
			em = factory.createEntityManager();
			Cliente c = null;
			try{
				em.getTransaction().begin();
				c = em.merge(cliente);
				em.getTransaction().commit();

			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();


			}finally{
				em.close();
			}

			return c;
		}
	
		
		public void atualizarAnotacoes(Cliente c, String anot){
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
			try{
				Query q = em.createNativeQuery("Update Cliente set anotacoes =? where idCliente =?");
				q.setParameter(1, anot);
				q.setParameter(2, c.getIdCliente());
				q.executeUpdate();
				em.getTransaction().commit();
				
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
			}finally{
				em.close();
			}
		}
		
		public Cliente atualizarCliente(Cliente cliente){
			em = factory.createEntityManager();
			em.getTransaction().begin();
			try{
				
				em.merge(cliente);
				em.getTransaction().commit();
				

			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();


			}finally{
				em.close();
			}

			return cliente;
		}
		
	
		public Cliente atualizarCidade(Cliente c, Cidade cid){
			em = factory.createEntityManager();
			em.getTransaction().begin();
			try{
			Query q = em.createNativeQuery("Update Cliente  set id_cidade =? where idCliente = ?");
			q.setParameter(1, cid.getId());
			q.setParameter(2, c.getIdCliente());
			
			q.executeUpdate();
			
			em.getTransaction().commit();
			
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
				
			}finally{
				em.close();
			}
			
			return c;
		}
		
		


		public Cliente atualizarFotoCartao(Cliente c) {
			em = factory.createEntityManager();
			
			try{
				
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Update Cliente  set urlCartao =? where idCliente =?");
			q.setParameter(1, c.getUrlCartao());
			q.setParameter(2, c.getIdCliente());
			
			q.executeUpdate();
			
			em.getTransaction().commit();
			
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
				
			}finally{
				em.close();
			}
			
			return c;
			
		}
		
		public void alterarRecebeuTrabalhos(Cliente c) {
			em = factory.createEntityManager();
			try{
			em.getTransaction().begin();
			
			Query q = em.createNativeQuery("Update Cliente set recebeuTrabalhos = true where idCliente =?");
			
			q.setParameter(1, c.getIdCliente());
			
			q.executeUpdate();
			
			em.getTransaction().commit();
			
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
				
			}finally{
				em.close();
			}
			
			
			
		}
		
		public void alterarIsClient(Cliente c) {
			em = factory.createEntityManager();
			try{
			em.getTransaction().begin();
			
			Query q = em.createNativeQuery("Update Cliente  set isClient = true where idCliente =?");
			
			q.setParameter(1, c.getIdCliente());
			
			q.executeUpdate();
			
			em.getTransaction().commit();
			
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
				
			}finally{
				em.close();
			}
			
			
			
		}
		
		public void alterarAtendimento(Cliente c, Funcionario func){
			em = factory.createEntityManager();
			try{
				em.getTransaction().begin();

				Query q = em.createNativeQuery("Update Cliente  set id_funcatendimento =? where idCliente =?");

				q.setParameter(1, func.getId());
				q.setParameter(2, c.getIdCliente());


				q.executeUpdate();

				em.getTransaction().commit();

			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();


			}finally{
				em.close();
			}

		}
		
		
		
		//****************************************** Buscas *****************************************************//
		

		
		public List<Cliente> localizarPorNomeFantasia(String nomeFantasia){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.nomeFantasia like :name order by u.razaoSocial");
			
			q.setParameter("name", nomeFantasia);
			q.setParameter("name", "%" + nomeFantasia.toUpperCase() + "%");
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		
		
		
		
		public List<Cliente> localizarPorRazaoSocial(String razaoSocial){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.razaoSocial like :name order by u.razaoSocial");
			
			q.setParameter("name", "%" + razaoSocial.toUpperCase() + "%");
		
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		public boolean localizarPorRazaoSocialExata(String razaoSocial){
			em = factory.createEntityManager();
			boolean achou = false;
			try{
				
			
			Query q = em.createQuery("Select u from Cliente u where u.razaoSocial =:name");
			
			q.setParameter("name", razaoSocial.toUpperCase());
			q.setMaxResults(1);
			achou = true;
			Cliente c = (Cliente) q.getSingleResult();
			}catch(NoResultException e ){
				achou = false;
			}finally{
				em.close();
			}
			
			return achou;
		}
		
		
		
		//Metodos do Segmento principal
		
		public List<Cliente> buscarPorSegmentoPrincipal(String segmento){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoPrincipal like :segmento order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmento.toUpperCase() + "%");
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		
		}
		
		public List<Cliente> buscarPorSegmentoAndIsClient(String segmento){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmento like :segmento and u.isCliente = true order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmento.toUpperCase() + "%");

			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		public List<Cliente> buscarPorSegmentoAndNotClient(String segmento){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmento like :segmento and u.isClient = false order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmento.toUpperCase() + "%");
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		
		//Metodos do Segmento secundario
		

		public List<Cliente> buscarPorSegmentoSecundario(String segmentoSecundario){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoSecundario like :segmento order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmentoSecundario.toUpperCase() + "%");
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		public List<Cliente> buscarPorSegmentoSecundarioAndIsClient(String segmentoSecundario){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoSecundario like :segmento and u.isCliente = true order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmentoSecundario.toUpperCase() + "%");
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		public List<Cliente> buscarPorSegmentoSecundarioAndNotClient(String segmentoSecundario){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoSecundario like :segmento and u.isClient = false order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmentoSecundario.toUpperCase() + "%");
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		
		//Metodos do Segmento Terciario
		

		public List<Cliente> buscarPorSegmentoTerciario(String segmentoTerciario){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoTerciario like :segmento order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmentoTerciario.toUpperCase() + "%");
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		public List<Cliente> buscarPorSegmentoTerciarioAndIsClient(String segmentoTerciario){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoTerciario like :segmento and u.isCliente = true order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmentoTerciario.toUpperCase() + "%");
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		public List<Cliente> buscarPorSegmentoTerciarioAndNotClient(String segmentoTerciario){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.segmentoTerciario like :segmento and u.isClient = false order by u.razaoSocial");
			
			q.setParameter("segmento", "%" + segmentoTerciario.toUpperCase() + "%");
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		//Metodos para buscar por categoria do cliente
		
		public List<Cliente> buscarPorCategoria(String categoria){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.categoria like :categoria order by u.razaoSocial");
			
			q.setParameter("categoria", "%" + categoria.toUpperCase() + "%");
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		
		//Metodos para buscar por categoria do cliente
		
		public List<Cliente> buscarPorCategoriaAndIsClient(String categoria){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where u.categoria like :categoria and u.isClient = true order by u.razaoSocial");
			
			q.setParameter("categoria", "%" + categoria.toUpperCase() + "%");
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		//Buscar todos que são clientes e que não são
		public List<Cliente> buscarAll(){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u order by u.razaoSocial");

			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		public List<Cliente> buscarTodosClientes(){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where  u.isClient = true order by u.razaoSocial");
			
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		public List<Cliente> buscarNotClientes(){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente u where  u.isClient = false order u.razaoSocial");
			
			
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		
		
		
		//Buscar por cidade
		
		public List<Cliente> buscarPorCidade(Cidade cidade){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Cliente where  u.Cidade =:cidade order by u.razaoSocial");
			q.setParameter("cidade", cidade);
			
			em.close();
			
			List<Cliente> clients = q.getResultList();
			em.close();
			return clients;
		}
		

		
		//Buscar Clientes daquela feira
		public List<Cliente> buscarPorFeiras(Feira feira){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select c from ClienteFeira c where c.id.idFeira =:feira");
			q.setParameter("feira", feira);
			List<ClienteFeira>clientes = q.getResultList();
			List<Cliente> clis = new ArrayList<Cliente>();
			
			for (ClienteFeira clienteFeira : clientes) {
				clis.add(clienteFeira.getId().getIdCliente());
			
			}
			Collections.sort(clis);
			
			em.close();
			return clis;
			
		}
		
		//Buscar Clientes daquela feira e cliente BI
		public List<Cliente> buscarPorFeirasAndIsClient(Feira feira){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select c from ClienteFeira c where c.id.idFeira =:feira");
			q.setParameter("feira", feira);
			List<ClienteFeira>clientes = q.getResultList();
			List<Cliente> clis = new ArrayList<Cliente>();
			
			for (ClienteFeira clienteFeira : clientes) {
				clis.add(clienteFeira.getId().getIdCliente());
			
			}
			Collections.sort(clis);
			
			em.close();
			return clis;
			
		}
		
		
		
		//Buscar Feiras daquele cliente
			public List<Feira> buscarFeirasDoCliente(Cliente cliente){
				em = factory.createEntityManager();
				Query q = em.createQuery("Select c from ClienteFeira c where c.id.idCliente =:cliente order by u.razaoSocial");
				q.setParameter("cliente", cliente);
				List<ClienteFeira>clienteFeiras = q.getResultList();
				List<Feira> feiras = new ArrayList<Feira>();
				for (ClienteFeira cf: clienteFeiras) {
					feiras.add(cf.getId().getIdFeira());
				}
				em.close();
				return feiras;
				
			}

		
		
		
	//Parametros que serão usados para buscar :
	//razaosocial, Categoria, Segmento principal, Anos que recebeu trabalhos, Feiras, 	
	public List<Cliente> buscarPorFeiraAndAnos(Feira f, List<Ano> anos, boolean recebeu){
		em = factory.createEntityManager();
		
		List<Cliente> listaFinal = new ArrayList<Cliente>();
		
		//primeiro busca os clientes daquela feira
		List<Cliente> clientesfeira = this.buscarPelaFeira(f);
		
		List<Cliente> clientesAno = new ArrayList<Cliente>();
		
		//Nessa lista, ve quais receberam trabalhos nesses anos que o usuário escolheu, apenas se recebeu veio como true:
		if(!clientesfeira.isEmpty()){
			if(recebeu){
				clientesAno = this.verificarClientesPorAno(clientesfeira, anos);
			
				listaFinal = clientesAno;
				
				//se "recebeu" veio como false,serão feitas as comparações apenas de isClient nome, categoria, segmento 
			}else{
				
				listaFinal = clientesfeira;
				
				
			}
			
			//Se a lista vier vazia já diz que não tem nenhum cliente naquela feira
		}else{
			listaFinal = null;
		}
		
		
		
		return listaFinal;
	}
	
	
	
	
	//Primeiro faz as buscas com Like nos parametros de nome
	public List<Cliente> searchStringParameters(HashMap<String, String> parametros, boolean isClient){
		em = factory.createEntityManager();
			
		int numerosand = 1; // começa com 1 and pelo isClient

		em = factory.createEntityManager();
		numerosand = numerosand + (parametros.size() - 1);

		Query q = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		if (parametros.size() > 3) {
		
			// Busca com todos os segmentos

						q = em.createQuery("Select c from Cliente c where c.razaoSocial like :razao and c.nomeFantasia like:fantasia and c.categoria like:categoria and c.segmentoPrincipal like :segprincipal and c.isClient =:isclient");
						q.setParameter("isclient", isClient);

						for (Map.Entry<String, String> entrada : parametros.entrySet()) {
							if (entrada.getKey().equalsIgnoreCase("razao")) {

								q.setParameter("razao", "%"
										+ entrada.getValue().toUpperCase() + "%");

							}
							
							if (entrada.getKey().equalsIgnoreCase("fantasia")) {

								q.setParameter("fantasia", "%"
										+ entrada.getValue().toUpperCase() + "%");

							}
							
							if (entrada.getKey().equalsIgnoreCase("categoria")) {

								q.setParameter("categoria", "%"
										+ entrada.getValue().toUpperCase() + "%");

							}

							if (entrada.getKey().equalsIgnoreCase("segprincipal")) {

								q.setParameter("segprincipal", "%"
										+ entrada.getValue().toUpperCase() + "%");

							}

						}
						
					} else {
						boolean razao = false;
						String razaosocial = null;
						boolean fantasia = false;
						String nomefantasia = null;
						boolean categ = false;
						String categoria = null;
						boolean segprin = false;
						String segprincipal = null;

						for (Map.Entry<String, String> entrada : parametros.entrySet()) {
							if (entrada.getKey().equalsIgnoreCase("razao")) {
								razao = true;
								razaosocial = entrada.getValue();
								
							}else if (entrada.getKey().equalsIgnoreCase("fantasia")){
								fantasia = true;
								nomefantasia = entrada.getValue();
							} else if (entrada.getKey().equalsIgnoreCase("categoria")) {

								categ = true;
								categoria = entrada.getValue();
							} else if (entrada.getKey().equalsIgnoreCase("segprincipal")) {
								segprin = true;
								segprincipal = entrada.getValue();
							}

						}

						// Checar quais parametros existem e quais não para montar as queries
						String query;
						query = "Select c from Cliente c where c.isClient =:isclient ";
						if (razao == true) {
							query = query + " and c.razaoSocial like :razao ";

						}
						
						if(fantasia == true){
							query = query + " and c.nomeFantasia like :fantasia ";
						}
						
						if (categ == true) {
							query = query
									+ " and c.categoria like :categoria ";
						}
						if (segprin == true) {
							query = query + " and c.segmentoPrincipal like :segprincipal ";
						}

						q = em.createQuery(query);
						q.setParameter("isclient", isClient);
						System.out.println(query);	
						// Setando os parametros

						if (razao == true) {
							q.setParameter("razao", "%" + razaosocial + "%");

						}
						
						
						
						if(fantasia ==true){
							q.setParameter("fantasia","%" + nomefantasia + "%");
						}
						
						
						if (categ == true) {
							q.setParameter("categoria","%" + categoria + "%");
						}
						if (segprin == true) {
							q.setParameter("segprincipal","%" + segprincipal + "%");

						}
							
					}
						
					clientes = q.getResultList();

					em.close();
					return clientes;
		
	}
	
	
	
	//Recebe todos os parametros, executa os dois metodos e compara as listas, retornando uma final
	public List<Cliente> completeFilteredSearch(HashMap<String, String> parametros, Feira f, List<Ano> anos, boolean recebeu, boolean isClient){
		List<Cliente> clientesString =  this.searchStringParameters(parametros, isClient);
		List<Cliente> clientesFeira = this.buscarPorFeiraAndAnos(f, anos, recebeu);
		List<Cliente> clientesFinal = new ArrayList<Cliente>();
		
		if(!clientesString.isEmpty() && !clientesFeira.isEmpty()){
			//Se não estiverem vazias faz a comparação.
			
			for (Cliente cliente : clientesFeira) {
				for (Cliente cliente1 : clientesFeira) {
					if(cliente1.getRazaoSocial().equals(cliente.getRazaoSocial())){
						clientesFinal.add(cliente);
					}
				}
			}
		}
		
		
		return clientesFinal;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Cliente> verificarClientesPorAno(List<Cliente> clientes, List<Ano> anos){
		
		
		List<Cliente> clientesAno = new ArrayList<Cliente>();
		List<Cliente> clientesClean = new ArrayList<Cliente>();
		
		for(Cliente c : clientes){
		//Cliente c1 = this.findByLazyInteger(c.getIdCliente());
		
		for (Ano ano : anos) {
			for (Ano ano1 : c.getAnostrabalhos()) {
				if(ano1.getAno() == ano.getAno()){
					//Significa que recebeu trabalho naquele ano
					
					clientesAno.add(c);
					}
					
					
				}
			}
		}
		
		
		for(int i = 0;  i < clientesAno.size(); i++){
			if(clientesClean.isEmpty()){
				clientesClean.add(clientesAno.get(i));
			}else{
				int count = 0;
				for (Cliente cliente : clientesClean) {
					if(clientesAno.get(i).getIdCliente() == cliente.getIdCliente()){
						count++;
					}
				}
				
				if(count == 0){
					clientesClean.add(clientesAno.get(i));
				}
			}
			
			
			
		}
		
		
		
		return clientesClean;
	}
	
	
	
	
	public List<Cliente> buscarPelaFeira(Feira f){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from ClienteFeira c where c.id.idFeira =:feira");
		q.setParameter("feira", f);
		List<ClienteFeira> cfeiras = q.getResultList();
		em.close();
		List<Cliente> clis = new ArrayList<Cliente>();
		for (ClienteFeira clienteFeira : cfeiras) {
			clis.add(clienteFeira.getId().getIdCliente());
		}
		
		return clis;
		
		
	}
	
	public List<Cliente> buscarClientesPorAno(Ano ano){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Ano a = em.find(Ano.class, ano.getIdAno());
		return a.getClientes();
	}
	
	
	
	
	
	// Se o usuário preencher só 1 dos segmentos, chama os metodos especificos deles
	public List<Cliente> buscarPorSegmentos(HashMap<String, String> segmentos,boolean isClient) {

		int numerosand = 1; // começa com 1 and pelo isClient

		em = factory.createEntityManager();
		numerosand = numerosand + (segmentos.size() - 1);

		Query q = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		if (segmentos.size() > 2) {

			// Busca com todos os segmentos

			q = em.createQuery("Select c from Cliente c where c.segmentoPrincipal like :principal and c.segmentoSecundario like:secundario and c.segmentoTerciario like :terciario and c.isClient =:isclient");
			q.setParameter("isclient", isClient);

			for (Map.Entry<String, String> entrada : segmentos.entrySet()) {
				if (entrada.getKey().equalsIgnoreCase("segprincipal")) {

					q.setParameter("principal", "%"
							+ entrada.getValue().toUpperCase() + "%");

				}
				if (entrada.getKey().equalsIgnoreCase("segsecundario")) {

					q.setParameter("secundario", "%"
							+ entrada.getValue().toUpperCase() + "%");

				}

				if (entrada.getKey().equalsIgnoreCase("segterciario")) {

					q.setParameter("terciario", "%"
							+ entrada.getValue().toUpperCase() + "%");

				}

			}
		} else {
			boolean segprin = false;
			String segprincipal = null;
			boolean segSec = false;
			String segSecundario = null;
			boolean segTer = false;
			String segTerciario = null;

			for (Map.Entry<String, String> entrada : segmentos.entrySet()) {
				if (entrada.getKey().equalsIgnoreCase("segprincipal")) {
					segprin = true;
					segprincipal = entrada.getValue();
				} else if (entrada.getKey().equalsIgnoreCase("segsecundario")) {

					segSec = true;
					segSecundario = entrada.getValue();
				} else if (entrada.getKey().equalsIgnoreCase("segterciario")) {
					segTer = true;
					segTerciario = entrada.getValue();
				}

			}

			// Checar quais parametros existem e quais não para montar as queries
			String query;
			query = "Select c from Cliente c where c.isClient =:isclient ";
			if (segprin == true) {
				query = query + " and c.segmentoPrincipal like :segprincipal ";

			}
			if (segSec == true) {
				query = query
						+ " and c.segmentoSecundario like :segsecundario ";
			}
			if (segTer == true) {
				query = query + " and c.segmentoTerciario like :segterciario ";
			}

			q = em.createQuery(query);
			q.setParameter("isclient", isClient);
			

			// Setando os parametros

			if (segprin == true) {
				q.setParameter("segprincipal", segprincipal);

			}
			if (segSec == true) {
				q.setParameter("segsecundario", segSecundario);
			}
			if (segTer == true) {
				q.setParameter("segterciario", segTerciario);

			}

		}

		clientes = q.getResultList();

		em.close();
		return clientes;

	}




	

	
	
	
	public List<Cliente> buscarPorAtendimento(Funcionario atendimento){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select c from Cliente c where c.atendimento =:atendimento");
		q.setParameter("atendimento", atendimento);
		List<Cliente> clientes = q.getResultList();
		return clientes;
	}
	

	public List<Cliente> buscarUltimosCadastrados(){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select c from Cliente c order by dataCadastro desc");
		q.setMaxResults(5);
		List<Cliente> clientes = q.getResultList();
		return clientes;
	}
	
	
	public List<Cliente> buscarPorNomeFantasia(String nomeFantasia) {
		em = factory.createEntityManager();
		Query q = em.createQuery("Select c from Cliente c where c.nomeFantasia like :nomeFantasia  order by c.razaoSocial");
		q.setParameter("nomeFantasia", "%"+ nomeFantasia + "%");
		List<Cliente> clientes = q.getResultList();
		return clientes;
	}



	
	
}
