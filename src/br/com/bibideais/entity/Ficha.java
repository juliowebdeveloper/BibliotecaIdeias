package br.com.bibideais.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ficha implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFicha;
	
	@Column
	private String urlimagem;
	
	private String nomeImagem;
	
	
	private String nomePath;
	
	
	
	@ManyToOne
	@JoinColumn(name ="id_cliente")
	private Cliente cliente;
	

	public long getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(long idFicha) {
		this.idFicha = idFicha;
	}

	public String getUrlimagem() {
		return urlimagem;
	}

	public void setUrlimagem(String urlimagem) {
		this.urlimagem = urlimagem;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNomePath() {
		return nomePath;
	}

	public void setNomePath(String nomePath) {
		this.nomePath = nomePath;
	}
	
	
	
	
	
}
