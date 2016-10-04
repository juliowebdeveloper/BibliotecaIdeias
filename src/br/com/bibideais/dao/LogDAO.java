package br.com.bibideais.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.LogAcesso;

public class LogDAO extends GenericDAO<LogAcesso> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private EntityManager em;
	
	
	
	public List<LogAcesso> buscarPorFuncionario(Funcionario f){
		em = factory.createEntityManager();
		Query q = em.createQuery("Select l from LogAcesso l where l.funcionario =:func order by l.data desc");
		q.setParameter("func", f);
		List<LogAcesso> logs = q.getResultList();
		em.close();
		return logs;
	}

	
	
	public HashMap<String,BigInteger> buscarPorFuncionarioYearAndMonth(long idFunc, int year, int month, int day ){
		Calendar c = Calendar.getInstance();
		
		int firstday = 0;
		int lastday = 0;
		
		//Se o dia vier como 0 então faz do mes todo, se não faz só daquele dia
		if(day != 0){
			c.set(year, month, day);
			
			//Primeiro dia daquele mes
			 firstday = day;
			
			// Ultimo dia daquele mes (Soma-se para ser entre o dia e o outro)
			 lastday = firstday +1;

		}else{
			c.set(year, month, 1);
			//Primeiro dia daquele mes
			 firstday = c.getActualMinimum(c.DAY_OF_MONTH);
			
			// Ultimo dia daquele mes
			 lastday = c.getActualMaximum(c.DAY_OF_MONTH);
		}
		
		
	
		//Montando a query a partir disso
		BigInteger countlogin;
		BigInteger countlogoff;
		BigInteger countcadastroucliente;
		BigInteger countacessoucliente;
		
		em = factory.createEntityManager();
		
		
		//primeira data
		
		String primeiradata = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(firstday);
		
		System.out.println("Primeira data = " + primeiradata);
		
		//Segunda data
		
		String segundadata = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(lastday);
		System.out.println("Segunda data = " + segundadata);
		

		
		//Query Login
		Query q = em.createNativeQuery("Select count(idLogAcesso) from logacesso where data between ? and ? and id_func = ? and acao like '%logou-se%'" );
		
		
		q.setParameter(1, primeiradata);
		q.setParameter(2, segundadata);
		q.setParameter(3, idFunc);
		
		countlogin = (BigInteger) q.getSingleResult();
		
		
		
		//Query Logoff
		q = em.createNativeQuery("Select count(idLogAcesso) from logacesso where data between ? and ? and id_func = ? and acao like '%deslogou-se%'" );
		
		
		q.setParameter(1, primeiradata);
		q.setParameter(2, segundadata);
		q.setParameter(3, idFunc);
		
		countlogoff = (BigInteger) q.getSingleResult();
		
		
		
		//Query Cadastrou  cliente
		q = em.createNativeQuery("Select count(idLogAcesso) from logacesso where data between ? and ? and id_func = ? and acao like '%cadastrou o Cliente%'" );
		
		
		q.setParameter(1, primeiradata);
		q.setParameter(2, segundadata);
		q.setParameter(3, idFunc);
		
		countcadastroucliente = (BigInteger) q.getSingleResult();
		
		
		
		//Query Acessou  cliente
		q = em.createNativeQuery("Select count(idLogAcesso) from logacesso where data between ? and ? and id_func = ? and acao like '%acessou o Cliente%'" );
		
		
		q.setParameter(1, primeiradata);
		q.setParameter(2, segundadata);
		q.setParameter(3, idFunc);
		
		countacessoucliente = (BigInteger) q.getSingleResult();
		HashMap <String, BigInteger> logs = new HashMap<String, BigInteger>();
		logs.put("Logou", countlogin);
		logs.put("Deslogou", countlogoff);
		logs.put("Cadastrou Cliente", countcadastroucliente);
		logs.put("Acessou Cliente", countacessoucliente);
		
		return logs;
		
		
	}
	
	
	


	public EntityManager getEm() {
		return em;
	}



	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<String>  buscarTopCadastradores(){
		em = factory.createEntityManager();
		List<String> nome = new ArrayList<String>();

		Query q = em.createNativeQuery("Select count(l.acao), f.nomeCompleto from logacesso  l, funcionario f where l.acao like ' cadastrou o Cliente ' and l.id_func = f.id group by id_func order by count(l.acao) desc limit 3");
		List<Object[]> list = (List<Object []>) q.getResultList();
		for (Object[] row : list) {
			BigInteger b = (BigInteger) row[0];
			b.toString();
			nome.add((String)row[1] + " cadastrou " + b.toString() + " cliente(s)");
			
		}
		
		
		return nome;
	}
	
	
	public List<String>  buscarTopLogin(){
		em = factory.createEntityManager();
		List<String> nome = new ArrayList<String>();
		Query q = em.createNativeQuery("Select count(l.acao), f.nomeCompleto from logacesso  l, funcionario f where l.acao like ' logou-se' and l.id_func = f.id group by id_func order by count(l.acao) desc limit 3");
		List<Object[]> list = (List<Object []>) q.getResultList();
		for (Object[] row : list) {
			BigInteger b = (BigInteger) row[0];
			b.toString();
			nome.add((String)row[1] + " logou-se " + b.toString() + " vez(es)");
			
		}
	
		return nome;
		
	}
	
	
	public List<String>  buscarTopAcesso(){
		em = factory.createEntityManager();
		List<String> nome = new ArrayList<String>();
		Query q = em.createNativeQuery("Select count(l.acao), f.nomeCompleto from logacesso  l, funcionario f where l.acao like ' acessou o Cliente ' and l.id_func = f.id group by id_func order by count(l.acao) desc limit 3");
		List<Object[]> list = (List<Object []>) q.getResultList();
		for (Object[] row : list) {
			BigInteger b = (BigInteger) row[0];
			b.toString();
			nome.add((String)row[1] + " acessou clientes " + b.toString() + " vez(es).");
			
		}
		
		
		return nome;
	}



	public void deletarLogsCliente(Cliente c) {
		em = factory.createEntityManager();
		try{
			em.getTransaction().begin();

			Query q = em.createQuery("Delete from LogAcesso where idCliente =:cliente");
			q.setParameter("cliente", c);
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
