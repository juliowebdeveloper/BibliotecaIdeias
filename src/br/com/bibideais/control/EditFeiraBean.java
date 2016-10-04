package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.converter.OrgConverter;
import br.com.bibideais.converter.PavilhaoConverter;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.OrganizadoraBO;


/**
 * 
 * @author Shido
 * Trata a edição de uma feira especifica.
 */
@ManagedBean(name ="editFeiraBean")
@SessionScoped
public class EditFeiraBean implements Serializable{

	private static final long serialVersionUID = 1L;


	private Feira feira;
	
	private List<Organizadora> orgs;
	
	private Organizadora organizadora;
	
	private Pavilhao pavilhao;

	private List<Pavilhao> pavs;
	
	private Organizadora orgSelecionada;
	
	private Pavilhao pavSelecionado;
	
	private FeiraBO bo;
	
	private String idOrga;
	
	@PostConstruct
	public void init(){
		//Pega a feira que foi colocada no request para usa-la
        HttpServletRequest req = FacesUtil.getRequest();

    	bo = new FeiraBO();
        feira = (Feira) req.getAttribute("feiraedit");
        pavilhao = feira.getLocal();
   
        orgs = OrgConverter.organizadoras;
        pavs = PavilhaoConverter.pavilhoes;
        
	
		
		//Setando o atributo da imagem de doc ou pdf de acordo com o que veio do banco.
		if(feira.getUrlManual() != null){
		if(	feira.getUrlManual().substring(feira.getUrlManual().lastIndexOf(".")).equalsIgnoreCase(".pdf")){
			manualStringImagem = "/site/images/pdficon.png";
		}else if(feira.getUrlManual().substring(feira.getUrlManual().lastIndexOf(".")).equalsIgnoreCase(".doc")|| 
				feira.getUrlManual().substring(feira.getUrlManual().lastIndexOf(".")).equalsIgnoreCase(".docx")){
					manualStringImagem = "/site/images/docicon.png";

				}

		}
		
		if(feira.getUrlMapa()!=null){
			if(	feira.getUrlMapa().substring(feira.getUrlManual().lastIndexOf(".")).equalsIgnoreCase(".pdf")){
				manualStringImagem = "/site/images/pdficon.png";
			}else if(feira.getUrlMapa().substring(feira.getUrlManual().lastIndexOf(".")).equalsIgnoreCase(".doc")|| 
					feira.getUrlMapa().substring(feira.getUrlManual().lastIndexOf(".")).equalsIgnoreCase(".docx")){
						manualStringImagem = "/site/images/docicon.png";

					}
		}
		
		
	}
	
	public void preencherExpositoresTotais(){
		feira.setTotalExpositores(feira.getExpositoresInt() + feira.getExpositoresNac());
	}
	
	
	
	
	   public void alterarOrganizadora(){
		 
		   MessageUtil mu = new MessageUtil();
		   if(orgSelecionada != null){
			   bo = new FeiraBO();
			   feira.setOrganizadora(orgSelecionada);
			   bo.updateFeira(feira);
			   mu.sendInfoMessageToUser("Organizadora alterada", null);
		   }else{
			   mu.sendErrorMessageToUser("Selecione uma organizadora", null);

		   }
	   }

	   public void alterarPavilhao(){

		   MessageUtil mu = new MessageUtil();
		   if(pavSelecionado != null){
			   bo = new FeiraBO();
		   feira.setLocal(pavSelecionado);
		   bo.updateFeira(feira);
		   mu.sendInfoMessageToUser("Local alterado", null);

		   }else{
			   mu.sendErrorMessageToUser("Selecione um local", null);

		   }
	   }
	   
	   
	   
	   
	   
	public void atualizarFeira(){
		
		try{
			MessageUtil u = new MessageUtil();
			u.sendInfoMessageToUser("Informações Atualizadas", null);
			bo.atualizarFeira(feira);
			
			
		}catch(Exception e ){
			MessageUtil u = new MessageUtil();
			u.sendErrorMessageToUser("Erro ao Alterar informações", null);
			e.printStackTrace();
		}
	}
	
	
	
	/*********************************Imagens do Logo********************************************/
	

	 /*Destino para onde será gravada a imagem do logo*/
	private String destination= ImagesUtil.pathLogos;
	
	//Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String LOGOURL = ImagesUtil.urlLogos;

  

	/*Imagem que irá aparecer na página*/
	private StreamedContent imagem;
  
