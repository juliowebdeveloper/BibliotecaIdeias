package br.com.bibideais.teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.bibideais.dao.FornecedorDAO;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Fornecedor;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.FornecedorBO;

public class FornecedorTeste {

	
	
	public static void main(String[] args) {
		FornecedorTeste t = new FornecedorTeste();
		for(int i =0; i<100; i++){
			t.inserirFornecedor();

		}
		//t.buscarPorSegmentoAndAssociated();
		//t.buscarPorSegmentoAndNOtAssociated();
		//t.testarMap();
		//t.linkedList();
	}
	
	
	public void linkedList(){
		FornecedorBO bo = new FornecedorBO();
		LinkedList<Fornecedor> list = new LinkedList<Fornecedor>();
		list = bo.buscarUltimosCadastrados();
		for (Fornecedor fornecedor : list) {
			System.out.println(fornecedor.getRazaoSocial());
		}
	} 
	
	public void testarMap(){
		FornecedorDAO dao = new FornecedorDAO();
		
		
		HashMap <String, String> parametros = new HashMap<String, String>();
		//fazendo teste passando razão social e segmento e associated = true
		parametros.put("razaosocial", "nova");
		parametros.put("segmento", "buffet");
		boolean asso = true;
		System.out.println(parametros.size());
		
		List<Fornecedor> forns1 = new ArrayList<Fornecedor>();
		forns1 = dao.filteredSearch(parametros, asso);
		for (Fornecedor fornecedor : forns1) {
			System.out.println("Buscado por razao e segmento e associated true" + fornecedor.getRazaoSocial() + fornecedor.isFornecedorBi());
		}
		
		
		
		
		
		//fazendo o teste passando razão social e segmento e associated = false
		HashMap <String, String> parametros1 = new HashMap<String, String>();
		System.out.println("Buscando por segmento e Razão social e associated = false");
		parametros1.put("razaosocial", "nova");
		parametros1.put("segmento", "buffet");
		boolean asso1 = false;
		
		List<Fornecedor> forns2 = new ArrayList<Fornecedor>();
		forns2 = dao.filteredSearch(parametros1, asso1);
		for (Fornecedor fornecedor : forns2) {
			System.out.println("Buscado por razao e segmento e associated false" + fornecedor.getRazaoSocial() + fornecedor.isFornecedorBi());
		}
		
		
		
		
		
		
		//fazendo o teste passando só razão social
		System.out.println("Buscando só pela razão social");
		HashMap <String, String> parametros2 = new HashMap<String, String>();
		parametros2.put("razaosocial", "nova");
		boolean asso2 = true;
		
		
		List<Fornecedor> forns3 = new ArrayList<Fornecedor>();
		forns3 = dao.filteredSearch(parametros2, asso2);
		for (Fornecedor fornecedor : forns3) {
			System.out.println("Buscado só por razão social " + fornecedor.getRazaoSocial() + fornecedor.isFornecedorBi());
		}
		
		
		
		
		
		
		//fazendo o teste passando só segmento
		System.out.println("Buscando só pelo segmento");
		HashMap <String, String> parametros3 = new HashMap<String, String>();
		parametros3.put("segmento", "buffet");
		boolean asso3 = true;
		
		List<Fornecedor> forns4 = new ArrayList<Fornecedor>();
		forns4 = dao.filteredSearch(parametros3, asso3);
		for (Fornecedor fornecedor : forns4) {
			System.out.println("Buscado por segmento e true " + fornecedor.getRazaoSocial() + fornecedor.isFornecedorBi());
		}
		
		
		
		
		
	}
	
	
	
	
	public void inserirFornecedor(){
		Cidade cidade = new Cidade();
		cidade = new CidadeBO().buscarPorId(206);
		
		
		//Fornecedor f = new Fornecedor("", "Distak Comunicação Visual", "", "Comunicação Visual",  listacontatos, "Av. Interlagos","2240", null, "Interlagos", "04660-002", cidade, true);
		//Fornecedor f = new Fornecedor("Rayer eventos", "TV plasma, Led, geladeira, som, luz", "Equipamentos", contatos, endereco, numero, complemento, bairro, cep, cidade, isFornecedorBi, email, telefone, nomeContato, cargo, site)
		Fornecedor f1 = new Fornecedor("Rayer eventos", "TV plasma, Led, geladeira, som, luz", "Equipamentos", null, null, null, null, null, cidade, true, "diego@rayereventos.com.br", "11-2261-5772", null, null, null, null, "Diego", null, null, null);
		Fornecedor f2 = new Fornecedor("JOSÉ MAIO ESTILISTA", "Estilista, bordados, malharia", "Uniformes", null, null, null, null, null, cidade, true, "Jmcriacoes@uol.com.br", "11-4425-2904", null, null, null, "11-9-9708-9557", "Valentina", null, null, null);
		Fornecedor f3 = new Fornecedor("Nova Eventos Corporativos Ltda", "Doceira, buffet", "Buffet", null, null, null, null, null, cidade, true, "atendimento@novaeventosbrasil.com", "11-2809-0271", null, null, null, "11- 9-6664-1720", "Adriano Paula da Silva", null, null, null);
		Fornecedor f4 = new Fornecedor("Hotma Arquitetura de Eventos", "Estandes", "Montadoras", "Rua: Moises Kauffmann", "340", null, "Barra Funda", "01140-010", cidade, true, "Getulio@hotma.com.br", "11-3728-4000", "11-7728-5133", null, "55*18290*248", null, "Getulio Tamada", "Sócio Diretor", "www.hotma.com.br", null);
		Fornecedor f5 = new Fornecedor("ATIVA EVENTOS", "Estandes", "Montadoras", "Rua Nilza","353", null, "Vila Esperança", "03651-120", cidade, true, "roberto@ativaeventos-loc.com.br", null, null, null, null, "11-9-9972-8081", "Roberto Tuzdjian", null, "WWW.ativaeventos-loc.com.br", null);
		Fornecedor f6 = new Fornecedor("Emmanuelle Mortalez", " garçon, mestre cerimônia, fotógrafo, arquitetos, tradutores, advogados, contadores, maquiadora, ajudante, recepcionista, copeira, segurança", "Staff", null, null, null, null, null, cidade, true, "emmanuellemoralez@hotmail.com", "11-2741-6478", null, null, null, "11-9-8188-1459", null, null, null, null);

		
		FornecedorBO bo = new FornecedorBO();
		bo.cadastrar(f1);
		bo.cadastrar(f2);
		bo.cadastrar(f3);
		bo.cadastrar(f4);
		bo.cadastrar(f5);
		bo.cadastrar(f6);
		
	}
	
	public void buscarPorSegmentoAndAssociated(){
		FornecedorBO bo = new FornecedorBO();
		List<Fornecedor> list = bo.buscarPorSegmentoAndAssociated("Comunicação Visual");
		for (Fornecedor fornecedor : list) {
		//	System.out.println(fornecedor.getNomeFantasia());
		}
	}
	
	public void buscarPorSegmentoAndNOtAssociated(){
		FornecedorBO bo = new FornecedorBO();
		List<Fornecedor> list = bo.buscarPorSegmentoAndNotAssociated("Comunicação Visual");
		for (Fornecedor fornecedor : list) {
			System.out.println(fornecedor.getRazaoSocial());
		}
	}
	
	
}
