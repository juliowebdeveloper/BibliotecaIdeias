package br.com.bibideais.control;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Entidade;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.EntidadeBO;
import br.com.bibideais.service.PaisBO;

/**
 * 
 * @author Shido
 * Trata a visualização, cadastro e edição de entidades.
 */
@ManagedBean(name ="entBean")
@ViewScoped
public class EntidadeBean implements Serializable{


	private static final long serialVersionUID = 1L;


	private Entidade newentidade;
	
	
	private List<Entidade> entidades;
	
	private Entidade entidadeSelecionada;
	
	
	private EntidadeBO bo;
	
	private String nomeEntidade;
	
	private List<Entidade> entByName;
	
	
	private LinkedList<Entidade> ultimosCadastrados;
	
	
	
	@PostConstruct
	public void init(){
		bo = new EntidadeBO();
		newentidade = new Entidade();
		ultimosCadastrados = bo.localizarUltimasCadastradas();
		
	}

	public void buscarEntidadesPorNome(){
		bo = new EntidadeBO();
		
		if(nomeEntidade !=null || !nomeEntidade.equals("")){
			entByName = bo.buscarPorNome(nomeEntidade);
			
			
		}else{
			entByName = bo.localizarTodasAlfabetic();
		}
	}
	
	public void alterarEntidade(){

		if(idCidadeE != 0 && idCidadeE != entidadeSelecionada.getCidade().getId()){
			CidadeBO cbo = new CidadeBO();
			Cidade c = cbo.buscarPorId(idCidadeE);
			entidadeSelecionada.setCidade(c);
		}
		
		
		MessageUtil u = new MessageUtil();

		try{
			bo.alterarEntidade(entidadeSelecionada);
			
			u.sendInfoMessageToUser("Informações Atualizadas", null);

			
		}catch(Exception e ){
			u.sendErrorMessageToUser("Erro ao Alterar informações", null);
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
    public boolean verificarCidade(){
    	if(idCidade == 0){
    		MessageUtil u = new MessageUtil();
    		u.sendErrorMessageToUser("Cidade Inválida","Por favor selecione uma cidade");    	  
    	     return false;
    	}else{
    		newentidade.setCidade((new CidadeBO().buscarPorId(idCidade)));
    		return true;
    	}
    }
	

    public void cadastrarEntidade(){

    	//Verificação da cidade
    	if(this.verificarCidade() == true){


    		MessageUtil u = new MessageUtil();
    		try{
    			Entidade banco = bo.cadastrar(newentidade);
    		
    			
    			HttpSession session = FacesUtil.getSession();
    			Funcionario func = (Funcionario) session.getAttribute("funcionario");
    		      //Inserindo Log
    			LogBean.inserirLog(AcaoUtil.CADASTROUENTIDADE + banco.getNome(), func, null);
		      
    			 ultimosCadastrados.removeLast();
		         ultimosCadastrados.addFirst(banco);
				

    			newentidade = new Entidade();
    			u.sendInfoMessageToUser("Entidade cadastrada", "");	

    			
    		}catch(Exception e){

    			u.sendErrorMessageToUser("Ocorreu um erro", "Não foi possível cadastrar essa entidade, confira se as informações estão corretas.");
    		}



    	}

    }


/*********************************** Cidades e  Países **************************************************************/
	
	private List<Cidade> cidades;
	
	private List<Pais> paises;
	
	
	private String codPais = "BRA";
	
	private int idCidade = 0;
	
	private int idPais;
	
 	
    /*Preenche toda a lista de paises*/
    public List<Pais> getPaises() {
    	if(paises == null){
    		paises = new PaisBO().buscarTodos();
    	}
    	return paises;
    }
  
    
    /*Preenche a cidade pelo Pais.*/
    public List<Cidade> getCidades(){
    	if(cidades == null){
    		cidades = new CidadeBO().buscarPorCodigoPais(codPais);
    		
    	}
    	
    	
    	return cidades;
    }


	
    public void stateChangeListener(ValueChangeEvent event) {
   
      
      cidades = new CidadeBO().buscarPorCodigoPais(event.getNewValue().toString());
  	
      
    }
    
    
    
    
/*********************************** Cidades e  Países da Edição **************************************************************/

	private List<Cidade> cidadesE ;
	
	private List<Pais> paisesE;
	
	
	private String codPaisE = "BRA" ;
	
	private int idCidadeE = 0;
	
	private int idPaisE;
	
	

    /*Preenche toda a lista de paises*/
    public List<Pais> getPaisesE() {
    	if(paisesE == null){
    		paisesE = new PaisBO().buscarTodos();
    	}
    	return paisesE;
    }
  
    
    /*Preenche a cidade pelo Pais.*/
    public List<Cidade> getCidadesE(){
    	if(cidadesE == null){
    		cidadesE = new CidadeBO().buscarPorCodigoPais(codPaisE);
    		
    	}
    	
    	
    	return cidadesE;
    }


	
    public void stateChangeListenerE(ValueChangeEvent event) {


      
      cidadesE = new CidadeBO().buscarPorCodigoPais(event.getNewValue().toString());
  	
      
    }
    
    
    
	
	
	
	
	
	
	


	public Entidade getNewentidade() {
		return newentidade;
	}



	public void setNewentidade(Entidade newentidade) {
		this.newentidade = newentidade;
	}



	public List<Entidade> getEntidades() {
		return entidades;
	}



	public void setEntidades(List<Entidade> entidades) {
		this.entidades = entidades;
	}



	public Entidade getEntidadeSelecionada() {
		return entidadeSelecionada;
	}



	public void setEntidadeSelecionada(Entidade entidadeSelecionada) {
		this.entidadeSelecionada = entidadeSelecionada;
	}



	public EntidadeBO getBo() {
		return bo;
	}



	public void setBo(EntidadeBO bo) {
		this.bo = bo;
	}


	public String getCodPais() {
		return codPais;
	}


	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}


	public int getIdCidade() {
		return idCidade;
	}


	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}


	public int getIdPais() {
		return idPais;
	}


	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}


	public String getCodPaisE() {
		return codPaisE;
	}


	public void setCodPaisE(String codPaisE) {
		this.codPaisE = codPaisE;
	}


	public int getIdCidadeE() {
		return idCidadeE;
	}


	public void setIdCidadeE(int idCidadeE) {
		this.idCidadeE = idCidadeE;
	}


	public int getIdPaisE() {
		return idPaisE;
	}


	public void setIdPaisE(int idPaisE) {
		this.idPaisE = idPaisE;
	}


	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}


	public void setCidadesE(List<Cidade> cidadesE) {
		this.cidadesE = cidadesE;
	}


	public void setPaisesE(List<Pais> paisesE) {
		this.paisesE = paisesE;
	}



	public String getNomeEntidade() {
		return nomeEntidade;
	}



	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<Entidade> getEntByName() {
		return entByName;
	}



	public void setEntByName(List<Entidade> entByName) {
		this.entByName = entByName;
	}

	public LinkedList<Entidade> getUltimosCadastrados() {
		return ultimosCadastrados;
	}

	public void setUltimosCadastrados(LinkedList<Entidade> ultimosCadastrados) {
		this.ultimosCadastrados = ultimosCadastrados;
	}
	
	
}
