package br.com.bibideais.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Fornecedor;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.FornecedorBO;
import br.com.bibideais.service.PaisBO;
/**
 * 
 * @author Shido
 * Trata o cadastro, visualização e edição de fornecedores
 */
@ManagedBean(name ="fornecedorBean")
@ViewScoped
public class FornecedorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Fornecedor> fornecedores;
	
	private List<Fornecedor> searchFornecedores;
	
	private Fornecedor newfornecedor;
	
	private FornecedorBO bo;
	
	private Fornecedor fornecedorSelecionado = new Fornecedor();
	
	private List<Fornecedor> fornecedoresSearch;
	
	private LinkedList<Fornecedor> ultimosCadastrados;
	
	
	
	
	//Parametros de busca
	private String segmento;
	
	private String razaoSocial;
	
	private boolean associated;
	
	private Fornecedor forneSelecionado;
	
	
	private String razaoBusca;
	
	private List<Fornecedor> fornesByRazao;
	
	private String segmentoBusca;
	
	private List<Fornecedor> fornesBySegm;
	
	//Hashmap para montar os parametors
	private HashMap<String, String> parametros = new HashMap<String, String>();
	
	
	
	@PostConstruct
	public void init(){
		bo = new FornecedorBO();
		fornecedores = bo.buscarTodosAlfabetic();
		fornecedoresSearch = new ArrayList<Fornecedor>();
		newfornecedor = new Fornecedor();
		ultimosCadastrados = bo.buscarUltimosCadastrados();
		fornecedorSelecionado = new Fornecedor();
		
	}
	
	
	public void filteredSearch(){
		
		
		if(razaoSocial != null){
			parametros.put("razaosocial", razaoSocial);
		}
		
		if(segmento != null){
			parametros.put("segmento", segmento);
		}
		
		bo = new FornecedorBO();
		
		fornecedoresSearch = bo.filteredSearch(parametros, true);
		
		if(fornecedoresSearch.size() > 50){
			MessageUtil mu = new MessageUtil();
			mu.sendWarnMessageToUser("Atenção", "O resultado da sua busca retornou mais de 100 registros, refine um pouco mais para melhores resultados");
		}
		

		
		
	}
	
	public void buscarPorRazaoSocial(){
		bo = new FornecedorBO();
		fornesByRazao = bo.buscarPorRazaoSocial(razaoBusca);
		System.out.println("Resultados busca razao");
		if(fornesByRazao.size() > 50) {
			MessageUtil mu = new MessageUtil();
			mu.sendWarnMessageToUser("Atenção", "O resultado da sua busca retornou mais de 50 registros, refine um pouco mais para melhores resultados");
		}
		/*for (Fornecedor f : fornesByRazao) {
			System.out.println(f.getRazaoSocial() + "     ----------  " + f.getSegmento());
		}*/
	}
	
	
	
	public void buscarPorSegmento(){
		bo = new FornecedorBO();
		fornesBySegm = bo.buscarPorSegmento(segmentoBusca);
		System.out.println("Resultados busca por segmento");
		if(fornesBySegm.size() > 50) {
			MessageUtil mu = new MessageUtil();
			mu.sendWarnMessageToUser("Atenção", "O resultado da sua busca retornou mais de 50 registros, refine um pouco mais para melhores resultados");
		}
	/*	for (Fornecedor f : fornesBySegm) {
			System.out.println(f.getRazaoSocial() + "     ----------  " + f.getSegmento());
		}*/
	}
	
	
	
	
	public String excluirFornecedor(){
		bo = new FornecedorBO();
		MessageUtil mu = new MessageUtil();
		try{
			System.out.println(forneSelecionado.getRazaoSocial());
			bo.excluirFornecedor(forneSelecionado);
			mu.sendInfoMessageToUser("Fornecedor Excluído", null);
			
			return "/site/pesquisafornecedores.xhtml?faces-redirect=true";
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Fornecedor não pode ser excluído", null);

		}
		
		return null;
	}
	
	
	
	public void cadastrarFornecedor(){
		//Verificação da cidade
				if(this.verificarCidade() == true){
					
					bo = new FornecedorBO();
					
				newfornecedor.setFornecedorBi(true);
				newfornecedor.setDataCadastro(Calendar.getInstance());
				MessageUtil u = new MessageUtil();
				try{
					
					Fornecedor banco = bo.cadastrar(newfornecedor);
					fornecedores.add(banco);
					HttpSession session = FacesUtil.getSession();
					Funcionario func = (Funcionario) session.getAttribute("funcionario");
				      //Inserindo Log
					LogBean.inserirLog(AcaoUtil.CADASTROUFORNECEDOR + banco.getRazaoSocial(), func, null);
			         
			         ultimosCadastrados.removeLast();
			         ultimosCadastrados.addFirst(banco);
					
					newfornecedor = new Fornecedor();
					u.sendInfoMessageToUser("Fornecedor cadastrado", "");	
				}catch(Exception e){
		    		
		    		u.sendErrorMessageToUser("Ocorreu um erro", "Não foi possível cadastrar esse fornecedor, confira se as informações estão corretas.");
				}
			}
	}
	
	
	
	
	
	public void alterarFornecedor(){
		bo = new FornecedorBO();
		
		

		if(idCidadeE != 0 && idCidadeE != fornecedorSelecionado.getCidade().getId()){
			CidadeBO cbo = new CidadeBO();
			Cidade c = cbo.buscarPorId(idCidadeE);
			fornecedorSelecionado.setCidade(c);
		}
		
		
		MessageUtil u = new MessageUtil();
		try{
			bo.alterarFornecedor(fornecedorSelecionado);
			u.sendInfoMessageToUser("Informações Atualizadas", "");
			
			
		
			
		}catch(Exception e ){
			u.sendErrorMessageToUser("Erro ao Alterar informações", "Erro");
			e.printStackTrace();
		}
		
	}
	
	
	 public boolean verificarCidade(){
	    	if(idCidade == 0){
	    		MessageUtil u = new MessageUtil();
	    		u.sendErrorMessageToUser("Cidade Inválida","Por favor selecione uma cidade");    	  
	    	     return false;
	    	}else{
	    		newfornecedor.setCidade((new CidadeBO().buscarPorId(idCidade)));
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


	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}


	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	public List<Fornecedor> getSearchFornecedores() {
		return searchFornecedores;
	}


	public void setSearchFornecedores(List<Fornecedor> searchFornecedores) {
		this.searchFornecedores = searchFornecedores;
	}


	public Fornecedor getNewfornecedor() {
		return newfornecedor;
	}


	public void setNewfornecedor(Fornecedor newfornecedor) {
		this.newfornecedor = newfornecedor;
	}


	public FornecedorBO getBo() {
		return bo;
	}


	public void setBo(FornecedorBO bo) {
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



	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}



	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}


	public String getSegmento() {
		return segmento;
	}


	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public boolean isAssociated() {
		return associated;
	}


	public void setAssociated(boolean associated) {
		this.associated = associated;
	}


	public HashMap<String, String> getParametros() {
		return parametros;
	}


	public void setParametros(HashMap<String, String> parametros) {
		this.parametros = parametros;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<Fornecedor> getFornecedoresSearch() {
		return fornecedoresSearch;
	}


	public void setFornecedoresSearch(List<Fornecedor> fornecedoresSearch) {
		this.fornecedoresSearch = fornecedoresSearch;
	}


	public List<Fornecedor> getUltimosCadastrados() {
		return ultimosCadastrados;
	}


	public void setUltimosCadastrados(List<Fornecedor> ultimosCadastrados) {
		this.ultimosCadastrados = (LinkedList<Fornecedor>) ultimosCadastrados;
	}


	public Fornecedor getForneSelecionado() {
		return forneSelecionado;
	}


	public void setForneSelecionado(Fornecedor forneSelecionado) {
		this.forneSelecionado = forneSelecionado;
	}


	public void setUltimosCadastrados(LinkedList<Fornecedor> ultimosCadastrados) {
		this.ultimosCadastrados = ultimosCadastrados;
	}


	public String getRazaoBusca() {
		return razaoBusca;
	}


	public void setRazaoBusca(String razaoBusca) {
		this.razaoBusca = razaoBusca;
	}


	public String getSegmentoBusca() {
		return segmentoBusca;
	}


	public void setSegmentoBusca(String segmentoBusca) {
		this.segmentoBusca = segmentoBusca;
	}


	public List<Fornecedor> getFornesByRazao() {
		return fornesByRazao;
	}


	public void setFornesByRazao(List<Fornecedor> fornesByRazao) {
		this.fornesByRazao = fornesByRazao;
	}


	public List<Fornecedor> getFornesBySegm() {
		return fornesBySegm;
	}


	public void setFornesBySegm(List<Fornecedor> fornesBySegm) {
		this.fornesBySegm = fornesBySegm;
	}
    
    
    
    
	
	
}
