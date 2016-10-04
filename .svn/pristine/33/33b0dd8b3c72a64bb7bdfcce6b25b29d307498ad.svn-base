package br.com.bibideais.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import br.com.bibideais.converter.AnoConverter;
import br.com.bibideais.converter.FeirasTotaisConverter;
import br.com.bibideais.converter.FuncConverter;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Ficha;
import br.com.bibideais.entity.FotoCliente;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.Pais;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;
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
import br.com.bibideais.service.LogBO;
import br.com.bibideais.service.PaisBO;
import br.com.bibideais.service.ProjetoBO;
;


/**
 * 
 * @author Shido
 * Trata a edição dos dados basicos do cliente
 */
@ManagedBean(name ="editCliBean")
@SessionScoped
public class EditClienteBean {

	


	private Cliente cliente;
	
	
	
	private List<ContatoCliente> contatos;
	
	private ContatoCliente contatoSelecionado;
	
	private ContatoCliente newContato;
	
	
	private List<Briefing> briefings;
	
	private Briefing selectedBriefing;
	
	private Briefing newBriefing = new Briefing();
	
	
	
	private Funcionario funcSelecionado;
	
	private List<Funcionario> funcionarios;
	
	
	/***********************ANOS*******************************************************/

	
	
	//Anos que o cliente ja recebeu trabalhos
	private List<Ano> anosCliente;
	
	//Anos para o cliente escolher 
	private List<Ano> anosEdit;
	
	private List<Ano> anosSelecionados;
	
	
	
	
	//Anos caso o cliente não tenha nenhum trabalho - Esteja cadastrando como cliente agora
	private List<Ano> anos;
	

	private DualListModel<Ano> anosEscolhidos;  
	
	
	
	/***********************FOTOS*******************************************************/
	private List<FotoCliente> fotos;
	

	
	private FotoCliente selectedPhoto;
	
	
	//Nova legenda
	private String novaLegendaFoto;
	
	
	/**********************Fichas*******************************************************/

		private List<Ficha> fichas;
	

		
		private Ficha selectedFicha;
		
		
		//Nova legenda
		private String novaLegendaFicha;
	
		
		
		/**********************Feiras*******************************************************/
		
		
		private List<Feira> feirasCliente;
		
		//Não inclui as feiras do cliente
		private List<Feira> feirasTotais;
		
		
		private List<Feira> selectedFeiras;
		
		//Feira a ser excluida
		private Feira selectedFeira;
		
		
		
		
	private ClienteBO bo;
	
	private FeiraBO feiraBo = new FeiraBO();
	
	
	
	/**********************Briefings*******************************************************/

	

	
	@PostConstruct
	public void init(){
	
		
		//Pega o cliente que foi colocado no request para usa-lo
        HttpServletRequest req = FacesUtil.getRequest();
        cliente = (Cliente) req.getAttribute("clienteedit");
        
        contatoSelecionado = new ContatoCliente();
        newContato = new ContatoCliente();

        contatos = cliente.getContatos();
        
        fotos = cliente.getFotosReferencia();
        fichas = cliente.getFichas();
        
        briefings = cliente.getBriefings();
        selectedBriefing = new Briefing();
        selectedFeira = new Feira();

        feirasCliente = cliente.getFeiras();

        feirasTotais = FeirasTotaisConverter.retornarFeiras(cliente);
 
        
        anosCliente = cliente.getAnostrabalhos();
        anosEdit = AnoConverter.anos;
        anos = AnoConverter.anos;
    	//Picklist de anos
		List<Ano> targetano = new ArrayList<Ano>();
		anosEscolhidos = new DualListModel<Ano>(anos, targetano);  
		
        
        funcionarios = FuncConverter.funcionarios;
        
        bo = new ClienteBO();
        
    	
	
        
	}
	
	
	
	
	public String editarProjetos(){
		HttpServletRequest request = FacesUtil.getRequest();
		
		  HttpSession session = FacesUtil.getSession();
	        
	        if(session.getAttribute("editProjBean") != null){
	        	session.removeAttribute("editProjBean");
	        }
	        request.setAttribute("clienteedit", cliente);
		
		return "editprojetos.xhtml";
	}

