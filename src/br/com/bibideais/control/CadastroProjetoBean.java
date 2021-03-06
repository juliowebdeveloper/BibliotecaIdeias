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
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.LogAcesso;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.ProjetoBO;

@ManagedBean(name ="cadProjetoBean")
@SessionScoped
public class CadastroProjetoBean {

	private Cliente cliente;
	
	private boolean normalflow;
	
	//Controla a div de mensagem
	private boolean noprojects;
	
	private Projeto newProjeto = new Projeto();
	
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	private Projeto projetoSelecionado;
	
	private List<VersaoProjeto> versoes ;
	
	private VersaoProjeto versaoSelecionada;
	
	private VersaoProjeto newVersao = new VersaoProjeto();
	
	private List<FotoProjeto> fotosProjeto ;
	
	private FotoProjeto fotoSelecionada;
	
	private ProjetoBO pbo;
	
	private LogAcesso registro;
	
	
	
	@PostConstruct
	public void init(){
		
		if(cliente != null){
			cliente = new Cliente();
		}
		//Pega o cliente que foi cadastrado.
        HttpServletRequest req = FacesUtil.getRequest();
		cliente = (Cliente) req.getAttribute("clientecadastrado");
				
				
		//Pegando o registro gerado
		 registro = (LogAcesso) req.getAttribute("registrocadastro");
							
				
				
		MessageUtil mu = new MessageUtil();
		//mu.sendWarnMessageToUser("Aten��o", "N�o h� nenhum projeto criado para esse cliente");
		if(cliente == null){
			
			mu.sendFatalMessageToUser("Erro", "Voc� est� tentando acessar essa p�gina fora do fluxo normal - Nenhum cliente encontrado.");
			normalflow = false;
		}else{
			
		normalflow = true;
		
		}
		
		
		
		
		
		
	}
	
	//Cadastra todos os projetos, relacionando-os ao cliente e cadastrando suas vers�es e respectivas fotos - 
	//Retorna a proxima p�gina: Cadastrar Briefings.
	public String cadastrarProjetos(){
		try{
			List<Projeto> projetoscadastrados = new ArrayList<Projeto>();
			pbo = new ProjetoBO();
			//Cadastrando os projetos
			if(!projetos.isEmpty()){
				for (Projeto pj : projetos) {
					Projeto cadastrado = pbo.criarProjeto(pj);
					projetoscadastrados.add(cadastrado);
				}
				cliente.setProjetos(projetoscadastrados);
			}
			
			
			
			//Colocando o cliente no request para o cadastro de briefings.
	         HttpSession session = FacesUtil.getSession();
	         HttpServletRequest req = FacesUtil.getRequest();
	         req.setAttribute("clientecadastrado", cliente);
	         //Removendo esse Bean da session.
	         session.removeAttribute("cadProjetoBean");
	       
			
			return "cadastrarbriefings";
		}catch(Exception e){
			e.printStackTrace();
			return "CadastroProjFail";
		}
	
	}
	
	
	
	
			//Coloca o projeto na lista apenas (Nao grava no banco)
			public void criarProjeto(){
				//Criando uma lista vazia de vers�es de projeto e adicionando - Se nao nao funciona quando abrir no dialog.
				List<VersaoProjeto> vs = new ArrayList<VersaoProjeto>();
				
				newProjeto.setVersoes(vs);
				
				newProjeto.setCliente(cliente);
				projetos.add(newProjeto);
				
				newProjeto = new Projeto();
			}
			
			
			
			//Visualiza a lista de vers�es 
			public void viewVersoes(){
				newVersao = new VersaoProjeto();
				versoes = projetoSelecionado.getVersoes();

			}
			
			
			//Cria uma nova vers�o e coloca no projeto selecionado
			public void criarVersao(){
				
				newVersao.setProjeto(projetoSelecionado);
				projetoSelecionado.getVersoes().add(newVersao);
				
				//Criando uma lista vazia de fotos de projeto - Se nao nao funciona quando abrir no dialog.
				List<FotoProjeto> fotos = new ArrayList<FotoProjeto>();
				newVersao.setFotosProjeto(fotos);
				
				newVersao = new VersaoProjeto();
			
			}
			
			
			
