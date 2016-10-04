package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.converter.FuncConverter;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.LogAcesso;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.AnoBO;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.PaisBO;


/**
 * 
 * @author Shido
 * Trata o cadastro de clientes
 */

@ManagedBean(name = "cadastroCliBean")
@SessionScoped
public class CadastroClienteBean {

	
	
	private Cliente cliente;
	
	
	private List<Cliente> ultimosCadastrados;
	
	
	

	/********************************** Funcionario *********************************/
	/*Lista de funcionario para preencher o combo*/
	private List<Funcionario> funcionarios;
	

	private Funcionario funCadastrador;
	
	
	private Funcionario funcAtendimento;
	
	
	
	/************************************* BOs *******************************************************/
	
	private ClienteBO bo;
	
	private AnoBO anoBo;
	
	private CidadeBO citybo;
	
	
	
	/********************************** Contatos *********************************/


	
	private ContatoCliente contato ;
	
	private List<ContatoCliente> contatos ;
	
	private ContatoCliente contatoSelecionado;
	
	
	
	/*********************************** Cidades e  Países **************************************************************/
	
	private List<Cidade> cidades;
	
	private List<Pais> paises;
	
	
	private String codPais = "BRA";
	
	private int idCidade = 206;
	
	
	
 	
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


	
	
	
	
	
	
	@PostConstruct
	public void init(){
	
		cliente = new Cliente();
		
		
		funcionarios = FuncConverter.funcionarios;
		HttpSession session = FacesUtil.getSession();
		funCadastrador = (Funcionario) session.getAttribute("funcionario");
		
		//Caso já tenha sido feito um  cadastro de cliente, limpar os managedbeans
		ClienteDadosBean dadoBean = (ClienteDadosBean) session.getAttribute("cliDadosBean");
		CadastroProjetoBean cadProjBean = (CadastroProjetoBean) session.getAttribute("cadProjetoBean");
		CadastroBriefingBean cadBrifBean = (CadastroBriefingBean) session.getAttribute("cadBriBean");
		CadastroClienteBean clibean = (CadastroClienteBean) session.getAttribute("cadastroCliBean");
		
		
		if(dadoBean != null){
			session.removeAttribute("cliDadosBean");
		}
		if(cadProjBean != null){
			session.removeAttribute("cadProjetoBean");
		}
		if(cadBrifBean !=null){
			session.removeAttribute("cadBriBean");
		}
		if(clibean != null){
			session.removeAttribute("cadastroCliBean");
		}
		
		
		//BOs
		anoBo = new AnoBO();
		bo = new ClienteBO();
		contatos = new ArrayList<ContatoCliente>();
		contatoSelecionado = new ContatoCliente();
		contato = new ContatoCliente();
		ultimosCadastrados = bo.buscarUltimosCadastrados();
		
	}
	

	

	
	
	
	

    /*************************************** Métodos  *************************************************************/
    
    public String cadastrarCliente(){
    	

    	MessageUtil mu = new MessageUtil();
		if(!this.verificarCidade()){
			
			mu.sendErrorMessageToUser("Cidade Inválida", "Por favor selecione uma cidade (As fotos referência e as fichas do cliente já estão salvas" );
			
			return "CadastroCliFail";
		}
			
			if(funcAtendimento ==null){
				mu.sendErrorMessageToUser("Selecione um funcionário para o atendimento", "" );
				return "CadastroCliFail";

			}
			
			
				try{

					cliente.setAtendimento(funcAtendimento);
					cliente.setCadastrador(funCadastrador);
					cliente.setDataCadastro(Calendar.getInstance());
					//Setando o Cartão
				if(filevent != null){
					//Copiar o avatar para o servidor que ja dará a url do arquivo.
					this.upload(filevent);
				     cliente.setUrlCartao(imagemurl);
					
				}
				
				
				boolean existe = bo.verificarCliente(cliente);
				
				if(existe){
					mu.sendErrorMessageToUser("Razão Social inválida", "Já existe um cliente com essa razão social cadastrado" );
					return "CadastroCliFail";
				}else{
					
				
				
				 Cliente cadastrado = bo.cadastrarCliente(cliente);
				 cadastrarContatos(cadastrado);
			
					   //Inserindo Log
				 	LogAcesso la = LogBean.inserirLog(AcaoUtil.CADASTROUCLIENTE,funCadastrador, cadastrado);
			         HttpSession session = FacesUtil.getSession();
			         HttpServletRequest req = FacesUtil.getRequest();
			         req.setAttribute("clientecadastrado", cadastrado);
			         req.setAttribute("registrocadastro", la);
			         session.removeAttribute("cadastroCliBean");
			       
			         
					return "cadastrarprojetos";
			         
				
				}
		        
			}catch(Exception e){
				e.printStackTrace();
				return "CadastroCliFail";
				
			}
			
			
		
	
    }


  //Metodos dos collectors
  	public void inserirContato() {  
  			System.out.println("INSERIR CONTATO");
  			contatos.add(contato);
  	
  		contato = new ContatoCliente(); 
  	}  



      
  	
  	public void removerContato(){
  		bo = new ClienteBO();
  		contatos.remove(contatoSelecionado);


  	}


  	