	public String editarBriefings(){
		HttpServletRequest request = FacesUtil.getRequest();
		
		  HttpSession session = FacesUtil.getSession();
	        
	        if(session.getAttribute("editBriBean") != null){
	        	session.removeAttribute("editBriBean");
	        }
	        request.setAttribute("clienteedit", cliente);
		
		return "editbriefings.xhtml";
	}
	

	public String excluirCliente(){

		try{
			 
		MessageUtil mu = new MessageUtil();
		
		
		
		 /*Destino para onde será gravada os arquvios do briefing */
		String destinationFiles= ImagesUtil.pathBriefings;
		//Excluir Briefings files dos briefings do cliente
		BriefingBO bbo = new BriefingBO();
		if(briefings != null){
		for (Briefing  bri : briefings) {
			bri.setFiles( bbo.buscarFilesPorBriefing(bri));
			if(!bri.getFiles().isEmpty()){
				for (BriefingFiles f : bri.getFiles()) {
					String imagename = f.getUrlFile().substring(f.getUrlFile().lastIndexOf("/")+1, f.getUrlFile().length());

					File file = new File(destinationFiles + imagename);
		            file.delete();	
					
		            //Excluindo o arquivo do briefing
		            bbo.excluirBriFile(f);
				}
			}
			//Excluir o briefing
			bbo.excluirBriefing(bri);
		}

		}

		
		
		//Excluir Projetos do Cliente
		
		 /*Destino para onde será gravada a imagem das fotos do projeto */
		String destinationFotosProj= ImagesUtil.pathFotosProjeto;
		
		ProjetoBO pbo = new ProjetoBO();
		if(cliente.getProjetos()!=null){
		for (Projeto proj : cliente.getProjetos()) {
				proj.setVersoes(pbo.buscarPeloProjeto(proj));
				
				if(proj.getVersoes()!=null){
				for (VersaoProjeto ver : proj.getVersoes()) {
					ver.setFotosProjeto(pbo.buscarFotoVersao(ver));
					if(ver.getFotosProjeto()!= null){
						
					
						for (FotoProjeto f: ver.getFotosProjeto()) {
						System.out.println("Foto url" + f.getUrlImagem());
						f.getUrlImagem();
						String imagename = f.getUrlImagem().substring(f.getUrlImagem().lastIndexOf("/")+1, f.getUrlImagem().length());

						//Deletando do sistema
						File file = new File(destinationFotosProj + imagename);
		                file.delete();	
		                
		                //Deletando do banco
		               
		                pbo.excluirFotoProjeto(f);
						}
					}
					pbo.excluirVersaoProjeto(ver);

				}
				
				}
			pbo.excluirProjeto(proj);
		}
		}
			
		System.out.println("Projetos excluídos");
		
		//Excluir Fotos do Cliente
		fbo = new FotoBO();
		
		if(cliente.getFotosReferencia() != null){
			for (FotoCliente f : cliente.getFotosReferencia()) {
				File file = new File(destinationFotos +   f.getNomeImagem());
		        file.delete();	
				fbo.excluirFotoCliente(f);

			}
			
		}
	

		
		
		//Excluir Fichas do Cliente
		bo = new ClienteBO();
		if(cliente.getFichas() != null){
			for (Ficha f : cliente.getFichas()) {
            	File file = new File(destinationFichas + f.getNomePath());
		        file.delete();	
		        
				bo.excluirFicha(f);

			}
			
		}
		
		
		
		feiraBo = new FeiraBO();
		
		//Desrelacionar o cliente das feiras
		if(feirasCliente != null){
			for (Feira fe : feirasCliente) {
			feiraBo.desrelacionarFeirasAoCliente(cliente, fe);

			}
		}
		
		
		//Remover os Contatos do Cliente
		if(cliente.getContatos()!=null){
			
			for (ContatoCliente c : cliente.getContatos()) {
				bo.excluirContato(c);
				
			}
		}
		
		//Remover os anos que recebeu trabalho
		AnoBO abo = new AnoBO();
		if(cliente.getAnostrabalhos()!=null){
			abo.desrelacionarAnosCliente(cliente);
			
		}
		
		//Excluir o log acesso
		LogBO logbo = new LogBO();
		logbo.deleterLogsCliente(cliente);
		
		
		//Excluir o Cliente
		bo.excluirCliente(cliente);
		
		HttpSession session = FacesUtil.getSession();
		session.removeAttribute("funcBean");
		
		return "/site/home.xhtml?faces-redirect=true";
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	
	}
	

		

	
	
	public void atualizarCliente(){
		MessageUtil u = new MessageUtil();

		try{
			
			bo.atualizarCliente(cliente);
			u.sendInfoMessageToUser("Informações Atualizadas", null);

			
		}catch(Exception e ){
			u.sendErrorMessageToUser("Erro ao Alterar informações", null);
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void alterarCidade(){
		
		MessageUtil mu = new MessageUtil();
		
		try{
			
			CidadeBO cbo = new CidadeBO();
			Cidade c = cbo.buscarPorId(idCidadeE);
			bo.atualizarCidade(cliente, c);
			cliente.setCidade(c);
			mu.sendInfoMessageToUser("Cidade alterada", null);

		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "A cidade não pôde ser alterada");
		}
		
	}
	
	
	
	
	
	public void alterarAtendimento(){
		MessageUtil mu = new MessageUtil();
		
		try{
			
			bo.alterarAtendimento(cliente, funcSelecionado);
			
			HttpSession session = FacesUtil.getSession();
			Funcionario func = (Funcionario) session.getAttribute("funcionario");
			   //Inserindo Log
			LogBean.inserirLog(AcaoUtil.ALTEROUATENDIMENTO, func, cliente);

	        
	     	cliente.setAtendimento(funcSelecionado);
			mu.sendInfoMessageToUser("Informações alteradas", "O funcionário " + funcSelecionado.getNomeCompleto() + " é agora responsável pelo atendimento desse cliente");
		
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "A cidade não pôde ser alterada");
		}
	}
	
	
	
	
	//Adiciona com os já existentes
	public String adicionarAnos(){
		AnoBO abo = new AnoBO();
		MessageUtil u = new MessageUtil();
		try{

		
		abo.relacionarAnosCliente(cliente, anosSelecionados);
		cliente.setAnostrabalhos(anosSelecionados);
		anosCliente = anosSelecionados;
		
			u.sendInfoMessageToUser("Anos registrados", null);
		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Os anos não puderam ser registrados para esse cliente");
		}
		
		return null;
	}
	
	
	
	
	
	//Cadastra novos anos = picklist
	public String cadastrarAnos(){
		AnoBO anoBo = new AnoBO();
		MessageUtil u = new MessageUtil();
		try{

			//target = picklist
		bo.alterarRecebeuTrabalhos(cliente);
		anoBo.relacionarAnosCliente(cliente, anosEscolhidos.getTarget());
		cliente.setAnostrabalhos(anosEscolhidos.getTarget());
		cliente.setRecebeuTrabalhos(true);
		anosCliente = anosEscolhidos.getTarget();
			u.sendInfoMessageToUser("Anos registrados", null);
		}catch(Exception e){
			e.printStackTrace();
			u.sendErrorMessageToUser("Erro", "Os anos não puderam ser registrados para esse cliente");
		}
		
		return null;
	}
	
	
	
	public void alterarRecebeuTrabalhos(){
	
		
		MessageUtil mu = new MessageUtil();
		
		try{
			
			bo.alterarRecebeuTrabalhos(cliente);
			cliente.setRecebeuTrabalhos(true);
			mu.sendInfoMessageToUser("Alterado", "Esse cliente recebeu trabalhos da BI");
			
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "A cidade não pôde ser alterada");
		}
	}
	
	
	
