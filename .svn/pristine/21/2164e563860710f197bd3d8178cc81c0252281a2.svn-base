package br.com.bibideais.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import br.com.bibideais.dao.AnoDAO;
import br.com.bibideais.dao.ClienteDAO;
import br.com.bibideais.dao.FeiraDAO;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Ficha;
import br.com.bibideais.entity.FotoCliente;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.service.AnoBO;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.FuncionarioBO;
import br.com.bibideais.service.ProjetoBO;


public class ClienteTeste {
	
	
	public static void main(String[] args) {
			//System.out.println(Calendar.getInstance());
		ClienteTeste t = new ClienteTeste();
		//t.cadastrarClientes();
		//t.relacionarAnosCliente();
		//t.localizarRazaoExata();
		//t.buscarClienteCompleto();
		//t.buscarPorAtendimento();
		//t.buscarCliPorFeira();
		//t.buscaCompleta();
		//t.searchStringParameters();
		//t.buscarPorFeiraEAnos();
		//t.searchByAno();
		//t.buscar();
		//t.compararFeiras();
		//t.buscarUltimosCadastrados();
		//t.buscarCliente();
		//t.testeBriefing();
		//t.buscarContatosPorFeira();
		//t.buscarAnosPorCliente();
		//t.buscarClientPorFeirasAndIsClient();
		t.buscarPorNomeFantasia();
	}
	
	public void buscarPorNomeFantasia(){
		ClienteBO bo = new ClienteBO();
		List<Cliente> clis = bo.buscarPorNomeFantasia("a");
		for (Cliente cliente : clis) {
			System.out.println(cliente.getNomeFantasia() + " é cliente bi? " + cliente.isClient());
		}
	}
	
	public void buscarAnosPorCliente(){
		AnoDAO dao = new AnoDAO();
		Cliente c = new ClienteBO().localizarPorId(2);
		dao.buscarPorCliente(c);
	}
	
	
	public void buscarClientPorFeirasAndIsClient(){
		Feira feira = new FeiraBO().buscarPorCodigo(1);
		List<Cliente>clis = new ClienteDAO().buscarPorFeirasAndIsClient(feira);
		for (Cliente cliente : clis) {
			System.out.println(cliente.getIdCliente() + "nome " + cliente.getRazaoSocial());
		}
		
	}
	public void buscarContatosPorFeira(){
		FeiraDAO dao = new FeiraDAO();
		ClienteBO bo = new ClienteBO();
		Feira f = dao.localizarPorNome("AES BRASIL EXPO").get(0);
		List<Cliente> clientesFeira = new ClienteBO().buscarClientesFeira(f);
		for (Cliente cliente : clientesFeira) {
			System.out.println(cliente.getRazaoSocial());
			List<ContatoCliente> c = bo.buscarContatos(cliente);
			for (ContatoCliente contatoCliente : c) {
				System.out.println(contatoCliente.getEmail());
			}
		}
		
	}
	
	public void buscarCliente(){
		ClienteBO bo = new ClienteBO();
		List<Cliente> clientes = bo.buscarTodos();
		for (Cliente cliente : clientes) {
			Cliente c = bo.buscarClienteCompleto(cliente.getIdCliente());
			List<Briefing> bs = c.getBriefings();
			if(!bs.isEmpty()){
				//System.out.println(c.getIdCliente());
				for (Briefing b : bs) {
				//System.out.println(b.getCodigo());
			}
			
			System.out.println("_________________________");
		}
		}
	
		
	}
	
	public void testeBriefing(){
		//Ordenadno os Briefings do Cliente
		Briefing b1 = new Briefing();
		Briefing b2 = new Briefing();
		Briefing b3 = new Briefing();
		Briefing b4 = new Briefing();
		b1.setCodigo("20199-0");
		b2.setCodigo("19199-9");
		b3.setCodigo("18199-5");
		b4.setCodigo("123-1");
		
		List<Briefing> brifs = new ArrayList<Briefing>();
		
		
		brifs.add(b1);
		brifs.add(b2);
		brifs.add(b3);
		brifs.add(b4);
		
		System.out.println("Não ordenados");
		for (Briefing briefing : brifs) {
			System.out.println(briefing.getCodigo());
		}
		
		System.out.println("________________");
		
		
		Collections.sort (brifs, new Comparator<Briefing> () {    
		    public int compare (Briefing b1, Briefing b2) {    
		        return b1.getCodigo().toUpperCase().compareTo (b2.getCodigo().toUpperCase());    
		    } 
		});
		System.out.println("Ordenados");
		for (Briefing briefing : brifs) {
			System.out.println(briefing.getCodigo());
		}
		
	}
	
	
	
	
	