			public void viewFotosProjeto(){
				fotosProjeto = new ArrayList<FotoProjeto>();
				fotosProjeto = versaoSelecionada.getFotosProjeto();
			
			}
			
			
			
			
			
			
			public void criarListFotos(){
				
				try{
					
					 MessageUtil mu = new MessageUtil();

					if(fotosmap.isEmpty()){
						mu.sendErrorMessageToUser("Aten��o", "Voc� n�o adicionou nenhuma foto");
					}
				
			
				 fotosdasversoes.put(versaoSelecionada, fotosmap);	 
				 
				 for (Map.Entry<VersaoProjeto, HashMap<String, InputStream>> fots : fotosdasversoes.entrySet()) {  
			        	
		    		 for (Map.Entry<String, InputStream> entrada : fotosmap.entrySet()) {  
		    			  //Chama o metodo que gravar� no disco passando o novo nome
		    			 	copyFotoFile(entrada.getKey(), entrada.getValue());

		    			 	
		 		    		newFoto.setVersaoprojeto(fots.getKey());
		 		    		newFoto.setUrlImagem(FOTOSURL+entrada.getKey());
		 		    		fotosProjeto.add(newFoto);
		 		    		newFoto = new FotoProjeto();
		    			}
		    		 
		    		 
		    		 }
		    	 
				 versaoSelecionada.setFotosProjeto(fotosProjeto);
				 fotosProjeto = new ArrayList<FotoProjeto>();
				 fotosdasversoes.clear();
				 fotosmap.clear();
				 
				 mu.sendInfoMessageToUser("Fotos Adicionadas", "As fotos foram adicionadas � vers�o de projeto " +versaoSelecionada.getVersao() + " com sucesso");
				 
		
				}catch(Exception e){
					 MessageUtil mu = new MessageUtil();
					 mu.sendInfoMessageToUser("Erro", "As fotos n�o puderam ser adicionadas � vers�o");
					 
				}
				 
			}
			
			
			
			
			
			
			public void excluirProjeto(){
			
				if(!projetoSelecionado.getVersoes().isEmpty()){
					
					for (VersaoProjeto vp : projetoSelecionado.getVersoes()) {
						if(!vp.getFotosProjeto().isEmpty()){
							//Deletar as fotos
							for (FotoProjeto f: vp.getFotosProjeto()) {
								f.getUrlImagem();
								String imagename = f.getUrlImagem().substring(f.getUrlImagem().lastIndexOf("/")+1, f.getUrlImagem().length());

								File file = new File(destinationFotos + imagename);
				                file.delete();	
							}

						}

						
					}
				}
				
				projetos.remove(projetoSelecionado);
				
			}
			
			
			
			
			
			public void excluirVersao(){
				//Apagar as fotos do sistema.
				if(!versaoSelecionada.getFotosProjeto().isEmpty()){
				for (FotoProjeto f: versaoSelecionada.getFotosProjeto()) {
					f.getUrlImagem();
					String imagename = f.getUrlImagem().substring(f.getUrlImagem().lastIndexOf("/")+1, f.getUrlImagem().length());

					File file = new File(destinationFotos + imagename);
	                file.delete();	
	            
	    			
				 }
			}
				
				versoes.remove(versaoSelecionada);
				projetoSelecionado.getVersoes().remove(versaoSelecionada);
				
			}
			
			
			
			
			public void excluirFoto(){
				fotosProjeto.remove(fotoSelecionada);
				
			}
			
			
			
	/*********************************Fotos do projeto********************************************/


			private FotoProjeto newFoto = new FotoProjeto();
		
