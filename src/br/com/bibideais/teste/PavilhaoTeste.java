package br.com.bibideais.teste;

import java.util.List;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.PavilhaoBO;

public class PavilhaoTeste {

	
	
	public static void main(String[] args) {
		PavilhaoTeste t = new PavilhaoTeste();
		//t.inserirPavilhoes();
		//t.buscarPorNome();
		//t.alterarPavilhao();
		//t.cadastrarPavilhaoTeste();
		t.inserirPavBI();
	}
	
	
	public void inserirPavBI(){
		Cidade cidade = new Cidade();
		cidade = new CidadeBO().buscarPorId(206);
		Pavilhao p6 = new Pavilhao("Pavilhão Genérico - BI", "Indisponível", "", null, "Indisponível", "", cidade, null, "", "adm@bibliotecadeideias.com.br", null, null, "");
		
		PavilhaoBO bo = new PavilhaoBO();
	
		bo.cadastrar(p6);
	}
	
	public void inserirPavilhoes(){
		Cidade cidade = new Cidade();
		cidade = new CidadeBO().buscarPorId(206);
		Pavilhao p = new Pavilhao("Transamérica Expo Center", "Av. Dr. Mário Villas Boas Rodrigues", "387", null, "Santo Amaro", "04757-020", cidade, null, "(11)5643-3000", "gcardoso@transamerica.com.br", "Gustavo Nogueira", "Operacional","www.transamericaexpo.com.br");
		Pavilhao p1 = new Pavilhao("Expo Center Norte (Pavilhões Verde, Vermelho, Azul, Branco)", "Rua José Bernardo Pinto", "333", null, "Vila Guilherme", "02055-000", cidade, null, "(11) 2224-5900", "atendimento@centernorte.com.br", null,null,"www.centernorte.com.br/expo-center-norte");
		Pavilhao p2 = new Pavilhao("Pavilhão da Bienal do Ibirapuera", "Av. Pedro Álvares Cabral, s/n – Parque do Ibirapuera – Portão 3", null, null, "Ibirapuera", "04094-000", cidade, null, "(11) 5576-7600", null, null, null, "http://www.bienal.org.br/FBSP/pt/Eventos/Paginas/default.aspx ");
		Pavilhao p3 = new Pavilhao("Anhembi Parque","Av. Olavo Fontoura","1209",null, "Santana", "02012-021", cidade, null, "(11) 2226-0400","atendimentoclientes@spturis.com", null, null, "www.anhembi.com.br");
		Pavilhao p4 = new Pavilhao("Centro de Exposições Imigrantes","Rodovia dos Imigrantes", "Km 1,5", null, "Água Funda", "04301-012", cidade, null, "(11) 5067-6767","marketing@agrocentro.com.br", null,null, "www.centroimigrantes.com.br");
		Pavilhao p5 = new Pavilhao("Expo Center Norte (Pavilhão Amarelo)", "Av. Otto Baumgart", "1000", null, "Vila Guilherme", "02055-000", cidade, null, "11-2089-8500", "atendimento@centernorte.com.br", null, null, "www.centernorte.com.br/expo-center-norte ");
		Pavilhao p6 = new Pavilhao("Pavilhão Genérico - BI", "Indisponível", "", null, "Indisponível", "", cidade, null, "", "adm@bibliotecadeideias.com.br", null, null, "");
		
		PavilhaoBO bo = new PavilhaoBO();
		bo.cadastrar(p);
		bo.cadastrar(p1);
		bo.cadastrar(p2);
		bo.cadastrar(p3);
		bo.cadastrar(p4);
		bo.cadastrar(p5);
		bo.cadastrar(p6);
		
	}
	

	public void cadastrarPavilhaoTeste(){
		Pavilhao p = new PavilhaoBO().buscarPorCodigo(38);
		System.out.println(p.getNome());
		PavilhaoBO bo = new PavilhaoBO();
		Pavilhao pav = bo.cadastrar(p);
		if(pav == null){
			System.out.println("oi");
		}else{
			System.out.println("nope");
		}
	}
	
	
	
	public void buscarPorNome(){
		PavilhaoBO bo = new PavilhaoBO();
		List<Pavilhao> list = bo.buscarPorNome("Expo");
		for (Pavilhao pavilhao : list) {
			System.out.println(pavilhao.getNome() + pavilhao.getEndereco());
		}
	}
	
	public void alterarPavilhao(){
		PavilhaoBO bo = new PavilhaoBO();
		Pavilhao p = bo.buscarPorCodigo(39);
		p.setNumero("333");
		p.setCidade(new CidadeBO().buscarPorId(206));
		bo.alterarPavilhao(p);
		
	}
	
}
