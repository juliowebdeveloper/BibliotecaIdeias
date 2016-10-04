package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class VersaoProjeto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idVersaoProjeto;
	
	private String versao;
	

	@ManyToOne
	@JoinColumn(name ="id_projeto")
	private Projeto projeto;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "versaoprojeto")
	private List<FotoProjeto> fotosProjeto;
	
	
	
	public long getIdVersaoProjeto() {
		return idVersaoProjeto;
	}

	public void setIdVersaoProjeto(long idVersaoProjeto) {
		this.idVersaoProjeto = idVersaoProjeto;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<FotoProjeto> getFotosProjeto() {
		return fotosProjeto;
	}

	public void setFotosProjeto(List<FotoProjeto> fotosProjeto) {
		this.fotosProjeto = fotosProjeto;
	}
	
	
	
	
	

}