	public void alterarIsClient(){
		
		MessageUtil mu = new MessageUtil();
		
		try{
			
			bo.alterarIsClient(cliente);
			cliente.setClient(true);
			mu.sendInfoMessageToUser("Alterado", "Esse cliente agora é cliente BI");

		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "A cidade não pôde ser alterada");
		}
	}
	
	
	
	
	
	/**************************CONTATOS***********************************************************************/
	
	public void atualizarContato(){
		MessageUtil mu = new MessageUtil();

		try{
			bo.atualizarContatoCliente(contatoSelecionado);
			
			mu.sendInfoMessageToUser("Informações alteradas", "As informações do Contato foram alteradas");
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "O contato não pode ser alterado");
			
		}
		
	}
	
	
	public void excluirContato(){
		MessageUtil mu = new MessageUtil();

		try{
			bo.excluirContato(contatoSelecionado);
			contatos.remove(contatoSelecionado);
			mu.sendInfoMessageToUser("Contato Excluído", null);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "O contato não pode ser excluído");
			
		}
	}
	
	public void adicionarContato(){
		MessageUtil mu = new MessageUtil();

		try{
			newContato.setCliente(cliente);
			ContatoCliente c1 = bo.inserirContato(newContato);
			contatos.add(c1);
			newContato = new ContatoCliente();
			mu.sendInfoMessageToUser("Contato Adicionado", null);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "O contato não pode ser adicionado");
			
		}
	}
	
	
	
	
	
	/******************************** BRIEFINGS **********************************************************/

	public void atualizarBriefing(){
		MessageUtil mu = new MessageUtil();

		try{
			BriefingBO bbo = new BriefingBO();
			bbo.atualizarBriefing(selectedBriefing);
			
			mu.sendInfoMessageToUser("Informações alteradas", "As informações do briefing foram alteradas");
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "O briefing não pode ser alterado");
			
		}
		
	}
	
	
	public void excluirBriefing(){
		MessageUtil mu = new MessageUtil();

		try{
			BriefingBO bbo = new BriefingBO();
			bbo.excluirBriefing(selectedBriefing);
			briefings.remove(selectedBriefing);
			mu.sendInfoMessageToUser("Briefing Excluído", null);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "O briefing não pode ser excluído");
			
		}
	}
	
	public void adicionarBriefing(){
		MessageUtil mu = new MessageUtil();
		

		try{
			BriefingBO bbo = new BriefingBO();

			newBriefing.setCliente(cliente);
			Briefing b1 = bbo.cadastrarBriefing(newBriefing);
			briefings.add(b1);
			newBriefing = new Briefing();
			mu.sendInfoMessageToUser("Briefing Adicionado", null);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "O briefing não pode ser adicionado");
			
		}
	}
	
	
	
	
	
