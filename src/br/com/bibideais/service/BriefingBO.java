package br.com.bibideais.service;

import java.io.Serializable;
import java.util.List;

import br.com.bibideais.dao.BriefingDAO;
import br.com.bibideais.dao.BriefingFilesDAO;
import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;

public class BriefingBO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private BriefingDAO bdao;
	
	private BriefingFilesDAO bfdao;
	
	public Briefing cadastrarBriefing(Briefing b){
		bdao = new BriefingDAO();
		return bdao.inserir(b);
	}
	
	public Briefing buscarPorId(int id){
		bdao = new BriefingDAO();
		return bdao.localizar(id);
	}

	public Briefing atualizarBriefing(Briefing b){
		bdao = new BriefingDAO();
		return bdao.updateBriefing(b);
	}
	
	public void excluirBriefing(Briefing b){
		bdao = new BriefingDAO();
		bdao.excluir(b.getIdBriefing());
	}
	
	public void alterarPlanilha(Briefing b){
		bdao = new BriefingDAO();
		bdao.alterarPlanilhaCustos(b);
	}
	
	
	
	
	//Metodos dos Briefing Files
	
	
	public BriefingFiles adicionarFile(BriefingFiles b){
		bfdao = new BriefingFilesDAO();
		return bfdao.inserir(b);
	}
	
	public List<BriefingFiles> buscarFilesPorBriefing(Briefing b){
		bfdao = new BriefingFilesDAO();
		return bfdao.buscarFilesPorBriefing(b);
	}

	public BriefingFiles updateFile(BriefingFiles b){
		bfdao = new BriefingFilesDAO();
		return bfdao.updateFile(b);
	}
	
	
	public void excluirBriFile(BriefingFiles b){
		bfdao = new BriefingFilesDAO();
		bfdao.excluirLong(b.getIdFile());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public BriefingFilesDAO getBfdao() {
		return bfdao;
	}

	public void setBfdao(BriefingFilesDAO bfdao) {
		this.bfdao = bfdao;
	}
	

	
	
	public BriefingDAO getBdao() {
		return bdao;
	}

	public void setBdao(BriefingDAO bdao) {
		this.bdao = bdao;
	}
	
}
