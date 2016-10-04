package br.com.bibideais.control;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.FuncionarioBO;
/**
 * 
 * @author Shido
 * Trata configurações do usuario como por exemplo alterar senha.
 */
@ManagedBean(name ="configBean")
@RequestScoped
public class ConfiguracoesBean {

	//Funcionario da session
	private Funcionario func;
	
	private FuncionarioBO bo;
	
	private String oldSenha;
	
	private String novaSenha;
	
	private String novaSenhaconf;
	
	
	
	
	@PostConstruct
	public void init(){
        HttpSession session = FacesUtil.getSession();
        setFunc((Funcionario) session.getAttribute("funcionario"));
        bo = new FuncionarioBO();
        
	}
	
	
	public String alterarSenha(){
		MessageUtil mu = new MessageUtil();
		if(oldSenha.equals(func.getPassword())){
			func.setPassword(novaSenha);
			
			bo.alterarSenha(func);
			
		
			func = bo.buscarPorCodigoLazy(func.getId());
	        HttpSession session = FacesUtil.getSession();

	        session.removeAttribute("funcionario");
	        session.setAttribute("funcionario", func);
	        
	        mu.sendInfoMessageToUser("Senha alterada", null);
			
		}else{
			mu.sendErrorMessageToUser("Não foi possível alterar sua senha", "Sua senha antiga não corresponde com a digitada");

		}
		
		return null;
		
	}
	
	
	
	
	/*
	
	<!-- 
	<h:form id ="altsenha">
	<p:messages id ="msgs" showDetail="true"/>
	<h2>Altere sua senha</h2>
	<hr/>

	<sub>Preencha sua senha antiga e escolha uma nova.</sub>
	<h:panelGrid columns = "2" styleClass="funcFormCadastro">

	<h:outputLabel value="Senha atual: " for="oldpass"  />
	<p:password value ="#{configBean.oldSenha}" required="true" style="color: black;" id ="oldpass"/>
	<h:outputLabel value="Nova senha: " for= "pwd1"/>
	<p:password id="pwd1" value ="#{configBean.novaSenha}"  match="pwd2" label="Password 1" required="true" style="color: black;" feedback="true" 
	            promptLabel="Digite uma senha" weakLabel="Senha fraca"  
	                        goodLabel="Senha boa" strongLabel="Senha forte" />
	<h:outputLabel value="Confirme a senha: " for ="pwd2" />
	<p:password id="pwd2" value ="#{configBean.novaSenhaconf}" label="Password 2" style="color: black;" required="true"/>

	</h:panelGrid>

	<p:commandButton action="#{configBean.alterarSenha}" value ="Alterar Senha" update="msgs" icon="ui-icon-circle-check"/>


	</h:form>

	      -->   */

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


	public String getOldSenha() {
		return oldSenha;
	}


	public void setOldSenha(String oldSenha) {
		this.oldSenha = oldSenha;
	}


	public String getNovaSenha() {
		return novaSenha;
	}


	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}


	public String getNovaSenhaconf() {
		return novaSenhaconf;
	}


	public void setNovaSenhaconf(String novaSenhaconf) {
		this.novaSenhaconf = novaSenhaconf;
	}
	
	
	
	
	
	
}
