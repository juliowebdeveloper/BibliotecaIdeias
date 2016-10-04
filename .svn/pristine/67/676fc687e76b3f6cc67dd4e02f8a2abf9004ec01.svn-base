package br.com.bibideais.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.AnoBO;
import br.com.bibideais.service.BriefingBO;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.ProjetoBO;

/**
 * 
 * @author Shido
 * Trata a visualização de um cliente especifico
 */
@ManagedBean(name ="cliViewBean")
@ViewScoped
public class ClienteViewBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private String idCliente;
	
	
	
	private Cliente cliente;
	
	private List<Feira> feirasCli;
	
	private List<Ano> anos;
	
	private List<FotoCliente> fotosCliente;
	
	private List<Ficha> fichas;
	
	private List<Briefing> briefings;
	
	private List<ContatoCliente> contatos;
	
	private List<BriefingFiles> brifiles;
	
	private Briefing briefingSelecionado;
	
	
	private List<Projeto> projetos;
	
	private Projeto projetoSelecionado;
	
	private List<VersaoProjeto> versoes;
	
	private VersaoProjeto versaoSelecionada;
	
	private List<FotoProjeto> fotosProjetos;
	
	
	
	private ClienteBO clibo;
	
	private AnoBO anobo;
	
	private FeiraBO feiraBo;
	
	private ProjetoBO projbo;
	
	private BriefingBO bribo;
	
	
	
	@PostConstruct
	public void init(){
		idCliente = FacesUtil.getRequestParameter("clienteid");

		if(idCliente == null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Caso venha do cadastro de cliente, limpar os managedbeans
		HttpSession session =  FacesUtil.getSession();
		ClienteDadosBean dadoBean = (ClienteDadosBean) session.getAttribute("cliDadosBean");
		CadastroClienteBean clibean = (CadastroClienteBean) session.getAttribute("cadastroCliBean");
		Funcionario func = (Funcionario) session.getAttribute("funcionario");
		if(dadoBean != null){
			session.removeAttribute("cliDadosBean");
		}
		
		if(clibean != null){
			session.removeAttribute("cadastroCliBean");
		}
		
		
		
		clibo = new ClienteBO();
		cliente = clibo.buscarClienteCompleto(Integer.parseInt(idCliente));
		feirasCli = cliente.getFeiras();
		anos = cliente.getAnostrabalhos();
		fotosCliente = cliente.getFotosReferencia();
		fichas = cliente.getFichas();
		briefings = cliente.getBriefings();
		contatos = cliente.getContatos();
		
		projetos = cliente.getProjetos();
		
		
		if(session.getAttribute("funcionario") != null){
		
			//Inserindo Log
			LogBean.inserirLog(AcaoUtil.ACESSOU, func, cliente);
    	
		}
		
		
	}

	public void atualizarAnotacoes(){
		clibo = new ClienteBO();
		MessageUtil mu = new MessageUtil();
		try{
			clibo.atualizarAnotacoes(cliente);
			
			mu.sendInfoMessageToUser("Anotações alteradas",  null);
		}catch(Exception e){
			mu.sendErrorMessageToUser("Erro", "Não foi possível alterar as anotações");
		}
		
	}
	
	
	public String editarCliente(){
		HttpServletRequest request = FacesUtil.getRequest();
		
		  HttpSession session = FacesUtil.getSession();
	        
	        if(session.getAttribute("editCliBean") != null){
	        	session.removeAttribute("editCliBean");
	        }
	        request.setAttribute("clienteedit", cliente);
		
		return "editcliente.xhtml";
		
	}

	
	public void getVersions(){
		projbo = new ProjetoBO();
		versoes = projbo.buscarPeloProjeto(projetoSelecionado);
		
		
	}

	
	public void getFotosVersao(){
		projbo = new ProjetoBO();
		fotosProjetos = projbo.buscarFotoVersao(versaoSelecionada);
	
	}
	

	public void getBriefingFiles(){
		bribo = new BriefingBO();
		brifiles = bribo.buscarFilesPorBriefing(briefingSelecionado);
		
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}




	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}




	public String getIdCliente() {
		return idCliente;
	}




	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}




	public List<Feira> getFeirasCli() {
		return feirasCli;
	}




	public void setFeirasCli(List<Feira> feirasCli) {
		this.feirasCli = feirasCli;
	}






	public ClienteBO getClibo() {
		return clibo;
	}




	public void setClibo(ClienteBO clibo) {
		this.clibo = clibo;
	}




	public AnoBO getAnobo() {
		return anobo;
	}




	public void setAnobo(AnoBO anobo) {
		this.anobo = anobo;
	}




	public FeiraBO getFeiraBo() {
		return feiraBo;
	}




	public void setFeiraBo(FeiraBO feiraBo) {
		this.feiraBo = feiraBo;
	}




	public List<Ano> getAnos() {
		return anos;
	}




	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}




	public List<FotoCliente> getFotosCliente() {
		return fotosCliente;
	}




	public void setFotosCliente(List<FotoCliente> fotosCliente) {
		this.fotosCliente = fotosCliente;
	}




	public List<Ficha> getFichas() {
		return fichas;
	}




	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}




	public List<Briefing> getBriefings() {
		return briefings;
	}




	public void setBriefings(List<Briefing> briefings) {
		this.briefings = briefings;
	}










	public List<ContatoCliente> getContatos() {
		return contatos;
	}










	public void setContatos(List<ContatoCliente> contatos) {
		this.contatos = contatos;
	}


	public List<BriefingFiles> getBrifiles() {
		return brifiles;
	}


	public void setBrifiles(List<BriefingFiles> brifiles) {
		this.brifiles = brifiles;
	}


	public List<Projeto> getProjetos() {
		return projetos;
	}


	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}


	public List<VersaoProjeto> getVersoes() {
		return versoes;
	}


	public void setVersoes(List<VersaoProjeto> versoes) {
		this.versoes = versoes;
	}


	public List<FotoProjeto> getFotosProjetos() {
		return fotosProjetos;
	}


	public void setFotosProjetos(List<FotoProjeto> fotosProjetos) {
		this.fotosProjetos = fotosProjetos;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}


	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}


	public VersaoProjeto getVersaoSelecionada() {
		return versaoSelecionada;
	}


	public void setVersaoSelecionada(VersaoProjeto versaoSelecionada) {
		this.versaoSelecionada = versaoSelecionada;
	}


	public ProjetoBO getProjbo() {
		return projbo;
	}


	public void setProjbo(ProjetoBO projbo) {
		this.projbo = projbo;
	}


	public Briefing getBriefingSelecionado() {
		return briefingSelecionado;
	}


	public void setBriefingSelecionado(Briefing briefingSelecionado) {
		this.briefingSelecionado = briefingSelecionado;
	}


	public BriefingBO getBribo() {
		return bribo;
	}


	public void setBribo(BriefingBO bribo) {
		this.bribo = bribo;
	}
	
	
	
	
	
	
	
}
