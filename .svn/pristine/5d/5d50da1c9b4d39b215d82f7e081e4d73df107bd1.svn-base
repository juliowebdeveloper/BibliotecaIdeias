package br.com.bibideais.control;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import br.com.bibideais.converter.FuncConverter;
import br.com.bibideais.entity.Ano;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.entity.LogAcesso;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.FuncionarioBO;
import br.com.bibideais.service.LogBO;

@ManagedBean(name ="logBean")
@ViewScoped
public class LogBean implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private List<LogAcesso> ownLogs;
	
	private List<LogAcesso> logFunc;
	
	private Funcionario funcEscolhido;
	
	private static Funcionario funcLogado;
	
	private boolean access;
	
	//Top cadastradores e que acessaram clientes
	private List<String> topCadastros;
	
	private List<String> topAcessos;
	
	
	private List<Funcionario> funcionarios;
	
	private LogBO lbo;
	
	
	private List<Integer> anos;
	
	private List<String> meses;
	
	
	private String codFunc;
	
	
	private int anoSelect;
	
	private String meselect;
	
	
	//Resultados das buscas por período
	
	private BigInteger logouResultado;
	
	private BigInteger deslogouResultado;

	private BigInteger cadastrouResultado;

	private BigInteger acessouResultado;


	@PostConstruct
	public void init(){
        HttpSession session = FacesUtil.getSession();
        funcLogado = (Funcionario) session.getAttribute("funcionario");

		FuncionarioBean funcBean = (FuncionarioBean) session.getAttribute("funcBean");
		
		
		if(!funcBean.isAdmin()){
			//access = false;
			MessageUtil mu = new MessageUtil();
			mu.sendFatalMessageToUser("Acesso Negado", "Você não tem permissões para acessar as informações dessa página");
			access = false;
		}else{
			access = true;
		}
		
	        
	        funcionarios = FuncConverter.funcionarios;
			lbo = new LogBO();
			ownLogs = lbo.buscarPorFuncionario(funcLogado);
			topAcessos = lbo.buscarTopAcessos();
			topCadastros = lbo.buscarTopCadastradores();
			topAcessos = lbo.buscarTopAcessos();
			
			anos = new ArrayList<Integer>();
			meses = new ArrayList<String>();
			for(int i = 2013; i<2017; i++){
				
				anos.add(i);
			}
			
			meses.add("Janeiro");
			meses.add("Fevereiro");
			meses.add("Março");
			meses.add("Abril");
			meses.add("Maio");
			meses.add("Junho");
			meses.add("Julho");
			meses.add("Agosto");
			meses.add("Setembro");
			meses.add("Outubro");
			meses.add("Novembro");
			meses.add("Dezembro");
			
		
		
	}
	
	public void buscarPorFuncionario(){
		logFunc = lbo.buscarPorFuncionario(funcEscolhido);
		
	}
	

	public void buscarPorPeriodo(){
		// Funcionario func1 = new FuncionarioBO().buscarPorCodigo(Long.parseLong(event.getNewValue().toString()));
	  	  // logFunc = lbo.buscarPorFuncionario(func1);
		//Funcionario func1 = new FuncionarioBO().buscarPorCodigo(Long.parseLong(codFunc));
		//logFunc = lbo.buscarPorFuncionario(func1);
		System.out.println("O funcionario selecionado foi " + codFunc);
		System.out.println("O ano selecionado foi " + anoSelect);
		System.out.println("O mes selecionado foi " + meselect);
		int mesSelecionado = -1;
		
		if(meselect.equalsIgnoreCase("Janeiro")){
			mesSelecionado = 0;
		}else if(meselect.equalsIgnoreCase("Fevereiro")){
			mesSelecionado = 1;
		}else if(meselect.equalsIgnoreCase("Março")){
			mesSelecionado = 2;
		}else if(meselect.equalsIgnoreCase("Abril")){
			mesSelecionado = 3;
		}else if(meselect.equalsIgnoreCase("Maio")){
			mesSelecionado = 4;
		}else if(meselect.equalsIgnoreCase("Junho")){
			mesSelecionado = 5;
		}else if(meselect.equalsIgnoreCase("Julho")){
			mesSelecionado = 6;
		}else if(meselect.equalsIgnoreCase("Agosto")){
			mesSelecionado = 7;
		}else if(meselect.equalsIgnoreCase("Setembro")){
			mesSelecionado = 8;
		}else if(meselect.equalsIgnoreCase("Outubro")){
			mesSelecionado = 9;
		}else if(meselect.equalsIgnoreCase("Novembro")){
			mesSelecionado = 10;
		}else if(meselect.equalsIgnoreCase("Dezembro")){
			mesSelecionado = 11;
		}
		
			
		HashMap<String, BigInteger> logs = lbo.buscarPorFuncionarioYearAndMonth(Long.parseLong(codFunc), anoSelect, mesSelecionado, 0);
		for (Map.Entry<String, BigInteger> entrada : logs.entrySet()) {  
			 if(entrada.getKey().equalsIgnoreCase("Logou")){
						logouResultado = entrada.getValue();
			 }
			 
			 if(entrada.getKey().equalsIgnoreCase("Deslogou")){
					deslogouResultado = entrada.getValue();
		 }
			 
			 if(entrada.getKey().equalsIgnoreCase("Cadastrou Cliente")){
					cadastrouResultado = entrada.getValue();
		 }
			 
			 if(entrada.getKey().equalsIgnoreCase("Acessou Cliente")){
					acessouResultado = entrada.getValue();
		 }
		}
		
	}
	
	
	
	
	  public void stateChangeListener(ValueChangeEvent event) {

	  	  Funcionario func1 = new FuncionarioBO().buscarPorCodigo(Long.parseLong(event.getNewValue().toString()));
	  	   logFunc = lbo.buscarPorFuncionario(func1);
	  	  
	    }

	  
	  
	  
		//Se a ação for "cadastrar algo, deve-se concatenar antes de enviar para esse metodo
		public static LogAcesso inserirLog(String acaoCompleta, Funcionario func, Cliente cliente){
			LogAcesso log = new LogAcesso();
			log.setFuncionario(func);
			log.setData(Calendar.getInstance());
			
			LogAcesso la = new LogAcesso();
			if(cliente == null){
				//Em caso de não ser um cadastro ou acesso à um cliente
				log.setAcao(acaoCompleta);
				System.out.println(log.getFuncionario().getNomeCompleto() + log.getAcao());
				LogBO lbo = new LogBO();
				la = lbo.inserirLog(log);
				
				
			}else{
				log.setAcao(acaoCompleta);
				log.setIdCliente(cliente);
				System.out.println(log.getFuncionario().getNomeCompleto() + log.getAcao());
				System.out.println(log.getData().getTime());
				LogBO lbo = new LogBO();
				la = lbo.inserirLog(log);
			}
			
			
			return la;
		}
	
	

	public List<LogAcesso> getOwnLogs() {
		return ownLogs;
	}

	public void setOwnLogs(List<LogAcesso> ownLogs) {
		this.ownLogs = ownLogs;
	}

	public List<LogAcesso> getLogFunc() {
		return logFunc;
	}

	public void setLogFunc(List<LogAcesso> logFunc) {
		this.logFunc = logFunc;
	}

	public Funcionario getFuncEscolhido() {
		return funcEscolhido;
	}

	public void setFuncEscolhido(Funcionario funcEscolhido) {
		this.funcEscolhido = funcEscolhido;
	}

	public Funcionario getFuncLogado() {
		return funcLogado;
	}

	public void setFuncLogado(Funcionario funcLogado) {
		this.funcLogado = funcLogado;
	}

	public LogBO getLbo() {
		return lbo;
	}

	public void setLbo(LogBO lbo) {
		this.lbo = lbo;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getCodFunc() {
		return codFunc;
	}

	public void setCodFunc(String codFunc) {
		this.codFunc = codFunc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	public List<String> getTopCadastros() {
		return topCadastros;
	}

	public void setTopCadastros(List<String> topCadastros) {
		this.topCadastros = topCadastros;
	}

	public List<String> getTopAcessos() {
		return topAcessos;
	}

	public void setTopAcessos(List<String> topAcessos) {
		this.topAcessos = topAcessos;
	}
	


	public BigInteger getLogouResultado() {
		return logouResultado;
	}

	public void setLogouResultado(BigInteger logouResultado) {
		this.logouResultado = logouResultado;
	}

	public BigInteger getDeslogouResultado() {
		return deslogouResultado;
	}

	public void setDeslogouResultado(BigInteger deslogouResultado) {
		this.deslogouResultado = deslogouResultado;
	}

	public BigInteger getCadastrouResultado() {
		return cadastrouResultado;
	}

	public void setCadastrouResultado(BigInteger cadastrouResultado) {
		this.cadastrouResultado = cadastrouResultado;
	}

	public BigInteger getAcessouResultado() {
		return acessouResultado;
	}

	public void setAcessouResultado(BigInteger acessouResultado) {
		this.acessouResultado = acessouResultado;
	}

	public int getAnoSelect() {
		return anoSelect;
	}

	public void setAnoSelect(int anoSelect) {
		this.anoSelect = anoSelect;
	}

	public String getMeselect() {
		return meselect;
	}

	public void setMeselect(String meselect) {
		this.meselect = meselect;
	}

	public List<Integer> getAnos() {
		return anos;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}

	public List<String> getMeses() {
		return meses;
	}

	public void setMeses(List<String> meses) {
		this.meses = meses;
	}

	
}
