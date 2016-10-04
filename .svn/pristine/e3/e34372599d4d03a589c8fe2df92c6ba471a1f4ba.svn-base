package br.com.bibideais.teste;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bibideais.dao.LogDAO;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.LogAcesso;
import br.com.bibideais.service.FuncionarioBO;

public class LogTeste {
	//public String urlFichas = "http://localhost:8085/fichas/";

	public static void main(String[] args) {
		LogTeste t = new LogTeste();
		//t.buscarTopCadastradores();
		//t.buscarPorFunc();
		t.buscarLogsPorData();

	}
	
	
	public void buscarTopCadastradores(){
		LogDAO dao = new LogDAO();
		//dao.buscarTopCadastradores();
		List<String> lista = dao.buscarTopCadastradores();
		for (String string : lista) {
			System.out.println(string);
		}
	}
	
	public void buscarLogsPorData(){
		//System.out.println(Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar c = Calendar.getInstance();
		c.set(2013, 1, 11);
		//Calendario Mes : Janeiro = 0, Fev = 1
		int lastday = c.getActualMaximum(c.DAY_OF_MONTH);
		//System.out.println("la" + lastday);
		//System.out.println(c.getActualMaximum(c.DAY_OF_MONTH));
		//System.out.println(c.getActualMinimum(c.DAY_OF_MONTH));
		
		
		LogDAO dao = new LogDAO();
		HashMap<String, BigInteger> logs = dao.buscarPorFuncionarioYearAndMonth(2, 2013, 10, 0);
		for (Map.Entry<String, BigInteger> entrada : logs.entrySet()) {  
			 if(entrada.getKey().equalsIgnoreCase("Logou")){
						System.out.println("Logou-se " + entrada.getValue());
						
			
			 }
			 
			 if(entrada.getKey().equalsIgnoreCase("Deslogou")){
					System.out.println("DesLogou-se " + entrada.getValue());
		
		 }
			 
			 if(entrada.getKey().equalsIgnoreCase("Cadastrou Cliente")){
					System.out.println("Cadastrou Cliente " + entrada.getValue());
		
		 }
			 
			 if(entrada.getKey().equalsIgnoreCase("Acessou Cliente")){
					System.out.println("Acessou Cliente " + entrada.getValue());
		
		 }
		}
	}
	
	
	
	
	
	
	public void buscarPorFunc(){
		Funcionario f = new FuncionarioBO().buscarPorCodigo(2);
		LogDAO dao = new LogDAO();
		List<LogAcesso> logs = dao.buscarPorFuncionario(f);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
		//String novaData = simpleDateFormat.format(data.getTime());
		System.out.println(simpleDateFormat.format(Calendar.getInstance().getTime()));
		
		for (LogAcesso logAcesso : logs) {
			if(logAcesso.getIdCliente() != null){
				System.out.println("O funcionario " + logAcesso.getFuncionario().getNomeCompleto() +  logAcesso.getAcao() + " "+ logAcesso.getIdCliente().getRazaoSocial() + " às " + 
						simpleDateFormat.format(logAcesso.getData().getTime()));
			}else{
				System.out.println("O funcionario " + logAcesso.getFuncionario().getNomeCompleto() +  logAcesso.getAcao() + " às " + 
						simpleDateFormat.format(logAcesso.getData().getTime()));
							
			}
		
			
		}
	}
	
}
