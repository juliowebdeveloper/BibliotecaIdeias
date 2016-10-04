package br.com.bibideais.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public abstract class GenericDAO <T> {

	static EntityManagerFactory factory = null;
	private Class<T> classe;

	static {
		try {
			
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("bibideias");			
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	
	
	  /*************************/
	
	  
	  
	@SuppressWarnings("unchecked")
	public GenericDAO(){

		Class<?> thisClass = getClass();
		System.out.println(thisClass);
		ParameterizedType t = (ParameterizedType) thisClass.getGenericSuperclass();
		Type t2 = t.getActualTypeArguments()[0];
		System.out.println(t2);
		this.classe = (Class<T>) t2;

	}

	public T localizar(int id) {

		EntityManager em = factory.createEntityManager();
		
		
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.find(classe, id);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

		return obj;

	}

	
	
	public T localizarlong(long id) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.find(classe, id);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

		return obj;

	}
	
	
	
	/*Esses metodos utilizam o getReference para trazer um proxy do objeto. 
	 * Utiliza-lo quando precisar setar algo dentro de uma entidade B que estiver dentro da entidade A 
	 * Caso contrario favor utilizar o find dos outros metodos
	 * Em caso de 2 sessões abertas exception, um sysout no metodo que chamar esse resolverá o problema 
	 * Breve explicação:
	 * JPA has the concept of an EntityManager, as you know. During your work in the entity manager
	 *  some objects are loaded from the database, can be modified and afterwards flushed to the database.
find() has to return an initialized instance of your object. If it is not already loaded in the EntityManager,
 it is retrieved from the database.
getReference() is allowed to return a proxy instread of an initialized instance, 
if the entity has not been loaded in the EntityManager before. In this proxy, only the primary key 
attribute is initialized. Proxies can be created without hitting the database, because the
 only initialize attribute is already given to the getReference() function.
The latter is useful where you have an entity A referencing an entity B, and you 
want to set the b-attribute of A to B, without having to load B from the database.
Only if you reference other attributes of B, the proxy will be initialized.*/
	
	public T findByLazy(long id){
		
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.getReference(classe, id);

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

		return obj;
	}
	
	public T findByLazyInteger(int id){
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.getReference(classe, id);

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

		return obj;
	}
	
	
	
	
	/*Muita atenção ao usar esse método, a connection permanecerá aberta, então você deverá fecha-la no proximo método.*/
	public T localizarNoClose(int id) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.find(classe, id);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			
		}

		return obj;

	}
	
	
	/*Muita atenção ao usar esse método, a connection permanecerá aberta, então você deverá fecha-la no proximo método.*/
	public T localizarLongNoClose(long id) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.find(classe, id);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			
		}

		return obj;

	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<T> localizarAll() {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		List<T> list = null;

		try {

			t.begin();
			list = (List<T>) em.createQuery("from " + classe.getSimpleName()).getResultList();
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

		return list;

	}
	


	public T inserir(T obj) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {

			t.begin();
			em.persist(obj);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();

		}

		return obj;
	}

	
	
	
	
	public void excluir(int id) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.find(classe, id);
			em.remove(obj);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

	}
	public void excluirLong(long id) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		T obj = null;

		try {

			t.begin();
			obj = em.find(classe, id);
			em.remove(obj);
			t.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

	}
	
	
	
	
	
	
	
	
	//Configurações do CDI*****************//
	/*	  @Produces
		  @RequestScoped
		   public EntityManager criaEntityManager() {
		        System.out.println("EntityManager criado");  

		      return factory.createEntityManager();
		   }
		
		  public void finaliza(@Disposes EntityManager manager) {
		      manager.close();
		   }
		  
		  */
		
		//private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("coverbands");

	/*	@Produces
		@ApplicationScoped
		public EntityManagerFactory criaFactory(){
	        System.out.println("EntityManagerFFFF criado");  

			return Persistence.createEntityManagerFactory("coverbands");
		}
		
		//Configurações do CDI*****************/
		 /* @Produces
		  @RequestScoped
		   public EntityManager criaEntityManager(EntityManagerFactory factory) {
		        System.out.println("EntityManager criado");  

		      return factory.createEntityManager();
		   }
		
		  public void finaliza(@Disposes EntityManager manager) {
		      manager.close();
		   }
		*/

}

