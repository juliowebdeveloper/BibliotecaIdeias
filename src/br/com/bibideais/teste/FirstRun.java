package br.com.bibideais.teste;

import java.util.List;

import br.com.bibideais.dao.AnoDAO;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.service.AnoBO;

public class FirstRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FirstRun r = new FirstRun();
		
		
		
		//r.buscarAno();
		
		//Rodar fullscript
		boolean okano = false;
		okano = r.buscarAno();
		if(!okano){
			System.out.println();
			r.preencherAnos();
		}else{
			//Cadastrar Entidade
			EntidadeTeste t = new EntidadeTeste();
			t.inserirEntidade();
			
			//Cadastrar Organizadoras
			OrganizadoraTeste ot = new OrganizadoraTeste();
			ot.inserirOrganizadoras();
			
			//Cadastrar Fornecedor
			FornecedorTeste ft = new FornecedorTeste();
			ft.inserirFornecedor();
			
			//Cadastrar Pavilhões.
			PavilhaoTeste pt = new PavilhaoTeste();
			pt.inserirPavilhoes();
			
			//Cadastrar Feiras
			FeiraTeste fet = new FeiraTeste();
			fet.cadastrarFeiras();
			//Cadastrar Cliente*/
			 ClienteTeste clit = new ClienteTeste();
			clit.cadastrarClientes();
			
			System.out.println("----------------Fim carga inicial----------------------");
		}
		
		
		
	}
	
	
	
	
	
	public void preencherAnos(){
		Ano ano = new Ano();
		AnoDAO dao = new AnoDAO();

		for(int i = 2002; i<2020; i++){
			ano.setAno(i);
			dao.inserir(ano);
			ano = new Ano();
		}

	}
	
	
	public boolean buscarAno(){
		AnoDAO dao = new AnoDAO();
		boolean ok;
		try{
			Ano a = dao.selectAno(2006);
			ok = true;
		}catch(Exception e){
			ok = false;
		}
		return ok;
	}
	
	public void buscarAnosDesc(){
		AnoDAO dao = new AnoDAO();
		List<Ano> anos = dao.buscarTodosDesc();
		for (Ano ano : anos) {
			System.out.println(ano.getAno());
		}
	}
	
	
}
