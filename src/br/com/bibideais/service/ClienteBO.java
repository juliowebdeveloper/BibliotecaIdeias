package br.com.bibideais.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.DataException;

import br.com.bibideais.dao.ClienteDAO;
import br.com.bibideais.dao.ContatoClienteDAO;
import br.com.bibideais.dao.FichaDAO;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ClienteFeira;
import br.com.bibideais.entity.ClienteFeiraID;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Ficha;
import br.com.bibideais.entity.Funcionario;

public class ClienteBO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClienteDAO dao;
	
	private FichaDAO fichadao;
	
	private AnoBO anobo;
	
	private BriefingBO bribo;
	
	private FotoBO fcbo;
	
	private ContatoClienteDAO cdao ;

	
	public void cadastrarCliente(Cliente cliente, List<Ano> anos, List<Feira> feiras){
		//Primeiro cadastra-se o cliente e busca o codigo de volta 
		//Com isso as fichas, fotos referencia e contatos já são cadastrados.
		dao = new ClienteDAO();
		Cliente banco = dao.inserir(cliente);
		
		
		//Cadatra os anos do cliente
		anobo = new AnoBO();
		anobo.relacionarAnosCliente(banco, anos);
		
		//Relacionar as feiras ao cliente
		FeiraBO fbo = new FeiraBO();
		fbo.relacionarFeirasAoCliente(banco, feiras);
	}
	
	
	public void excluirCliente(Cliente c){
		dao = new ClienteDAO();
		dao.excluir(c.getIdCliente());
	}
	
	public Cliente cadastrarCliente(Cliente cliente){
		dao = new ClienteDAO();
		boolean achou = dao.localizarPorRazaoSocialExata(cliente.getRazaoSocial());
		Cliente banco = null;
		if(!achou){
			banco = dao.inserir(cliente);

		}
		return banco;
	}
	
	public boolean verificarCliente(Cliente cliente){
		dao = new ClienteDAO();
		boolean achou = dao.localizarPorRazaoSocialExata(cliente.getRazaoSocial());
		
		return achou;
	}
	
	
	public BigInteger countCliente(){
		dao = new ClienteDAO();
		return dao.countCliente();
	}
	
	public ContatoCliente inserirContato(ContatoCliente c){
		ContatoClienteDAO cdao = new ContatoClienteDAO();
		return cdao.inserir(c);
	}
	
	//Altera diretamente para "SIM" já que nao poderá voltar para "NÂO"
	public void alterarRecebeuTrabalhos(Cliente c){
		dao = new ClienteDAO();
		dao.alterarRecebeuTrabalhos(c);
	}
	
	
	//Altera diretamente para "SIM" já que nao poderá voltar para "NÂO"
	public void alterarIsClient(Cliente c){
		dao = new ClienteDAO();
		dao.alterarIsClient(c);
	}

	//Usado pra atualizar fotos fichas e briefings.
	public Cliente updateCliente(Cliente cliente){
		dao = new ClienteDAO();
		return dao.updateCliente(cliente);
	}
	
	
	public void alterarAtendimento(Cliente c, Funcionario func){
		dao = new ClienteDAO();
		dao.alterarAtendimento(c, func);
	}
	
	//Atualiza os dados basicos
	public Cliente atualizarCliente(Cliente cliente){
		dao = new ClienteDAO();
		return dao.updateCliente(cliente);
	}
	
	public void atualizarCidade(Cliente cliente, Cidade cid){
		dao = new ClienteDAO();
		dao.atualizarCidade(cliente, cid);
	}
	
	public void atualizarAnotacoes(Cliente c){
		dao = new ClienteDAO();
		dao.atualizarAnotacoes(c, c.getAnotacoes());
		
	}
	
	//Atualiza apenas o cartão
	public Cliente atualizarFotoCartao(Cliente c){
		dao = new ClienteDAO();
		return dao.atualizarFotoCartao(c);
	}
	
	
	public ContatoCliente atualizarContatoCliente(ContatoCliente contato){
		ContatoClienteDAO cdao = new ContatoClienteDAO();
		return cdao.atualizarContato(contato);
	}
	
	public void excluirContato(ContatoCliente contato){
		ContatoClienteDAO cdao = new ContatoClienteDAO();
		cdao.excluir(contato.getIdContatoCliente());
	}
	