	private String imagemurl;
	
	
  
  
  
  
  /*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
  public void upload(FileUploadEvent event) {  
     
      try {
    	  
      	//Imagem pra aparecer na pagina
      	setImagem(new DefaultStreamedContent(event.getFile().getInputstream()));
      	
      	//Gera o nome da imagem
      	ImagesUtil u = new ImagesUtil();
     	if(event.getFile().getFileName()!=null || !event.getFile().getFileName().equalsIgnoreCase("") || !event.getFile().getFileName().equalsIgnoreCase("undefined")){

      	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
    	if(fileName2 !=null){
      	//Chama o metodo que gravará no disco passando o novo nome
          copyFile(fileName2, event.getFile().getInputstream());
         
          
          //Atualizando o registro
          bo = new FeiraBO();
          feira.setUrlLogo(imagemurl);
          feira  = bo.atualizarLogo(feira);
     	}
		MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Imagem de logo atualizada", null);
		  
		
     	}
      } catch (IOException e) {
  
      	MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Um erro ocorreu ao tentar alterar o logo", null);
		
          e.printStackTrace();
      }

  }  

  public void alterarLocal(){
	  
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
           
              
              //Deletar a antiga foto do sistema (se não for a default)
            	  //Preparando o nome da string do logo para deletar do sistema.
              if(feira.getUrlLogo() != null){
             	 int i = feira.getUrlLogo().lastIndexOf('/');
          		String s = feira.getUrlLogo().substring(i + 1);

              	File f = new File(destination + s);
              	
                  f.delete();	
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



  
  /*********************************Imagens do Mapa********************************************/
	

	 /*Destino para onde será gravada a imagem do logo*/
	private String destinationmapa= ImagesUtil.pathMapas;
	
	//Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String MAPAURL = ImagesUtil.urlMapaFeira;



	/*Imagem que irá aparecer na página*/
	private StreamedContent imagemMapa;

