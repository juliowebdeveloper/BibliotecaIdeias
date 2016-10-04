package br.com.bibideais.jsfutil;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.io.FilenameUtils;

public class ImagesUtil {

	/*Caminho fisico para onde será gravada os cartoes do cliente*/
	public static String pathCartoes = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\cartoes\\";
	
	/*Caminho fisico para onde será gravada as fichas do cliente*/
	public static String pathFichas = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\fichas\\";
	
	/*Caminho fisico para onde será gravadaos logos das feiras*/
	public static String pathLogos = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\logos\\";


	/*Caminho fisico para onde será gravados os mapas das feiras*/
	public static String pathMapas = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\mapas\\";

	/*Caminho fisico para onde será gravados os manuais das feiras*/
	public static String pathManuais = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\manuais\\";

	
	
	/*Caminho fisico para onde será gravadaos logos das organizadoras*/
	public static String pathOrgLogo = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\orglogo\\";

	
	
	/*Caminho fisico das imagens das fotos cliente*/
	public static String pathFotos = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\fotocliente\\";
	
	

	/*Caminho fisico das fotos do projeto*/
	public static String pathFotosProjeto = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\fotosprojetos\\";
	
	/*Caminho fisico dos briefings*/
	public static String pathBriefings = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\briefings\\";
	
	/*Caminho fisico das planilhas de custo*/
	public static String pathPlanilhasCusto = "C:\\Development\\Servidores\\apache-tomcat-7.0.68\\webapps\\ROOT\\planilhascusto\\";
	
	
	
	/*Caminho relativo da url fichas*/
	public static String urlFichas = gerarFichaUrl() ;
	
	public static String urlCartoes = gerarCartoesUrl();

	public static String urlLogos = gerarLogosUrl();
	
	public static String urlOrgLogos = gerarOrgLogosUrl();
	
	public static String urlFotoCliente = gerarFotoCliente();
	
	public static String urlMapaFeira = gerarMapaUrl();
	
	public static String urlManuaisFeira = gerarManuaisUrl();
	
	public static String urlFotosProjetos = gerarFotosProjetos();
	
	public static String urlBriefings = gerarBriefings();
	
	public static String urlPlanilhasCusto = gerarPlanilhasCusto();
	
	
	public ImagesUtil(){
		
	}
	
	
	public static String gerarFichaUrl(){
		 String urlFichas = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
	          urlFichas = urlFichas+ InetAddress.getLocalHost().getHostAddress() + ":8085/fichas/";
	          
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return urlFichas;

	}
	
	public static String gerarMapaUrl(){
		 String urlMapas = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
	          urlMapas = urlMapas+ InetAddress.getLocalHost().getHostAddress() + ":8085/mapas/";
	          
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
       return urlMapas;

	}
	
	public static String gerarPlanilhasCusto(){
		 String urlPlanilhas = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
	          urlPlanilhas = urlPlanilhas+ InetAddress.getLocalHost().getHostAddress() + ":8085/planilhascusto/";
	          
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
     return urlPlanilhas;

	}
	
	
	public static String gerarBriefings(){
		 String urlBriefings = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
	          urlBriefings = urlBriefings+ InetAddress.getLocalHost().getHostAddress() + ":8085/briefings/";
	          
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
      return urlBriefings;

	}
	
	public static String gerarFotosProjetos(){
		 String urlFotosProjetos = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
	          urlFotosProjetos = urlFotosProjetos+ InetAddress.getLocalHost().getHostAddress() + ":8085/fotosprojetos/";
	          
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
     return urlFotosProjetos;

	}
	
	
	
	public static String gerarManuaisUrl(){
		 String urlManuais = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
	          urlManuais = urlManuais+ InetAddress.getLocalHost().getHostAddress() + ":8085/manuais/";
	          
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
      return urlManuais;

	}
	
	
	public static String gerarCartoesUrl(){
		  String urlCartoes = "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
			    urlCartoes = urlCartoes+ InetAddress.getLocalHost().getHostAddress() + ":8085/cartoes/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return urlCartoes;

	}
	
	
	public static String gerarLogosUrl(){
		  String urlLogos= "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
			    urlLogos = urlLogos+ InetAddress.getLocalHost().getHostAddress() + ":8085/logos/";
			    
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return urlLogos;

	}
	
