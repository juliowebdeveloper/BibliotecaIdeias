package br.com.bibideais.teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.bibideais.dao.FeiraDAO;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ClienteFeira;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.OrganizadoraBO;
import br.com.bibideais.service.PavilhaoBO;

public class FeiraTeste {

	
	public static void main(String[] args) {
		FeiraTeste f = new FeiraTeste();
		//f.cadastrarFeiras();
		//f.buscarFeirasPavilhao();
		//f.buscarFeirasOrganizadora();
		//f.buscarFeirasPorAno();
		//f.filteredSearch();
		//f.buscarPorNome();
		f.buscarPorCliente();
	}
	
	public void buscarPorCliente(){
		ClienteBO bo = new ClienteBO();
		Cliente c = bo.getReferenceID(37);
		FeiraDAO dao  = new FeiraDAO();
		List<ClienteFeira> cfes = dao.buscarPorCliente(c);
		List<Feira> feiras = new ArrayList<Feira>();
		for (ClienteFeira clienteFeira : cfes) {
			System.out.println(clienteFeira.getId().getIdFeira().getNomeFeira());
			feiras.add(clienteFeira.getId().getIdFeira());
			
		}
		
		for (Feira feira : feiras) {
			System.out.println(feira.getEdicao());
		}
		
		
		
	}

	
	
	
	public void buscarPorNome(){
		FeiraBO bo = new FeiraBO();
		List<Feira> feiras = bo.buscarPorNome("bra");
		for (Feira feira : feiras) {
			System.out.println(feira.getNomeFeira());
		}
	}
	
	public void cadastrarFeiras(){
		Cidade cidade = new CidadeBO().buscarPorId(206);
	
		//Pavilh�o Transamerica Expo Center
		List<Pavilhao> pavs = new PavilhaoBO().buscarPorNome("Transam�rica");
		Pavilhao transamerica = new Pavilhao();
		for (Pavilhao pavilhao : pavs) {
			transamerica = pavilhao;
		}
		
		
		
		 pavs = new PavilhaoBO().buscarPorNome("Imigrantes");
		
		 Pavilhao imigrantes = new Pavilhao();
		 for (Pavilhao pavilhao : pavs) {
			imigrantes = pavilhao;
		}
		 
		 
		 Pavilhao expocenter = null;
		 List<Pavilhao> expos = new PavilhaoBO().buscarPorNome("Expo Center Norte (Pavilh�es Verde, Vermelho, Azul, Branco)");
		 for (Pavilhao pavilhao : expos) {
			expocenter = pavilhao;
		}
		 
		 Pavilhao anhembi = null;
		 List<Pavilhao> anhembis = new PavilhaoBO().buscarPorNome("Anhembi");
		 for (Pavilhao pavilhao : anhembis) {
			anhembi = pavilhao;
		}
		 
		 Pavilhao bienal = null;
		 List<Pavilhao> bienals = new PavilhaoBO().buscarPorNome("Pavilh�o da Bienal");
		 for (Pavilhao pavilhao : bienals) {
			bienal = pavilhao;
		}
		
		
			//Feiras Organizadas Pela Francal
			List<Organizadora> francals = new OrganizadoraBO().localizarRazaoSocial("FRANCAL - FEIRAS E EMPREENDIMENTOS LTDA");
			Organizadora francal = new Organizadora();
			for (Organizadora organizadora : francals) {
				francal = organizadora;
				System.out.println(francal.getBairro());
			}
			
		
		Feira f1 = new Feira("AES BRASIL EXPO - 17a. Conven��o e Exposi��o de Tecnologia : �udio, V�deo, Ilumina��o e Instala��es Especiais", null, null, "17�", "2013", null, "7 a 9/maio de 2013", ImagesUtil.urlLogos + "aes.jpg", 0, 0, 0, 0, 0, expocenter, null, null, true, francal, cidade);
		Feira f2 = new Feira("Natal Show 2013 - 4� Feira de Artigos e Decora��o de Natal", null, null, "4�", "2013", null, "5 a 8/junho de 2013", ImagesUtil.urlLogos + "natalshow.jpg", 0, 0 , 0 , 0 , 0 , expocenter, null, null, true, francal, cidade);
		Feira f3 = new Feira("EXPO PARQUES E FESTAS 2013 - 7� Feira Internacional de Produtos e Servi�os para Parques Tem�ticos, Buffets e Festas Infantis", null, null, "7�", "2013", null, "5 a 8/junho de 2013", ImagesUtil.urlLogos + "expoparques.jpg", 0, 0 , 0 , 0 , 0 , expocenter, null, null, true, francal, cidade);
		Feira f4 = new Feira("BIO BRAZIL FAIR 2013 - 9� Feira Internacional de Produtos Org�nicos e Agroecologia", null, null, "9�", "2013", null, "27 a 30/junho de 2013", ImagesUtil.urlLogos + "biobrazil.jpg", 0, 0 , 0 , 0 , 0 , bienal, null, null, true, francal, cidade);
		Feira f5 = new Feira("NATURALTECH 2013 - 9� Feira Internacional de Alimenta��o Saud�vel, Produtos Naturais e Sa�de", null, null, "9�", "2013", null, "27 a 30/junho de 2013", ImagesUtil.urlLogos + "naturaltech.jpg", 0, 0 , 0 , 0 , 0 , bienal, null, null, true, francal, cidade);
		Feira f6 = new Feira("EXPOLAZER 2013 - 19� Feira Internacional de Piscinas, Spas e Ambientes de Lazer", null, null, "19�", "2013", null, "7 a 10/agosto de 2013", ImagesUtil.urlLogos + "expolazer.jpg", 0, 0 , 0 , 0 , 0 , expocenter, null, null, true, francal, cidade);
		Feira f7 = new Feira("Feira Internacional de Produtos para Papelarias, scrit�rios, Escolas e Revendas de Inform�tica", null, null, "27�", "2013", null, "19 a 22/agosto de 2013", ImagesUtil.urlLogos + "lightingweekbrasil.jpg", 0, 0 , 0 , 0 , 0 , anhembi, null, null, true, francal, cidade);

	
		
		
		
		
		FeiraBO bo = new FeiraBO();
		bo.cadastrar(f1);
		bo.cadastrar(f2);
		bo.cadastrar(f3);
		bo.cadastrar(f4);
		bo.cadastrar(f5);
		bo.cadastrar(f6);
		bo.cadastrar(f7);
		
		
		
		/*//FEIRAS ORGANIZADAS POR CIPA
		List<Organizadora> cipas = new OrganizadoraBO().localizarRazaoSocial("CIPA FM PUBLICACOES E EVENTOS LTDA");
		Organizadora cipa = new Organizadora();
		for (Organizadora organizadora : cipas) {
			cipa = organizadora;
			System.out.println(cipa.getBairro());
		}*/
		
	}
	
