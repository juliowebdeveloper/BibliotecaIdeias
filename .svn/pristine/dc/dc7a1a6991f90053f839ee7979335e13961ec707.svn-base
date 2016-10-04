package br.com.bibideais.teste;

import br.com.bibideais.dao.CalendarioDAO;
import br.com.bibideais.entity.Calendario;


public class CalendarioTeste {
	public static void main(String[] args) {
			Calendario c = new CalendarioDAO().localizar(1);
			System.out.println(c.getDescricao());
			
			Calendario segundo = new CalendarioDAO().update(c.getDescricao(), c.getDataInicio(), c.getDataFim(),true, c);
			System.out.println(segundo.getDescricao());
	}
}
