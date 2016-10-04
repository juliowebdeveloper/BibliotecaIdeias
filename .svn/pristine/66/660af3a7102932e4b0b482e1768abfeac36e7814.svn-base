package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Feira implements Serializable, Comparable<Feira>{

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFeira;
	
	@Column(length = 200, nullable = false)
	private String nomeFeira;
	
	@Column
	private String telefone;
	
	@Column(length = 100)
	private String email;
	
	@Column(length = 12)
	private String edicao;
	
	@Column(length = 4)
	private String ano;
	
	@Column(length = 15)
	private String frequencia;
	
	@Column(length = 30)
	private String periodo;
	
	private String urlLogo;
	
	
	
	private int totalExpositores;
	
	private int expositoresNac;
	
	private int expositoresInt;
	
	private int numeroVisitantes;
	
	private int metroConstruido;
	
	@ManyToOne
	@JoinColumn(name ="id_local")
	private Pavilhao local;
	
	@Column(length = 4000 )
	private String anotacoes;
	
	@Column(length = 100)
	private String site;
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean biConstruiu;
	
	@ManyToOne
	@JoinColumn(name ="id_organizadora")
	private Organizadora organizadora;


	private String urlManual;
	
	private String urlMapa;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;

	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	

	public int getIdFeira() {
		return idFeira;
	}




	public void setIdFeira(int idFeira) {
		this.idFeira = idFeira;
	}




	public String getNomeFeira() {
		return nomeFeira;
	}




	public void setNomeFeira(String nomeFeira) {
		this.nomeFeira = nomeFeira;
	}




	public String getTelefone() {
		return telefone;
	}




	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getEdicao() {
		return edicao;
	}




	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}




	public String getAno() {
		return ano;
	}




	public void setAno(String ano) {
		this.ano = ano;
	}




	public String getFrequencia() {
		return frequencia;
	}




	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}




	public String getPeriodo() {
		return periodo;
	}




	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}




	public int getTotalExpositores() {
		return totalExpositores;
	}




	public void setTotalExpositores(int totalExpositores) {
		this.totalExpositores = totalExpositores;
	}




	public int getExpositoresNac() {
		return expositoresNac;
	}




	public void setExpositoresNac(int expositoresNac) {
		this.expositoresNac = expositoresNac;
	}




	public int getExpositoresInt() {
		return expositoresInt;
	}




	public void setExpositoresInt(int expositoresInt) {
		this.expositoresInt = expositoresInt;
	}




	public int getNumeroVisitantes() {
		return numeroVisitantes;
	}




	public void setNumeroVisitantes(int numeroVisitantes) {
		this.numeroVisitantes = numeroVisitantes;
	}




	public int getMetroConstruido() {
		return metroConstruido;
	}




	public void setMetroConstruido(int metroConstruido) {
		this.metroConstruido = metroConstruido;
	}




	public Pavilhao getLocal() {
		return local;
	}




	public void setLocal(Pavilhao local) {
		this.local = local;
	}




	public String getAnotacoes() {
		return anotacoes;
	}




	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}




	public String getSite() {
		return site;
	}




	public void setSite(String site) {
		this.site = site;
	}




	public Organizadora getOrganizadora() {
		return organizadora;
	}




	public void setOrganizadora(Organizadora organizadora) {
		this.organizadora = organizadora;
	}




	public boolean isBiConstruiu() {
		return biConstruiu;
	}




	public void setBiConstruiu(boolean biConstruiu) {
		this.biConstruiu = biConstruiu;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getUrlLogo() {
		return urlLogo;
	}




	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}






public Feira(){}

	public Feira(String nomeFeira, String telefone, String email,
			String edicao, String ano, String frequencia, String periodo,
			String urlLogo, int totalExpositores, int expositoresNac,
			int expositoresInt, int numeroVisitantes, int metroConstruido,
			Pavilhao local, String anotacoes, String site, boolean biConstruiu,
			Organizadora organizadora, Cidade cidade) {
		super();
		this.nomeFeira = nomeFeira;
		this.telefone = telefone;
		this.email = email;
		this.edicao = edicao;
		this.ano = ano;
		this.frequencia = frequencia;
		this.periodo = periodo;
		this.urlLogo = urlLogo;
		this.totalExpositores = totalExpositores;
		this.expositoresNac = expositoresNac;
		this.expositoresInt = expositoresInt;
		this.numeroVisitantes = numeroVisitantes;
		this.metroConstruido = metroConstruido;
		this.local = local;
		this.anotacoes = anotacoes;
		this.site = site;
		this.biConstruiu = biConstruiu;
		this.organizadora = organizadora;
		this.cidade = cidade;
		
	}



	
	
	

	public Cidade getCidade() {
		return cidade;
	}




	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}




	@Override
	public int compareTo(Feira o) {
		return getNomeFeira().toLowerCase().compareTo(o.getNomeFeira().toLowerCase());
	}




	public String getUrlManual() {
		return urlManual;
	}




	public void setUrlManual(String urlManual) {
		this.urlManual = urlManual;
	}




	public String getUrlMapa() {
		return urlMapa;
	}




	public void setUrlMapa(String urlMapa) {
		this.urlMapa = urlMapa;
	}




	public Calendar getDataCadastro() {
		return dataCadastro;
	}




	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	

	
	
	
}
