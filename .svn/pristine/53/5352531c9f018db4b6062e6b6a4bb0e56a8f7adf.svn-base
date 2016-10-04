package br.com.bibideais.control;


import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import br.com.bibideais.converter.PavilhaoConverter;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.PaisBO;
import br.com.bibideais.service.PavilhaoBO;
/**
 * 
 * @author Shido
 * Trata cadastro, visualização e edição dos pavilhoes além de pesquisa
 */
@ManagedBean(name ="pavBean")
@SessionScoped
public class PavilhaoBean {

	
	private List<Pavilhao> pavilhoes;
	
	private PavilhaoBO bo;
	
	private Pavilhao pavilhaoSelecionado = new Pavilhao();
	
	private Pavilhao newpavilhao;

	private LinkedList<Pavilhao> ultimosCadastrados;
	
	private List<Pavilhao> pavByName;
	
	private String nomePavilhao = new String();

	@PostConstruct
	public void init(){
		bo = new PavilhaoBO();
		pavilhaoSelecionado = new Pavilhao();
		newpavilhao = new Pavilhao();
		ultimosCadastrados = bo.buscarUltimosCadastrados();
		for (Pavilhao pav : ultimosCadastrados) {
			System.out.println(pav.getNome());
		}
	}

	
	public void pesquisarPorNome(){
		bo = new PavilhaoBO();
		
		if(nomePavilhao !=null || !nomePavilhao.equals("")){
			pavByName = bo.buscarPorNome(nomePavilhao);
			
			
		}else{
			pavByName = bo.buscarTodosAlphabetic();
		}
		
		
	}
	
	
	
	public void alterarPavilhao(){
		bo = new PavilhaoBO();
		

		if(idCidadeE != 0 && idCidadeE != pavilhaoSelecionado.getCidade().getId()){
			CidadeBO cbo = new CidadeBO();
			Cidade c = cbo.buscarPorId(idCidadeE);
			pavilhaoSelecionado.setCidade(c);
		}
		
		
		MessageUtil u = new MessageUtil();

		try{
			bo.alterarPavilhao(pavilhaoSelecionado);
			
			u.sendInfoMessageToUser("Informações Atualizadas",null);
			
			
		}catch(Exception e ){
			u.sendErrorMessageToUser("Erro ao Alterar informações", null);
			e.printStackTrace();
		}
		

		
		
	}
	
	public void cadastrarPavilhao(){
		
		//Verificação da cidade
		if(this.verificarCidade() == true){

			bo = new PavilhaoBO();
			MessageUtil u = new MessageUtil();
			try{
				Pavilhao banco = bo.cadastrar(newpavilhao);
			
					
					HttpSession session = FacesUtil.getSession();
					Funcionario func = (Funcionario) session.getAttribute("funcionario");
				      //Inserindo Log
					LogBean.inserirLog(AcaoUtil.CADASTROUPAVILHAO + banco.getNome(),func, null);
			      
			 	
					
			         if(session.getAttribute("cadFeiraBean")!= null){
							session.removeAttribute("cadFeiraBean");

						}
					
			         
			         ultimosCadastrados.removeLast();
			         ultimosCadastrados.addFirst(banco);

			         
					newpavilhao = new Pavilhao();
					
					 
					
					PavilhaoConverter.pavilhoes.clear();
					PavilhaoConverter.pavilhoes = new PavilhaoBO().buscarTodosAlphabetic();
					
					
					u.sendInfoMessageToUser("Pavilhão cadastrado", null);	
				
				
				
			}catch(Exception e){
	    		e.printStackTrace();
	    		u.sendErrorMessageToUser("Ocorreu um erro", "Não foi possível cadastrar esse pavilhão, confira se as informações estão corretas.");
			}
			
			
			
		}
		
	}
	
	
    public boolean verificarCidade(){
    	if(idCidade == 0){
    		MessageUtil u = new MessageUtil();
    		u.sendErrorMessageToUser("Cidade Inválida","Por favor selecione uma cidade");    	  
    	     return false;
    	}else{
    		newpavilhao.setCidade((new CidadeBO().buscarPorId(idCidade)));
    		return true;
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
    
    
    
    
    
    
    
    
    
    
    
    
	
	public List<Pavilhao> getPavilhoes() {
		return pavilhoes;
	}

	public void setPavilhoes(List<Pavilhao> pavilhoes) {
		this.pavilhoes = pavilhoes;
	}

	public PavilhaoBO getBo() {
		return bo;
	}

	public void setBo(PavilhaoBO bo) {
		this.bo = bo;
	}

	public Pavilhao getPavilhaoSelecionado() {
		return pavilhaoSelecionado;
	}

	public void setPavilhaoSelecionado(Pavilhao pavilhaoSelecionado) {
		this.pavilhaoSelecionado = pavilhaoSelecionado;
	}

	public Pavilhao getNewpavilhao() {
		return newpavilhao;
	}

	public void setNewpavilhao(Pavilhao newpavilhao) {
		this.newpavilhao = newpavilhao;
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


	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	public void setPaises(List<Pais> paises) {
		this.paises = paises;
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


	public void setCidadesE(List<Cidade> cidadesE) {
		this.cidadesE = cidadesE;
	}


	public void setPaisesE(List<Pais> paisesE) {
		this.paisesE = paisesE;
	}




	public List<Pavilhao> getPavByName() {
		return pavByName;
	}


	public void setPavByName(List<Pavilhao> pavByName) {
		this.pavByName = pavByName;
	}


	public String getNomePavilhao() {
		return nomePavilhao;
	}


	public void setNomePavilhao(String nomePavilhao) {
		this.nomePavilhao = nomePavilhao;
	}


	public LinkedList<Pavilhao> getUltimosCadastrados() {
		return ultimosCadastrados;
	}


	public void setUltimosCadastrados(LinkedList<Pavilhao> ultimosCadastrados) {
		this.ultimosCadastrados = ultimosCadastrados;
	}

}
