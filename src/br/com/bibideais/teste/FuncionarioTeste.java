package br.com.bibideais.teste;

import java.util.List;

import br.com.bibideais.dao.AuthorityDAO;
import br.com.bibideais.dao.FuncionarioDAO;
import br.com.bibideais.entity.Authority;
import br.com.bibideais.entity.Funcionario;
import br.com.bibideais.jsfutil.CriptografiaUtil;
import br.com.bibideais.service.AuthorityBO;
import br.com.bibideais.service.FuncionarioBO;

public class FuncionarioTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FuncionarioTeste t = new FuncionarioTeste();
		//t.buscarAuthorities();
	//	t.buscarTodosFuncs();
		//t.inserirFuncionarios();
		//t.buscarFuncionario();
		//t.cadastrarFuncionario();
		//t.desativarFunc();
		//t.buscarAuths();
		t.criptografiaTeste();

	}
	
	public void criptografiaTeste(){
		Funcionario f = new FuncionarioBO().buscarFuncionarioUsername("shido");
		System.out.println(f.getPassword());
		System.out.println(CriptografiaUtil.MD5(f.getPassword()));
	}
	
	
	

	public void desativarFunc(){
		Funcionario f = new FuncionarioBO().buscarFuncionarioUsername("func");
		FuncionarioBO bo = new FuncionarioBO();
		bo.desativarFuncionario(f);
	}
	
	public void buscarAuths(){
		AuthorityDAO dao = new AuthorityDAO();
		List<Funcionario> funcs = new FuncionarioDAO().buscarTodosFuncs();
		for (Funcionario funcionario : funcs) {
			funcionario.setAuthorities(dao.findByFunc(funcionario));
		
			for (Authority a : funcionario.getAuthorities()) {
				System.out.println(a.getName() + funcionario.getEmail());
			}
		}
		
		
	}
	
	
	
	
	
	
	
	public void cadastrarFuncionario(){
		FuncionarioBO bo = new FuncionarioBO();
		Funcionario f1 = new Funcionario();
		f1.setEmail("teste@teste.com.br");
		f1.setUsername("teste");
		f1.setNomeCompleto("Teste");
		f1.setPassword("teste");
		f1.setEnable(true);
		//bo.cadastrarFuncionario(f1);
		
	}
	
	public void inserirFuncionarios(){
		Funcionario f1 = new Funcionario();
		Funcionario f2 = new Funcionario();
		Funcionario f3 = new Funcionario();
		Funcionario f4 = new Funcionario();
		Funcionario f5 = new Funcionario();
		
		f1.setUsername("augusto");
		f1.setEmail("augusto@bibliotecaideias.com");
		f1.setEnable(true);
		f1.setPassword("1234");
		f1.setNomeCompleto("Augusto Dos Anjos");
		
		FuncionarioBO bo = new FuncionarioBO();
		//bo.cadastrarFuncionario(f1);
		
		
	}
	
	public void buscarFuncionario(){
		System.out.println(new FuncionarioBO().buscarFuncionarioUsername("shido").getNomeCompleto());
	}
	
	
	public void buscarAuthorities(){
		AuthorityBO bo = new AuthorityBO();
		Authority a = bo.findUserAuthority("Role_Func");
		System.out.println(a.getName());
	}
	

	public void buscarTodosFuncs(){
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funs = dao.localizarAll();
		for (Funcionario funcionario : funs) {
			System.out.println(funcionario.getNomeCompleto());
			funcionario.setAuthorities(dao.findByLazy(funcionario.getId()).getAuthorities());
			for (Authority a  : funcionario.getAuthorities()) {
				System.out.println(a.getName());
			}
		}
		
	}
	
	
	
	
}