	public static String gerarOrgLogosUrl(){
		  String urlOrgLogos= "http://";

		try {
			InetAddress.getLocalHost().getHostAddress();
			
			    urlOrgLogos = urlOrgLogos+ InetAddress.getLocalHost().getHostAddress() + ":8085/orglogo/";
			    
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  return urlOrgLogos;

	}
	
	
	public static String gerarFotoCliente(){
		String urlFotoCliente = "http://";
		try {
			InetAddress.getLocalHost().getHostAddress();
			
			 
			    urlFotoCliente = urlFotoCliente+ InetAddress.getLocalHost().getHostAddress() + ":8085/fotocliente/";
				
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return urlFotoCliente;

	}
    
   
	
	
	
	
	public String gerarIdentificadorImagem(String fileName){
		String[] letras =  {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","W","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","w","y","z"};
		int aleatorio; 
		int maxnumer = (int) ((int) 10 + (Math.random() * 30));
		//para gerar uma quantidade letras 
		String words = "B";
		for(int i = 1; i<=maxnumer; i++){ 
		aleatorio = (int) (Math.random() * (letras.length -1) ); //essa função gera 
		//os numeros aleatorios
		System.out.print(letras[aleatorio]); 
		words = words + letras[aleatorio];
		
		} 
		
		String fileName2 = null;
		
		try{
			System.out.println("Last index of"+ fileName.lastIndexOf("."));
			fileName2 = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), words);
		}catch(StringIndexOutOfBoundsException e){
			fileName2 = null;
		}
		
		return fileName2;
	}
	
	public String gerarIdentificadorPlanilhaCustos(String fileName, String codBriefing){
		String[] letras =  {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","W","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","w","y","z"};
		int aleatorio; 
		int maxnumer = (int) ((int) 10 + (Math.random() * 30));
		//para gerar uma quantidade letras 
		String words = null;
		for(int i = 1; i<=maxnumer; i++){ 
		aleatorio = (int) (Math.random() * (letras.length -1) ); //essa função gera 
		//os numeros aleatorios
		ImagesUtil iu = new ImagesUtil();
		fileName = this.replaceCaracteres(fileName);
		 words =  letras[aleatorio];
		
		} 
		String fileName2 = "B_" + codBriefing +"_"+ words +"_" + fileName;
		
		return fileName2;
	}
	
	
	
	public String gerarIdentificadorBriefingFile(String fileName, String codBriefing){
		String[] letras =  {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","W","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","w","y","z"};
		int aleatorio; 
		int maxnumer = (int) ((int) 10 + (Math.random() * 30));
		//para gerar uma quantidade letras 
		String words = null;
		
		
		fileName = this.replaceCaracteres(fileName);
		
		
		for(int i = 1; i<=maxnumer; i++){ 
		aleatorio = (int) (Math.random() * (letras.length -1) ); //essa função gera 
		//os numeros aleatorios
		
		 words =  letras[aleatorio];
		
		} 
		String fileName2 = "B_"+ words + codBriefing +"_"  + fileName;
		
		return fileName2;
	}
	
	//A partir da url que foi gerada para o briefing usando o metodo gerarIdentificadorBriefinFile
	//É retornado só o nome original do arquivo para que possa ser gravado
	public String retornarBriefingFileName(String urlBriefing){
		String filename = urlBriefing.substring(urlBriefing.lastIndexOf('/')+1);
		return filename;
	}

	public String retornarTipoFile(String fileName){
		return FilenameUtils.getExtension(fileName);
	}
	
	
	
	
	
	
	
	
	
	public String replaceCaracteres(String fileName){
		fileName = this.replaceAll(fileName, 'ã', 'a');
		fileName = this.replaceAll(fileName, 'õ', 'a');
		fileName = this.replaceAll(fileName, ' ', '_');
		fileName = this.replaceAll(fileName, 'á', 'a');
		fileName = this.replaceAll(fileName, 'é', 'e');
		fileName = this.replaceAll(fileName, 'í', 'i');
		fileName = this.replaceAll(fileName, 'ó', 'o');
		fileName = this.replaceAll(fileName, 'ú', 'u');
		fileName = this.replaceAll(fileName, 'ç', 'c');
		
		return fileName;
	}
	
	
	
	

	public String replaceAll(String s, char old, char newchar) {
		while (s.indexOf(old) != -1) {
	 		s = s.replace(old, newchar);
		}
		return s;
	}
	
	
}
