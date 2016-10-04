package br.com.bibideais.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bibideais.dao.FuncionarioDAO;
import br.com.bibideais.entity.Authority;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.CriptografiaUtil;

public class FuncionarioBO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FuncionarioDAO dao;
	
	
	
	public Funcionario cadastrarFuncionario(Funcionario func, String permissao){
		boolean existeuser = this.verificarFuncionario(func);
		Funcionario f = new Funcionario();
		if(!existeuser){
			System.out.println("*****Não existe funcionario com esse email nem username continua cadastro****");
			dao = new FuncionarioDAO();
			if(permissao.equalsIgnoreCase("ROLE_FUNC")){
				f = dao.inserirFuncAuthFunc(func);

			}else if(permissao.equalsIgnoreCase("ROLE_ADMIN")){
				f = dao.inserirFuncAuthAdmin(func);
			}
		}else{
			System.out.println("Funcionario com esse e-mail encontrado");
			f = null;
		}
	
		return f;
	}
	
	
	
	
	public boolean verificarFuncionario(Funcionario func){
		dao = new FuncionarioDAO();
		Funcionario existe = null;
		
	
		boolean e = false;

			//Existe o Funcionario
			
			existe = dao.verificarFunc(func.getUsername());
			if(existe == null){
			e = false;
			//Se retornar false é porque a Funcionario não existe, se retornar true ele existe.
			System.out.println("Funcionario não existe");
			
			}else{
				e = true;
				System.out.println("Funcionario Existe");
			}
			return e;
	}
	
	

	
	
	
	public Funcionario alterarSenha(Funcionario func){
		dao = new FuncionarioDAO();
		return dao.mudarSenha(func);
	}
	

	
	//Login de pagina feito pelo spring security
	
	
	
	//Login que servirá para buscar se a Funcionario ja existe
/*	public Funcionario verificarSenha(String email, String username, String senha){
		dao = new FuncionarioDAO();
		
		String criptSenha = CriptografiaUtil.MD5(senha);
		System.out.println(CriptografiaUtil.MD5(senha));
		System.out.println("CriptSenha" + criptSenha);
		
		//System.out.println("old senha na bo" + senha);
		Funcionario usernobanco = null;
	
		
		//se a Funcionario existir, verifica se a senha bate com a fornecida
			 usernobanco = dao.verificarFunc(username);
			 
			 if(usernobanco == null){
			 }else{
			 	System.out.println("No banco" + usernobanco.getUsername() +" " + usernobanco.getPassword());
			if(usernobanco.getPassword().equals(criptSenha)){
				//se as senhas forem iguais, ok poderá logar
				System.out.println("retornou true do logar");
				
				
			}else{
				System.out.println("Retornou false do logar");
			}
			 }
		
		return usernobanco;
	
		
	}
	*/
	
	//Login que servirá para buscar se a Funcionario ja existe
		public Funcionario verificarSenha(String username, String senha){
			dao = new FuncionarioDAO();
		
			
			Funcionario usernobanco = new Funcionario();
			
			
			try{
				usernobanco = dao.verificarFunc(username);
			}catch (Exception e){
				e.printStackTrace();
				usernobanco = null;
			}
				 
				//System.out.println(usernobanco.getUsername() + usernobanco.getPassword());
				 	
				 	
		//se a Funcionario existir, verifica se a senha bate com a fornecida
			if(usernobanco != null){
				if(usernobanco.getPassword().equals(senha)){
					//se as senhas forem iguais, ok poderá logar
					System.out.println("Login true");
					
				}else{
					System.out.println("Login False");
					usernobanco = null;
				}
				
			}
			return usernobanco;
		
			
		}

	
		public Funcionario buscarFuncionarioUsername(String username){
			return new FuncionarioDAO().localizarFuncPorFuncionarioname(username);
		}
		

		public List<Funcionario> buscarTodosFuncionarios(){
			List<Funcionario> funcs = new ArrayList<Funcionario>();
			for (Funcionario funcionario : funcs) {
				for (Authority auth: funcionario.getAuthorities()) {
					System.out.println(funcionario.getNomeCompleto() + "Auth dele: " + auth.getName());
				}
			}
			return new FuncionarioDAO().buscarTodosFuncs();
		}
	

		public Authority buscarAuthPorFunc(Funcionario func){
			dao = new FuncionarioDAO();
			Funcionario f = dao.findByLazy(func.getId());
			Authority au = null;
		
			au = f.getAuthorities().get(0);
			return au;
		}
		
		
		public Funcionario buscarPorCodigoLazy(long codigo){
			dao = new FuncionarioDAO();
			Funcionario f = dao.findByLazy(codigo);
			
			return f;
			
			
		}
		
		public Funcionario buscarPorCodigo(long codigo){
			dao = new FuncionarioDAO();
			return dao.localizarlong(codigo);
		}
		
		public Funcionario desativarFuncionario(Funcionario func) {
			dao = new FuncionarioDAO();
			return dao.desativarFunc(func);
		}
		
		public Funcionario ativarFuncionario(Funcionario func) {
			dao = new FuncionarioDAO();
			return dao.ativarFunc(func);
		}
		
		
		public Funcionario alterarPermissoes(Funcionario func, String permissao){
			dao = new FuncionarioDAO();
			if(permissao.equalsIgnoreCase("ROLE_FUNC")){
				return dao.alterarPermissaoParaAdm(func);
			}else{
				return dao.alterarPermissaoParaFunc(func);
			}
		}
		
		
		public List<Funcionario> buscarUltimosCadastrados(){
			dao = new FuncionarioDAO();
			return dao.buscarUltimosFuncs();
		}
		
}
