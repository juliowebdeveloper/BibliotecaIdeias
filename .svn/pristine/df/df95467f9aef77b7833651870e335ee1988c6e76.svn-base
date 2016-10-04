package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.converter.AnoConverter;
import br.com.bibideais.converter.FeiraConverter;
import br.com.bibideais.converter.FuncConverter;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Ficha;
import br.com.bibideais.entity.FotoCliente;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.AnoBO;
import br.com.bibideais.service.BriefingBO;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.FotoBO;
import br.com.bibideais.service.PaisBO;


/**
 * 
 * @author Shido
 * Trata o cadastro de clientes
 */
@ManagedBean(name = "cadastroFullCliBean")
@SessionScoped
public class CadastroFullCliBean {

    private static Logger logger = Logger.getLogger(CadastroFullCliBean.class.getName());  

    private boolean skip;  
    
    
	public String onFlowProcess(FlowEvent event) {  
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep());  
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }  
	

	private Cliente cliente;
	
	
	private List<Cliente> ultimosCadastrados;
	
	
	
	//Cliente que acabou de ser cadastrado
		private Cliente clienteCadastrado;
		
		
		
		private FotoBO fotoBo;
		
		private BriefingBO briBo;
		
		private FeiraBO fbo;
		
		
		
		
		private boolean normalflow = true;
		
		
		
		/********************************** Feiras *********************************/
		//Lista de feiras para preencher o combo
		private List<Feira> feiras;
		
		private List<Feira> feirasSelecionadas;
		
		/********************************** Anos *********************************/

		
		private List<Ano> anos;
		

		private DualListModel<Ano> anosEscolhidos;  
		
		
		/********************************** Briefings *********************************/
		
		private Briefing briefing = new Briefing();
		
		private List<Briefing> briefings;
		
		
		/********************************** Contatos *********************************/


		
		private ContatoCliente contato = new ContatoCliente();
		
		private List<ContatoCliente> contatos ;
		
		
		/********************************** Fotos e fichas *********************************/

		private List<FotoCliente> fotos;
		
		private List<Ficha> fichas;
		
		
	
	
	
	
	
	
	

	/********************************** Funcionario *********************************/
	/*Lista de funcionario para preencher o combo*/
	private List<Funcionario> funcionarios;
	

	private Funcionario funCadastrador;
	
	
	private Funcionario funcAtendimento;
	
	
	
	/************************************* BOs *******************************************************/
	
	private ClienteBO bo;
	
	private AnoBO anoBo;
	
	private CidadeBO citybo;
	
	
	
	
	
	
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
		CadastroClienteBean clibean = (CadastroClienteBean) session.getAttribute("cadastroCliBean");
		if(dadoBean != null){
			session.removeAttribute("cliDadosBean");
		}
		
		if(clibean != null){
			session.removeAttribute("cadastroCliBean");
		}
		
		
		//BOs
		anoBo = new AnoBO();
		bo = new ClienteBO();
		
		ultimosCadastrados = bo.buscarUltimosCadastrados();
		
		
		
		contatos = new ArrayList<ContatoCliente>();
		
		briefings = new ArrayList<Briefing>();
		
		feiras = FeiraConverter.feiras;
		anos = AnoConverter.anos;
		fotos = new ArrayList<FotoCliente>();
		fichas = new ArrayList<Ficha>();
		
	
		//BOs
		anoBo = new AnoBO();
		bo = new ClienteBO();
		
		
		//Picklist de anos
		List<Ano> targetano = new ArrayList<Ano>();
		
		anosEscolhidos = new DualListModel<Ano>(anos, targetano);  
		
		
	}
	

	


    /*************************************** Métodos  *************************************************************/
    
    public String cadastrarCliente(){
    	
    	MessageUtil mu = new MessageUtil();
		if(!this.verificarCidade()){
			
			mu.sendErrorMessageToUser("Cidade Inválida", "Por favor selecione uma cidade (As fotos referência e as fichas do cliente já estão salvas" );
			
			return "CadastroCliFail";
		}
			
			if(funcAtendimento ==null){
				mu.sendErrorMessageToUser("Selecione um funcionário para o atendimento", "Escolha o funcionário responsável pelo atendimento" );
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
				 
				
					   //Inserindo Log
				 
				 	LogBean.inserirLog(AcaoUtil.CADASTROUCLIENTE, funCadastrador, cadastrado);
			         
			         HttpSession session = FacesUtil.getSession();
			         HttpServletRequest req = FacesUtil.getRequest();
			         req.setAttribute("clientecadastrado", cadastrado);
			         session.removeAttribute("cadastroCliBean");
			       
			         
					return "cadastrardadoscliente";
			         
				
				}
		        
			}catch(Exception e){
				e.printStackTrace();
				return "CadastroCliFail";
				
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
     	
     	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
     	
     	//Chama o metodo que gravará no disco passando o novo nome
         copyFile(fileName2, event.getFile().getInputstream());
      
	
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
 
 

			
//Metodos dos collectors
	public String inserirContato() {  

		contato = new ContatoCliente(); 

		return null; 
	}  


	public String inserirBriefing() {  

		briefing = new Briefing(); 

		return null; 
	}  
   
	
	
	
	
	

	public void cadastrarContatos(){
		
		MessageUtil u = new MessageUtil();
		try{
			for (ContatoCliente c1 : contatos) {
				c1.setCliente(cliente);
			}
			bo.inserirContatos(contatos);
			u.sendInfoMessageToUser("Contatos Cadastrados", null);
		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Os contatos não puderam ser cadastrados para esse cliente");
		}
	}

	

	
	
	
	public void cadastrarBriefings(){
		briBo = new BriefingBO();
		MessageUtil u = new MessageUtil();
		try{

			for (Briefing b : briefings) {
				b.setCliente(cliente);
				briBo.cadastrarBriefing(b);
			}
			u.sendInfoMessageToUser("Briefings Cadastrados", null);
		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Os briefings não puderam ser cadastrados para esse cliente");
		}
	}

	
	

	
	
	public void cadastrarAnos(){
		anoBo = new AnoBO();
		MessageUtil u = new MessageUtil();
		try{

			//target = picklist
		anoBo.relacionarAnosCliente(cliente, anosEscolhidos.getTarget());
		
			u.sendInfoMessageToUser("Anos registrados", null);
		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Os anos não puderam ser registrados para esse cliente");
		}
	}

	public void cadastrarFeiras(){
		fbo = new FeiraBO();
		MessageUtil u = new MessageUtil();
		try{
		fbo.relacionarFeirasAoCliente(cliente, feirasSelecionadas);
		u.sendInfoMessageToUser("Feiras relacionadas", null);

		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Não foi possível cadastrar essas feiras ao cliente");
		}
	}

	

	
	
	
	
	
	
	
	
	
   
	/*********************************Imagens foto referencia********************************************/

	//map que será preenchido com o nome da foto e o inputstream pra ser gravado só depois
	private HashMap<String, InputStream> fotosmap = new HashMap<String, InputStream>();



	    
		 /*Destino para onde será gravada a imagem das fotos do cliente */
		private String destinationFotos= ImagesUtil.pathFotos;
		
		//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficará mais facil de exibi-las
		private static String FOTOSURL = ImagesUtil.urlFotoCliente;

	 
		
		public void handleFotoUpload(FileUploadEvent event) {
			
			try {
				ImagesUtil u = new ImagesUtil();
	        	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
	        	
	        	fotosmap.put(fileName2, event.getFile().getInputstream());
	        	
	        	
	        	

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	    
	    
	    //Esse será o metodo chamado para gravar as fotos do cliente
	    public void uploadFotos(){
	    	
	    	try{
	    		
	    		if(fotosmap.isEmpty()){
	    			MessageUtil mu = new MessageUtil();
		    		mu.sendWarnMessageToUser("Você não adicionou nenhuma foto", null);
	    		 
	    		}else{
	    			
	    		
	    		
	    		 for (Map.Entry<String, InputStream> entrada : fotosmap.entrySet()) {  
	 	              
	 	             //Chama o metodo que gravará no disco passando o novo nome
	 			         copyFileFotos(entrada.getKey(), entrada.getValue());
	 			         
	 			        FotoCliente f = new FotoCliente();
	 			        f.setCliente(cliente);
	 			        f.setNomeImagem(entrada.getKey());
	 			        f.setLegenda("Legenda");
	 			        f.setUrlimagem(FOTOSURL+entrada.getKey());
	 		    		fotos.add(f);
	 		    		
	    			}
	    		
	       
	    		
	        	 FotoBO fotobo = new FotoBO();
	    		 fotobo.inserirFotos(fotos);
	    
	    		 
		    		MessageUtil mu = new MessageUtil();
		    		mu.sendInfoMessageToUser("Fotos cadastradas", null);
	    		}
	    	}catch (Exception e) {

	    		MessageUtil mu = new MessageUtil();
	    		mu.sendErrorMessageToUser("Não foi possível fazer o upload das imagens", null);
	    		
	    		e.printStackTrace();
	        }
	      
          
	    }
	    
	    
	   
	    public void copyFileFotos(String fileName, InputStream in) {
	           try {
	             
	                // write the inputStream to a FileOutputStream
	        	   
	        	   	
	                OutputStream out = new FileOutputStream(new File(destinationFotos + fileName));
	              
	             
	                
	                int read = 0;
	                byte[] bytes = new byte[1024];
	             
	                while ((read = in.read(bytes)) != -1) {
	                    out.write(bytes, 0, read);
	                }
	             
	                in.close();
	                out.flush();
	                out.close();
	                File dir = new File(destinationFotos);
	                dir.listFiles();
	             
	                System.out.println("New file created!");
	                } catch (IOException e) {
	                e.printStackTrace();
	                }
	    }
		

	/*************************************** Imagens fichas *********************************************************/

	 	//map que será preenchido com o nome da foto e o inputstream pra ser gravado só depois
		private HashMap<String, InputStream> fichasmap = new HashMap<String, InputStream>();



		    
			 /*Destino para onde será gravada a imagem das fotos do cliente */
			private String destinationFichas= ImagesUtil.pathFichas;
			
			//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficará mais facil de exibi-las
			private static String FICHASSURL = ImagesUtil.urlFichas;

		 
			
			

			public void handleFichaUpload(FileUploadEvent event) {
				
				try {
					ImagesUtil u = new ImagesUtil();
		        	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
		        	
		        	fichasmap.put(fileName2, event.getFile().getInputstream());
		        	
		        	
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
		    
		    
		    //Esse será o metodo chamado para gravar as fichas do cliente
		    public void uploadFichas(){
		    	
		    	
		    	
		    	try{
		    	
		    		
		    		if(fichasmap.isEmpty()){
		    			MessageUtil mu = new MessageUtil();
			    		mu.sendWarnMessageToUser("Você não adicionou nenhuma ficha", null);
			        	
		    		}else{
		    			
		    	
		    		
		    		 for (Map.Entry<String, InputStream> entrada : fichasmap.entrySet()) {  
		 	              
		 	             //Chama o metodo que gravará no disco passando o novo nome
		 			         copyFichaFile(entrada.getKey(), entrada.getValue());
		 			         
		 			        Ficha f = new Ficha();
		 			        f.setCliente(cliente);
		 			        f.setNomeImagem(entrada.getKey());
		 			        f.setNomePath(entrada.getKey());
		 			        f.setUrlimagem(FICHASSURL+entrada.getKey());
		 		    		fichas.add(f);
		 		    		
		    			}
		    		 	
		    	
		    		bo = new ClienteBO();
		    		bo.inserirFichas(fichas);
		        	
		    		MessageUtil mu = new MessageUtil();
		    		mu.sendInfoMessageToUser("Fichas cadastradas", null);
		    		}
		    	
		    	}catch (Exception e) {

		    		MessageUtil mu = new MessageUtil();
		    		mu.sendErrorMessageToUser("Não foi possível fazer o upload das imagens", null);
		    		
		    		e.printStackTrace();
		        }
		      
	           
		    }
		    
		    
		   
		    public void copyFichaFile(String fileName, InputStream in) {
		           try {
		             
		                // write the inputStream to a FileOutputStream
		        	   
		        	   	
		                OutputStream out = new FileOutputStream(new File(destinationFichas + fileName));
		              
		             
		                
		                int read = 0;
		                byte[] bytes = new byte[1024];
		             
		                while ((read = in.read(bytes)) != -1) {
		                    out.write(bytes, 0, read);
		                }
		             
		                in.close();
		                out.flush();
		                out.close();
		                File dir = new File(destinationFichas);
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


	public Cliente getClienteCadastrado() {
		return clienteCadastrado;
	}


	public void setClienteCadastrado(Cliente clienteCadastrado) {
		this.clienteCadastrado = clienteCadastrado;
	}


	public FotoBO getFotoBo() {
		return fotoBo;
	}


	public void setFotoBo(FotoBO fotoBo) {
		this.fotoBo = fotoBo;
	}


	public BriefingBO getBriBo() {
		return briBo;
	}


	public void setBriBo(BriefingBO briBo) {
		this.briBo = briBo;
	}


	public FeiraBO getFbo() {
		return fbo;
	}


	public void setFbo(FeiraBO fbo) {
		this.fbo = fbo;
	}


	public boolean isNormalflow() {
		return normalflow;
	}


	public void setNormalflow(boolean normalflow) {
		this.normalflow = normalflow;
	}


	public List<Feira> getFeiras() {
		return feiras;
	}


	public void setFeiras(List<Feira> feiras) {
		this.feiras = feiras;
	}


	public List<Feira> getFeirasSelecionadas() {
		return feirasSelecionadas;
	}


	public void setFeirasSelecionadas(List<Feira> feirasSelecionadas) {
		this.feirasSelecionadas = feirasSelecionadas;
	}


	public List<Ano> getAnos() {
		return anos;
	}


	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}


	public DualListModel<Ano> getAnosEscolhidos() {
		return anosEscolhidos;
	}


	public void setAnosEscolhidos(DualListModel<Ano> anosEscolhidos) {
		this.anosEscolhidos = anosEscolhidos;
	}


	public Briefing getBriefing() {
		return briefing;
	}


	public void setBriefing(Briefing briefing) {
		this.briefing = briefing;
	}


	public List<Briefing> getBriefings() {
		return briefings;
	}


	public void setBriefings(List<Briefing> briefings) {
		this.briefings = briefings;
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


	public List<FotoCliente> getFotos() {
		return fotos;
	}


	public void setFotos(List<FotoCliente> fotos) {
		this.fotos = fotos;
	}


	public List<Ficha> getFichas() {
		return fichas;
	}


	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}


	public HashMap<String, InputStream> getFotosmap() {
		return fotosmap;
	}


	public void setFotosmap(HashMap<String, InputStream> fotosmap) {
		this.fotosmap = fotosmap;
	}


	public String getDestinationFotos() {
		return destinationFotos;
	}


	public void setDestinationFotos(String destinationFotos) {
		this.destinationFotos = destinationFotos;
	}


	public static String getFOTOSURL() {
		return FOTOSURL;
	}


	public static void setFOTOSURL(String fOTOSURL) {
		FOTOSURL = fOTOSURL;
	}


	public HashMap<String, InputStream> getFichasmap() {
		return fichasmap;
	}


	public void setFichasmap(HashMap<String, InputStream> fichasmap) {
		this.fichasmap = fichasmap;
	}


	public String getDestinationFichas() {
		return destinationFichas;
	}


	public void setDestinationFichas(String destinationFichas) {
		this.destinationFichas = destinationFichas;
	}


	public static String getFICHASSURL() {
		return FICHASSURL;
	}


	public static void setFICHASSURL(String fICHASSURL) {
		FICHASSURL = fICHASSURL;
	}


	public static Logger getLogger() {
		return logger;
	}


	public static void setLogger(Logger logger) {
		CadastroFullCliBean.logger = logger;
	}


	public boolean isSkip() {
		return skip;
	}


	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	
	
	
	
	
}