		 	//map que ser� preenchido com o nome da foto e o inputstream pra ser gravado s� depois
			private HashMap<String, InputStream> fotosmap = new HashMap<String, InputStream>();

			private HashMap<VersaoProjeto, HashMap<String,InputStream>> fotosdasversoes = new HashMap<VersaoProjeto, HashMap<String,InputStream>>();

		    
			 /*Destino para onde ser� gravada a imagem das fotos do projeto */
			private String destinationFotos= ImagesUtil.pathFotosProjeto;
			
			//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficar� mais facil de exibi-las
			private static String FOTOSURL = ImagesUtil.urlFotosProjetos;

		    private String imagemFotourl;
		    
		    
		    //Nome da foto que ser� gravada no banco.
		    private String nomeImagemFoto;
		    
		    
		    public void handleFotoUpload(FileUploadEvent event) {
		    	MessageUtil mu = new MessageUtil();
				
				try {
					ImagesUtil u = new ImagesUtil();
		        	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
		        	
		        	fotosmap.put(fileName2, event.getFile().getInputstream());
		        	mu.sendInfoMessageToUser("Fotos registradas", "Clique em 'Inserir fotos nessa vers�o do projeto' para concluir e adiciona-las � vers�o do projeto");

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			

		    
		    
		   
		    public void copyFotoFile(String fileName, InputStream in) {
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

	    
		  
			
			
			
			

	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public boolean isNormalflow() {
		return normalflow;
	}



	public void setNormalflow(boolean normalflow) {
		this.normalflow = normalflow;
	}



	public Projeto getNewProjeto() {
		return newProjeto;
	}



	public void setNewProjeto(Projeto newProjeto) {
		this.newProjeto = newProjeto;
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



	public VersaoProjeto getNewVersao() {
		return newVersao;
	}



	public void setNewVersao(VersaoProjeto newVersao) {
		this.newVersao = newVersao;
	}



	public List<FotoProjeto> getFotosProjeto() {
		return fotosProjeto;
	}



	public void setFotosProjeto(List<FotoProjeto> fotosProjeto) {
		this.fotosProjeto = fotosProjeto;
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



	public boolean isNoprojects() {
		return noprojects;
	}



	public void setNoprojects(boolean noprojects) {
		this.noprojects = noprojects;
	}


	public FotoProjeto getFotoSelecionada() {
		return fotoSelecionada;
	}


	public void setFotoSelecionada(FotoProjeto fotoSelecionada) {
		this.fotoSelecionada = fotoSelecionada;
	}


	public FotoProjeto getNewFoto() {
		return newFoto;
	}


	public void setNewFoto(FotoProjeto newFoto) {
		this.newFoto = newFoto;
	}


	public HashMap<String, InputStream> getFotosmap() {
		return fotosmap;
	}


	public void setFotosmap(HashMap<String, InputStream> fotosmap) {
		this.fotosmap = fotosmap;
	}


	public HashMap<VersaoProjeto, HashMap<String, InputStream>> getFotosdasversoes() {
		return fotosdasversoes;
	}


	public void setFotosdasversoes(
			HashMap<VersaoProjeto, HashMap<String, InputStream>> fotosdasversoes) {
		this.fotosdasversoes = fotosdasversoes;
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


	public String getImagemFotourl() {
		return imagemFotourl;
	}


	public void setImagemFotourl(String imagemFotourl) {
		this.imagemFotourl = imagemFotourl;
	}


	public String getNomeImagemFoto() {
		return nomeImagemFoto;
	}


	public void setNomeImagemFoto(String nomeImagemFoto) {
		this.nomeImagemFoto = nomeImagemFoto;
	}

	public ProjetoBO getPbo() {
		return pbo;
	}

	public void setPbo(ProjetoBO pbo) {
		this.pbo = pbo;
	}

	public LogAcesso getRegistro() {
		return registro;
	}

	public void setRegistro(LogAcesso registro) {
		this.registro = registro;
	}
	
	
	
	
	
	
		
				
	}
	
	
	

