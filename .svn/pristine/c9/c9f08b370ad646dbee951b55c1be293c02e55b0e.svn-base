package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Organizadora;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.jsfutil.ImagesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.OrganizadoraBO;
import br.com.bibideais.service.PaisBO;
/**
 * 
 * @author Shido
 * Trata a busca por organizadoras, e edição das mesmas
 */
@ManagedBean(name ="orgBean")
@SessionScoped
public class OrganizadoraBean {

	//Nova organizadora
	private Organizadora organizadora;
	
	//Lista de organizadoras que vem do banco
	private List<Organizadora> organizadoras;
	
	private String paramBusca;
	
	private Organizadora orgaSelecionada;
	
	private List<Feira> feirasOrganizadora;
	
	
	private OrganizadoraBO bo;
	
	private BigInteger totalOrganizadoras;
	
	
	   
	//Preenche toda vez que o usuario visualizar detalhes da feira
   public void preencherFeirasOrgSelecionada(){

		   feirasOrganizadora = new FeiraBO().buscarPorOrganizadora(orgaSelecionada);
		  
	   }
	
	
	
	/*********************************Imagens do Logo********************************************/
	

	 /*Destino para onde será gravada a imagem do logo*/
	private String destination= ImagesUtil.pathOrgLogo;
	
	//Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
	private static String LOGOURL = ImagesUtil.urlOrgLogos;

   

   /*Imagem que irá aparecer na página*/
   private StreamedContent imagem;
   
   private String imagemurl;
	
	

   
   
   /*Metodo que irá chamar o copy file, esse permite que outras ações sejam feitas como mostrar mensagem*/
   public void upload(FileUploadEvent event) {  
      
       try {
       	//Imagem pra aparecer na pagina
       	setImagem(new DefaultStreamedContent(event.getFile().getInputstream()));
       	
       	//Gera o nome nome da imagem
       	ImagesUtil u = new ImagesUtil();
     	if(event.getFile().getFileName()!=null || !event.getFile().getFileName().equalsIgnoreCase("") || !event.getFile().getFileName().equalsIgnoreCase("undefined")){

       	String fileName2 = u.gerarIdentificadorImagem(event.getFile().getFileName());
    	if(fileName2 !=null){
       	//Chama o metodo que gravará no disco passando o novo nome
           copyFile(fileName2, event.getFile().getInputstream());
           
           
           //Atualizando o registro
           bo = new OrganizadoraBO();
           orgaSelecionada.setLogourl(imagemurl);
		orgaSelecionada  = bo.atualizarLogo(orgaSelecionada);
			
		MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Imagem de logo atualizada", null);
		  
    	}
     	}
       } catch (IOException e) {
   
       	MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Um erro ocorreu ao tentar alterar o logo", null);
		
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
            
               
               //Deletar a antiga foto do sistema (se não for a default)
              // if(!orgaSelecionada.getLogourl().equalsIgnoreCase("default.jpg")){
               if(orgaSelecionada.getLogourl() !=null){
            	   //Preparando o nome da string do LOGO para deletar do sistema.
               	 int i = orgaSelecionada.getLogourl().lastIndexOf('/');
            		String s = orgaSelecionada.getLogourl().substring(i + 1);
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
   
   
   
   
   
	
	
	
	@PostConstruct
	public void init(){
		bo = new OrganizadoraBO();
		totalOrganizadoras = bo.countOrganizadoras();
		orgaSelecionada = new Organizadora();
		organizadoras = new ArrayList<Organizadora>();
		
		
		
		
		
	}

	
	public void buscarOrganizadorasPorNome(){
		bo = new OrganizadoraBO();
		if(paramBusca == null){
			organizadoras = bo.buscarTodas();
		}else{
			organizadoras = bo.localizarRazaoSocial(paramBusca);
				
		}
		
	}
	

		public void alterarOrganizadora(){
			
				bo = new OrganizadoraBO();
				
				if(idCidade != 0 && idCidade != orgaSelecionada.getCidade().getId()){
					CidadeBO cbo = new CidadeBO();
					Cidade c = cbo.buscarPorId(idCidade);
					orgaSelecionada.setCidade(c);
				}
				
				
				
				try{
					MessageUtil u = new MessageUtil();
					u.sendInfoMessageToUser("Informações Atualizadas", "Success");
					bo.alterarOrganizadora(orgaSelecionada);
					
					if(paramBusca == null){
						organizadoras = bo.buscarTodas();
					}else{
						organizadoras = bo.localizarRazaoSocial(paramBusca);
							
					}
					
					
				}catch(Exception e ){
					MessageUtil u = new MessageUtil();
					u.sendErrorMessageToUser("Erro ao Alterar informações", "Erro");
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
		    		//neworganizadora.setCidade((new CidadeBO().buscarPorId(idCidade)));
		    		return true;
		    	}
		    }
		    
		
	    public void stateChangeListener(ValueChangeEvent event) {

	      
	      cidades = new CidadeBO().buscarPorCodigoPais(event.getNewValue().toString());
	  	
	      
	    }
	    
	    
	
	
	
	

	public Organizadora getOrganizadora() {
		return organizadora;
	}


	public void setOrganizadora(Organizadora organizadora) {
		this.organizadora = organizadora;
	}


	public List<Organizadora> getOrganizadoras() {
		return organizadoras;
	}


	public void setOrganizadoras(List<Organizadora> organizadoras) {
		this.organizadoras = organizadoras;
	}


	public String getParamBusca() {
		return paramBusca;
	}


	public void setParamBusca(String paramBusca) {
		this.paramBusca = paramBusca;
	}


	public OrganizadoraBO getBo() {
		return bo;
	}


	public void setBo(OrganizadoraBO bo) {
		this.bo = bo;
	}


	public BigInteger getTotalOrganizadoras() {
		return totalOrganizadoras;
	}


	public void setTotalOrganizadoras(BigInteger totalOrganizadoras) {
		this.totalOrganizadoras = totalOrganizadoras;
	}


	public Organizadora getOrgaSelecionada() {
		return orgaSelecionada;
	}


	public void setOrgaSelecionada(Organizadora orgaSelecionada) {
		this.orgaSelecionada = orgaSelecionada;
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




	public StreamedContent getImagem() {
		return imagem;
	}




	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}


	public List<Feira> getFeirasOrganizadora() {
		return feirasOrganizadora;
	}


	public void setFeirasOrganizadora(List<Feira> feirasOrganizadora) {
		this.feirasOrganizadora = feirasOrganizadora;
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


	public String getImagemurl() {
		return imagemurl;
	}


	public void setImagemurl(String imagemurl) {
		this.imagemurl = imagemurl;
	}
	
	
	
	
	
	
	
	
}
