package br.com.bibideais.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bibideais.dao.FotoProjetoDAO;
import br.com.bibideais.dao.ProjetoDAO;
import br.com.bibideais.dao.VersaoProjetoDAO;
import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;

public class ProjetoBO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private ProjetoDAO pdao;
	
	private VersaoProjetoDAO vdao;
	
	private FotoProjetoDAO fdao;
	
	/*******************************Projetos*************************************************/
	
	//Cria projeto Completo
	public Projeto criarProjeto(Projeto p){
		try{
			pdao = new ProjetoDAO();
			pdao.inserir(p);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}

	


	public Projeto alterarProjeto(Projeto p) {
		pdao = new ProjetoDAO();
		return pdao.alterarProjeto(p);
		
	}
	
	public Projeto updateProjeto(Projeto p) {
		pdao = new ProjetoDAO();
		return pdao.updateProjeto(p);
		
	}
	
	
	//Buscas de Projeto
	public Projeto buscarPeloIdLazy(Projeto p){
		pdao = new ProjetoDAO();
		return pdao.findByLazy(p.getIdProjeto());
	}
	
	
	
	public List<Projeto> buscarPorMetragem(String metragem){
		pdao = new ProjetoDAO();
		return pdao.buscarPorMetragem(metragem);
	}
	
	
	
	public void excluirProjeto(Projeto p){
		pdao = new ProjetoDAO();
		pdao.excluirLong(p.getIdProjeto());
	}
	

	public List<Projeto> buscarPorAno(String ano) {
		pdao = new ProjetoDAO();
		return pdao.buscarPorAno(ano);
	}

	
	/*******************************Versões de Projeto*************************************************/
	
	public VersaoProjeto criarVersao(VersaoProjeto v){
		vdao = new VersaoProjetoDAO();
		return vdao.inserir(v);
		
	}
	
	
	//Buscas de Versões
	public List<VersaoProjeto> buscarPeloProjeto(Projeto p){
		vdao = new VersaoProjetoDAO();
		return vdao.buscarPeloProjeto(p);
	}
	
	
	public void excluirVersaoProjeto(VersaoProjeto v){
		vdao = new VersaoProjetoDAO();
		vdao.excluirLong(v.getIdVersaoProjeto());
	}
	
	
	public VersaoProjeto buscarVersapPeloIdLazy(VersaoProjeto v){
		vdao= new VersaoProjetoDAO();
		return vdao.findByLazy(v.getIdVersaoProjeto());
	}

	
	
	
	
	/*******************************Fotos Projeto*************************************************/

	
	public List<FotoProjeto> inserirFotosProjeto(List<FotoProjeto> fotos){
		fdao = new FotoProjetoDAO();
		List<FotoProjeto> inseridas = new ArrayList<FotoProjeto>();
		for (FotoProjeto fotoProjeto : inseridas) {
			inseridas.add(fdao.inserir(fotoProjeto));
			
		}
		
		return inseridas;
	}
	
	
	public FotoProjeto inserirFotoProjeto(FotoProjeto foto){
		fdao = new FotoProjetoDAO();
		return fdao.inserir(foto);
	}
	
	
	
	//Buscas das fotos das versões
	public List<FotoProjeto> buscarFotoVersao(VersaoProjeto ver){
		fdao = new FotoProjetoDAO();
		return fdao.buscarFotosPorVersao(ver);
	}
	
	
	public void excluirFotoProjeto(FotoProjeto foto){
		fdao = new FotoProjetoDAO();
		fdao.excluirLong(foto.getIdFotoProjeto());
	}
	
	
	
	

	public ProjetoDAO getPdao() {
		return pdao;
	}



	public void setPdao(ProjetoDAO pdao) {
		this.pdao = pdao;
	}


	public VersaoProjetoDAO getVdao() {
		return vdao;
	}


	public void setVdao(VersaoProjetoDAO vdao) {
		this.vdao = vdao;
	}


	public FotoProjetoDAO getFdao() {
		return fdao;
	}


	public void setFdao(FotoProjetoDAO fdao) {
		this.fdao = fdao;
	}




	public VersaoProjeto buscarVersaoPeloNomeEProjeto(Projeto projeto, String nome) {
		vdao = new VersaoProjetoDAO();
		
		return vdao.buscarPeloNomeEProjeto(projeto, nome);
	}


	public  void atualizarNomeVersao(VersaoProjeto ver, String nome){
		vdao = new VersaoProjetoDAO();
		vdao.atualizarNomeVersao(ver, nome);
	}



	
	
	
	
	
	
	
}
