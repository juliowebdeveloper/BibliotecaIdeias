package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



//Classe que representa a relação entre os anos que o cliente recebeu trabalhos da biblioteca ideias 
@Entity
public class Ano implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAno;
	
	@Column(length = 4)
	private int ano;
	
	
	
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "anostrabalhos", targetEntity = Cliente.class)
	private List<Cliente> clientes;




	public long getIdAno() {
		return idAno;
	}




	public void setIdAno(long idAno) {
		this.idAno = idAno;
	}







	public List<Cliente> getClientes() {
		return clientes;
	}




	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public int getAno() {
		return ano;
	}




	public void setAno(int ano) {
		this.ano = ano;
	}
	
	
	
}
