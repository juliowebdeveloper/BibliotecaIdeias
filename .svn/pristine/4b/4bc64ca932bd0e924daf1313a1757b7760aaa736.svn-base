package br.com.bibideais.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import br.com.bibideais.dao.LogDAO;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.LogAcesso;

public class LogBO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogDAO dao;
	
	
	public LogAcesso inserirLog(LogAcesso log){
		dao = new LogDAO();
		log.setData(Calendar.getInstance());
		
		
		return dao.inserir(log);
	}
	
	
	public List<LogAcesso> buscarPorFuncionario(Funcionario func){
		dao = new LogDAO();
		List<LogAcesso> logs = dao.buscarPorFuncionario(func);
		return logs;
	}
	
	
	public HashMap<String, BigInteger> buscarPorFuncionarioYearAndMonth(long idFunc, int year, int month, int day ){
		dao = new LogDAO();
		HashMap<String, BigInteger> logs = dao.buscarPorFuncionarioYearAndMonth(idFunc, year, month, day);
		return logs;
	}
	
	
	
	public void deleterLogsCliente(Cliente c){
		dao = new LogDAO();
		dao.deletarLogsCliente(c);
	}
	
	public List<String> buscarTopCadastradores(){
		dao = new LogDAO();
		return dao.buscarTopCadastradores();
	}
	
	public List<String> buscarTopAcessos(){
		dao = new LogDAO();
		return dao.buscarTopAcesso();
	}
	
	public List<String> buscarTopLogin(){
		dao = new LogDAO();
		return dao.buscarTopLogin();
	}
	

	public LogDAO getDao() {
		return dao;
	}

	public void setDao(LogDAO dao) {
		this.dao = dao;
	}
	
	
	
	
	
}
