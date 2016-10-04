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

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;

import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.BriefingBO;

@ManagedBean(name ="editBriBean")
@SessionScoped
public class EditBriefingBean {

	private Cliente cliente;
	
	private List<Briefing> briefings;
	
	private List<BriefingFiles> briFiles;
	
	private Briefing newBriefing = new Briefing();
	
	private Briefing briefingSelecionado;
	
	private BriefingFiles newBriFile = new BriefingFiles();
	
	private BriefingFiles briFileSelecionado;
	
	private BriefingBO bo;
	
	
	private boolean possuiPlanilha;
	
	@PostConstruct
	public void init(){
		//Pega o cliente que foi colocado no request para usa-lo
        HttpServletRequest req = FacesUtil.getRequest();
        cliente = (Cliente) req.getAttribute("clienteedit");
        
		briefings = cliente.getBriefings();
		
		
	}
	
	
	
	public void adicionarBriefing(){
		//Criando lista vazia de briefing files
		List<BriefingFiles> brifis = new ArrayList<BriefingFiles>();
		newBriefing.setFiles(brifis);
		newBriefing.setCliente(cliente);
		//Cadastrando o novo briefing no banco
		bo = new BriefingBO();
		bo.cadastrarBriefing(newBriefing);
		briefings.add(newBriefing);
		newBriefing = new Briefing();
		
	}
	
	public void viewBriefing(){
		if(briefingSelecionado.getUrlPlanilhaCustos()!= null){
			possuiPlanilha = true;
		}
		briFiles = briefingSelecionado.getFiles();
		
		bo = new BriefingBO();
		briFiles = bo.buscarFilesPorBriefing(briefingSelecionado);
		briefingSelecionado.setFiles(briFiles);


	
	}
	
	
	