/*	
	**************Metodos relacionados à e-mails clientes ********************
	*/
	
	
	public List<String> buscarAllEmails(){
		ContatoClienteDAO cdao = new ContatoClienteDAO();
		return cdao.buscarTodosEmails();
	}
	
	
	
	/* ******************* Metodos relacionados à ficha ****************************************/
	
	public Ficha atualizarNomeFicha(Ficha f){
		fichadao = new FichaDAO();
		return fichadao.atualizarNomeFichaQuery(f);
	}
	
	public Ficha inserirFicha(Ficha f){
		fichadao = new FichaDAO();
		return fichadao.inserir(f);
	}
	
	public void inserirFichas(List<Ficha> fs){
		fichadao = new FichaDAO();
		fichadao.inserirFichas(fs);
	}
	
	public void excluirFicha(Ficha f){
		fichadao = new FichaDAO();
		fichadao.excluirFichaQuery(f);
	}
	
	
	
	/* ******************* Metodos relacionados aos contatos ****************************************/

	
	
	public String inserirContatos(List<ContatoCliente> contatos){
		String result = null;
		try{
			cdao = new ContatoClienteDAO();
			cdao.inserirContatos(contatos);
			result = null;
			
		}catch(DataException de){
			
			de.printStackTrace();
			result = "Data Exception";
			
		}catch(Exception e){
			e.printStackTrace();
			result ="Erro";
		}
		
		return result;
	}
	
	
	public List<ContatoCliente> buscarContatos(Cliente c){
		cdao = new ContatoClienteDAO();
		return cdao.buscarContatosPorCliente(c);
	}
	
	
	
	
	/****************************Buscas de cliente***********************************/
	
	//Busca o cliente full
	public Cliente buscarClienteCompleto(int id){
		dao = new ClienteDAO();
		Cliente c = dao.findByLazyInteger(id);
		FeiraBO bo = new FeiraBO();
		//Busca as feiras e seta elas, o resto vem completo já.
		c.setFeiras(bo.buscarFeiraPorCliente(c));
		List<Briefing> brifs = c.getBriefings();
		
		//Ordenadno os Briefings do Cliente
		Collections.sort (brifs, new Comparator<Briefing> () {    
		    public int compare (Briefing b1, Briefing b2) {    
		        return b1.getCodigo().toUpperCase().compareTo (b2.getCodigo().toUpperCase());    
		    } 
		});

	
		c.setBriefings(brifs);
		
		return c;
	}
	
	
	
	
	public List<Cliente> buscarPorFeira(Feira f){
		dao = new ClienteDAO();
		return dao.buscarPelaFeira(f);
	}
	
	
	
	
	
	public List<Cliente> buscarPorAnos(List<Ano> anos){
		dao = new ClienteDAO();
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (Ano ano : anos) {
			List<Cliente> templist = new ArrayList<Cliente>();
			templist = dao.buscarClientesPorAno(ano);
			clientes.addAll(templist);
		}
		
		List<Cliente> clientesClean = new ArrayList<Cliente>();
		
		//Limpando a lista de duplicidades
		for(int i = 0;  i < clientes.size(); i++){
			if(clientesClean.isEmpty()){
				clientesClean.add(clientes.get(i));
			}else{
				int count = 0;
				for (Cliente cliente : clientesClean) {
					if(clientes.get(i).getIdCliente() == cliente.getIdCliente()){
						count++;
					}
				}
				
				if(count == 0){
					clientesClean.add(clientes.get(i));
				}
			}
			
			
			
		}
		
		return clientesClean;
	}
	
	
	
	
	
	public List<Cliente> completeFilteredSearch(HashMap<String, String> parametros, Feira f, List<Ano> anos, boolean recebeu, boolean isClient){
		dao = new ClienteDAO();
		return dao.completeFilteredSearch(parametros, f, anos, recebeu, isClient);
		
	}
	
	
	
	public List<Cliente> buscarAllClients(){
		dao = new ClienteDAO();
		return dao.localizarAll();
	}
	

	//Busca apenas pelo nome fantasia sem se preocupar se é cliente BI ou nao
	public List<Cliente> buscarPorNomeFantasia(String nomeFantasia) {
	dao = new ClienteDAO();
		return dao.buscarPorNomeFantasia(nomeFantasia);
	}

	
	public List<Cliente> buscarPorFeiraAndAnos(Feira f, List<Ano> anos, boolean recebeu){
		dao = new ClienteDAO();
		return dao.buscarPorFeiraAndAnos(f, anos, recebeu);
	}
	
	
	
	
	public List<Cliente> searchStringParameters(HashMap<String, String> parametros, boolean isClient){
		dao = new ClienteDAO();
		return dao.searchStringParameters(parametros, isClient);
	}
	
	
	
	
	public List<Cliente> buscarPorSegmentos(HashMap<String, String> segmentos,boolean isClient) {
		dao = new ClienteDAO();
		return dao.buscarPorSegmentos(segmentos, isClient);
	}
	
	
	
	public List<Cliente> verificarClientesPorAno(List<Cliente> clientes, List<Ano> anos){
		dao = new ClienteDAO();
		return dao.verificarClientesPorAno(clientes, anos);
	}
	
	
	
	
	
	
	public Cliente localizarPorId(int id){
		dao = new ClienteDAO();
		return dao.localizar(id);
	}
	
	
	public Cliente getReferenceID(int id){
		dao = new ClienteDAO();
		return dao.findByLazyInteger(id);
	}
	
	
	//Cliente e nao cliente
	public List<Cliente> buscarTodos(){
		dao = new ClienteDAO();
		return dao.buscarAll();
	}
	
	//Só cliente
	public List<Cliente> buscarTodosClientes(){
		dao = new ClienteDAO();
		return dao.buscarTodosClientes();
	}
	
	//Só não clientes
	
	public List<Cliente> buscarTodosNotCliente(){
		dao = new ClienteDAO();
		return dao.buscarNotClientes();
	}
	
	
	public List<Cliente> buscarPorCidade(Cidade cidade){
		dao = new ClienteDAO();
		return dao.buscarPorCidade(cidade);
	}
	
	
	//Segmentos
	
	public List<Cliente> buscarPorSegmentoPrincipal(String segmento, String isClient){
		dao = new ClienteDAO();
		List<Cliente> lista = new ArrayList<Cliente>();
		if(segmento.equalsIgnoreCase("Principal")){
			if(isClient.equalsIgnoreCase("true")){
				lista = dao.buscarPorSegmentoAndIsClient(segmento);

			}else if (isClient.equalsIgnoreCase("false")){
				lista = dao.buscarPorSegmentoAndNotClient(segmento);
			}else{
				lista = dao.buscarPorSegmentoPrincipal(segmento);

			}
		}
		
		if(segmento.equalsIgnoreCase("Secundario")){
			if(isClient.equalsIgnoreCase("true")){
				lista = dao.buscarPorSegmentoSecundarioAndIsClient(segmento);

			}else if (isClient.equalsIgnoreCase("false")){
				lista = dao.buscarPorSegmentoSecundarioAndNotClient(segmento);
			}else{
				lista = dao.buscarPorSegmentoSecundario(segmento);

			}
		
		}
		if(segmento.equalsIgnoreCase("Terciario")){
			if(isClient.equalsIgnoreCase("true")){
				lista = dao.buscarPorSegmentoTerciarioAndIsClient(segmento);

			}else if (isClient.equalsIgnoreCase("false")){
				lista =dao.buscarPorSegmentoTerciarioAndNotClient(segmento);
			}else{
				lista = dao.buscarPorSegmentoTerciario(segmento);

			}
		
		}
		
		return lista;
		
	}


	
	//Categoria
	public List<Cliente> buscarPorCategoria(String categoria, String isClient){
		dao = new ClienteDAO();
		if(isClient.equalsIgnoreCase("true")){
			return dao.buscarPorCategoriaAndIsClient(categoria);

		}else{
			return dao.buscarPorCategoria(categoria);

		}
	}
	

	
	
	
	//Relacionar feiras
	public boolean relacionarClienteFeira(Cliente c, Feira f){
		dao = new ClienteDAO();
		ClienteFeiraID id = new ClienteFeiraID();
		id.setIdCliente(c);
		id.setIdFeira(f);
		ClienteFeira cf = new ClienteFeira();
		cf.setId(id);
		return dao.relacionarClienteFeira(cf);
	}
	
	//Traz todos os clientes daquela feira - Que são clientes BI
	public List<Cliente> buscarClientesFeira(Feira feira){
		dao = new ClienteDAO();
		return dao.buscarPorFeiras(feira);
	}
	
	//Traz todos os clientes daquela feira - Que são clientes BI
		public List<Cliente> buscarClientesFeiraAndIsClient(Feira feira){
			dao = new ClienteDAO();
			return dao.buscarPorFeirasAndIsClient(feira);
		}
		
		
	
	//Anos
	public void relacionarAnoCliente(Cliente c, List<Ano> anos ){
		anobo = new AnoBO();
		try{
			anobo.relacionarAnosCliente(c, anos);

		}catch(Exception e){
			e.printStackTrace();
		}
	}




	
	
	public List<Cliente> buscarPorAtendimento(Funcionario f){
		dao = new ClienteDAO();
		return dao.buscarPorAtendimento(f);
	}
	
	
	
	public List<Cliente> buscarUltimosCadastrados(){
		dao = new ClienteDAO();
		return dao.buscarUltimosCadastrados();
	}
	
	
	
	
	
	
	
	
	
	



	public ClienteDAO getDao() {
		return dao;
	}







	public void setDao(ClienteDAO dao) {
		this.dao = dao;
	}







	public AnoBO getAnobo() {
		return anobo;
	}







	public void setAnobo(AnoBO anobo) {
		this.anobo = anobo;
	}





	public FichaDAO getFichadao() {
		return fichadao;
	}





	public void setFichadao(FichaDAO fichadao) {
		this.fichadao = fichadao;
	}





	public BriefingBO getBribo() {
		return bribo;
	}





	public void setBribo(BriefingBO bribo) {
		this.bribo = bribo;
	}





	public FotoBO getFcbo() {
		return fcbo;
	}





	public void setFcbo(FotoBO fcbo) {
		this.fcbo = fcbo;
	}






	
	
}
