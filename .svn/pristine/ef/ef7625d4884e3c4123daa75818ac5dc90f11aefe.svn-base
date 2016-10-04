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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.ProjetoBO;

@ManagedBean(name ="editProjBean")
@SessionScoped
public class EditProjetoBean {

	//@ManagedProperty(value = "#{editCliBean}")
	private EditClienteBean editClienteBean;
	
	private Projeto newProjeto = new Projeto();
	
	private List<Projeto> projetos;
	
	private Projeto projetoSelecionado;
	
	private VersaoProjeto newVersao;
	
	private List<VersaoProjeto> versoes;
	
	private VersaoProjeto versaoSelecionada;
	
	private FotoProjeto fotoSelecionada;
	
	private List<FotoProjeto> fotosProjeto;
	
	private ProjetoBO pbo;


	private Cliente cliente;
	
	private ClienteBO clibo;
	

	public EditProjetoBean(){
	
	}

	
	
	@PostConstruct	
	public void init(){
		//Pega o cliente que foi colocado no request para usa-lo
        HttpServletRequest req = FacesUtil.getRequest();
        cliente = (Cliente) req.getAttribute("clienteedit");
        
		projetos = cliente.getProjetos();
	
		
		
	
		
	}
		

	
	
	/********************************Projetos**************************************************************/
			//Cria o projeto no banco.
			public void criarProjeto(){
				MessageUtil mu = new MessageUtil();
				try{
					//Criando uma lista vazia de versões de projeto e adicionando
					List<VersaoProjeto> vs = new ArrayList<VersaoProjeto>();
					pbo = new ProjetoBO();
					newProjeto.setVersoes(vs);
					newProjeto.setCliente(cliente);
					projetos.add(pbo.criarProjeto(newProjeto));

					newProjeto = new Projeto();
					mu.sendInfoMessageToUser("Projeto Adicionado", "O projeto foi adicionado, clique no mesmo na lista de projetos para editar suas versões");
				}catch(Exception e){
					e.printStackTrace();
					mu.sendErrorMessageToUser("Erro", "O projeto não pode ser criado");
				}
				
			}
			

			public void excluirProjeto(){
				 pbo = new ProjetoBO();
				 MessageUtil mu = new MessageUtil();
					//Essa será excluida do banco - se nao fizer isso da erro de lazy collection

				 Projeto projetoSelecionado2 = pbo.buscarPeloIdLazy(projetoSelecionado);
				 try{
					 if(!projetoSelecionado2.getVersoes().isEmpty()){
							
							for (VersaoProjeto vp : projetoSelecionado2.getVersoes()) {
									if(!vp.getFotosProjeto().isEmpty()){
										//Deletar as fotos
										for (FotoProjeto f: vp.getFotosProjeto()) {
											f.getUrlImagem();
											String imagename = f.getUrlImagem().substring(f.getUrlImagem().lastIndexOf("/")+1, f.getUrlImagem().length());

											//Deletando do sistema
											File file = new File(destinationFotos + imagename);
							                file.delete();	
							                
							                //Deletando do banco
							               
							                pbo.excluirFotoProjeto(f);
							                
											}
											
									}
										pbo.excluirVersaoProjeto(vp);
									
								}
							
							}
							pbo.excluirProjeto(projetoSelecionado);
							projetos.remove(projetoSelecionado);
							
							mu.sendInfoMessageToUser("Projeto removido", "O projeto foi removido com sucesso");
				 }catch(Exception e){
					 e.printStackTrace();
					 mu.sendErrorMessageToUser("Erro", "O projeto não pode ser excluído");
				 }
				
				
			}
			
			public void alterarProjeto(){
				pbo = new ProjetoBO();
				MessageUtil mu = new MessageUtil();
				
				try{
					
					pbo.updateProjeto(projetoSelecionado);
					mu.sendInfoMessageToUser("Projeto alterado", "As informações foram alteradas");
				}catch(Exception e){
					e.printStackTrace();
					mu.sendErrorMessageToUser("Erro", "As informações não puderam ser alteradas");
				}
				
			}
			
			/************************************ Versões******************************************************/
			