	private String mapaimagemurl;
	
	
	private String mapaStringImagem;



/*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
public void uploadMapa(FileUploadEvent event) {  
  
   try {
 	  

   	//Imagem pra aparecer na pagina
   	//setImagemMapa(new DefaultStreamedContent(event.getFile().getInputstream()));
   	
   	//Gera o nome da imagem
   	ImagesUtil u = new ImagesUtil();
   	
   	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
   	
   	//Chama o metodo que gravará no disco passando o novo nome
       copyMapaFile(fileName2, event.getFile().getInputstream());
      
       
       //Atualizando o registro
       bo = new FeiraBO();
       feira.setUrlMapa(mapaimagemurl);
       feira  = bo.atualizarMapa(feira);
			
		MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Mapa atualizado", null);
		  
		
		
		//Imagem pra aparecer na pagina
		
		if(event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".pdf")){

			mapaStringImagem = "/site/images/pdficon.png";
		}else if (event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".doc") 
				|| event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".docx")){

			mapaStringImagem = "/site/images/docicon.png";

		}
		  
		
		
   } catch (IOException e) {

   	MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Um erro ocorreu ao tentar alterar o mapa", null);
		
       e.printStackTrace();
   }

}  




/*Metodo que copiará a imagem para o disco e salvará na pasta (destination)*/
public void copyMapaFile(String fileName, InputStream in) {
      try {
        
           // write the inputStream to a FileOutputStream
   	   
           OutputStream out = new FileOutputStream(new File(destinationmapa + fileName));
         
           mapaimagemurl = MAPAURL + fileName;
           

           
           int read = 0;
           byte[] bytes = new byte[1024];
        
           while ((read = in.read(bytes)) != -1) {
               out.write(bytes, 0, read);
           }
        
           
           //Deletar a antiga foto do sistema (se não for a default)
         	  //Preparando o nome da string do logo para deletar do sistema.
           if(feira.getUrlMapa() != null){
          	 int i = feira.getUrlMapa().lastIndexOf('/');
       		String s = feira.getUrlMapa().substring(i + 1);

           	File f = new File(destinationmapa + s);
           	
               f.delete();	
           }

           in.close();
           out.flush();
           out.close();
           File dir = new File(destinationmapa);
           dir.listFiles();
        
           System.out.println("New file created!");
           } catch (IOException e) {
           e.printStackTrace();
           }
}





/*********************************Imagens do Manual********************************************/


/*Destino para onde será gravada a imagem do logo*/
private String destinationmanual= ImagesUtil.pathManuais;

//Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
private static String MANUALURL = ImagesUtil.urlManuaisFeira;



private String manualStringImagem;



private String manualimagemurl;






/*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
public void uploadManual(FileUploadEvent event) {  

try {
  

	//Gera o nome da imagem
	ImagesUtil u = new ImagesUtil();
	
	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
	
	//Chama o metodo que gravará no disco passando o novo nome
  copyManualFile(fileName2, event.getFile().getInputstream());
 
  
  //Atualizando o registro
  bo = new FeiraBO();
  feira.setUrlManual(manualimagemurl);
  feira  = bo.atualizarManual(feira);
		
	MessageUtil mu = new MessageUtil();
	mu.sendInfoMessageToUser("Manual atualizado", null);
	
	
	
	//Imagem pra aparecer na pagina
	
	if(event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".pdf")){

		manualStringImagem = "/site/images/pdficon.png";
	}else if (event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".doc") 
			|| event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf(".")).equalsIgnoreCase(".docx")){

		manualStringImagem = "/site/images/docicon.png";

	}
	  
	
	
	
} catch (IOException e) {

	MessageUtil mu = new MessageUtil();
	mu.sendInfoMessageToUser("Um erro ocorreu ao tentar alterar o manual", null);
	
  e.printStackTrace();
}

}  




/*Metodo que copiará a imagem para o disco e salvará na pasta (destination)*/
public void copyManualFile(String fileName, InputStream in) {
 try {
   
      // write the inputStream to a FileOutputStream
	   
      OutputStream out = new FileOutputStream(new File(destinationmanual + fileName));
    
      manualimagemurl = MANUALURL + fileName;
      
 
      
      int read = 0;
      byte[] bytes = new byte[1024];
   
      while ((read = in.read(bytes)) != -1) {
          out.write(bytes, 0, read);
      }
   
      
      //Deletar a antiga foto do sistema (se não for a default)
    	  //Preparando o nome da string do logo para deletar do sistema.
      if(feira.getUrlManual() != null){
     	 int i = feira.getUrlManual().lastIndexOf('/');
  		String s = feira.getUrlManual().substring(i + 1);

      	File f = new File(destinationmanual + s);
      	
          f.delete();	
      }

      in.close();
      out.flush();
      out.close();
      File dir = new File(destinationmanual);
      dir.listFiles();
   
      System.out.println("New file created!");
      } catch (IOException e) {
      e.printStackTrace();
      }
}









  

public Feira getFeira() {
	return feira;
}




public void setFeira(Feira feira) {
	this.feira = feira;
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





public FeiraBO getBo() {
	return bo;
}


public void setBo(FeiraBO bo) {
	this.bo = bo;
}

public String getDestinationmapa() {
	return destinationmapa;
}

public void setDestinationmapa(String destinationmapa) {
	this.destinationmapa = destinationmapa;
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

public String getMapaimagemurl() {
	return mapaimagemurl;
}

public void setMapaimagemurl(String mapaimagemurl) {
	this.mapaimagemurl = mapaimagemurl;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public String getDestinationmanual() {
	return destinationmanual;
}

public void setDestinationmanual(String destinationmanual) {
	this.destinationmanual = destinationmanual;
}

public static String getMANUALURL() {
	return MANUALURL;
}

public static void setMANUALURL(String mANUALURL) {
	MANUALURL = mANUALURL;
}



public String getManualimagemurl() {
	return manualimagemurl;
}

public void setManualimagemurl(String manualimagemurl) {
	this.manualimagemurl = manualimagemurl;
}



public String getManualStringImagem() {
	return manualStringImagem;
}

public void setManualStringImagem(String manualStringImagem) {
	this.manualStringImagem = manualStringImagem;
}

public String getMapaStringImagem() {
	return mapaStringImagem;
}

public void setMapaStringImagem(String mapaStringImagem) {
	this.mapaStringImagem = mapaStringImagem;
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

public Organizadora getOrgSelecionada() {
	return orgSelecionada;
}

public void setOrgSelecionada(Organizadora orgSelecionada) {
	this.orgSelecionada = orgSelecionada;
}

public Pavilhao getPavSelecionado() {
	return pavSelecionado;
}

public void setPavSelecionado(Pavilhao pavSelecionado) {
	this.pavSelecionado = pavSelecionado;
}

public Organizadora getOrganizadora() {
	return organizadora;
}

public void setOrganizadora(Organizadora organizadora) {
	this.organizadora = organizadora;
}

public Pavilhao getPavilhao() {
	return pavilhao;
}

public void setPavilhao(Pavilhao pavilhao) {
	this.pavilhao = pavilhao;
}

public String getIdOrga() {
	return idOrga;
}

public void setIdOrga(String idOrga) {
	this.idOrga = idOrga;
}
  
  
  
  
  
	
	
	
	
	
	
}