	public void compararFeiras(){
		Cliente c = new ClienteDAO().localizar(37);
		FeiraBO bo = new FeiraBO();
		List<Feira> feiras = bo.subtrairFeirasCliente(c);
		System.out.println("______________Feiras__________________________________");
		for (Feira feira : feiras) {
			System.out.println("ID FEIRA " + feira.getIdFeira() + " Nome " + feira.getNomeFeira());
		}
	}
	
	public void buscarUltimosCadastrados(){
		List<Cliente> clientes = new ClienteDAO().buscarUltimosCadastrados();
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getRazaoSocial());
		}
	}
	
	public void buscar(){
		Cliente c1 = new ClienteDAO().localizar(1);
		System.out.println(c1.getRazaoSocial());
		System.out.println(c1.getUrlCartao());
		int i = c1.getUrlCartao().lastIndexOf('/');
		String s = c1.getUrlCartao().substring(i + 1);
		System.out.println(s);
	}
	
	public void searchByAno(){
		Ano a = new Ano();
		Ano a1 = new Ano();
		a = new AnoDAO().localizarlong(42);
		a1 = new AnoDAO().localizarlong(56);
		List<Ano> anos = new ArrayList<Ano>();
		anos.add(a);
		anos.add(a1);
		List<Cliente> clientes = new ClienteBO().buscarPorAnos(anos);
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getRazaoSocial());
		}
	}
	
	public void buscaCompleta(){
		HashMap<String, String> parametros = new HashMap<String, String>();
		Feira f = new FeiraBO().buscarPorCodigo(4);
		boolean isClient = true;
		boolean recebeu = true;
		
		System.out.println(f.getNomeFeira());
		System.out.println("_____________________");
		
		List<Ano> anos = new ArrayList<Ano>();
		anos.add(new AnoBO().selectAno(2011));
		anos.add(new AnoBO().selectAno(2025));
		
		parametros.put("razao", "Hospi");
		parametros.put("categoria", "Hospi");
		parametros.put("segprincipal", "Hospi");
		
		ClienteDAO dao = new ClienteDAO();
		//List<Cliente> clients = dao.completeFilteredSearch(parametros, f, anos, recebeu, isClient);
		List<Cliente> clients = new ClienteBO().completeFilteredSearch(parametros, f, anos, recebeu, isClient);
		
		for (Cliente cliente : clients) {
			System.out.print("ID " + cliente.getIdCliente() + "Nome: " +  cliente.getRazaoSocial() + " " );
			for (Ano ano: cliente.getAnostrabalhos()) {
				System.out.print(ano.getAno()+ " ");
				System.out.println();
				
			}
			
			
		}
		
	}
	
	public void searchStringParameters(){
		HashMap<String, String> parametros = new HashMap<String, String>();
		boolean isClient = true;
	

		parametros.put("razao", "Air");
		parametros.put("fantasia","Air" );
		parametros.put("categoria", "a");
		parametros.put("segprincipal", "a");
		
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clients = dao.searchStringParameters(parametros, isClient);
		for (Cliente cliente : clients) {
			System.out.println(cliente.getRazaoSocial());
		}
	}
	
	public void buscarPorFeiraEAnos(){
		Feira f = new FeiraBO().buscarPorCodigo(4);
		boolean isClient = true;
		boolean recebeu = true;
		
		System.out.println(f.getNomeFeira());
		System.out.println("_____________________");
		
		List<Ano> anos = new ArrayList<Ano>();
		anos.add(new AnoBO().selectAno(2011));
		anos.add(new AnoBO().selectAno(2025));
		
		
		
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clients = dao.buscarPorFeiraAndAnos(f, anos, recebeu);
		for (Cliente cliente : clients) {
			System.out.println(cliente.getRazaoSocial());
		}
	}
	
	
	
	
	public void buscarPorAtendimento(){
		Funcionario f = new FuncionarioBO().buscarPorCodigo(3);
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clis = dao.buscarPorAtendimento(f);
		for (Cliente cliente : clis) {
			System.out.println(cliente.getRazaoSocial());
		}
	}
	
	
	public void buscarCliPorFeira(){
		ClienteBO bo = new ClienteBO();
		List<Cliente> clis = bo.buscarClientesFeira(new FeiraDAO().localizar(4));
		for (Cliente cliente : clis) {
			System.out.println(cliente.getRazaoSocial());
		}
	}
	
	
	public void buscarClienteCompleto(){
		ClienteBO bo = new ClienteBO();
		//Cliente c = bo.getReferenceID(37);
		Cliente c = bo.buscarClienteCompleto(1);
		
		for (Briefing br : c.getBriefings()) {
			System.out.println("______________");
			System.out.println(br.getCodigo());
			System.out.println( "Briefing url cortada " + br.getUrlPlanilhaCustos().substring(br.getUrlPlanilhaCustos().lastIndexOf('/')+1));
		}
		
		for(ContatoCliente ca: c.getContatos()){
			System.out.println("______________");
			System.out.println(ca.getTelefone());
		}
		
		
		for (Ano anos : c.getAnostrabalhos()) {
			System.out.println("______________");
			System.out.println(anos.getAno());
		}
		
		for(Ficha f: c.getFichas()){
			System.out.println("______________");
			System.out.println(f.getUrlimagem());
		}
		
		for(FotoCliente fa: c.getFotosReferencia()){
			System.out.println("______________");
			System.out.println(fa.getUrlimagem());
		}
		
		
		for (Briefing b : c.getBriefings()) {
			for (BriefingFiles brif : b.getFiles()) {
				System.out.println(brif.getUrlFile());
			}
		}
		
		
		for (Projeto p : c.getProjetos()) {
			System.out.println("Projeto id: " + p.getIdProjeto() + " Ano: " + p.getAno());
			System.out.println("Versoes_________________________");
			for (VersaoProjeto v : p.getVersoes()) {
				System.out.println("Versão: " + v.getVersao());
				System.out.println("Fotos projeto da versão  " + v.getVersao());
				for (FotoProjeto f : v.getFotosProjeto()) {
					System.out.println("URL " + f.getUrlImagem());
				}
			}
		}
		
		
	}
	
	
	
	public void relacionarAnosCliente(){
		Cliente c = new ClienteBO().localizarPorId(1);
		 List<Ano> anos = new ArrayList<Ano>();
		 anos.add(new AnoBO().selectAno(2006));
		 anos.add(new AnoBO().selectAno(2007));
		 anos.add(new AnoBO().selectAno(2008));
		 anos.add(new AnoBO().selectAno(2009));
		 anos.add(new AnoBO().selectAno(2010));
		 anos.add(new AnoBO().selectAno(2011));
		 anos.add(new AnoBO().selectAno(2012));
		 anos.add(new AnoBO().selectAno(2013));
		 
		 AnoBO bo = new AnoBO();
		 bo.relacionarAnosCliente(c, anos);
		
	}
	
	
	public void cadastrarClientes(){
		Cliente c1 = new Cliente();
		c1.setRazaoSocial("AIR PRODUCTS BRASIL LTDA");
		c1.setNomeFantasia("Air Products Brasil");
		//Funcionarios
		c1.setCadastrador(new FuncionarioBO().buscarPorCodigo(2));
		c1.setAtendimento(new FuncionarioBO().buscarPorCodigo(1));
		
		c1.setAnotacoes("Atendemos AP desde 2006, com o trabalho realizado para este cliente a BI obteve: Prêmio Caio 2006 – e Prêmio Caio da Década.");
		c1.setCategoria("Indústria Química");
		c1.setSegmentoPrincipal("Gases industriais");
		//Endereço
		c1.setRua("Av. Francisco Matarazzo");
		c1.setNumero("1400");
		c1.setComplemento("11º andar – Cond.Edif. MIlano");
		c1.setBairro("Água Branca");
		c1.setCep("05001-903");
		c1.setNacionalidade("Nacional");
		c1.setCidade(new CidadeBO().buscarPorId(206));
		//É cliente
		c1.setClient(true);
		c1.setRecebeuTrabalhos(true);
		
		//Logo / Cartão do cliente
		c1.setUrlCartao(ImagesUtil.urlCartoes + "air_products.jpg");
		
		//Ficha
		List<Ficha> fichas = new ArrayList<Ficha>();
		Ficha f1 = new Ficha();
		f1.setCliente(c1);
		f1.setNomeImagem("AIR PRODUCTS - MECÂNICA 2006 - PRÊMIO CAIO JACARÉ DE OURO");
		f1.setUrlimagem(ImagesUtil.urlFichas +"airproductstrans.jpg");
		fichas.add(f1);
		
		Ficha f2 = new Ficha();
		f2.setCliente(c1);
		f2.setNomeImagem("AIR PRODUCTS  MECANICA  MARÇO 2008 ANHEMBI");
		f2.setUrlimagem(ImagesUtil.urlFichas +"airproducts2008.jpg");
		fichas.add(f2);
		
		Ficha f3 = new Ficha();
		f3.setCliente(c1);
		f3.setNomeImagem("AIR PRODUCTS  - ICR  - TRANSAMERICA - 2012");
		f3.setUrlimagem(ImagesUtil.urlFichas +"airproducts2012.jpg");
		fichas.add(f3);
		
		
		c1.setFichas(fichas);
		
		
		//Lista de contatos daquele cliente
		
		List<ContatoCliente> contatos = new ArrayList<ContatoCliente>();
		ContatoCliente cc1 = new ContatoCliente();
		cc1.setTelefone("3856-1723");
		cc1.setNome("Fátima Jardim");
		cc1.setEmail("jardimfp@airproducts.com");
		cc1.setCargo("Departamento de compras");
		cc1.setCliente(c1);
		
		ContatoCliente cc2 = new ContatoCliente();
		cc2.setTelefone("3856-1724");
		cc2.setNome("Movan R.R. Silva");
		cc2.setEmail("Silvamr2@airproducts.com");
		cc2.setCelular("9-9641-4767");
		cc2.setCliente(c1);
		
		ContatoCliente cc3 = new ContatoCliente();
		cc3.setTelefone("3856-1668");
		cc3.setNome("Valdir B.Fontes");
		cc3.setEmail("fontesvb@airproducts.com.br");
		cc3.setCelular("9-9953-9648");
		cc3.setCliente(c1);
		
		
		contatos.add(cc1);
		contatos.add(cc2);
		contatos.add(cc3);
		
		c1.setContatos(contatos);
		
		 c1.setSite("www.airproducts.com.br");
		 
		 List<Ano> anos = new ArrayList<Ano>();
		 anos.add(new AnoBO().selectAno(2006));
		 anos.add(new AnoBO().selectAno(2007));
		 anos.add(new AnoBO().selectAno(2008));
		 anos.add(new AnoBO().selectAno(2009));
		 anos.add(new AnoBO().selectAno(2010));
		 anos.add(new AnoBO().selectAno(2011));
		 anos.add(new AnoBO().selectAno(2012));
		 anos.add(new AnoBO().selectAno(2013));
		 
		//Os anos são inseridos pela AnoBO nos metodos que relacionam anos e cliente
		
		 
		 c1.setDataCadastro(Calendar.getInstance());
		 
		 // Inserindo Briefings e os arquivos
		 Briefing b = new Briefing();
		 b.setStatus("Aprovado");
		 b.setAnotacoes("Briefing Aprovado");
		 b.setCodigo("169-06");
		 b.setCliente(c1);
		 b.setUrlPlanilhaCustos(ImagesUtil.urlPlanilhasCusto + "PLANILHA_CUSTOS_2013.xlsx");
		 
		 
		 //Lista com os arquivos de briefing
		 List<BriefingFiles> brifiles = new ArrayList<BriefingFiles>();
		 
		 BriefingFiles bri1 = new BriefingFiles();
		 bri1.setNomeArquivo("01OurIdentity1_01");
		 bri1.setDescricao("Descrição Air Products");
		 bri1.setUrlFile(ImagesUtil.urlBriefings + "01OurIdentity1_01.pdf" );
		 bri1.setBriefing(b);
		 
		 BriefingFiles bri2 = new BriefingFiles();
		 bri2.setNomeArquivo("02Basic+Elements1_04");
		 bri2.setDescricao("Elementos Básicos");
		 bri2.setUrlFile(ImagesUtil.urlBriefings + "02Basic+Elements1_04.pdf" );
		 bri2.setBriefing(b);
		 
		 BriefingFiles bri3 = new BriefingFiles();
		 bri3.setNomeArquivo("08MarketingMtls1_01");
		 bri3.setDescricao("Marketing");
		 bri3.setUrlFile(ImagesUtil.urlBriefings + "08MarketingMtls1_01.pdf" );
		 bri3.setBriefing(b);
		 
		 BriefingFiles bri4 = new BriefingFiles();
		 bri4.setNomeArquivo("09_Advertising");
		 bri4.setDescricao("Anuncio");
		 bri4.setUrlFile(ImagesUtil.urlBriefings + "09_Advertising.pdf" );
		 bri4.setBriefing(b);
		 
		 BriefingFiles bri5 = new BriefingFiles();
		 bri5.setNomeArquivo("10Exhibitions5_03");
		 bri5.setDescricao("Exibição");
		 bri5.setUrlFile(ImagesUtil.urlBriefings + "10Exhibitions5_03.pdf" );
		 bri5.setBriefing(b);
		 
		 BriefingFiles bri6 = new BriefingFiles();
		 bri6.setNomeArquivo("B - 169-06 - Air Products - Feira Mecânica");
		 bri6.setDescricao("Briefing Air Products 169-06");
		 bri6.setUrlFile(ImagesUtil.urlBriefings + "B-169-06-Air_Products_Feira_Mecânica.doc" );
		 bri6.setBriefing(b);
		 
		 
		 brifiles.add(bri1);
		 brifiles.add(bri2);
		 brifiles.add(bri3);
		 brifiles.add(bri4);
		 brifiles.add(bri5);
		 brifiles.add(bri6);
		 
		 b.setFiles(brifiles);
		 List<Briefing> briefings = new ArrayList<Briefing>();
		 briefings.add(b);
		 c1.setBriefings(briefings);
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 //Lista de fotos referencia
		 List<FotoCliente> fotos = new ArrayList<FotoCliente>();
		 
		 
		 FotoCliente fc = new FotoCliente();
		 fc.setCliente(c1);
		 fc.setLegenda("Air Products - 2006 - Feira Mecânica");
		 fc.setNomeImagem("Air Products - 2006 - Feira Mecânica");
		 fc.setUrlimagem(ImagesUtil.urlFotoCliente+"air2006.jpg");
		 
		 FotoCliente fc2 = new FotoCliente();
		 fc2.setCliente(c1);
		 fc2.setLegenda("Air Products - 2012 - Feira Mecânica");
		 fc2.setNomeImagem("Air Products - 2012 - Feira Mecânica");
		 fc2.setUrlimagem(ImagesUtil.urlFotoCliente+"air2012.jpg");
		 fotos.add(fc);
		 fotos.add(fc2);
		 
		 c1.setFotosReferencia(fotos);
		
		 ClienteDAO dao = new ClienteDAO();
		 Cliente banco = dao.inserir(c1);
		
		 AnoBO abo = new AnoBO();
		 abo.relacionarAnosCliente(banco, anos);
		 
		 
		 
		 
		 
		 /************************************ Inserir projetos do Cliente Air Products *********************************/
		 Projeto proj1 = new Projeto();
		 proj1.setAno("2006");
		 proj1.setArquiteto("Arquiteto Teste");
		 proj1.setMetragem("Até 20m²");
		 proj1.setCliente(banco);
		 proj1.setSegmento("Teste");
		 
		 //Varias fotos da mesma versao
		 	VersaoProjeto v1 = new VersaoProjeto();
		 	v1.setVersao("Versão 1");
		 	v1.setProjeto(proj1);
		 	
			FotoProjeto fo1 = new FotoProjeto();
			fo1.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020014.JPG");
			fo1.setVersaoprojeto(v1);
			FotoProjeto fo2 = new FotoProjeto();
			fo2.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020015.JPG");
			fo2.setVersaoprojeto(v1);
			FotoProjeto fo3 = new FotoProjeto();
			fo3.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020016.JPG");
			fo3.setVersaoprojeto(v1);	
			FotoProjeto fo4 = new FotoProjeto();
			fo4.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020017.JPG");
			fo4.setVersaoprojeto(v1);	
			FotoProjeto fo5 = new FotoProjeto();
			fo5.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020018.JPG");
			fo5.setVersaoprojeto(v1);	
			FotoProjeto fo6 = new FotoProjeto();
			fo6.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020019.JPG");
			fo6.setVersaoprojeto(v1);	
			FotoProjeto fo7 = new FotoProjeto();
			fo7.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020020.JPG");
			fo7.setVersaoprojeto(v1);	
			
			List<FotoProjeto> fotosp = new ArrayList<FotoProjeto>();
			fotosp.add(fo1);
			fotosp.add(fo2);
			fotosp.add(fo3);
			fotosp.add(fo4);
			fotosp.add(fo5);
			fotosp.add(fo6);
			fotosp.add(fo7);
			
			
			v1.setFotosProjeto(fotosp);
			
			//Outra versão de projeto
			VersaoProjeto v2 = new VersaoProjeto();
			v2.setVersao("Versão 2");
			v2.setProjeto(proj1);
			
			
			//Fotos dessa outra versao
			FotoProjeto fo8 = new FotoProjeto();
			fo8.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020019a.JPG");
			fo8.setVersaoprojeto(v2);
			
			FotoProjeto fo9 = new FotoProjeto();
			fo9.setUrlImagem(ImagesUtil.urlFotosProjetos + "carpete.JPG");
			fo9.setVersaoprojeto(v2);
			
			FotoProjeto fo10 = new FotoProjeto();
			fo10.setUrlImagem(ImagesUtil.urlFotosProjetos + "colocacao_acabamento_parede_visinho.JPG");
			fo10.setVersaoprojeto(v2);
			
			FotoProjeto fo11 = new FotoProjeto();
			fo11.setUrlImagem(ImagesUtil.urlFotosProjetos + "colocacao_cordao_rodape.JPG");
			fo11.setVersaoprojeto(v2);
			
			FotoProjeto fo12 = new FotoProjeto();
			fo12.setUrlImagem(ImagesUtil.urlFotosProjetos+ "Colocacao_Imagens_A.JPG");
			fo12.setVersaoprojeto(v2);
			FotoProjeto fo13 = new FotoProjeto();
			fo13.setUrlImagem(ImagesUtil.urlFotosProjetos+ "Colocacao_Insulfim_A.JPG");
			fo13.setVersaoprojeto(v2);
			FotoProjeto fo14 = new FotoProjeto();
			fo14.setUrlImagem(ImagesUtil.urlFotosProjetos + "colocacao_insulfim_B.JPG");
			fo14.setVersaoprojeto(v2);
			FotoProjeto fo15 = new FotoProjeto();
			fo15.setUrlImagem(ImagesUtil.urlFotosProjetos + "Colocacao_Logos_Adicionais_(mesa e paredes).JPG");
			fo15.setVersaoprojeto(v2);
			FotoProjeto fo16 = new FotoProjeto();
			fo16.setUrlImagem(ImagesUtil.urlFotosProjetos + "Colocacao_logos_laterais.JPG");
			fo16.setVersaoprojeto(v2);
			FotoProjeto fo17 = new FotoProjeto();
			fo17.setUrlImagem(ImagesUtil.urlFotosProjetos + "Colocacao_Mais_Iluminacao.JPG");
			fo17.setVersaoprojeto(v2);
			FotoProjeto fo18 = new FotoProjeto();
			fo18.setUrlImagem(ImagesUtil.urlFotosProjetos + "S2020016a.JPG");
			fo18.setVersaoprojeto(v2);
			
			
			List<FotoProjeto> fotos2 = new ArrayList<FotoProjeto>();
			fotos2.add(fo8);
			fotos2.add(fo9);
			fotos2.add(fo10);
			fotos2.add(fo11);
			fotos2.add(fo12);
			fotos2.add(fo13);
			fotos2.add(fo14);
			fotos2.add(fo15);
			fotos2.add(fo16);
			fotos2.add(fo17);
			fotos2.add(fo18);
			
			v2.setFotosProjeto(fotos2);
			
			
			//Fazendo lista das versões do projeto
			List<VersaoProjeto> versoes = new ArrayList<VersaoProjeto>();
			versoes.add(v1);
			versoes.add(v2);
			
			proj1.setVersoes(versoes);
			
			ProjetoBO projbo = new ProjetoBO();
			projbo.criarProjeto(proj1);
			
			
		 
		
	}
	
	
	
	public void localizarRazaoExata(){
		ClienteDAO dao = new ClienteDAO();
		boolean c = dao.localizarPorRazaoSocialExata("Air Products brasil ltda");
			System.out.println(c);
	
	}
	
	
	
	
	
	
}
