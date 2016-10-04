package br.com.bibideais.teste;

import java.math.BigInteger;
import java.util.List;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.OrganizadoraBO;

public class OrganizadoraTeste {

	
	public static void main(String[] args) {
		OrganizadoraTeste t = new OrganizadoraTeste();
		//t.inserirOrganizadoras();
		//t.buscarOrganizadoraNome();
		//t.count();
		t.inserirOrgGenerica();
	}
	
	public void inserirOrgGenerica(){
		Cidade cidade = new Cidade();
		OrganizadoraBO bo = new OrganizadoraBO();

		cidade = new CidadeBO().buscarPorId(206);
		Organizadora org29 = new Organizadora("Organizadora Genérica - BI", null, null, null, null, "", "", "", null, "Endereço Indisponível", null, null, "Indisponível", "", cidade, ImagesUtil.urlOrgLogos + "bi.jpg");	
		bo.cadastrar(org29);
	}
	
	
	public void inserirOrganizadoras(){
		Cidade cidade = new Cidade();
		cidade = new CidadeBO().buscarPorId(206);
		Cidade barueri = new CidadeBO().buscarPorId(305);
		Cidade curitiba = new CidadeBO().buscarPorId(212);
		Cidade rio = new CidadeBO().buscarPorId(207);
		Cidade portoalegre = new CidadeBO().buscarPorId(214);
		Cidade joinville = new CidadeBO().buscarPorId(245);
		Cidade serra = new CidadeBO().buscarPorId(265)	;	
		Cidade fortaleza = new CidadeBO().buscarPorId(210);
		Cidade goiania = new CidadeBO().buscarPorId(218);
		
		Organizadora org1 = new Organizadora("BTS INFORMA FEIRAS, EVENTOS E EDITORA LTDA.", null, null, null, "Daniela Alves", "(11) 3598-7800", "(11) 3598-7801", "daniela.alves@btsmedia.biz", null, "Rua Bela Cintra", "967","AND 11 - CJ 112A","Consolação","01415003", cidade, ImagesUtil.urlOrgLogos + "btslogo.jpg");
		Organizadora org2 = new Organizadora("CIPA FM PUBLICACOES E EVENTOS LTDA", null, null, null, "Kelly", "(11) 5585-4355", "(11) 5585-4355", "kelly@cipanet.com.br", null, "R CORREIA DE LEMOS", "158", "2º AND", "CHACARA INGLESA", "04140000", cidade, ImagesUtil.urlOrgLogos + "cipalogo.jpg");
		Organizadora org3 = new Organizadora("BEAUTY FAIR EVENTOS E PROMOCOES LTDA", null, null, null, "Maria", "(11) 3373-4633", "(11) 3263-0052", "MARIA@BEAUTYFAIR.COM.BR", null, "AV PAULISTA","491", "CJ 82", "Bela vista", "01311000", cidade, ImagesUtil.urlOrgLogos + "beauty.png");
		Organizadora org4 = new Organizadora("AMBIENTEPRESS PRODUCOES SS LTDA", null, null, null, null,"(11) 3917-2878", "(11) 3917-2878", "rmai2@rmai.com.br", null, "RUA MANUEL CAMPELLO", "241", "", "VILA INACIO", "05206020", cidade, ImagesUtil.urlOrgLogos + "ambientepress.jpg");
		Organizadora org5 = new Organizadora("AGROCENTRO EMPREENDIMENTOS E PARTICIPAÇÕES LTDA", null, null, null, "Maurizio","(11) 5058.9400", null, "MAURIZIO.GERENCIA@CENTROIMIGRANTES.COM.BR", null, "RODOVIA DOS IMIGRANTES,,KM 1,5", null, null, "ÁGUA FUNDA", "04329900", cidade, ImagesUtil.urlOrgLogos + "agrocentro.jpg");	
		Organizadora org6 = new Organizadora("ALCANTARA EVENTOS LTDA", null, null, null, null, "(11) 4191-4324", "(11) 3104-7567", "amfp@alcantara.com.br", null, "RUA CEARA", "269", "CONJUNTO A", "Alphaville", "06465130", barueri, ImagesUtil.urlOrgLogos + "alcantaramachado.jpg");	
		Organizadora org7 = new Organizadora("BOAT SHOW EVENTOS LTDA", null, null, null, null, "(11) 2186-1000", "(11) 2186-1080", "INFO@BOATSHOW.COM.BR", null, "AVENIDA BRIGADEIRO FARIA LIMA", "3064", "10° ANDAR", " JARDIM PAULISTANO"	, "01451000", cidade, ImagesUtil.urlOrgLogos + "boatshow.jpg");	
		Organizadora org8 = new Organizadora("CLARION EVENTS BRASIL EXIBIÇOES E FEIRAS LTDA", null, null, null, null, "(11) 3214-1300", "(11) 3256-3513", "TAIS.FRAILE@CLARIONEVENTS.COM", null, "AL. SANTOS", "2441", "9 ANDAR", "Consolação", "01419101", cidade, ImagesUtil.urlOrgLogos + "clarion.jpg");	
		Organizadora org9 = new Organizadora("COUROMODA FEIRAS COMERCIAIS LTDA", null, null, null, null, "(11) 3897.6100", "(11) 3897.6186", "couromoda@couromoda.com.br", null, "R JOAO MANUEL", "923", "6° ANDAR", "CERQUEIRA CESAR", "01411001", cidade, ImagesUtil.urlOrgLogos + "couromoda.jpg");	
		Organizadora org10 = new Organizadora("DIRETRIZ FEIRAS E EVENTOS LTDA", null, null, null, null, "(41) 3075-1100", "(41) 3075-1121", "diretriz@diretriz.com.br", null, "RUA GRA NICCO", "113", "BLOCO 4 - 4° ANDAR", "MOSSUNGUE", "81200200", curitiba, ImagesUtil.urlOrgLogos + "diretriz.jpg");	
		Organizadora org11 = new Organizadora("EXPONOR BRASIL FEIRAS E EVENTOS LTDA", null, null, null, null, "(11) 3141-9444", "(11) 3141-9445", "exponor@exponor.com.br", null, "R PLINIO BARRETO", "285", "2º ANDAR", "BELA VISTA", "01313020", cidade, ImagesUtil.urlOrgLogos + "exponor.jpg");	
		Organizadora org12 = new Organizadora("FAGGA PROMOCAO DE EVENTOS S.A", null, null, null, null, "(21) 3035-3100", "(21) 3035-3100", "MAIL@FAGGA.COM.BR", null, "RUA CONDE DE IRAJA", "260", "1 ANDAR", "Botafogo", "22271020", rio, ImagesUtil.urlOrgLogos + "fagga.jpg");	
		Organizadora org13 = new Organizadora("FCEM - FEIRAS, CONGRESSOS E EMPREENDIMENTOS LTDA", null, null, null, null, "(51) 3382 0700", "(51) 3382 0700", "FCEM@FCEM.COM.BR", null, "RUA SAO BENEDITO", "662", null, "BOM JESUS", "91420530", portoalegre, ImagesUtil.urlOrgLogos + "fcem.jpg");	
		Organizadora org14 = new Organizadora("FEIRA E CIA EVENTOS LTDA", null, null, null, null, "(11) 3025-5555", "(11) 3025-5550", "DIRECAO@FEIRAECIA.COM.BR", null, "AVENIDA QUEIROZ FILHO", "1700", "CASA 58", "VILA LEOPOLDINA", "05319000", cidade, ImagesUtil.urlOrgLogos + "feiraecia.jpg");	
		Organizadora org15 = new Organizadora("FIORDE LOGÍSTICA INTERNACIONAL / TTI LOG LOGÍSTICA INTERNACIONAL", null, null, null, null, "(11) 3218-7000", "(11) 3218-7000", "FIORDE@FIORDE.COM.BR", null, "RUA FREI CANECA", "739", null, "CONSOLAÇÃO", "01307001", cidade, ImagesUtil.urlOrgLogos + "fiorde.jpg");	
		Organizadora org16 = new Organizadora("FRANCAL - FEIRAS E EMPREENDIMENTOS LTDA", null, null, null, null, "(11) 2226-3100", "(11) 2226-3200", "feiras@francal.com.br", null, "OTR Calçada das Hortências", "55", "Segundo Piso Alphaville Comercial","Alphaville","06453017", barueri, ImagesUtil.urlOrgLogos + "francal.jpg");	
		Organizadora org17 = new Organizadora("FREI CANECA SHOPPING CONV.CENTER LTDA", null, null, null, null, "(11) 3472-2000", "(11) 3472-2021", "WCHAGAS@FREICANECASHOPPING.COM.BR", null, "R FREI CANECA", "569", null, "CERQUEIRA CESAR", "01307001", cidade, ImagesUtil.urlOrgLogos + "freicaneca.jpg");	
		Organizadora org18 = new Organizadora("GEO EVENTOS S.A", null, null, null, null, "11 2344-4200", "11 2344-4247", "CONTATO@GEOEVENTOS.COM", null, "AVENIDA BRIGADEIRO LUÍS ANTÔNIO", "4893", "SALA 01", "JARDIM PAULISTA", "01401002", cidade, ImagesUtil.urlOrgLogos + "geo.jpg");	
		Organizadora org19 = new Organizadora("GL EVENTS CENTRO DE CONVENCOES LTDA", null, null, null, null, "(21) 3035-9100", "(21) 3035-9100", "RIOCENTRO@RIOCENTRO.COM.BR", null, "AV SALVADOR ALLENDE", "6555", "PAV. 3 - PORT. H","RECREIO DOS BANDEIRANTES", "22780160", rio, ImagesUtil.urlOrgLogos + "glevents.jpg");	
		Organizadora org20 = new Organizadora("GRAFITE FEIRAS E PROMOCOES LTDA", null, null, null, null, "(11) 2105-7000", "(11) 2105-7001", "TARSO.JORDAO@GRAFITEFEIRAS.COM.BR", null, "AV. JUSCELINO KUBITSCHECK", "1830", "13º ANDAR", "VILA OLIMPIA", "04543900", cidade, ImagesUtil.urlOrgLogos + "grafite.jpg");	
		Organizadora org21 = new Organizadora("HOSPITALAR FEIRAS,CONG. E EMPREENDIMENTOS LTDA", null, null, null, null,"(11) 3897-6162", "(11) 3897-6186", "HOSPITALAR@HOSPITALAR.COM.BR", null, "RUA PADRE JOAO MANUEL", "923", "6° ANDAR", "CERQUEIRA CESAR", "01411001", cidade, ImagesUtil.urlOrgLogos + "hospitalar.jpg");	
		Organizadora org22 = new Organizadora("MESSE BRASIL FEIRAS E PROMOCOES LTDA", null, null, null, null, "(47) 3451-3000", "(47) 3451-3001", "feiras@messebrasil.com.br", null, "RUA ARARANGUA", "77", null, "TERREO AMERICA", "89204310", joinville, ImagesUtil.urlOrgLogos + "messe.jpg");	
		Organizadora org23 = new Organizadora("MILANEZ & MILANEZE S/A", null, null, null, null, "(27) 3434-0600", "(27) 3434-0601", "INFO@MILANEZMILANEZE.COM.BR", null, "AV. JOSÉ MARTINS M RATO", "1117", null, "BAIRRO DE FÁTIMA", "29160790", serra, ImagesUtil.urlOrgLogos + "milanez.jpg");	
		Organizadora org24 = new Organizadora("NUERNBERGMESSE BRASIL - FEIRAS E CONGRESSOS LTDA", null, null, null, null, "(11) 3205-5000", "(11) 3205-5000", "nm-brasil@nm-brasil.com.br", null, "R VERBO DIVINO", "1547", "7 ANDAR","CHACARA SANTO ANTONIO", "04719002", cidade, ImagesUtil.urlOrgLogos + "nuernbergmesse.jpg");	
		Organizadora org25 = new Organizadora("PORTTE TURISMO E EVENTOS LTDA", null, null, null, null, "(71) 3341-3024", "(71) 3341-2519", "PORTTE@PORTTE.COM.BR", null, "Avenida Dom Luís", "500", "Sala 913 - Shopping Aldeota", "Meireles", "60160230", fortaleza, ImagesUtil.urlOrgLogos + "portte.jpg");	
		Organizadora org26 = new Organizadora("REED EXHIBITIONS ALCANTARA MACHADO LTDA", null, null, null, null, "(11) 3060-5000", "(11) 3060-5001", "RXAM@REEDEXPO.COM.BR", null, "RUA BELA CINTRA", "1200", "7° ANDAR", "CERQUEIRA CESAR", "01415001", cidade, ImagesUtil.urlOrgLogos + "redexhibitions.jpg");	
		Organizadora org27 = new Organizadora("SAO PAULO FEIRAS COMERCIAIS LTDA", null, null, null, null, "(11) 3897-6100", "(11) 3897-9191", "hairbrasil@hairbrasil.com.br", null, "RUA PADRE JOAO MANUEL", "923", "6° ANDAR", "CERQUEIRA CESAR", "01411001", cidade, ImagesUtil.urlOrgLogos + "saopaulofeiras.jpg");	
		Organizadora org28 = new Organizadora("TECNICAS PROMOCIONAIS DE EVENTOS LTDA", null, null, null, null, "(62) 3267-0700", "(62) 3267-0700", "INFO@TECNIPROM.COM.BR", null, "RUA 17 COM RUA 15-A,,S/N QUADRA 17 LOTE 03 A 11 E 35 A 43", null, null, "POLO EMPRESARIAL DE GOIAS", "74985215", goiania, ImagesUtil.urlOrgLogos + "tecniprom.jpg");	
		Organizadora org29 = new Organizadora("Organizadora Genérica - BI", null, null, null, null, "", "", "", null, "Endereço Indisponível", null, null, "Indisponível", "", cidade, ImagesUtil.urlOrgLogos + "bi.jpg");	

		
		
		
		
		OrganizadoraBO bo = new OrganizadoraBO();
		bo.cadastrar(org1);
		bo.cadastrar(org2);
		bo.cadastrar(org3);
		bo.cadastrar(org4);
		bo.cadastrar(org5);
		bo.cadastrar(org6);
		bo.cadastrar(org7);
		bo.cadastrar(org8);
		bo.cadastrar(org9);
		bo.cadastrar(org10);
		bo.cadastrar(org11);
		bo.cadastrar(org12);
		bo.cadastrar(org13);
		bo.cadastrar(org14);
		bo.cadastrar(org15);
		bo.cadastrar(org16);
		bo.cadastrar(org17);
		bo.cadastrar(org18);
		bo.cadastrar(org19);
		bo.cadastrar(org20);
		bo.cadastrar(org21);
		bo.cadastrar(org22);
		bo.cadastrar(org23);
		bo.cadastrar(org24);
		bo.cadastrar(org25);
		bo.cadastrar(org26);
		bo.cadastrar(org27);
		bo.cadastrar(org28);
		bo.cadastrar(org29);
		
		
		
		
	}
	
	
	public void buscarOrganizadoraNome(){
		OrganizadoraBO bo = new OrganizadoraBO();
		List<Organizadora> list = bo.localizarRazaoSocial("FAIR");
		for (Organizadora organizadora : list) {
			System.out.println(organizadora.getRazaoSocial());
		}
	}
	public void count(){
		OrganizadoraBO bo = new OrganizadoraBO();
		BigInteger i = bo.countOrganizadoras();
		System.out.println(i);
		System.out.println(bo.countOrganizadoras());
	}
}
