package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;

import br.com.bibideais.converter.AnoConverter;
import br.com.bibideais.converter.FeiraConverter;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Ficha;
import br.com.bibideais.entity.FotoCliente;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.AnoBO;
import br.com.bibideais.service.BriefingBO;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.FotoBO;


/**
 * 
 * @author Shido
 * Trata o cadastro dos dados extras dos clientes - É executado após o cadastro de cliente
 */
@ManagedBean(name ="cliDadosBean")
@SessionScoped
public class ClienteDadosBean {

	//Cliente que acabou de ser cadastrado
	private Cliente cliente;
	
	private ClienteBO bo;
	
	private AnoBO anoBo;
	
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


	
	private ContatoCliente contato ;
	
	private List<ContatoCliente> contatos ;
	
	private ContatoCliente contatoSelecionado;
	
	/********************************** Fotos e fichas *********************************/

	private List<FotoCliente> fotos;
	
	private List<Ficha> fichas;
	
	
	
	@PostConstruct
	public void init(){
		//Pega o cliente que acabou de ser cadastrado.
        HttpServletRequest req = FacesUtil.getRequest();
		cliente = (Cliente) req.getAttribute("clientecadastrado");
		contato = new ContatoCliente();
		if(cliente == null){
			MessageUtil mu = new MessageUtil();
			mu.sendFatalMessageToUser("Erro", "Você está tentando acessar essa página fora do fluxo normal - Nenhum cliente encontrado.");
			normalflow = false;
		}else{
			
		normalflow = true;
		
		
		contatos = new ArrayList<ContatoCliente>();
		contatoSelecionado = new ContatoCliente();
		briefings = new ArrayList<Briefing>();
		//FeiraConverter.feiras.clear();
		//FeiraConverter.feiras = new FeiraBO().localizarTodas();
		
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
		
	}
	
	/*
	//Metodos dos collectors
	public void inserirContato() {  
		contato.setCliente(cliente);
		
		bo = new ClienteBO();
		MessageUtil u = new MessageUtil();
		try{
		ContatoCliente cadastrado = bo.inserirContato(contato);
			contatos.add(cadastrado);
			u.sendInfoMessageToUser("Contato Cadastrado", null);

		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "O contato não pode ser cadastrado para esse cliente");
		}
		
		contato = new ContatoCliente(); 
	}  */


	public String inserirBriefing() {  

		briefing = new Briefing(); 

		return null; 
	}  
    
	
/*	public void removerContato(){
		MessageUtil u = new MessageUtil();
		bo = new ClienteBO();

		try{
			bo.excluirContato(contatoSelecionado);
			u.sendInfoMessageToUser("Contato Removido", null);

			}catch(Exception e){
				e.printStackTrace();
				u.sendErrorMessageToUser("Erro", "O contato não pode ser excluído para esse cliente");
			}
		
		contatos.remove(contatoSelecionado);
	}
	
	
	

	public void cadastrarContatos(){
		bo = new ClienteBO();
		MessageUtil u = new MessageUtil();
		try{
			
			bo.inserirContatos(contatos);
			List<ContatoCliente> aux = new ArrayList<ContatoCliente>();
			aux.addAll(contatos);
			contatos.clear();
			contatos.addAll(aux);
			u.sendInfoMessageToUser("Contatos Cadastrados", null);
			
		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Os contatos não puderam ser cadastrados para esse cliente");
		}
	}

	*/

	
	
	
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



			public Cliente getCliente() {
				return cliente;
			}



			public void setCliente(Cliente cliente) {
				this.cliente = cliente;
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


			public FeiraBO getFbo() {
				return fbo;
			}


			public void setFbo(FeiraBO fbo) {
				this.fbo = fbo;
			}


			public ContatoCliente getContatoSelecionado() {
				return contatoSelecionado;
			}


			public void setContatoSelecionado(ContatoCliente contatoSelecionado) {
				this.contatoSelecionado = contatoSelecionado;
			}
			

			
			
			
			
	
	
	
	
	
}
