package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.converter.OrgConverter;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.OrganizadoraBO;
import br.com.bibideais.service.PaisBO;


/**
 * 
 * @author Shido
 * Trata o cadastro de Organizadoras
 */
@ManagedBean(name ="cadOrgBean")
@ViewScoped
public class CadastroOrganizadoraBean {

	
	private Organizadora neworganizadora;
	
	
	private OrganizadoraBO bo;
	
	
	private List<Organizadora> ultimasCadastradas;
	
	@PostConstruct
	public void init(){
		bo = new OrganizadoraBO();
		neworganizadora = new Organizadora();
		ultimasCadastradas = bo.buscarUltimasCadastradas();
	}
	
	
	
	public void cadastrarOrganizadora(){
		MessageUtil mu = new MessageUtil();
		if(!this.verificarCidade()){
			
			mu.sendErrorMessageToUser("Cidade Inválida", "Por favor selecione uma cidade" );
		}else{
			try{
				
				if(filevent != null){
					//Copiar o avatar para o servidor que ja dará a url do arquivo.
					this.upload(filevent);
				     neworganizadora.setLogourl(imagemurl);
					
				}
				
				
			     bo = new OrganizadoraBO();
					bo.cadastrar(neworganizadora);

			 		HttpSession session = FacesUtil.getSession();		        
			         Funcionario f = (Funcionario) session.getAttribute("funcionario");
			         
			         
			      //Inserindo Log
			     LogBean.inserirLog(AcaoUtil.CADASTROUORGANIZADORA + neworganizadora.getRazaoSocial() , f,  null);
		      
			     
			     imagem = new DefaultStreamedContent();
			     
				mu.sendInfoMessageToUser("Organizadora Cadastrada", null );
				
				neworganizadora = new Organizadora();
				
				if(session.getAttribute("cadFeiraBean")!= null){
					session.removeAttribute("cadFeiraBean");

				}
				ultimasCadastradas = bo.buscarUltimasCadastradas();
				OrgConverter.organizadoras.clear();
				OrgConverter.organizadoras = new OrganizadoraBO().buscarTodasAlfabetic();
				
			}catch(Exception e){
				mu.sendErrorMessageToUser("Um erro ocorreu", "Não foi possível cadatrar a organizadora" );
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	
	
	/*********************************Imagens do Logo********************************************/
	

	 /*Destino para onde será gravada a imagem do logo*/
	private String destination= ImagesUtil.pathOrgLogo;
	
	//Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String LOGOURL = ImagesUtil.urlOrgLogos;

  

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

  public boolean verificarCidade(){
	  if(idCidade == 0){
		  MessageUtil u = new MessageUtil();
		  u.sendErrorMessageToUser("Cidade Inválida","Por favor selecione uma cidade");    	  
		  return false;
	  }else{
		  neworganizadora.setCidade((new CidadeBO().buscarPorId(idCidade)));
		  return true;
	  }
  }


  public void stateChangeListener(ValueChangeEvent event) {
	   
	

	  cidades = new CidadeBO().buscarPorCodigoPais(event.getNewValue().toString());
	  	

  }



	

	
	
	

	public Organizadora getNeworganizadora() {
		return neworganizadora;
	}

	public void setNeworganizadora(Organizadora neworganizadora) {
		this.neworganizadora = neworganizadora;
	}



	public OrganizadoraBO getBo() {
		return bo;
	}



	public void setBo(OrganizadoraBO bo) {
		this.bo = bo;
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



	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}



	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}



	public List<Organizadora> getUltimasCadastradas() {
		return ultimasCadastradas;
	}



	public void setUltimasCadastradas(List<Organizadora> ultimasCadastradas) {
		this.ultimasCadastradas = ultimasCadastradas;
	}
	
	
	
	
}
