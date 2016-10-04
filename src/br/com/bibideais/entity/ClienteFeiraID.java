package br.com.bibideais.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ClienteFeiraID implements Serializable{


	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn (name="id_cliente")
	private Cliente idCliente;
	
	@ManyToOne
	@JoinColumn (name="id_feira")
	private Feira idFeira;

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public Feira getIdFeira() {
		return idFeira;
	}

	public void setIdFeira(Feira idFeira) {
		this.idFeira = idFeira;
	}
	
	
	
	
}
