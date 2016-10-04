package br.com.bibideais.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.bibideais.dao.ProjetoDAO;
import br.com.bibideais.dao.VersaoProjetoDAO;
import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.ProjetoBO;

public class ProjetoTeste {

	
	public static void main(String[] args) {
		ProjetoTeste t = new ProjetoTeste();
		//t.criarProjeto();
		//t.buscarVersoesProjeto();
		//t.buscarFotosVersao();
		//t.buscarPorMetragem();
		t.buscarPorAno();
	}

	
	
	public void criarProjeto(){
		Cliente c = new ClienteBO().buscarClienteCompleto(69);
		System.out.println(c.getRazaoSocial());
		List<Projeto> projetos = new ArrayList<Projeto>();
		
		//Criando vários projetos para o cliente
		Projeto p1 = new Projeto();
		
		//Varias versoes do mesmo projeto
		VersaoProjeto v1 = new VersaoProjeto();
		v1.setVersao("Versão 1 do projeto");
		
		//Varias fotos da mesma versao
		FotoProjeto f1 = new FotoProjeto();
		f1.setUrlImagem("1xxxxxxxxxxxxxxxxxxxxxxxxxx");
		f1.setVersaoprojeto(v1);
			FotoProjeto f2 = new FotoProjeto();
			f2.setUrlImagem("2xxxxxxxxxxxxxxxxxxxxxxxxxx");
			f2.setVersaoprojeto(v1);
				FotoProjeto f3 = new FotoProjeto();
				f3.setUrlImagem("3xxxxxxxxxxxxxxxxxxxxxxxxxx");
				f3.setVersaoprojeto(v1);		
				
				//Adicionando as fotos para uma lista 
				List<FotoProjeto> listaFotosProjetos1 = new ArrayList<FotoProjeto>();
				listaFotosProjetos1.add(f1);
				listaFotosProjetos1.add(f2);
				listaFotosProjetos1.add(f3);
				
				//Adiciona a lista à Versão
				v1.setFotosProjeto(listaFotosProjetos1);
				v1.setProjeto(p1);
				
		VersaoProjeto v2 = new VersaoProjeto();
		v2.setVersao("Versão 2 do projeto");
		
		FotoProjeto f4= new FotoProjeto();
		f4.setUrlImagem("4xxxxxxxxxxxxxxxxxxxxxxxxxx");
		f4.setVersaoprojeto(v2);
			FotoProjeto f5 = new FotoProjeto();
			f5.setUrlImagem("5xxxxxxxxxxxxxxxxxxxxxxxxxx");
			f5.setVersaoprojeto(v2);
				FotoProjeto f6 = new FotoProjeto();
				f6.setUrlImagem("6xxxxxxxxxxxxxxxxxxxxxxxxxx");
				f6.setVersaoprojeto(v2);
				
				//Adicionando as fotos para uma lista 
				List<FotoProjeto> listaFotosProjetos2 = new ArrayList<FotoProjeto>();
				listaFotosProjetos2.add(f4);
				listaFotosProjetos2.add(f5);
				listaFotosProjetos2.add(f6);
				//Adiciona a lista à Versão
				v2.setFotosProjeto(listaFotosProjetos2);
				v2.setProjeto(p1);
				
					
		VersaoProjeto v3 = new VersaoProjeto();
		v3.setVersao("Versão 3 do projeto");
		
		FotoProjeto f7 = new FotoProjeto();
		f7.setUrlImagem("7xxxxxxxxxxxxxxxxxxxxxxxxxx");
		f7.setVersaoprojeto(v3);
			FotoProjeto f8 = new FotoProjeto();
			f8.setUrlImagem("8xxxxxxxxxxxxxxxxxxxxxxxxxx");
			f8.setVersaoprojeto(v3);
				FotoProjeto f9 = new FotoProjeto();
				f9.setUrlImagem("9xxxxxxxxxxxxxxxxxxxxxxxxxx");
				f9.setVersaoprojeto(v3);
				
				//Adicionando as fotos para uma lista 
				List<FotoProjeto> listaFotosProjetos3 = new ArrayList<FotoProjeto>();
				listaFotosProjetos3.add(f7);
				listaFotosProjetos3.add(f8);
				listaFotosProjetos3.add(f9);
				//Adiciona a lista à Versão
				v3.setFotosProjeto(listaFotosProjetos3);
					v3.setProjeto(p1);
				
		Projeto p2 = new Projeto();
		VersaoProjeto v5 = new VersaoProjeto();
		VersaoProjeto v6 = new VersaoProjeto();
		VersaoProjeto v7 = new VersaoProjeto();
		VersaoProjeto v8 = new VersaoProjeto();

		
		p1.setArquiteto("Arquiteto 1");
		p1.setCliente(c);
		p1.setMetragem("Até 20m");
		p1.setAno("2006");
		p1.setSegmento("Cenario");
		
		//Lista de versões
		List<VersaoProjeto> versoes = new ArrayList<VersaoProjeto>();
		versoes.add(v1);
		versoes.add(v2);
		versoes.add(v3);
		
		p1.setVersoes(versoes);
		
		
		ProjetoBO bo = new ProjetoBO();
		bo.criarProjeto(p1);
		
		
		//c.setProjetos(projetos);
	}
	
	public void buscarVersoesProjeto(){
		ProjetoDAO dao = new ProjetoDAO();
		Projeto p = dao.findByLazy(1);
		VersaoProjetoDAO vdao = new VersaoProjetoDAO();
		List<VersaoProjeto> list = vdao.buscarPeloProjeto(p);
		for (VersaoProjeto versaoProjeto : list) {
			System.out.println(versaoProjeto.getVersao());
		}
	}
	
	public void buscarPorMetragem(){
		ProjetoBO bo = new ProjetoBO();
		List<Projeto>ps = bo.buscarPorMetragem("Até 100m²");
		for (Projeto projeto : ps) {
			System.out.println(projeto.getAno() + projeto.getMetragem());
		}
	}
	
	
	public void buscarPorAno(){
		ProjetoBO bo = new ProjetoBO();
		List<Projeto>ps = bo.buscarPorAno("2009");
		for (Projeto projeto : ps) {
			System.out.println(projeto.getAno() + projeto.getMetragem());
		}
	}
	
	public void buscarFotosVersao(){
		ProjetoBO bo = new ProjetoBO();
		List<FotoProjeto> fotos = bo.buscarFotoVersao(new VersaoProjetoDAO().findByLazy(1));
		
		for (FotoProjeto fotoProjeto : fotos) {
			System.out.println(fotoProjeto.getUrlImagem());
		}
	}
	
	
	
}