  	public void cadastrarContatos(Cliente cadastrado){
  		bo = new ClienteBO();

  		try{
  			
  			if(!contatos.isEmpty()){
  				 for (ContatoCliente c : contatos) {
 					c.setCliente(cliente);
 				}
   			
  				 bo.inserirContatos(contatos);
   		
  			}
  		
  			contatos.clear();
	
  			
  		}catch(Exception e){
  			e.printStackTrace();
  		}
  	}

  	

    
    public boolean verificarCidade(){
    	if(idCidade == 0){
    		MessageUtil mu = new MessageUtil();
    		mu.sendErrorMessageToUser("Cidade inválida", "Escolha uma cidade");
    		    	  
    	     return false;
    	}else{
    		cliente.setCidade(new CidadeBO().buscarPorId(idCidade));
    		return true;
    	}
    }
    
    
    
    
    /*********************************Imagem do Logo********************************************/
	

	 /*Destino para onde será gravada a imagem do logo - Cartão de visitas*/
	private String destination= ImagesUtil.pathCartoes;
	
	//Caminho default para as imagens do cartao .Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String LOGOURL = ImagesUtil.urlCartoes;

 

	/*Imagem que irá aparecer na página*/
	private StreamedContent imagem ;

	private String imagemurl;

	/*FileUploadEvent que será preenchido na hora que o usuário fizer o upload, para só depois ser gravado com o metodo
	 * upload e copiar o arquivo para o servidor*/
	private FileUploadEvent filevent;

 
 
 /*Metodo que pega a imagem que o usuário upou e mostra na tela, além de setar o avatar com ela.
  * Esse metodo será chamado sempre que o usuário clicar em "Adicionar Foto" e sempre mudando a imagem em questão*/
	public void handleFileUpload(FileUploadEvent event) {
		
		try {
			System.out.println("file upload");
			imagem = new DefaultStreamedContent(event.getFile().getInputstream());
			filevent = event;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
 
 
 /*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
 public void upload(FileUploadEvent event) {  
    
     try {
     
     	//Gera o nome nome da imagem
     	ImagesUtil u = new ImagesUtil();
     	if(event.getFile().getFileName()!=null || !event.getFile().getFileName().equalsIgnoreCase("") || !event.getFile().getFileName().equalsIgnoreCase("undefined") ){
     		System.out.println("É diferente de Null o nome do arquivo de upload: " + event.getFile().getFileName());
     		
         	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
         	if(fileName2 !=null){
         		//Chama o metodo que gravará no disco passando o novo nome
                copyFile(fileName2, event.getFile().getInputstream());
         	}
         	
         
     	}
     	
     	
	
     } catch (IOException e) {
 
         e.printStackTrace();
     }

 }  


 
 
 /*Metodo que copiará a imagem para o disco e salvará na pasta (destination)*/
 public void copyFile(String fileName, InputStream in) {
        try {
          
             // write the inputStream to a FileOutputStream
     	   
             OutputStream out = new FileOutputStream(new File(destination + fileName));
           
             imagemurl = LOGOURL + fileName;
             
             
             
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
          
    
             in.close();
             out.flush();
             out.close();
             File dir = new File(destination);
             dir.listFiles();
          
             System.out.println("New file created!");
             } catch (IOException e) {
             e.printStackTrace();
             }
 }
 
 

			
			
	    
	    

    
    /*****************************Gets and Sets ****************************************************/
    
    
    
    
    
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


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}





	public Funcionario getFunCadastrador() {
		
		return funCadastrador;
	}


	public void setFunCadastrador(Funcionario funCadastrador) {
		this.funCadastrador = funCadastrador;
	}


	public Funcionario getFuncAtendimento() {
		return funcAtendimento;
	}


	public void setFuncAtendimento(Funcionario funcAtendimento) {
		this.funcAtendimento = funcAtendimento;
	}


	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}


	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}


	

	public ClienteBO getBo() {
		return bo;
	}


	public void setBo(ClienteBO bo) {
		this.bo = bo;
	}


	public AnoBO getAnoBo() {
		return anoBo;
	}


	public void setAnoBo(AnoBO anoBo) {
		this.anoBo = anoBo;
	}


	public CidadeBO getCitybo() {
		return citybo;
	}


	public void setCitybo(CidadeBO citybo) {
		this.citybo = citybo;
	}



	public FileUploadEvent getFilevent() {
		return filevent;
	}


	public void setFilevent(FileUploadEvent filevent) {
		this.filevent = filevent;
	}


	


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public static String getLOGOURL() {
		return LOGOURL;
	}


	public static void setLOGOURL(String lOGOURL) {
		LOGOURL = lOGOURL;
	}


	public StreamedContent getImagem() {
		return imagem;
	}


	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}


	public String getImagemurl() {
		return imagemurl;
	}


	public void setImagemurl(String imagemurl) {
		this.imagemurl = imagemurl;
	}


	public List<Cliente> getUltimosCadastrados() {
		return ultimosCadastrados;
	}


	public void setUltimosCadastrados(List<Cliente> ultimosCadastrados) {
		this.ultimosCadastrados = ultimosCadastrados;
	}


	public ContatoCliente getContato() {
		return contato;
	}


	public void setContato(ContatoCliente contato) {
		this.contato = contato;
	}


	public List<ContatoCliente> getContatos() {
		return contatos;
	}


	public void setContatos(List<ContatoCliente> contatos) {
		this.contatos = contatos;
	}


	public ContatoCliente getContatoSelecionado() {
		return contatoSelecionado;
	}


	public void setContatoSelecionado(ContatoCliente contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

}