package br.com.bibideais.teste;

import java.net.InetAddress;
import java.util.List;

import br.com.bibideais.dao.BriefingDAO;
import br.com.bibideais.dao.BriefingFilesDAO;
import br.com.bibideais.dao.ClienteDAO;
import br.com.bibideais.dao.FeiraDAO;
import br.com.bibideais.dao.FichaDAO;
import br.com.bibideais.dao.FotoClienteDAO;
import br.com.bibideais.dao.FotoProjetoDAO;
import br.com.bibideais.dao.OrganizadoraDAO;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.entity.Ficha;
import br.com.bibideais.entity.FotoCliente;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Organizadora;

public class LimparImagensBanco {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LimparImagensBanco b = new LimparImagensBanco();
		b.limparAvatarCliente();
		b.limparAvatarFeira();
		b.limparManualFeira();
		b.limparMapaFeira();
		b.limparAvatarOrganizadora();
		b.limparFichaCliente();
		b.limparFotoCliente();
		b.limparBriefingFiles();
		b.limparFotosProjetos();
		b.limparPlanilhaCusto();
		
		
	}

	
		public void limparAvatarCliente(){
			ClienteDAO cdao = new ClienteDAO();
			try{
				
			
			List<Cliente> clientes = cdao.localizarAll();
			for (Cliente cliente : clientes) {
				System.out.println(cliente.getUrlCartao());
				if(cliente.getUrlCartao()!= null){
					
				String urlBanco = cliente.getUrlCartao();
				
				String pedacoIp = cliente.getUrlCartao().substring(cliente.getUrlCartao().indexOf("//"),cliente.getUrlCartao().lastIndexOf(':'));
				String pedacoOk = cliente.getUrlCartao().substring(cliente.getUrlCartao().lastIndexOf(':'), cliente.getUrlCartao().length());
				System.out.println("Pedaço ok: " + pedacoOk);
				System.out.println("Pedaço IP " +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println("IP NOVO: " + ipNovo);
					//urlBanco.replace(cliente.getUrlCartao().substring(cliente.getUrlCartao().indexOf("//"),cliente.getUrlCartao().lastIndexOf(':')), "3213123");
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL " + urlFinal);
					System.out.println("__________________________");
					cliente.setUrlCartao(urlFinal);
				cdao.atualizarFotoCartao(cliente);
				}
				
			}
			
			
			}catch(Exception e){
				
			}
		}
	
		
		
		public void limparFotosProjetos(){
			FotoProjetoDAO dao = new FotoProjetoDAO();
			try{
				
				
				List<FotoProjeto> fotos= dao.localizarAll();
				for (FotoProjeto f : fotos) {
					System.out.println(f.getUrlImagem());
					if(f.getUrlImagem()!= null){
						
					String urlBanco = f.getUrlImagem();
					
					String pedacoIp = f.getUrlImagem().substring(f.getUrlImagem().indexOf("//"),f.getUrlImagem().lastIndexOf(':'));
					String pedacoOk = f.getUrlImagem().substring(f.getUrlImagem().lastIndexOf(':'), f.getUrlImagem().length());
					System.out.println("Pedaço ok: Foto projeto " + pedacoOk);
					System.out.println("Pedaço IP fotoprojeto" +pedacoIp);
					String ipNovo = "http://";
					ipNovo = ipNovo +
							InetAddress.getLocalHost().getHostAddress();
					System.out.println("IP NOVO: foto projeto " + ipNovo);
					
						String urlFinal = ipNovo + pedacoOk;
						System.out.println("Nova URL FEIRA " + urlFinal);
						System.out.println("__________________________");
						f.setUrlImagem(urlFinal);

						dao.atualizarUrlFoto(f);
					}
	
					
				}
				
				
				}catch(Exception e){
					
				}
		}
		
		public void limparPlanilhaCusto(){
			BriefingDAO dao = new BriefingDAO();
			try{
				
				
				List<Briefing> bris= dao.localizarAll();
				for (Briefing f : bris) {
					System.out.println(f.getUrlPlanilhaCustos());
					if(f.getUrlPlanilhaCustos()!= null){
						
					String urlBanco = f.getUrlPlanilhaCustos();
					
					String pedacoIp = f.getUrlPlanilhaCustos().substring(f.getUrlPlanilhaCustos().indexOf("//"),f.getUrlPlanilhaCustos().lastIndexOf(':'));
					String pedacoOk = f.getUrlPlanilhaCustos().substring(f.getUrlPlanilhaCustos().lastIndexOf(':'), f.getUrlPlanilhaCustos().length());
					System.out.println("Pedaço ok: Foto projeto " + pedacoOk);
					System.out.println("Pedaço IP fotoprojeto" +pedacoIp);
					String ipNovo = "http://";
					ipNovo = ipNovo +
							InetAddress.getLocalHost().getHostAddress();
					System.out.println("IP NOVO: foto projeto " + ipNovo);
					
						String urlFinal = ipNovo + pedacoOk;
						System.out.println("Nova URL FEIRA " + urlFinal);
						System.out.println("__________________________");
						f.setUrlPlanilhaCustos(urlFinal);

						dao.alterarPlanilhaCustos(f);
					}
	
					
				}
				
				
				}catch(Exception e){
					
				}
		}
		
		
		
		public void limparBriefingFiles(){
			BriefingFilesDAO dao = new BriefingFilesDAO();
			try{
				
				
				List<BriefingFiles> bris= dao.localizarAll();
				for (BriefingFiles f : bris) {
					System.out.println(f.getUrlFile());
					if(f.getUrlFile()!= null){
						
					String urlBanco = f.getUrlFile();
					
					String pedacoIp = f.getUrlFile().substring(f.getUrlFile().indexOf("//"),f.getUrlFile().lastIndexOf(':'));
					String pedacoOk = f.getUrlFile().substring(f.getUrlFile().lastIndexOf(':'), f.getUrlFile().length());
					System.out.println("Pedaço ok: Foto projeto " + pedacoOk);
					System.out.println("Pedaço IP fotoprojeto" +pedacoIp);
					String ipNovo = "http://";
					ipNovo = ipNovo +
							InetAddress.getLocalHost().getHostAddress();
					System.out.println("IP NOVO: foto projeto " + ipNovo);
					
						String urlFinal = ipNovo + pedacoOk;
						System.out.println("Nova URL FEIRA " + urlFinal);
						System.out.println("__________________________");
						f.setUrlFile(urlFinal);

						dao.atualizarUrlBriefing(f);
					}
	
					
				}
				
				
				}catch(Exception e){
					
				}
		}
		
		
		
		
		
		
		
		
		public void limparAvatarFeira(){
			FeiraDAO cdao = new FeiraDAO();
			try{
				
			
			List<Feira> feiras= cdao.localizarAll();
			for (Feira feira : feiras) {
				System.out.println(feira.getUrlLogo());
				if(feira.getUrlLogo()!= null){
					
				String urlBanco = feira.getUrlLogo();
				
				String pedacoIp = feira.getUrlLogo().substring(feira.getUrlLogo().indexOf("//"),feira.getUrlLogo().lastIndexOf(':'));
				String pedacoOk = feira.getUrlLogo().substring(feira.getUrlLogo().lastIndexOf(':'), feira.getUrlLogo().length());
				System.out.println("Pedaço ok: FEIRA " + pedacoOk);
				System.out.println("Pedaço IP FEIRA" +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println("IP NOVO: FEIRA " + ipNovo);
					//urlBanco.replace(cliente.getUrlCartao().substring(cliente.getUrlCartao().indexOf("//"),cliente.getUrlCartao().lastIndexOf(':')), "3213123");
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL FEIRA " + urlFinal);
					System.out.println("__________________________");
					feira.setUrlLogo(urlFinal);

				cdao.atualizarLogo(feira);
				}
				
		
				
				
			}
			
			
			}catch(Exception e){
				
			}
		}
		
		
		public void limparMapaFeira(){
			FeiraDAO cdao = new FeiraDAO();
			try{
				
			
			List<Feira> feiras= cdao.localizarAll();
			for (Feira feira : feiras) {
				System.out.println(feira.getUrlMapa());
				if(feira.getUrlMapa()!= null){
					
				String urlBanco = feira.getUrlMapa();
				
				String pedacoIp = feira.getUrlMapa().substring(feira.getUrlMapa().indexOf("//"),feira.getUrlMapa().lastIndexOf(':'));
				String pedacoOk = feira.getUrlMapa().substring(feira.getUrlMapa().lastIndexOf(':'), feira.getUrlMapa().length());
				System.out.println("Pedaço ok: FEIRA " + pedacoOk);
				System.out.println("Pedaço IP FEIRA" +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println("IP NOVO: FEIRA " + ipNovo);
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL FEIRA " + urlFinal);
					System.out.println("__________________________");
					feira.setUrlMapa(urlFinal);

				cdao.atualizarMapa(feira);
				}

				
			}
			
			
			}catch(Exception e){
				
			}
		}
		
		
		
		public void limparManualFeira(){
			FeiraDAO cdao = new FeiraDAO();
			try{
				
			
			List<Feira> feiras= cdao.localizarAll();
			for (Feira feira : feiras) {
				System.out.println(feira.getUrlManual());
				if(feira.getUrlManual()!= null){
					
				String urlBanco = feira.getUrlManual();
				
				String pedacoIp = feira.getUrlManual().substring(feira.getUrlManual().indexOf("//"),feira.getUrlManual().lastIndexOf(':'));
				String pedacoOk = feira.getUrlManual().substring(feira.getUrlManual().lastIndexOf(':'), feira.getUrlManual().length());
				System.out.println("Pedaço ok: FEIRA " + pedacoOk);
				System.out.println("Pedaço IP manual" +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println(InetAddress.getLocalHost().getHostAddress());
				System.out.println("IP NOVO: FEIRA " + ipNovo);
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL FEIRA " + urlFinal);
					System.out.println("__________________________");
					feira.setUrlManual(urlFinal);

				cdao.atualizarManual(feira);
				}
				
		
				
				
			}
			
			
			}catch(Exception e){
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		public void limparFotoCliente(){
			FotoClienteDAO cdao = new FotoClienteDAO();
			try{
				
			
			List<FotoCliente> fotos = cdao.localizarAll();
			for (FotoCliente foto : fotos) {
				System.out.println(foto.getUrlimagem());
				if(foto.getUrlimagem()!= null){
					
				String urlBanco = foto.getUrlimagem();
				
				String pedacoIp = foto.getUrlimagem().substring(foto.getUrlimagem().indexOf("//"),foto.getUrlimagem().lastIndexOf(':'));
				String pedacoOk = foto.getUrlimagem().substring(foto.getUrlimagem().lastIndexOf(':'), foto.getUrlimagem().length());
				System.out.println("Pedaço ok FOTO: " + pedacoOk);
				System.out.println("Pedaço IP  FOTO" +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println("IP NOVO FOTO: " + ipNovo);
					//urlBanco.replace(cliente.getUrlCartao().substring(cliente.getUrlCartao().indexOf("//"),cliente.getUrlCartao().lastIndexOf(':')), "3213123");
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL FOTO " + urlFinal);
					System.out.println("__________________________");
					foto.setUrlimagem(urlFinal);
					cdao.atualizarFoto(foto);
				}
				
			}
			
			
			}catch(Exception e){
				
			}
		}
	
		
		
		
		
		public void limparFichaCliente(){
			FichaDAO cdao = new FichaDAO();
			try{
				
			
			List<Ficha> fichas = cdao.localizarAll();
			for (Ficha ficha : fichas) {
				System.out.println(ficha.getUrlimagem());
				if(ficha.getUrlimagem()!= null){
					
				String urlBanco = ficha.getUrlimagem();
				
				String pedacoIp = ficha.getUrlimagem().substring(ficha.getUrlimagem().indexOf("//"),ficha.getUrlimagem().lastIndexOf(':'));
				String pedacoOk = ficha.getUrlimagem().substring(ficha.getUrlimagem().lastIndexOf(':'), ficha.getUrlimagem().length());
				System.out.println("Pedaço ok FICHA: " + pedacoOk);
				System.out.println("Pedaço IP FICHA " +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println("IP NOVO: " + ipNovo);
					//urlBanco.replace(cliente.getUrlCartao().substring(cliente.getUrlCartao().indexOf("//"),cliente.getUrlCartao().lastIndexOf(':')), "3213123");
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL FICHA" + urlFinal);
					System.out.println("__________________________");
					ficha.setUrlimagem(urlFinal);
					
			cdao.atualizarUrlFicha(ficha);
				}
				
			}
			
			
			}catch(Exception e){
				
			}
		}
	
		
		
		
		public void limparAvatarOrganizadora(){
			OrganizadoraDAO cdao = new OrganizadoraDAO();
			try{
				
			
			List<Organizadora> organizadoras = cdao.localizarAll();
			for (Organizadora organizadora : organizadoras) {
				System.out.println(organizadora.getLogourl());
				if(organizadora.getLogourl()!= null){
					
				String urlBanco = organizadora.getLogourl();
				
				String pedacoIp = organizadora.getLogourl().substring(organizadora.getLogourl().indexOf("//"),organizadora.getLogourl().lastIndexOf(':'));
				String pedacoOk = organizadora.getLogourl().substring(organizadora.getLogourl().lastIndexOf(':'), organizadora.getLogourl().length());
				System.out.println("Pedaço ok organizadora: " + pedacoOk);
				System.out.println("Pedaço IP organizadora " +pedacoIp);
				String ipNovo = "http://";
				ipNovo = ipNovo +
						InetAddress.getLocalHost().getHostAddress();
				System.out.println("IP NOVO: " + ipNovo);
					//urlBanco.replace(cliente.getUrlCartao().substring(cliente.getUrlCartao().indexOf("//"),cliente.getUrlCartao().lastIndexOf(':')), "3213123");
				
					String urlFinal = ipNovo + pedacoOk;
					System.out.println("Nova URL Organizadora " + urlFinal);
					System.out.println("__________________________");
					organizadora.setLogourl(urlFinal);
					cdao.alterarLogo(organizadora);
				}
				
			}
			
			
			}catch(Exception e){
				
			}
		}
	
		
		
		
		
		
		
		
	
}
