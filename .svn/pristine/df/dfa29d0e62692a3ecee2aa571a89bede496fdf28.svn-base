package br.com.bibideais.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import br.com.bibideais.converter.FuncConverter;
import br.com.bibideais.entity.Authority;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.AuthorityBO;
import br.com.bibideais.service.FuncionarioBO;

/**
 * 
 * @author Shido
 * Trata a visualização dos funcionarios, assim como edição, alterar permissões, e o cadastro.
 */
@ManagedBean(name ="funcManagerBean")
@ViewScoped
public class FuncManagerBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Lista com todos os funcionários
	private List<Funcionario> funcionarios;
	
	private List<Funcionario> ultimosFuncs;
	
	
	private Funcionario newfuncionario;
	
	private Funcionario funcSelecionado;
	
	private FuncionarioBO bo;
	
	private List<Authority> auths;
	
	private String permissao;
	
	private boolean access;
	
	private String password1;
	
	private String password2;
	
	
	@PostConstruct
	public void init(){
	
		
		HttpSession session = FacesUtil.getSession();
		FuncionarioBean funcBean = (FuncionarioBean) session.getAttribute("funcBean");

		
		if(!funcBean.isAdmin()){
			access = false;
			MessageUtil mu = new MessageUtil();
			mu.sendFatalMessageToUser("Acesso Negado", "Você não tem permissões para acessar as informações dessa página");
		}else{
			access = true;
			auths = new AuthorityBO().findAll();
			AuthorityBO adao = new AuthorityBO();
			bo = new FuncionarioBO();
			funcionarios = new ArrayList<Funcionario>();
			funcionarios = bo.buscarTodosFuncionarios();
			newfuncionario = new Funcionario();
			//ultimosFuncs = bo.buscarUltimosCadastrados();
			
			//for (Funcionario f:)
			
			for (Funcionario f : funcionarios) {
				f.setAuthorities(adao.buscarPorFuncionario(f));
			}

		}
		
		

	}
	




	public void ativarDesativarFunc(){
		bo = new FuncionarioBO();
		MessageUtil mu = new MessageUtil();

		try {
			
			if(funcSelecionado.isEnable() == false){
				bo.ativarFuncionario(funcSelecionado);
				funcSelecionado.setEnable(true);
				mu.sendInfoMessageToUser("Usuário Ativado", "");
			}else{
				bo.desativarFuncionario(funcSelecionado);
				funcSelecionado.setEnable(false);
				mu.sendInfoMessageToUser("Usuário Desativado", "");
			}
			
		} catch (Exception e) {
			mu.sendErrorMessageToUser("Ocorreu um erro", "O usuário não pode ser ativado");
			e.printStackTrace();
		}
	}
	
	
	
	public void resetarSenha(){
		bo = new FuncionarioBO();
		MessageUtil mu = new MessageUtil();
		try{
			funcSelecionado.setPassword("123");

			bo.alterarSenha(funcSelecionado);
			mu.sendInfoMessageToUser("Senha do funcionário "+ funcSelecionado.getNomeCompleto() + " foi alterada", "A senha foi alterada para 123, por favor informe o funcionário dessa alteração");

		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "A senha desse funcionário não pode ser alterada");

		}
	}
	
	

	public void alterarPermissao(){
		MessageUtil mu = new MessageUtil();
		try{
			bo.alterarPermissoes(funcSelecionado, funcSelecionado.getAuthorities().get(0).getName());
			funcSelecionado.setAuthorities(new FuncionarioBO().buscarPorCodigoLazy(funcSelecionado.getId()).getAuthorities());
			mu.sendInfoMessageToUser("Permissões Alteradas", null);

			
		} catch (Exception e) {
			mu.sendErrorMessageToUser("Ocorreu um erro", "Não foi possivel alterar as permissões desse usuário");
			e.printStackTrace();
		}
		
		
	}

	
	
	public void cadastrarFuncionario(){
		
		MessageUtil mu = new MessageUtil();
		try{
			newfuncionario.setEnable(true);
			//newfuncionario.setPassword(CriptografiaUtil.MD5(password1));
			newfuncionario.setPassword(password1);
		
			Funcionario banco = bo.cadastrarFuncionario(newfuncionario, permissao);
			
			try{
				mu.sendInfoMessageToUser("Funcionário Cadastrado", null);
				funcionarios = bo.buscarTodosFuncionarios();
				newfuncionario = new Funcionario();
				
			}catch(NullPointerException e1){
				newfuncionario = new Funcionario();
				mu.sendErrorMessageToUser("Username e/ou e-mail em uso", "Por favor escolha um novo nome de usuário ou altere o e-mail");

			}
		
			FuncConverter.funcionarios.add(banco);
		}catch(Exception e){
			e.printStackTrace();
			mu.sendErrorMessageToUser("Ocorreu um erro", "Não foi possivel cadastrar esse funcionário");

		}

	}



	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}





	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}





	public Funcionario getNewfuncionario() {
		return newfuncionario;
	}





	public void setNewfuncionario(Funcionario newfuncionario) {
		this.newfuncionario = newfuncionario;
	}





	public Funcionario getFuncSelecionado() {
		return funcSelecionado;
	}





	public void setFuncSelecionado(Funcionario funcSelecionado) {
		this.funcSelecionado = funcSelecionado;
	}





	public String getPermissao() {
		return permissao;
	}





	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}





	public String getPassword1() {
		return password1;
	}





	public void setPassword1(String password1) {
		this.password1 = password1;
	}





	public String getPassword2() {
		return password2;
	}





	public void setPassword2(String password2) {
		this.password2 = password2;
	}









	public List<Authority> getAuths() {
		return auths;
	}





	public void setAuths(List<Authority> auths) {
		this.auths = auths;
	}





	public void setBo(FuncionarioBO bo) {
		this.bo = bo;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FuncionarioBO getBo() {
		return bo;
	}





	public List<Funcionario> getUltimosFuncs() {
		return ultimosFuncs;
	}





	public void setUltimosFuncs(List<Funcionario> ultimosFuncs) {
		this.ultimosFuncs = ultimosFuncs;
	}
	
	
	
	
	
	
}