			//Visualiza a lista de versões 
			public void viewVersoes(){
				newVersao = new VersaoProjeto();
				pbo = new ProjetoBO();
				versoes = pbo.buscarPeloProjeto(projetoSelecionado);
				projetoSelecionado.setVersoes(versoes);
				
						
			}
			
			
			//Cria uma nova versão e coloca no projeto selecionado
			public void criarVersao(){
				try{
					
					//Criando uma lista vazia de fotos de projeto - Se nao nao funciona quando abrir no dialog.
					List<FotoProjeto> fotos = new ArrayList<FotoProjeto>();
					newVersao.setFotosProjeto(fotos);
					newVersao.setProjeto(projetoSelecionado);
					projetoSelecionado.getVersoes().add(newVersao);
					
					//Insere no banco
					pbo = new ProjetoBO();
					pbo.criarVersao(newVersao);
					
					newVersao = new VersaoProjeto();
				}catch(Exception e){
					MessageUtil mu = new MessageUtil();
					mu.sendErrorMessageToUser("Erro", "A versão não pode ser criada");
					e.printStackTrace();
					
				}
			
			
			}
			
			public void onCellEdit(CellEditEvent event){
				Object oldValue = event.getOldValue(); 
				Object newValue = event.getNewValue();  
				String valor = (String) oldValue;
				pbo = new ProjetoBO();
				//Buscando a versão antiga baseada no nome e no projeto
				VersaoProjeto versaoAntiga = pbo.buscarVersaoPeloNomeEProjeto(projetoSelecionado, valor);
				//Atualizando a versão antiga
				 pbo.atualizarNomeVersao(versaoAntiga,(String)newValue);
				 
			}
			
			
			
			public void excluirVersao(){
				pbo = new ProjetoBO();
				MessageUtil mu = new MessageUtil();
				//Essa será excluida do banco - se nao fizer isso da erro de lazy collection
				VersaoProjeto versaoSelecionada2 = pbo.buscarVersapPeloIdLazy(versaoSelecionada);
				try{
					if(!versaoSelecionada2.getFotosProjeto().isEmpty()){
						for (FotoProjeto f: versaoSelecionada2.getFotosProjeto()) {
							f.getUrlImagem();
							String imagename = f.getUrlImagem().substring(f.getUrlImagem().lastIndexOf("/")+1, f.getUrlImagem().length());
							//Apagar as fotos do sistema.
							File file = new File(destinationFotos + imagename);
							file.delete();	

							//Excluir do banco
							pbo.excluirFotoProjeto(f);

						}
					}
					pbo.excluirVersaoProjeto(versaoSelecionada);
					versoes.remove(versaoSelecionada);
					projetoSelecionado.getVersoes().remove(versaoSelecionada);
				}catch(Exception e){
					e.printStackTrace();
					mu.sendErrorMessageToUser("Erro", "A versão de projeto não pode ser excluída");
				}
				

			}
			
			
			
			
			
			/************************************ Fotos Projeto ******************************************************/

			
			
			
			
			
			public void viewFotosProjeto(){
				pbo = new ProjetoBO();
				
				fotosProjeto = pbo.buscarFotoVersao(versaoSelecionada);
				versaoSelecionada.setFotosProjeto(fotosProjeto);
				
			}
			
			
			
			
			
			
			public void criarListFotos(){
				
				try{
					
					 MessageUtil mu = new MessageUtil();

					if(fotosmap.isEmpty()){
						mu.sendErrorMessageToUser("Atenção", "Você não adicionou nenhuma foto");
					}
				
				
				 fotosdasversoes.put(versaoSelecionada, fotosmap);	 
				 
				 for (Map.Entry<VersaoProjeto, HashMap<String, InputStream>> fots : fotosdasversoes.entrySet()) {  
			        	
		    		 for (Map.Entry<String, InputStream> entrada : fotosmap.entrySet()) {  
		    			  //Chama o metodo que gravará no disco passando o novo nome
		    			 	copyFotoFile(entrada.getKey(), entrada.getValue());

		    			 	System.out.println("___________Foto Copiada____________");
	 			       
		 		    		newFoto.setVersaoprojeto(fots.getKey());
		 		    		newFoto.setUrlImagem(FOTOSURL+entrada.getKey());
		 		    		
		 		    		
		 		    		pbo = new ProjetoBO();
		 		    		//Inserindo no banco e adicionando a lista de fotos
		 		    		fotosProjeto.add(pbo.inserirFotoProjeto(newFoto));
		 		    		
		 		    		newFoto = new FotoProjeto();
		    			}
		    		 
		    		 
		    		 }
				 
				 fotosProjeto = new ArrayList<FotoProjeto>();
				 fotosdasversoes.clear();
				 fotosmap.clear();
				 
				 mu.sendInfoMessageToUser("Fotos Adicionadas", "As fotos foram adicionadas à versão de projeto " +versaoSelecionada.getVersao() + " com sucesso");
				 
		
				}catch(Exception e){
					 MessageUtil mu = new MessageUtil();
					 mu.sendInfoMessageToUser("Erro", "As fotos não puderam ser adicionadas à versão");
					 
				}
				 
			}
			
			
			