/************************************Feiras *****************************************************/
	
	
	
	

	public void excluirFeiraCliente(){
		//Remove da lista de feiras do cliente e adiciona na lista de checkbox
		MessageUtil mu = new MessageUtil();
		try{
			feiraBo.desrelacionarFeirasAoCliente(cliente, selectedFeira);
			feirasCliente.remove(selectedFeira);
			feirasTotais.add(selectedFeira);
			
			mu.sendInfoMessageToUser("Feira removida", null);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "Não foi possível remover a feira");
			
		}
		
	}
	
	public void relacionarFeirasCliente(){
		//Junta as duas feiras e refaz a busca para atualziar na página
		
		MessageUtil mu = new MessageUtil();
		try{
			feirasCliente.addAll(selectedFeiras);
			
			feiraBo.relacionarFeirasAoCliente(cliente, feirasCliente);
			
	        feirasTotais = FeirasTotaisConverter.retornarFeiras(cliente);

		
			mu.sendInfoMessageToUser("Feiras relacionadas", null);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "Não foi possível adicionar as feiras ao cliente");

			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	//Fotos e Fichas - Metodos
	public void alterarLegenda(){
		
		MessageUtil mu = new MessageUtil();
		
		try{
			fbo = new FotoBO();
			fbo.atualizarLegendaQuery(selectedPhoto);
			mu.sendInfoMessageToUser("Legenda alterada", null);

		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "Não foi possível alterar a legenda");
		}

	}
	
	public void deletarFoto(){
		MessageUtil mu = new MessageUtil();
		
		try{
			fbo = new FotoBO();
			fbo.excluirFotoCliente(selectedPhoto);
			
			  //Deletar a antiga foto do sistema
            
        	File f = new File(destinationFotos +   selectedPhoto.getNomeImagem());
            f.delete();	
			
			fotos.remove(selectedPhoto);
			mu.sendInfoMessageToUser("Foto Excluída", null);
			
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "Não foi possível excluir a foto");
		}
	}
	
	public void alterarNomeFicha(){
		MessageUtil mu = new MessageUtil();
		
		try{
			bo.atualizarNomeFicha(selectedFicha);
			mu.sendInfoMessageToUser("Nome da ficha alterada", null);

		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "Não foi possível alterar o nome dessa ficha");
		}
	}
	
	
	public void deletarFicha(){
		MessageUtil mu = new MessageUtil();
		
		try{
			bo.excluirFicha(selectedFicha);
		
			  //Deletar a antiga ficha do sistema
            
            	File f = new File(destinationFichas + selectedFicha.getNomePath());
                f.delete();	
            
            	fichas.remove(selectedFicha);
    			mu.sendInfoMessageToUser("Ficha Excluída", null);
    			
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Erro", "Não foi possível excluir a ficha");
		}
	}
	
	
	
	/*********************************** Cidades e  Países da Edição **************************************************************/

	private List<Cidade> cidadesE ;
	
	private List<Pais> paisesE;
	
	
	private String codPaisE = "BRA" ;
	
	private int idCidadeE = 0;
	
	private int idPaisE;
	
	

    /*Preenche toda a lista de paises*/
    public List<Pais> getPaisesE() {
    	if(paisesE == null){
    		paisesE = new PaisBO().buscarTodos();
    	}
    	return paisesE;
    }
  
    
    /*Preenche a cidade pelo Pais.*/
    public List<Cidade> getCidadesE(){
    	if(cidadesE == null){
    		cidadesE = new CidadeBO().buscarPorCodigoPais(codPaisE);
    		
    	}
    	
    	
    	return cidadesE;
    }


	
    public void stateChangeListenerE(ValueChangeEvent event) {
       
      
      
      cidadesE = new CidadeBO().buscarPorCodigoPais(event.getNewValue().toString());
  	
      
    }
    
    
    
    
    
    /*********************************Imagens do Logo********************************************/
	

    /*Destino para onde será gravada a imagem do logo*/
    private String destination= ImagesUtil.pathCartoes;

    //Caminho default para as imagens do avatar.Com esse caminho salvo na URL ficará mais facil de exibi-las
    private static String LOGOURL = ImagesUtil.urlCartoes;



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
         	

    		//Atualizando o registro no BD
    		bo = new ClienteBO();
    		cliente.setUrlCartao(imagemurl);
    		cliente  = bo.atualizarFotoCartao(cliente);

    		MessageUtil mu = new MessageUtil();
    		mu.sendInfoMessageToUser("Imagem do cartão atualizada", null);
         	}
    		
         	}
    	} catch (IOException e) {

    		MessageUtil mu = new MessageUtil();
    		mu.sendInfoMessageToUser("Um erro ocorreu ao tentar alterar o cartão do cliente", null);

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

    		if(cliente.getUrlCartao() != null){
    			//Deletar a antiga foto do sistema (se não for a default)
    			if(!cliente.getUrlCartao().equalsIgnoreCase("default.jpg")){
    				//Preparando o nome da string do cartão para deletar do sistema.
    				int i = cliente.getUrlCartao().lastIndexOf('/');
    				String s = cliente.getUrlCartao().substring(i + 1);
    				File f = new File(destination + s);
    				f.delete();	
    			}
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



	
	
 /*********************************Imagens foto referencia********************************************/


		private FotoCliente newFoto = new FotoCliente();
	

	    
		 /*Destino para onde será gravada a imagem das fotos do cliente */
		private String destinationFotos= ImagesUtil.pathFotos;
		
		//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficará mais facil de exibi-las
		private static String FOTOSURL = ImagesUtil.urlFotoCliente;

	    /*Imagem que irá aparecer na página*/
	    private StreamedContent imagemFoto;
	    
	    private String imagemFotourl;
	    
	    private FotoBO fbo;
	    
	    //Nome da foto que será gravada no banco.
	    private String nomeImagemFoto;
	    
	    
	    
	    
	    //Esse será o metodo chamado para gravar as fotos do cliente
	    public void uploadFotos(FileUploadEvent event){
	    	
	    	try{
	    		
	    		//Imagem 
	        	imagemFoto = new DefaultStreamedContent(event.getFile().getInputstream());
	        	
	        	//Gera o nome nome da imagem
	        	ImagesUtil iu = new ImagesUtil();
	        	String fileName2 = iu.gerarIdentificadorImagem(event.getFile().getFileName());
	        	
	        	//Chama o metodo que gravará no disco passando o novo nome
	            copyFileFotos(fileName2, event.getFile().getInputstream());
	            
	            fbo = new FotoBO();
	            newFoto.setNomeImagem(nomeImagemFoto);
	            newFoto.setUrlimagem(imagemFotourl);
	            newFoto.setLegenda("Sem legenda");
	            newFoto.setCliente(cliente);
	            
	            FotoCliente fotoBanco = fbo.inserirFotoCliente(newFoto);
				fotos.add(fotoBanco);
				newFoto = new FotoCliente();
				
	        	MessageUtil mu = new MessageUtil();
	        	mu.sendInfoMessageToUser("Fotos adicionadas", null);
	    		
	    		 
	    		 
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
	              
	                imagemFotourl = FOTOSURL + fileName;
	                nomeImagemFoto = fileName;
	                
	              
	                
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
	             
	                } catch (IOException e) {
	                e.printStackTrace();
	                }
	    }
		

	/*************************************** Imagens fichas *********************************************************/

			private Ficha newFicha = new Ficha();
	

		    
			 /*Destino para onde será gravada a imagem das fotos do cliente */
			private String destinationFichas= ImagesUtil.pathFichas;
			
			//Caminho default para as imagens das fotos .Com esse caminho salvo na URL ficará mais facil de exibi-las
			private static String FICHASSURL = ImagesUtil.urlFichas;

		 

		    /*Imagem que irá aparecer na página*/
		    private StreamedContent imagemFicha;
		    
		    private String imagemFichaurl;
		    
		   
		    
		    //Nome da foto que será gravada no banco.
		    private String nomeImagemFicha;
		    
			

		
		    
		    //Esse será o metodo chamado para gravar as fichas do cliente
		    public void uploadFichas(FileUploadEvent event){
		    	
		    	try{
		    		
		    		//Imagem 
		        	imagemFicha = new DefaultStreamedContent(event.getFile().getInputstream());
		        	
		        	//Gera o nome nome da imagem
		        	ImagesUtil iu = new ImagesUtil();
		        	String fileName2 = iu.gerarIdentificadorImagem(event.getFile().getFileName());
		        	
		        	//Chama o metodo que gravará no disco passando o novo nome
		            copyFileFichas(fileName2, event.getFile().getInputstream());
		            
		            
		            
		            newFicha.setUrlimagem(imagemFichaurl);
		            newFicha.setNomePath(nomeImagemFicha);
		            newFicha.setNomeImagem(nomeImagemFicha);
		            newFicha.setCliente(cliente);
		            

		            Ficha fichabanco = bo.inserirFicha(newFicha);
		            fichas.add(fichabanco);
					newFicha = new Ficha();
					
		        	MessageUtil mu = new MessageUtil();
		        	mu.sendInfoMessageToUser("Fichas adicionadas", null);
		    		
		    		 
		    	
		    	}catch (Exception e) {

		    		MessageUtil mu = new MessageUtil();
		    		mu.sendErrorMessageToUser("Não foi possível fazer o upload das fichas", null);
		    		
		    		e.printStackTrace();
		        }
		      
	           
		    }
		    
		    
		   
		    public void copyFileFichas(String fileName, InputStream in) {
		           try {
		             
		                // write the inputStream to a FileOutputStream
		        	   
		        	   	
		                OutputStream out = new FileOutputStream(new File(destinationFichas + fileName));
		              
		                imagemFichaurl = FICHASSURL + fileName;
		                nomeImagemFicha = fileName;
		                
		                
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

	public String getCodPaisE() {
		return codPaisE;
	}

	public void setCodPaisE(String codPaisE) {
		this.codPaisE = codPaisE;
	}

	public int getIdCidadeE() {
		return idCidadeE;
	}

	public void setIdCidadeE(int idCidadeE) {
		this.idCidadeE = idCidadeE;
	}

	public int getIdPaisE() {
		return idPaisE;
	}

	public void setIdPaisE(int idPaisE) {
		this.idPaisE = idPaisE;
	}

	public void setCidadesE(List<Cidade> cidadesE) {
		this.cidadesE = cidadesE;
	}

	public void setPaisesE(List<Pais> paisesE) {
		this.paisesE = paisesE;
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

	public ContatoCliente getNewContato() {
		return newContato;
	}

	public void setNewContato(ContatoCliente newContato) {
		this.newContato = newContato;
	}

	public List<Ano> getAnosCliente() {
		return anosCliente;
	}

	public void setAnosCliente(List<Ano> anosCliente) {
		this.anosCliente = anosCliente;
	}

	public List<Ano> getAnosEdit() {
		return anosEdit;
	}

	public void setAnosEdit(List<Ano> anosEdit) {
		this.anosEdit = anosEdit;
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

	public List<Ano> getAnosSelecionados() {
		return anosSelecionados;
	}

	public void setAnosSelecionados(List<Ano> anosSelecionados) {
		this.anosSelecionados = anosSelecionados;
	}

	public Funcionario getFuncSelecionado() {
		return funcSelecionado;
	}

	public void setFuncSelecionado(Funcionario funcSelecionado) {
		this.funcSelecionado = funcSelecionado;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
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

	public FotoCliente getNewFoto() {
		return newFoto;
	}

	public void setNewFoto(FotoCliente newFoto) {
		this.newFoto = newFoto;
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

	public StreamedContent getImagemFoto() {
		return imagemFoto;
	}

	public void setImagemFoto(StreamedContent imagemFoto) {
		this.imagemFoto = imagemFoto;
	}

	public String getImagemFotourl() {
		return imagemFotourl;
	}

	public void setImagemFotourl(String imagemFotourl) {
		this.imagemFotourl = imagemFotourl;
	}

	public FotoBO getFbo() {
		return fbo;
	}

	public void setFbo(FotoBO fbo) {
		this.fbo = fbo;
	}

	public String getNomeImagemFoto() {
		return nomeImagemFoto;
	}

	public void setNomeImagemFoto(String nomeImagemFoto) {
		this.nomeImagemFoto = nomeImagemFoto;
	}

	public Ficha getNewFicha() {
		return newFicha;
	}

	public void setNewFicha(Ficha newFicha) {
		this.newFicha = newFicha;
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

	public StreamedContent getImagemFicha() {
		return imagemFicha;
	}

	public void setImagemFicha(StreamedContent imagemFicha) {
		this.imagemFicha = imagemFicha;
	}

	public String getImagemFichaurl() {
		return imagemFichaurl;
	}

	public void setImagemFichaurl(String imagemFichaurl) {
		this.imagemFichaurl = imagemFichaurl;
	}

	public String getNomeImagemFicha() {
		return nomeImagemFicha;
	}

	public void setNomeImagemFicha(String nomeImagemFicha) {
		this.nomeImagemFicha = nomeImagemFicha;
	}


	public FotoCliente getSelectedPhoto() {
		return selectedPhoto;
	}

	public void setSelectedPhoto(FotoCliente selectedPhoto) {
		this.selectedPhoto = selectedPhoto;
	}

	public String getNovaLegendaFoto() {
		return novaLegendaFoto;
	}

	public void setNovaLegendaFoto(String novaLegendaFoto) {
		this.novaLegendaFoto = novaLegendaFoto;
	}


	public Ficha getSelectedFicha() {
		return selectedFicha;
	}

	public void setSelectedFicha(Ficha selectedFicha) {
		this.selectedFicha = selectedFicha;
	}

	public String getNovaLegendaFicha() {
		return novaLegendaFicha;
	}

	public void setNovaLegendaFicha(String novaLegendaFicha) {
		this.novaLegendaFicha = novaLegendaFicha;
	}



	public List<Briefing> getBriefings() {
		return briefings;
	}

	public void setBriefings(List<Briefing> briefings) {
		this.briefings = briefings;
	}

	public Briefing getSelectedBriefing() {
		return selectedBriefing;
	}

	public void setSelectedBriefing(Briefing selectedBriefing) {
		this.selectedBriefing = selectedBriefing;
	}

	public Briefing getNewBriefing() {
		return newBriefing;
	}

	public void setNewBriefing(Briefing newBriefing) {
		this.newBriefing = newBriefing;
	}

	public List<Feira> getFeirasCliente() {
		return feirasCliente;
	}

	public void setFeirasCliente(List<Feira> feirasCliente) {
		this.feirasCliente = feirasCliente;
	}

	public List<Feira> getFeirasTotais() {
		return feirasTotais;
	}

	public void setFeirasTotais(List<Feira> feirasTotais) {
		this.feirasTotais = feirasTotais;
	}

	public List<Feira> getSelectedFeiras() {
		return selectedFeiras;
	}

	public void setSelectedFeiras(List<Feira> selectedFeiras) {
		this.selectedFeiras = selectedFeiras;
	}

	public FeiraBO getFeiraBo() {
		return feiraBo;
	}

	public void setFeiraBo(FeiraBO feiraBo) {
		this.feiraBo = feiraBo;
	}

	public Feira getSelectedFeira() {
		return selectedFeira;
	}

	public void setSelectedFeira(Feira selectedFeira) {
		this.selectedFeira = selectedFeira;
	}
	
	
	
	
}
