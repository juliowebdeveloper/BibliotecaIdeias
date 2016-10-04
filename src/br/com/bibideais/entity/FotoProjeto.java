package br.com.bibideais.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FotoProjeto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idFotoProjeto;
	
	
	private String urlImagem;
	
	@ManyToOne
	@JoinColumn(name = "id_versao")
	private VersaoProjeto versaoprojeto;
	
	
	


	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public VersaoProjeto getVersaoprojeto() {
		return versaoprojeto;
	}

	public void setVersaoprojeto(VersaoProjeto versaoprojeto) {
		this.versaoprojeto = versaoprojeto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getIdFotoProjeto() {
		return idFotoProjeto;
	}

	public void setIdFotoProjeto(long idFotoProjeto) {
		this.idFotoProjeto = idFotoProjeto;
	}
	
	
	
	
}