			public void viewFoto(){
				System.out.println(fotoSelecionada.getUrlImagem());
			}
			
			
	
	
	
	
	
	
			public void excluirFoto(){

				pbo = new ProjetoBO();
				MessageUtil mu = new MessageUtil();
				try{
					String imagename = fotoSelecionada.getUrlImagem().substring(fotoSelecionada.getUrlImagem().lastIndexOf("/")+1, fotoSelecionada.getUrlImagem().length());
					//Apagar a foto do sistema.
					File file = new File(destinationFotos + imagename);
					file.delete();	

					//Excluir do banco
					pbo.excluirFotoProjeto(fotoSelecionada);
					fotosProjeto.remove(fotoSelecionada);
					versaoSelecionada.getFotosProjeto().remove(fotoSelecionada);
					mu.sendInfoMessageToUser("Foto removida", "Foto removida com sucesso dessa versão");
				}catch(Exception e){
					e.printStackTrace();
					mu.sendErrorMessageToUser("Erro", "A foto não pode ser removida");
				}


			}


	
	
	
	
	/*********************************Fotos do projeto********************************************/


	private FotoProjeto newFoto = new FotoProjeto();

 	//map que será preenchido com o nome da foto e o inputstream pra ser gravado só depois
	private HashMap<String, InputStream> fotosmap = new HashMap<String, InputStream>();

	private HashMap<VersaoProjeto, HashMap<String,InputStream>> fotosdasversoes = new HashMap<VersaoProjeto, HashMap<String,InputStream>>();

    
	 /*Destino para onde será gravada a imagem das fotos do projeto */
	private String destinationFotos= ImagesUtil.pathFotosProjeto;
	
	//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String FOTOSURL = ImagesUtil.urlFotosProjetos;

    private String imagemFotourl;
    
    
    //Nome da foto que será gravada no banco.
    private String nomeImagemFoto;
    
    
    public void handleFotoUpload(FileUploadEvent event) {
    	MessageUtil mu = new MessageUtil();
		
		try {
			ImagesUtil u = new ImagesUtil();
        	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
        	
        	fotosmap.put(fileName2, event.getFile().getInputstream());
        	mu.sendInfoMessageToUser("Fotos registradas", "Clique em 'Inserir fotos nessa versão do projeto' para concluir e adiciona-las à versão do projeto");

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


	public ClienteBO getClibo() {
		return clibo;
	}


	public void setClibo(ClienteBO clibo) {
		this.clibo = clibo;
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


	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}


	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}


	public VersaoProjeto getNewVersao() {
		return newVersao;
	}


	public void setNewVersao(VersaoProjeto newVersao) {
		this.newVersao = newVersao;
	}


	public List<VersaoProjeto> getVersoes() {
		return versoes;
	}


	public void setVersoes(List<VersaoProjeto> versoes) {
		this.versoes = versoes;
	}


	public VersaoProjeto getVersaoSelecionada() {
		return versaoSelecionada;
	}


	public void setVersaoSelecionada(VersaoProjeto versaoSelecionada) {
		this.versaoSelecionada = versaoSelecionada;
	}


	public FotoProjeto getFotoSelecionada() {
		return fotoSelecionada;
	}


	public void setFotoSelecionada(FotoProjeto fotoSelecionada) {
		this.fotoSelecionada = fotoSelecionada;
	}


	public List<FotoProjeto> getFotosProjeto() {
		return fotosProjeto;
	}


	public void setFotosProjeto(List<FotoProjeto> fotosProjeto) {
		this.fotosProjeto = fotosProjeto;
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



	public EditClienteBean getEditClienteBean() {
		return editClienteBean;
	}



	public void setEditClienteBean(EditClienteBean editClienteBean) {
		this.editClienteBean = editClienteBean;
	}



	public ProjetoBO getPbo() {
		return pbo;
	}



	public void setPbo(ProjetoBO pbo) {
		this.pbo = pbo;
	}


  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*****************************************************************************/

	
	
	
	
	
	
	
	
	
	
	
}