	public void buscarFeirasOrganizadora(){
		List<Feira> feiras = new FeiraDAO().buscarPorOrganizadora(new OrganizadoraBO().localizarRazaoSocial("Francal").get(0));
		System.out.println("__________________Organizadora__________________________________________");
		for (Feira feira : feiras) {
			System.out.println(feira.getNomeFeira());
		}
	}
	
	public void buscarFeirasPavilhao(){
		List<Feira> feiras = new FeiraDAO().buscarPorLocal(new PavilhaoBO().buscarPorNome("Pavilh�o da Bienal").get(0));
		System.out.println("__________________Pavilhao__________________________________________");

		for (Feira feira : feiras) {
			System.out.println(feira.getNomeFeira());
		}
	}
	
	public void buscarFeirasPorAno(){
		List<Feira> feiras = new FeiraDAO().buscarPorAno("2013");
		System.out.println("____________________Ano________________________________________");

		for (Feira feira : feiras) {
			System.out.println(feira.getNomeFeira());
		}
	}
	
	public void filteredSearch(){
		//Busca por locais, por biconstruiu, por nome, por ano, por organizadora
		long inicio = System.currentTimeMillis();    
		//metodo();    
		
		
		HashMap <String, String> parametros = new HashMap<String, String>();
		//parametros.put("nome", "2013");
		//Como local e organizadora chegar� na p�gina
		//Chegar� um objeto, mas o array s� aceita string;
		//Pode colocar o id da organizadora em forma de string e dentro do metodo buscar novamente para trazer o objeto., o mesmo para local
		
		Pavilhao pav = new PavilhaoBO().buscarPorNome("Pavilh�o da Bienal").get(0);
		String idpav = Integer.toString(pav.getIdPavilhao());
		//parametros.put("local", idpav);
		//parametros.put("ano", "2013");
		Organizadora org = new OrganizadoraBO().localizarRazaoSocial("Francal").get(0);
		String idorg = Integer.toString(org.getIdOrganizadora());
		//parametros.put("organizadora",idorg );
		boolean construiu = true;
		System.out.println(parametros.size());
		
		FeiraDAO dao = new FeiraDAO();
		
		List<Feira> feiras = dao.filteredSearch(parametros, construiu);
		for (Feira feira : feiras) {
			System.out.println("Nome da feira: " + feira.getNomeFeira() + "\n" +
				"Pavilhao da feira: " + feira.getLocal().getNome() +
				"\n" + "Organizadora: " + feira.getOrganizadora().getRazaoSocial());
			System.out.println("________________________________________");
		}
		
		long fim  = System.currentTimeMillis();    
		System.out.println( fim - inicio );
		
	}
	
	
	
}