	public void criarListaBriefingFiles(){
		try{
			
			 MessageUtil mu = new MessageUtil();

			if(filesmap.isEmpty()){
				mu.sendErrorMessageToUser("Atenção", "Você não adicionou nenhum arquivo");
			}
			
			
	
		 filesdosbriefings.put(briefingSelecionado, filesmap);	 
		 
		 for (Map.Entry<Briefing, HashMap<String, InputStream>> bris : filesdosbriefings.entrySet()) {  
	        	
   		 for (Map.Entry<String, InputStream> entrada : filesmap.entrySet()) {  
   			  //Chama o metodo que gravará no disco passando o novo nome

   			 	copyBriefingFiles(entrada.getKey(), entrada.getValue());

		    		
		         	newBriFile.setBriefing(briefingSelecionado);
		         	newBriFile.setUrlFile(FILESSURL+entrada.getKey());
		         	//A partir da url pega só o nome do arquivo :
		         	ImagesUtil iu = new ImagesUtil();
		         	newBriFile.setNomeArquivo(iu.retornarBriefingFileName(newBriFile.getUrlFile()));	
		         	newBriFile.setDescricao("Descrição");
		         	bo = new BriefingBO();
		         	//Adicionando no banco
		         	briFiles.add(bo.adicionarFile(newBriFile));
		         	newBriFile = new BriefingFiles();
   			}
   		 
   		 
   		 }
   	 
		 briefingSelecionado.setFiles(briFiles);
		 filesdosbriefings.clear();
		 filesmap.clear();
		 
		 mu.sendInfoMessageToUser("Arquivos adicionados", "Os arquivos foram adicionados ao briefing " + briefingSelecionado.getCodigo() + " com sucesso");
		 

		}catch(Exception e){
			 MessageUtil mu = new MessageUtil();
			 e.printStackTrace();
			 mu.sendInfoMessageToUser("Erro", "Os arquivos não puderam ser adicionadas ao briefing");
			 
		}
		 
	}
	
	
	public void atualizarBriefing(){
		MessageUtil mu = new MessageUtil();
		try{
			bo = new BriefingBO();
			bo.atualizarBriefing(briefingSelecionado);
			mu.sendInfoMessageToUser("Briefing Atualizado", null);
		}catch(Exception e){
			mu.sendErrorMessageToUser("Erro", "O Briefing não pode ser atualizado");

			e.printStackTrace();
		}
	}
	
	
	public void atualizarFile(CellEditEvent event){

			bo = new BriefingBO();
	        Object newValue = event.getNewValue();  
	 
	       if(newValue != null){
	        int i = event.getRowIndex();

	        BriefingFiles f = briFiles.get(i);
	        f.setDescricao((String)newValue);
	        System.out.println(f.getDescricao());
	     
	        bo.updateFile(f);
	       }
	}
	
	
	
	
	public void excluirBriefing(){
		MessageUtil mu = new MessageUtil();

		try{
		
		if(briefingSelecionado.getUrlPlanilhaCustos()!=null){
			//Deletar a planilha antiga do sistema.
			File f = new File(destinationPlanilha + briefingSelecionado.getUrlPlanilhaCustos().substring(briefingSelecionado.getUrlPlanilhaCustos().lastIndexOf('/')+1));
            f.delete();	
		}
		
		bo = new BriefingBO();
		briefingSelecionado.setFiles( bo.buscarFilesPorBriefing(briefingSelecionado));
		//Apagar os arquivos do briefing do sistema.
		if(!briefingSelecionado.getFiles().isEmpty()){
		for (BriefingFiles f: briefingSelecionado.getFiles()) {
			String imagename = f.getUrlFile().substring(f.getUrlFile().lastIndexOf("/")+1, f.getUrlFile().length());

			File file = new File(destinationFiles + imagename);
            file.delete();	
            bo.excluirBriFile(f);
			}
			
		}
		bo.excluirBriefing(briefingSelecionado);
		briefings.remove(briefingSelecionado);
		mu.sendInfoMessageToUser("Briefing Excluído", "O Briefing e seus arquivos foram excluídos");
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "O Briefing não pode ser excluído");
		}
		
	}
	
	
	
	
	
	
	
	public void excluirBriefingFile(){
		
		MessageUtil mu = new MessageUtil();
		try{
		
				String imagename = briFileSelecionado.getUrlFile().substring(briFileSelecionado.getUrlFile().lastIndexOf("/")+1, briFileSelecionado.getUrlFile().length());

				File file = new File(destinationFiles + imagename);
	            file.delete();	
	            
	            bo = new BriefingBO();
	            bo.excluirBriFile(briFileSelecionado);
			briFiles.remove(briFileSelecionado);
			mu.sendInfoMessageToUser("Arquivo Excluído", "O Arquivo foi removido do briefing");
			}catch(Exception e){
				e.printStackTrace();
				mu.sendErrorMessageToUser("Erro", "O Arquivo não pode ser excluído");
			}
			
	}

	
	
	
	
	/*********************Upload de Planilha de Custos.*******************************************/	

	 /*Destino para onde será gravada a imagem das fotos do projeto */
	private String destinationPlanilha= ImagesUtil.pathPlanilhasCusto;
	
	//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String PLANILHAURL = ImagesUtil.urlPlanilhasCusto;

  
    public void handlePlanilhaUpload(FileUploadEvent event) {
    	MessageUtil mu = new MessageUtil();

		
		try {
			ImagesUtil u = new ImagesUtil();
        	
			if(briefingSelecionado.getUrlPlanilhaCustos()!=null){
				//Deletar a planilha antiga do sistema.
				File f = new File(destinationPlanilha + briefingSelecionado.getUrlPlanilhaCustos().substring(briefingSelecionado.getUrlPlanilhaCustos().lastIndexOf('/')+1));
	            f.delete();	
			}

			String fileName2 = u.gerarIdentificadorPlanilhaCustos(event.getFile().getFileName(), briefingSelecionado.getCodigo());
        	
        	copyPlanilhaFile(fileName2, event.getFile().getInputstream());
        	briefingSelecionado.setUrlPlanilhaCustos(PLANILHAURL+fileName2);
        	//Atualizar a planilha no banco
        	bo = new BriefingBO();

        	bo.alterarPlanilha(briefingSelecionado);
        	
        	mu.sendInfoMessageToUser("Planilha Adicionada", null);
		} catch (Exception ex) {
        	mu.sendErrorMessageToUser("Erro", "A Planilha não pode ser adicionada");

			ex.printStackTrace();
		}
	}
	

    
   
    public void copyPlanilhaFile(String fileName, InputStream in) {
           try {
             
                // write the inputStream to a FileOutputStream

                OutputStream out = new FileOutputStream(new File(destinationPlanilha + fileName));

                
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
                File dir = new File(destinationPlanilha);
                dir.listFiles();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                e.printStackTrace();
                }
    }


  
	
	
	
    /********************************* Briefing Files********************************************/
    
    
    

 	//map que será preenchido com o nome do arquivo e o inputstream pra ser gravado só depois
	private HashMap<String, InputStream> filesmap = new HashMap<String, InputStream>();
	private HashMap<Briefing, HashMap<String,InputStream>> filesdosbriefings = new HashMap<Briefing, HashMap<String,InputStream>>();
	

    
	 /*Destino para onde será gravada os arquvios do briefing */
	private String destinationFiles= ImagesUtil.pathBriefings;
	
	//Caminho default para os arquivos do briefing .Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String FILESSURL = ImagesUtil.urlBriefings;



    
    public void handleFilesUpload(FileUploadEvent event) {
    	MessageUtil mu = new MessageUtil();
		
		try {
			ImagesUtil u = new ImagesUtil();
			String filename1 = event.getFile().getFileName();
        	String fileName2 = u.gerarIdentificadorBriefingFile(filename1, briefingSelecionado.getCodigo());
        	filesmap.put(fileName2, event.getFile().getInputstream());
        	
        	mu.sendInfoMessageToUser("Arquivo(s) registrados(s)", "Clique em 'Inserir arquivos ao briefing' para adiciona-los ao briefing"); 
        	
		} catch (Exception ex) {
        	mu.sendErrorMessageToUser("Erro", "Os arquivos nao puderam ser adicionados"); 

			ex.printStackTrace();
		}
	}
	
    
    
    
    
    
    
   
    public void copyBriefingFiles(String fileName, InputStream in) {
           try {
             
                // write the inputStream to a FileOutputStream
        	   	
                OutputStream out = new FileOutputStream(new File(destinationFiles + fileName));

                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
                File dir = new File(destinationFiles);
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



	public List<Briefing> getBriefings() {
		return briefings;
	}



	public void setBriefings(List<Briefing> briefings) {
		this.briefings = briefings;
	}



	public List<BriefingFiles> getBriFiles() {
		return briFiles;
	}



	public void setBriFiles(List<BriefingFiles> briFiles) {
		this.briFiles = briFiles;
	}



	public Briefing getNewBriefing() {
		return newBriefing;
	}



	public void setNewBriefing(Briefing newBriefing) {
		this.newBriefing = newBriefing;
	}



	public Briefing getBriefingSelecionado() {
		return briefingSelecionado;
	}



	public void setBriefingSelecionado(Briefing briefingSelecionado) {
		this.briefingSelecionado = briefingSelecionado;
	}



	public BriefingFiles getNewBriFile() {
		return newBriFile;
	}



	public void setNewBriFile(BriefingFiles newBriFile) {
		this.newBriFile = newBriFile;
	}



	public BriefingBO getBo() {
		return bo;
	}



	public void setBo(BriefingBO bo) {
		this.bo = bo;
	}



	public boolean isPossuiPlanilha() {
		return possuiPlanilha;
	}



	public void setPossuiPlanilha(boolean possuiPlanilha) {
		this.possuiPlanilha = possuiPlanilha;
	}



	public String getDestinationPlanilha() {
		return destinationPlanilha;
	}



	public void setDestinationPlanilha(String destinationPlanilha) {
		this.destinationPlanilha = destinationPlanilha;
	}



	public static String getPLANILHAURL() {
		return PLANILHAURL;
	}



	public static void setPLANILHAURL(String pLANILHAURL) {
		PLANILHAURL = pLANILHAURL;
	}



	public HashMap<String, InputStream> getFilesmap() {
		return filesmap;
	}



	public void setFilesmap(HashMap<String, InputStream> filesmap) {
		this.filesmap = filesmap;
	}



	public HashMap<Briefing, HashMap<String, InputStream>> getFilesdosbriefings() {
		return filesdosbriefings;
	}



	public void setFilesdosbriefings(
			HashMap<Briefing, HashMap<String, InputStream>> filesdosbriefings) {
		this.filesdosbriefings = filesdosbriefings;
	}



	public String getDestinationFiles() {
		return destinationFiles;
	}



	public void setDestinationFiles(String destinationFiles) {
		this.destinationFiles = destinationFiles;
	}



	public static String getFILESSURL() {
		return FILESSURL;
	}



	public static void setFILESSURL(String fILESSURL) {
		FILESSURL = fILESSURL;
	}



	public BriefingFiles getBriFileSelecionado() {
		return briFileSelecionado;
	}



	public void setBriFileSelecionado(BriefingFiles briFileSelecionado) {
		this.briFileSelecionado = briFileSelecionado;
	}


	

	
	
}
