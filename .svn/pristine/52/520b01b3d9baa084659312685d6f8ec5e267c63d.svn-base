package br.com.bibideais.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bibideais.entity.Authority;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.MessageUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Inject
	private EntityManager em = null; 
	
	
	public Funcionario inserirFuncAuthAdmin(Funcionario func){
		Funcionario func2 = super.inserir(func);
		em = factory.createEntityManager();
		
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Insert into func_auth (func_id, auth_authority) values (?,?)");
			q.setParameter(1, func2.getId());
			q.setParameter(2, "ROLE_ADMIN");
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
		
		
		return func2;
	}
	
	public Funcionario inserirFuncAuthFunc(Funcionario func){
		Funcionario func2 = super.inserir(func);
		em = factory.createEntityManager();
		
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Insert into func_auth (func_id, auth_authority) values (?,?)");
			q.setParameter(1, func2.getId());
			q.setParameter(2, "ROLE_FUNC");
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
		
		
		return func2;
	}
	
	
	
	
	
	//Verifica se o usuario existe com aquele Funcionarioname ou aquele email
		public Funcionario verificarFunc(String username){
			em = factory.createEntityManager();
			
			Query q = em.createQuery("Select u from Funcionario u where  u.username like :username");
			
			q.setParameter("username", username);
			q.setMaxResults(1);
			
			Funcionario u = new Funcionario();
			try{
			u = (Funcionario) q.getSingleResult();
			
			}catch(NoResultException e){
			
				u = null;
			}finally{
				em.close();
			}
			
			
			
			return u;
		}
		
		
		
		
		public Funcionario localizarFuncPorFuncionarioname(String username){
			em = factory.createEntityManager();
			Query q = em.createQuery("Select u from Funcionario u where u.username like :name");
			
			q.setParameter("name", username);
			q.setMaxResults(1);
			
			Funcionario u = null;
			//boolean existe = false;
			try{
			u = (Funcionario) q.getSingleResult();
			//existe = true;
			
			}catch(NoResultException e){
				u = null;
			}finally{
				em.close();
			}
			return u;
		}
		
	
		

		public Funcionario updateFuncionario(Funcionario func){
			em = factory.createEntityManager();
			try{
				em.getTransaction().begin();
				em.merge(func);
				em.getTransaction().commit();
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				
			}finally{
				em.close();
			}
			
			

			return func;
		}
		
		
		public Funcionario mudarSenha(Funcionario func){
			em = factory.createEntityManager();
			try{

				em.getTransaction().begin();
				Query q = em.createNativeQuery("Update Funcionario set password =? where id =?");
				q.setParameter(1, func.getPassword());
				q.setParameter(2, func.getId());

				q.executeUpdate();
				em.getTransaction().commit();


			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();

			}finally{
				em.close();
			}


			return func;
		}

		
		public Funcionario desativarFunc(Funcionario func) {
			em = factory.createEntityManager();
			try{

				em.getTransaction().begin();
				Query q = em.createQuery("Update Funcionario f set f.enable = false where f.id =?");
				q.setParameter(1, func.getId());
				q.executeUpdate();
				em.getTransaction().commit();


			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();

			}finally{
				em.close();
			}
			
			return func;
		}
		
		public Funcionario ativarFunc(Funcionario func) {
			em = factory.createEntityManager();
			try{

				em.getTransaction().begin();
				Query q = em.createQuery("Update Funcionario f set f.enable = true where f.id =?");
				q.setParameter(1, func.getId());
				q.executeUpdate();
				em.getTransaction().commit();


			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();

			}finally{
				em.close();
			}
			
			return func;
		}
		
		
	public Funcionario alterarPermissaoParaAdm(Funcionario func){
		
		em = factory.createEntityManager();
		
		Funcionario func2 = em.find(Funcionario.class, func.getId());
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Update func_auth set auth_authority = ? where func_id =?");
			q.setParameter(1, "ROLE_ADMIN");
			q.setParameter(2, func2.getId());
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
		
		
		return func2;
	}
	
	
	
	public Funcionario alterarPermissaoParaFunc(Funcionario func){
		
		em = factory.createEntityManager();
		
		Funcionario func2 = em.find(Funcionario.class, func.getId());
		try{
			em.getTransaction().begin();
			Query q = em.createNativeQuery("Update func_auth set auth_authority = ? where func_id =?");
			q.setParameter(1, "ROLE_FUNC");
			q.setParameter(2, func2.getId());
			q.executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
			
		}finally{
			em.close();
		}
		
		
		return func2;
	}
		
		
		public List<Funcionario> buscarUltimosFuncs(){
			em = factory.createEntityManager();
			Query q = em.createQuery("From Funcionario f order by f.id desc");
			q.setMaxResults(5);
			return q.getResultList();
		}
		
		
		
		public List<Funcionario> buscarTodosFuncs(){
			
			List<Funcionario> funs = this.localizarAllAphabetic();
			for (Funcionario funcionario : funs) {
				funcionario.setAuthorities(this.findByLazy(funcionario.getId()).getAuthorities());
			}
		
			return funs;
		}
		
		public List<Funcionario> localizarAllAphabetic(){
			em = factory.createEntityManager();
			Query q = em.createQuery("From Funcionario f order by f.nomeCompleto");
			return q.getResultList();
			
		}
		
		
		

		public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
}
