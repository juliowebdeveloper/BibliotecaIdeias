package br.com.bibideais.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bibideais.converter.AnoConverter;
import br.com.bibideais.converter.FeiraConverter;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.AnoBO;
import br.com.bibideais.service.ClienteBO;

/**
 * 
 * @author Shido
 * Trata a busca de clientes
 */
@ManagedBean(name ="cliBuscaBean")
@SessionScoped
public class ClienteBuscaBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	//Listas que serão usadas em todos os metodos
	
	private List <Feira> feiras;
	
	private List<Ano> anos;
	
	
	
	private ClienteBO bo;
	
	
	/********************** Busca Rapida ******************************/
	
	
	private String fantasiaFast;
	
	private List<Cliente> clientesFastSearch;
	
	
	
	
	
	
	
	/*************************** Busca completa *******************************/

	private String razaoCompleta;
	
	private String fantasiaCompleta;
	
	private String categoriaCompleta;
	
	private String segprinCompleta;
	
	
	private HashMap<String, String> parametrosCompletos;
	
	private Feira feiraCompletaSelecionada;
	
	private List<Ano> anosCompletoSelecionados;
	
	
	//Não precisa das booleans pois ele obviamente recebeu trabalhos ja que será buscado por anos

	private boolean isClientCompleto = true;
	
	private boolean recebeuTrabCompleto = true;
	

	private List<Cliente> clientesByCompleto;
	
	
	/*************************** Busca só por feiras *******************************/
	
	private Feira feiraOnlySelecionada;
	

	private List<Cliente> clientesByFeira;
	
	private boolean isClientFeira;
	
	private boolean recebeuTrabFeira;
	
	private boolean isClientFeiras;
	
	private boolean recebeuTrabFeiras;
	
	
	/*************************** Busca por feiras e anos *******************************/

	private Feira feiraAnosFeiraSelecionada;
	
	private List<Ano> anosFeirasAnosSelecionados;
	
	private List<Cliente> clientesByFeiraAno;
	
	//Não precisa das booleans pois ele obviamente recebeu trabalhos ja que será buscado por anos
	
	
	/*************************** Busca por anos *******************************/
	
	
	private List<Ano> anosSelecionados;
	

	private List<Cliente> clientesByAno;
	

	//Não precisa das booleans pois ele obviamente recebeu trabalhos ja que será buscado por anos
	
	/*************************** Busca por Strings *******************************/
	
	private String razaoString;
	
	private String fantasiaString;
	
	private String categoriaString;
	
	private String segprinString;
	
	
	private HashMap<String, String> parametrosString;

	private boolean isClientString;
	
	private boolean recebeuTrabString;
	

	private List<Cliente> clientesByString;
	
	
	
	/*************************** Busca por Segmentos *******************************/
	private HashMap<String, String> segmentos;
	
	
	private String segmentoPrincipal;
	
	private String segmentoSecundario;
	
	private String segmentoTerciario;
	
	
	private List<Cliente> clientesBySegmentos;
	
	private boolean isClientSegmento;
	
	
	
	
	
	
	private List<Cliente> allClients;
	
	
	
	private List<String> allEmails;
	
	
	
	
	
	@PostConstruct
	public void init(){
		parametrosCompletos = new HashMap<String, String>();
		parametrosString = new HashMap<String, String>();
		
		//Listas usadas em todos os selects
		feiras = FeiraConverter.feiras;
		anos = AnoConverter.anos;
		bo = new ClienteBO();
		
	}

	//Busca todos os cliente por nome fantasia sem se preocupar se é cliente BI
	public void fastSearch(){
		bo = new ClienteBO();
		clientesFastSearch = bo.buscarPorNomeFantasia(fantasiaFast);
		if(clientesFastSearch.size() > 50) {
			MessageUtil mu = new MessageUtil();
			mu.sendWarnMessageToUser("Atenção", "O resultado da sua busca retornou mais de 50 registros, refine um pouco mais para melhores resultados");
		}
	}

	public void buscarTodosEmailsCliente(){
		bo = new ClienteBO();
		allEmails = bo.buscarAllEmails();
		
	}
	
	
	public List<String> getAllEmails() {
		return allEmails;
	}


	public void setAllEmails(List<String> allEmails) {
		this.allEmails = allEmails;
	}


	public void buscarTodosClientes(){
		bo = new ClienteBO();
		AnoBO abo = new AnoBO();
		allClients = bo.buscarTodos();
		for (Cliente c : allClients) {
			c.setContatos(bo.buscarContatos(c));
		
			List<Integer> anosinteger = abo.buscarAnosPorCliente(c);
			List<Ano> anos = new ArrayList<Ano>();
			for (Integer integer : anosinteger) {
				Ano a = new Ano();
				a.setAno(integer);
				anos.add(a);
			}
			c.setAnostrabalhos(anos);
		}
		
		
	}
	
	
	public void buscarPorString(){
		parametrosString.put("razao", razaoString);
		parametrosString.put("fantasia", fantasiaString);
		parametrosString.put("categoria", categoriaString);
		parametrosString.put("segprincipal", segprinString);
		clientesByString = bo.searchStringParameters(parametrosString, isClientString);
		
		
	}

	
	
	public void buscarPorFeiraAnos(){
		
		clientesByFeiraAno = bo.buscarPorFeiraAndAnos(feiraAnosFeiraSelecionada, anosFeirasAnosSelecionados, true);
	
		
	}
	
	public void buscarPorFeiras(){
		clientesByFeira = bo.buscarClientesFeira(feiraOnlySelecionada);
	
		
	}
	
	
	public void buscarPorAnos(){
		clientesByAno = bo.buscarPorAnos(anosSelecionados);
	
		
	}
	
	
	
	public void buscaCompleta(){
		parametrosString.put("razao", razaoCompleta);
		parametrosString.put("fantasia", fantasiaCompleta);
		parametrosString.put("categoria", categoriaCompleta);
		parametrosString.put("segprincipal", segprinCompleta);
		
		clientesByCompleto = bo.completeFilteredSearch(parametrosCompletos, feiraCompletaSelecionada, anosCompletoSelecionados, true,true);
		
		
		
	}
	
	
	
	public void buscarPorSegmentos(){
		segmentos.put("segprincipal", segmentoPrincipal);
		segmentos.put("segsecundario", segmentoSecundario);
		segmentos.put("segterciario", segmentoTerciario);
		
		clientesBySegmentos = bo.buscarPorSegmentos(segmentos, isClientSegmento);
		
	}
	
	
	public List<Feira> getFeiras() {
		return feiras;
	}





	public void setFeiras(List<Feira> feiras) {
		this.feiras = feiras;
	}





	public List<Ano> getAnos() {
		return anos;
	}





	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}





	public HashMap<String, String> getParametrosCompletos() {
		return parametrosCompletos;
	}





	public void setParametrosCompletos(HashMap<String, String> parametrosCompletos) {
		this.parametrosCompletos = parametrosCompletos;
	}





	public HashMap<String, String> getParametrosString() {
		return parametrosString;
	}





	public void setParametrosString(HashMap<String, String> parametrosString) {
		this.parametrosString = parametrosString;
	}





	public String getRazaoCompleta() {
		return razaoCompleta;
	}





	public void setRazaoCompleta(String razaoCompleta) {
		this.razaoCompleta = razaoCompleta;
	}





	public String getCategoriaCompleta() {
		return categoriaCompleta;
	}





	public void setCategoriaCompleta(String categoriaCompleta) {
		this.categoriaCompleta = categoriaCompleta;
	}





	public String getSegprinCompleta() {
		return segprinCompleta;
	}





	public void setSegprinCompleta(String segprinCompleta) {
		this.segprinCompleta = segprinCompleta;
	}





	public Feira getFeiraCompletaSelecionada() {
		return feiraCompletaSelecionada;
	}





	public void setFeiraCompletaSelecionada(Feira feiraCompletaSelecionada) {
		this.feiraCompletaSelecionada = feiraCompletaSelecionada;
	}





	public List<Ano> getAnosCompletoSelecionados() {
		return anosCompletoSelecionados;
	}





	public void setAnosCompletoSelecionados(List<Ano> anosCompletoSelecionados) {
		this.anosCompletoSelecionados = anosCompletoSelecionados;
	}





	public boolean isClientCompleto() {
		return isClientCompleto;
	}





	public void setClientCompleto(boolean isClientCompleto) {
		this.isClientCompleto = isClientCompleto;
	}





	public boolean isRecebeuTrabCompleto() {
		return recebeuTrabCompleto;
	}





	public void setRecebeuTrabCompleto(boolean recebeuTrabCompleto) {
		this.recebeuTrabCompleto = recebeuTrabCompleto;
	}





	public Feira getFeiraOnlySelecionada() {
		return feiraOnlySelecionada;
	}





	public void setFeiraOnlySelecionada(Feira feiraOnlySelecionada) {
		this.feiraOnlySelecionada = feiraOnlySelecionada;
	}





	public boolean isClientFeira() {
		return isClientFeira;
	}





	public void setClientFeira(boolean isClientFeira) {
		this.isClientFeira = isClientFeira;
	}





	public boolean isRecebeuTrabFeira() {
		return recebeuTrabFeira;
	}





	public void setRecebeuTrabFeira(boolean recebeuTrabFeira) {
		this.recebeuTrabFeira = recebeuTrabFeira;
	}





	public boolean isClientFeiras() {
		return isClientFeiras;
	}





	public void setClientFeiras(boolean isClientFeiras) {
		this.isClientFeiras = isClientFeiras;
	}





	public boolean isRecebeuTrabFeiras() {
		return recebeuTrabFeiras;
	}





	public void setRecebeuTrabFeiras(boolean recebeuTrabFeiras) {
		this.recebeuTrabFeiras = recebeuTrabFeiras;
	}





	public Feira getFeiraAnosFeiraSelecionada() {
		return feiraAnosFeiraSelecionada;
	}





	public void setFeiraAnosFeiraSelecionada(Feira feiraAnosFeiraSelecionada) {
		this.feiraAnosFeiraSelecionada = feiraAnosFeiraSelecionada;
	}





	public List<Ano> getAnosFeirasAnosSelecionados() {
		return anosFeirasAnosSelecionados;
	}





	public void setAnosFeirasAnosSelecionados(List<Ano> anosFeirasAnosSelecionados) {
		this.anosFeirasAnosSelecionados = anosFeirasAnosSelecionados;
	}





	public List<Ano> getAnosSelecionados() {
		return anosSelecionados;
	}





	public void setAnosSelecionados(List<Ano> anosSelecionados) {
		this.anosSelecionados = anosSelecionados;
	}





	public String getRazaoString() {
		return razaoString;
	}





	public void setRazaoString(String razaoString) {
		this.razaoString = razaoString;
	}





	public String getCategoriaString() {
		return categoriaString;
	}





	public void setCategoriaString(String categoriaString) {
		this.categoriaString = categoriaString;
	}





	public String getSegprinString() {
		return segprinString;
	}





	public void setSegprinString(String segprinString) {
		this.segprinString = segprinString;
	}





	public boolean isClientString() {
		return isClientString;
	}





	public void setClientString(boolean isClientString) {
		this.isClientString = isClientString;
	}





	public boolean isRecebeuTrabString() {
		return recebeuTrabString;
	}





	public void setRecebeuTrabString(boolean recebeuTrabString) {
		this.recebeuTrabString = recebeuTrabString;
	}


	public ClienteBO getBo() {
		return bo;
	}


	public void setBo(ClienteBO bo) {
		this.bo = bo;
	}








	public List<Cliente> getClientesByCompleto() {
		return clientesByCompleto;
	}








	public void setClientesByCompleto(List<Cliente> clientesByCompleto) {
		this.clientesByCompleto = clientesByCompleto;
	}








	public List<Cliente> getClientesByFeiraAno() {
		return clientesByFeiraAno;
	}








	public void setClientesByFeiraAno(List<Cliente> clientesByFeiraAno) {
		this.clientesByFeiraAno = clientesByFeiraAno;
	}








	public List<Cliente> getClientesByAno() {
		return clientesByAno;
	}








	public void setClientesByAno(List<Cliente> clientesByAno) {
		this.clientesByAno = clientesByAno;
	}








	public List<Cliente> getClientesByString() {
		return clientesByString;
	}








	public void setClientesByString(List<Cliente> clientesByString) {
		this.clientesByString = clientesByString;
	}








	public List<Cliente> getClientesByFeira() {
		return clientesByFeira;
	}








	public void setClientesByFeira(List<Cliente> clientesByFeira) {
		this.clientesByFeira = clientesByFeira;
	}








	public HashMap<String, String> getSegmentos() {
		return segmentos;
	}








	public void setSegmentos(HashMap<String, String> segmentos) {
		this.segmentos = segmentos;
	}








	public String getSegmentoPrincipal() {
		return segmentoPrincipal;
	}








	public void setSegmentoPrincipal(String segmentoPrincipal) {
		this.segmentoPrincipal = segmentoPrincipal;
	}








	public String getSegmentoSecundario() {
		return segmentoSecundario;
	}








	public void setSegmentoSecundario(String segmentoSecundario) {
		this.segmentoSecundario = segmentoSecundario;
	}








	public String getSegmentoTerciario() {
		return segmentoTerciario;
	}








	public void setSegmentoTerciario(String segmentoTerciario) {
		this.segmentoTerciario = segmentoTerciario;
	}























	public boolean isClientSegmento() {
		return isClientSegmento;
	}








	public void setClientSegmento(boolean isClientSegmento) {
		this.isClientSegmento = isClientSegmento;
	}








	public List<Cliente> getClientesBySegmentos() {
		return clientesBySegmentos;
	}








	public void setClientesBySegmentos(List<Cliente> clientesBySegmentos) {
		this.clientesBySegmentos = clientesBySegmentos;
	}








	public List<Cliente> getAllClients() {
		return allClients;
	}








	public void setAllClients(List<Cliente> allClients) {
		this.allClients = allClients;
	}








	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getFantasiaString() {
		return fantasiaString;
	}


	public void setFantasiaString(String fantasiaString) {
		this.fantasiaString = fantasiaString;
	}


	public String getFantasiaCompleta() {
		return fantasiaCompleta;
	}


	public void setFantasiaCompleta(String fantasiaCompleta) {
		this.fantasiaCompleta = fantasiaCompleta;
	}

	public String getFantasiaFast() {
		return fantasiaFast;
	}

	public void setFantasiaFast(String fantasiaFast) {
		this.fantasiaFast = fantasiaFast;
	}

	public List<Cliente> getClientesFastSearch() {
		return clientesFastSearch;
	}

	public void setClientesFastSearch(List<Cliente> clientesFastSearch) {
		this.clientesFastSearch = clientesFastSearch;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
