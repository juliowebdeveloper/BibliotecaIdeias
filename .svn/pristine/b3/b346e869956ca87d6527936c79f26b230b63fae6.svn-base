package br.com.bibideais.control;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
import br.com.bibideais.service.FornecedorBO;
import br.com.bibideais.service.FuncionarioBO;
import br.com.bibideais.service.OrganizadoraBO;
import br.com.bibideais.teste.LimparImagensBanco;
/**
 * 
 * @author Shido
 * Trata as permissões do funcionario - usado em todas as páginas.
 */
@ManagedBean(name ="funcBean")
@SessionScoped
public class FuncionarioBean {

	//Funcionario logado
	private Funcionario funcionario;
	
	private FuncionarioBO funcbo;
	
	//Permissão do Usuario
	private String role;
	
	//Boolean que vai controlar acessos caso seja admin ou nao
	private boolean admin;
	
	//Super user - All access
	private boolean superadmin;
	
	private List<Cliente> clientes;
	
	
	private String dateToday;
	
	
	private BigInteger totalOrganizadoras;
	
	private BigInteger totalFeiras;

	private BigInteger totalClientes;

	private BigInteger totalFornecedores;

	
	@PostConstruct
	public void init(){
		
		try{
			HttpSession session = FacesUtil.getSession();

		funcionario = (Funcionario) session.getAttribute("funcionario");
		
		if(funcionario != null){
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateToday = sdf.format(Calendar.getInstance().getTime());
			
			
			funcbo = new FuncionarioBO();
			
			role = funcbo.buscarAuthPorFunc(funcionario).getName();
			

			if(role.equalsIgnoreCase("Role_Func")){
				admin = false;
			}else if(role.equalsIgnoreCase("Role_admin")){
				admin = true;
				OrganizadoraBO orgbo = new OrganizadoraBO();
				ClienteBO cbo = new ClienteBO();
				FornecedorBO fbo = new FornecedorBO();
				FeiraBO febo = new FeiraBO();
				totalOrganizadoras = orgbo.countOrganizadoras();
				totalFeiras = febo.countFeiras();
				totalClientes = cbo.countCliente();
				totalFornecedores = fbo.countFornecedores();
				
			}
			
			
			if(funcionario !=null && funcionario.getUsername().equalsIgnoreCase("shido")){
				superadmin = true;
			
			}else{
				superadmin = false;
			}
			
			clientes = new ClienteBO().buscarPorAtendimento(funcionario);
			
		}
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void cleanImages(){
		System.out.println("Clean Images");
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
		MessageUtil mu = new MessageUtil();
		mu.sendInfoMessageToUser("Imagens Limpas", null);
	}


	
	
	public void atualizarTotais(){
		OrganizadoraBO orgbo = new OrganizadoraBO();
		ClienteBO cbo = new ClienteBO();
		FornecedorBO fbo = new FornecedorBO();
		FeiraBO febo = new FeiraBO();
		totalOrganizadoras = orgbo.countOrganizadoras();
		totalFeiras = febo.countFeiras();
		totalClientes = cbo.countCliente();
		totalFornecedores = fbo.countFornecedores();
	}
	



	
	public String logout() {  
	   
	      //Inserindo Log
		LogBean.inserirLog(AcaoUtil.DESLOGOU,funcionario, null);
		HttpSession session = FacesUtil.getSession();
	    session.invalidate();  
	    return "../index.xhtml?faces-redirect=true";  
	}  
	

	
	
	
	public String logoutAdmin() {  
		  //Inserindo Log
		LogBean.inserirLog(AcaoUtil.DESLOGOU,funcionario, null);
		HttpSession session = FacesUtil.getSession();
		session.invalidate();  
 
	    return "../../index.xhtml?faces-redirect=true";  
	}  
	
	
	
	
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public FuncionarioBO getFuncbo() {
		return funcbo;
	}


	public void setFuncbo(FuncionarioBO funcbo) {
		this.funcbo = funcbo;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}







	public List<Cliente> getClientes() {
		return clientes;
	}







	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}







	public String getDateToday() {
		return dateToday;
	}







	public void setDateToday(String dateToday) {
		this.dateToday = dateToday;
	}






	public BigInteger getTotalOrganizadoras() {
		return totalOrganizadoras;
	}






	public void setTotalOrganizadoras(BigInteger totalOrganizadoras) {
		this.totalOrganizadoras = totalOrganizadoras;
	}






	public BigInteger getTotalFeiras() {
		return totalFeiras;
	}






	public void setTotalFeiras(BigInteger totalFeiras) {
		this.totalFeiras = totalFeiras;
	}






	public BigInteger getTotalClientes() {
		return totalClientes;
	}






	public void setTotalClientes(BigInteger totalClientes) {
		this.totalClientes = totalClientes;
	}






	public BigInteger getTotalFornecedores() {
		return totalFornecedores;
	}






	public void setTotalFornecedores(BigInteger totalFornecedores) {
		this.totalFornecedores = totalFornecedores;
	}

	public boolean isSuperadmin() {
		return superadmin;
	}

	public void setSuperadmin(boolean superadmin) {
		this.superadmin = superadmin;
	}
	
	
	
	
	
	
	
}
