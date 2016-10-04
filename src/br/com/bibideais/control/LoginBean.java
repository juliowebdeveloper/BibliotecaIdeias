package br.com.bibideais.control;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.AcaoUtil;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.FuncionarioBO;
/**
 * 
 * @author Shido
 * Trata o login do funcionário no sistema
 *
 */
@ManagedBean(name ="loginBean")
@SessionScoped
public class LoginBean {

	
	
	private Funcionario func;
	private FuncionarioBO bo;
	

	
	public LoginBean(){

	}
	

	
	
	




	@PostConstruct
	public void init(){
		func = new Funcionario();
		
	}
	
	
	
	public String logar(){
        //Apagando da session caso tenha feito um antigo login
		HttpSession session = FacesUtil.getSession();
     if(session.getAttribute("funcionario") != null){
    	 session.removeAttribute("funcionario");
    	 session.removeAttribute("funcBean");
    
    	 
     		}
     
 		MessageUtil mu = new MessageUtil();

        bo = new FuncionarioBO();
        Funcionario userbanco = new Funcionario();

        	
        userbanco = bo.verificarSenha(func.getUsername(), func.getPassword());

    
        Funcionario f = new Funcionario();
       

        if(userbanco ==null || userbanco.isEnable() ==false){
        	mu.sendErrorMessageToUser("Login inválido", "Usuário ou Senha inválidos ou o usuário está desativado");
        	

        }else{
        	try{
        		f = bo.buscarFuncionarioUsername(func.getUsername());
        		session.setAttribute("funcionario", f);
        		
        	      //Inserindo Log
        		
        		LogBean.inserirLog(AcaoUtil.LOGOU, f, null);
        		
        		return "/site/home.xhtml?faces-redirect=true";
        	}catch(NullPointerException e){
        		mu.sendErrorMessageToUser("Ocorreu um erro ao efetuar o login", null);

        	}
        	
        }

        return null;
	}



	public Funcionario getFunc() {
		return func;
	}



	public void setFunc(Funcionario func) {
		this.func = func;
	}



	public FuncionarioBO getBo() {
		return bo;
	}



	public void setBo(FuncionarioBO bo) {
		this.bo = bo;
	}










	
	
	
	
	
	
	
	
	
	
}
