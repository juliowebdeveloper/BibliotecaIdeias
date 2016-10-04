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
public class ContatoCliente implements Serializable{

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContatoCliente;
	
	@ManyToOne
	@JoinColumn(name ="id_cliente")
	private Cliente cliente;
	
	@Column(length = 30)
	private String skype;
	
	@Column
	private String cargo;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String telefone;
	
	@Column
	private String celular;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 50)
	private String redeSocial;

	
	
	public int getIdContatoCliente() {
		return idContatoCliente;
	}

	public void setIdContatoCliente(int idContatoCliente) {
		this.idContatoCliente = idContatoCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
