package br.com.bibideais.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bibideais.entity.FotoProjeto;
import br.com.bibideais.entity.Projeto;
import br.com.bibideais.entity.VersaoProjeto;
import br.com.bibideais.service.ProjetoBO;

@ManagedBean(name ="projSearchBean")
@ViewScoped
public class ProjetoSearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	//Parametros de busca
	private String metragem;
	private String ano;
	
	private List<Projeto> projetosMetragem = new ArrayList<Projeto>();
	
	private List<Projeto> projetosAno = new ArrayList<Projeto>();
	
	private Projeto projetoSelecionado;
	
	private List<VersaoProjeto> versoes = new ArrayList<VersaoProjeto>();
	
	private VersaoProjeto versaoSelecionada;
	
	private List<FotoProjeto> fotosProjetos = new ArrayList<FotoProjeto>();
	
	private ProjetoBO pbo;
	
	
	@PostConstruct
	public void init(){
		
	}
	
	
	
	
	public void getVersions(){
		pbo = new ProjetoBO();
		versoes = pbo.buscarPeloProjeto(projetoSelecionado);
		
		
	}

	
	public void getFotosVersao(){
		pbo = new ProjetoBO();
		fotosProjetos = pbo.buscarFotoVersao(versaoSelecionada);
		
	}
	
	
	
	
	public void buscarPorMetragem(){
		pbo = new ProjetoBO();
		projetosMetragem = pbo.buscarPorMetragem(metragem);
	}
	
	
	public void buscarPorAno(){
		pbo = new ProjetoBO();
		projetosAno = pbo.buscarPorAno(ano);
	}


	public String getMetragem() {
		return metragem;
	}


	public void setMetragem(String metragem) {
		this.metragem = metragem;
	}


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	public List<Projeto> getProjetosMetragem() {
		return projetosMetragem;
	}


	public void setProjetosMetragem(List<Projeto> projetosMetragem) {
		this.projetosMetragem = projetosMetragem;
	}


	public List<Projeto> getProjetosAno() {
		return projetosAno;
	}


	public void setProjetosAno(List<Projeto> projetosAno) {
		this.projetosAno = projetosAno;
	}


	public ProjetoBO getPbo() {
		return pbo;
	}


	public void setPbo(ProjetoBO pbo) {
		this.pbo = pbo;
	}




	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}




	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public List<VersaoProjeto> getVersoes() {
		return versoes;
	}




	public void setVersoes(List<VersaoProjeto> versoes) {
		this.versoes = versoes;
	}




	public VersaoProjeto getVersaoSelecionada() {
		return versaoSelecionada;
	}




	public void setVersaoSelecionada(VersaoProjeto versaoSelecionada) {
		this.versaoSelecionada = versaoSelecionada;
	}




	public List<FotoProjeto> getFotosProjetos() {
		return fotosProjetos;
	}




	public void setFotosProjetos(List<FotoProjeto> fotosProjetos) {
		this.fotosProjetos = fotosProjetos;
	}
	
}
