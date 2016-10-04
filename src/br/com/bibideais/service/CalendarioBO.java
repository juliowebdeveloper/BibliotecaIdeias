package br.com.bibideais.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.bibideais.dao.CalendarioDAO;
import br.com.bibideais.entity.Calendario;

public class CalendarioBO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private CalendarioDAO dao;
	 
	
	    public List<Calendario> buscarTodasCalendarios(){
	    	dao = new CalendarioDAO();
	        return dao.localizarAll();
	    }
	 
	    public void adicionarCalendario(Calendario calendario){
		      dao = new CalendarioDAO();
		      dao.inserir(calendario);
	    }
	 
	  public Calendario update(String titulo, Calendar dataInicio, Calendar dataFim, boolean allday, Calendario cad){
		  dao = new CalendarioDAO();
		  return dao.update(titulo, dataInicio, dataFim, allday, cad);
		  
	  }
	  
	  public void delete(String titulo, Calendar dataInicio, Calendar dataFim, boolean allday, Calendario cad){
		  dao = new CalendarioDAO();
		 dao.deletarEvento(titulo, dataInicio, dataFim, allday, cad);
	  }
}
