package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.DataException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.converter.FeiraConverter;
import br.com.bibideais.converter.FeirasTotaisConverter;
import br.com.bibideais.converter.OrgConverter;
import br.com.bibideais.converter.PavilhaoConverter;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.FeiraBO;

/**
 * 
 * @author Shido
 * Trata o cadastro de feiras
 */
@ManagedBean(name ="cadFeiraBean")
@SessionScoped
public class CadastroFeiraBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private FeiraBO bo;
	
	private Feira newFeira;
	
	private List<Organizadora> orgs;
	
	private List<Pavilhao> pavs;
	
	private boolean construiu;
	
	
	private Pavilhao pavilhaoSelecionado;
	
	private Organizadora organizadoraSelecionada;
	
	
	private LinkedList<Feira> ultimasFeiras;
	
	
	private int totalExpositores;
	
	
	
	
	
	@PostConstruct
	public void init(){
		newFeira = new Feira();
		bo = new FeiraBO();
		orgs = OrgConverter.organizadoras;
		pavs = PavilhaoConverter.pavilhoes;
		ultimasFeiras = bo.buscarUltimasCadastras();
	}
	

	
	public void cadastrarFeira(){
		MessageUtil mu = new MessageUtil();
		
		try{
			
			if( organizadoraSelecionada == null || organizadoraSelecionada.getRazaoSocial() == null ){
				mu.sendErrorMessageToUser("Erro", "Você precisa escolher uma organizadora para poder cadastrar a feira");
				
			}else if (pavilhaoSelecionado ==  null || pavilhaoSelecionado.getNome() == null ){
				mu.sendErrorMessageToUser("Erro", "Você precisa escolher um pavilhão para poder cadastrar a feira");

			}else{
				
			newFeira.setOrganizadora(organizadoraSelecionada);
			newFeira.setLocal(pavilhaoSelecionado);
			newFeira.setCidade(pavilhaoSelecionado.getCidade());
			newFeira.setBiConstruiu(construiu);
			newFeira.setTotalExpositores(totalExpositores);
			newFeira.setDataCadastro(Calendar.getInstance());
			if(filevent != null){
				//Copiar o avatar para o servidor que ja dará a url do arquivo.
				this.upload(filevent);
			     newFeira.setUrlLogo(imagemurl);
				
			}
			
			if(fileventmapa != null){
				this.uploadMapa(fileventmapa);
				newFeira.setUrlMapa(mapaurl);
			}
			
			if(fileventmanual!= null){
				this.uploadManual(fileventmanual);
				newFeira.setUrlManual(manualurl);
			}
			
			
		     try{
		         Feira cadastrada = bo.cadastrar(newFeira);
		         HttpSession session = FacesUtil.getSession();
		         Funcionario f = (Funcionario) session.getAttribute("funcionario");
		        	 //Inserindo Log
			        LogBean.inserirLog(AcaoUtil.CADASTROUFEIRA +newFeira.getNomeFeira(),f,  null);
			         
			         imagem = new DefaultStreamedContent();
				     imagemMapa = new DefaultStreamedContent();
				     imagemManualString = new String();
				     organizadoraSelecionada = new Organizadora();
				     pavilhaoSelecionado = new Pavilhao();
					
					
					ultimasFeiras.removeLast();
					ultimasFeiras.addFirst(cadastrada);
					
					FeiraConverter.feiras.clear();
					FeiraConverter.feiras = new FeiraBO().localizarTodas();
					
					FeirasTotaisConverter.feirasTotais.clear();
					
					FeirasTotaisConverter.feirasTotais = new FeiraBO().localizarTodas();
					
					newFeira = new Feira();
					
					
					if(session.getAttribute("cadastroCliBean")!=null){
						session.removeAttribute("cadastroCliBean");
					}
					
					
					
					mu.sendInfoMessageToUser("Feira Cadastrada", null);
					
					
		     }catch(DataException e){
					mu.sendErrorMessageToUser("Um erro ocorreu", "Não foi possível cadastrar essa feira, verifique se os dados estão corretos" );
					e.printStackTrace();
		     }
		
		    
			
			}
		}catch(Exception e){
			mu.sendErrorMessageToUser("Um erro ocorreu", "Não foi possível cadatrar essa feira, verifique se os dados estão corretos" );
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void preencherExpositoresTotais(){
		totalExpositores = newFeira.getExpositoresInt() + newFeira.getExpositoresNac();

	}

	
	
	/*********************************Imagens do Logo********************************************/
	

	 /*Destino para onde será gravada a imagem do logo*/
	private String destination= ImagesUtil.pathLogos;
	
	//Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String LOGOURL = ImagesUtil.urlLogos;
	

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
     	//Imagem pra aparecer na pagina
     	//imagem = new DefaultStreamedContent(event.getFile().getInputstream());
     	
     	//Gera o nome nome da imagem
     	ImagesUtil u = new ImagesUtil();
     	if(event.getFile().getFileName()!=null || !event.getFile().getFileName().equalsIgnoreCase("")|| !event.getFile().getFileName().equalsIgnoreCase("undefined")){

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


 
	/*********************************Manuais da feira********************************************/

 /*Destino para onde será gravada a imagem do manual*/
	private String destinationManual= ImagesUtil.pathManuais;
	
	//Caminho default para as imagens do manual.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String MANUALURL = ImagesUtil.urlManuaisFeira;
	

	/*Imagem que irá aparecer na página*/
	private StreamedContent imagemManual ;
	
	/*Url do icon de pdf ou de doc que aparecerá na pagina*/
	private String imagemManualString ;
	
	

	private String manualurl;

	/*FileUploadEvent que será preenchido na hora que o usuário fizer o upload, para só depois ser gravado com o metodo
	 * upload e copiar o arquivo para o servidor*/
	private FileUploadEvent fileventmanual;

 
 
 /*Metodo que pega a imagem que o usuário upou e mostra na tela, além de setar o avatar com ela.
  * Esse metodo será chamado sempre que o usuário clicar em "Adicionar Foto" e sempre mudando a imagem em questão*/
	public void handleManualUpload(FileUploadEvent event) {
		
		try {


			//Setando o icone na página de doc ou pdf.
			imagemManual = new DefaultStreamedContent(event.getFile().getInputstream());
			
			if(event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".pdf")){

				imagemManualString = "/site/images/pdficon.png";
			}else if (event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".doc") 
					|| event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".docx")){

				imagemManualString = "/site/images/docicon.png";

			}
			
			fileventmanual = event;
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
 
 
 /*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
 public void uploadManual(FileUploadEvent event) {  
    
     try {
     	//Imagem pra aparecer na pagina
     	//imagem = new DefaultStreamedContent(event.getFile().getInputstream());
     	
     	//Gera o nome nome da imagem
     	ImagesUtil u = new ImagesUtil();
     	
     	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
     	
     	//Chama o metodo que gravará no disco passando o novo nome
         copyManualFile(fileName2, event.getFile().getInputstream());
      
	
     } catch (IOException e) {
 
         e.printStackTrace();
     }

 }  


 
 
 /*Metodo que copiará a imagem para o disco e salvará na pasta (destination)*/
 public void copyManualFile(String fileName, InputStream in) {
        try {
          
             // write the inputStream to a FileOutputStream
     	   
             OutputStream out = new FileOutputStream(new File(destinationManual + fileName));
           
             manualurl = MANUALURL + fileName;
             
             
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
          
    
             in.close();
             out.flush();
             out.close();
             File dir = new File(destinationManual);
             dir.listFiles();
          
             System.out.println("New file created!");
             } catch (IOException e) {
             e.printStackTrace();
             }
 }

 
 
 
 /*********************************Mapas da feira********************************************/

 /*Destino para onde será gravada a imagem do mapa*/
	private String destinationMapa= ImagesUtil.pathMapas;
	
	//Caminho default para as imagens do mapa.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String MAPAURL = ImagesUtil.urlMapaFeira;
	

	/*Imagem que irá aparecer na página*/
	private StreamedContent imagemMapa ;

	private String mapaurl;

	/*FileUploadEvent que será preenchido na hora que o usuário fizer o upload, para só depois ser gravado com o metodo
	 * upload e copiar o arquivo para o servidor*/
	private FileUploadEvent fileventmapa;

	private String imagemMapaString;
 
 /*Metodo que pega a imagem que o usuário upou e mostra na tela, além de setar o avatar com ela.
  * Esse metodo será chamado sempre que o usuário clicar em "Adicionar Foto" e sempre mudando a imagem em questão*/
	public void handleMapaUpload(FileUploadEvent event) {
		
		try {
			imagemMapa = new DefaultStreamedContent(event.getFile().getInputstream());

			if(event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".pdf")){

				imagemMapaString = "/site/images/pdficon.png";
			}else if (event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".doc") 
					|| event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".docx")){

				imagemMapaString = "/site/images/docicon.png";

			}
		
		fileventmapa = event;


		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
 
 
 /*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
 public void uploadMapa(FileUploadEvent event) {  
    
     try {
     	//Imagem pra aparecer na pagina
     	//imagem = new DefaultStreamedContent(event.getFile().getInputstream());
     	
     	//Gera o nome nome da imagem
     	ImagesUtil u = new ImagesUtil();
     	
     	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
     	
     	//Chama o metodo que gravará no disco passando o novo nome
         copyMapaFile(fileName2, event.getFile().getInputstream());
      
	
     } catch (IOException e) {
 
         e.printStackTrace();
     }

 }  


 
 
 /*Metodo que copiará a imagem para o disco e salvará na pasta (destination)*/
 public void copyMapaFile(String fileName, InputStream in) {
        try {
          
             // write the inputStream to a FileOutputStream
     	   
             OutputStream out = new FileOutputStream(new File(destinationMapa + fileName));
           
             mapaurl = MAPAURL + fileName;
             
             
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
          
    
             in.close();
             out.flush();
             out.close();
             File dir = new File(destinationMapa);
             dir.listFiles();
          
             System.out.println("New file created!");
             } catch (IOException e) {
             e.printStackTrace();
             }
 }

 
 
 
 
 
 
 
 
public FeiraBO getBo() {
	return bo;
}


public void setBo(FeiraBO bo) {
	this.bo = bo;
}


public Feira getNewFeira() {
	return newFeira;
}


public void setNewFeira(Feira newFeira) {
	this.newFeira = newFeira;
}


public List<Organizadora> getOrgs() {
	return orgs;
}


public void setOrgs(List<Organizadora> orgs) {
	this.orgs = orgs;
}


public List<Pavilhao> getPavs() {
	return pavs;
}


public void setPavs(List<Pavilhao> pavs) {
	this.pavs = pavs;
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


public FileUploadEvent getFilevent() {
	return filevent;
}


public void setFilevent(FileUploadEvent filevent) {
	this.filevent = filevent;
}


public boolean isConstruiu() {
	return construiu;
}


public void setConstruiu(boolean construiu) {
	this.construiu = construiu;
}


public Pavilhao getPavilhaoSelecionado() {
	return pavilhaoSelecionado;
}


public void setPavilhaoSelecionado(Pavilhao pavilhaoSelecionado) {
	this.pavilhaoSelecionado = pavilhaoSelecionado;
}


public Organizadora getOrganizadoraSelecionada() {
	return organizadoraSelecionada;
}


public void setOrganizadoraSelecionada(Organizadora organizadoraSelecionada) {
	this.organizadoraSelecionada = organizadoraSelecionada;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}

public List<Feira> getUltimasFeiras() {
	return ultimasFeiras;
}

public void setUltimasFeiras(LinkedList<Feira> ultimasFeiras) {
	this.ultimasFeiras = ultimasFeiras;
}

public String getDestinationMapa() {
	return destinationMapa;
}

public void setDestinationMapa(String destinationMapa) {
	this.destinationMapa = destinationMapa;
}

public static String getMAPAURL() {
	return MAPAURL;
}

public static void setMAPAURL(String mAPAURL) {
	MAPAURL = mAPAURL;
}

public StreamedContent getImagemMapa() {
	return imagemMapa;
}

public void setImagemMapa(StreamedContent imagemMapa) {
	this.imagemMapa = imagemMapa;
}

public String getMapaurl() {
	return mapaurl;
}

public void setMapaurl(String mapaurl) {
	this.mapaurl = mapaurl;
}

public FileUploadEvent getFileventmapa() {
	return fileventmapa;
}

public void setFileventmapa(FileUploadEvent fileventmapa) {
	this.fileventmapa = fileventmapa;
}

public String getDestinationManual() {
	return destinationManual;
}

public void setDestinationManual(String destinationManual) {
	this.destinationManual = destinationManual;
}

public static String getMANUALURL() {
	return MANUALURL;
}

public static void setMANUALURL(String mANUALURL) {
	MANUALURL = mANUALURL;
}

public StreamedContent getImagemManual() {
	return imagemManual;
}

public void setImagemManual(StreamedContent imagemManual) {
	this.imagemManual = imagemManual;
}

public String getManualurl() {
	return manualurl;
}

public void setManualurl(String manualurl) {
	this.manualurl = manualurl;
}

public FileUploadEvent getFileventmanual() {
	return fileventmanual;
}

public void setFileventmanual(FileUploadEvent fileventmanual) {
	this.fileventmanual = fileventmanual;
}

public String getImagemManualString() {
	return imagemManualString;
}

public void setImagemManualString(String imagemManualString) {
	this.imagemManualString = imagemManualString;
}

public int getTotalExpositores() {
	return totalExpositores;
}

public void setTotalExpositores(int totalExpositores) {
	this.totalExpositores = totalExpositores;
}

public String getImagemMapaString() {
	return imagemMapaString;
}

public void setImagemMapaString(String imagemMapaString) {
	this.imagemMapaString = imagemMapaString;
}



 
 
 
 
	
}
