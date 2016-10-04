package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Briefing implements Serializable, Comparable<Briefing>{

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBriefing;
	
	@Column(nullable = false)
	private String codigo;
	
	@Column
	private String status;
	
	private String anotacoes;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	private String urlPlanilhaCustos;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="briefing")
	private List<BriefingFiles> files;
	
	

	public int getIdBriefing() {
		return idBriefing;
	}

	public void setIdBriefing(int idBriefing) {
		this.idBriefing = idBriefing;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}




	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int compareTo(Briefing o) {
		return getCodigo().compareTo(o.codigo);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrlPlanilhaCustos() {
		return urlPlanilhaCustos;
	}

	public void setUrlPlanilhaCustos(String urlPlanilhaCustos) {
		this.urlPlanilhaCustos = urlPlanilhaCustos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<BriefingFiles> getFiles() {
		return files;
	}

	public void setFiles(List<BriefingFiles> files) {
		this.files = files;
	}
	
	
	
	
	
}
