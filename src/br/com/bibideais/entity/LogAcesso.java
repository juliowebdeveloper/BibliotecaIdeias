package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LogAcesso implements Serializable{

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLogAcesso;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	
	//Data que o funcionario inseriu
	@Temporal(TemporalType.DATE)
	private Calendar dataFunc;
	
	
	//Ação que foi realizada para ser gravada
	private String acao;
	
	
	private String anotacoes;
	
	
	//Cliente que foi acessado para ir no log
	@ManyToOne
	@JoinColumn(name ="id_cliente")
	private Cliente idCliente;
	
	//Funcionario que estava fazendo o acesso.
	@ManyToOne
	@JoinColumn(name ="id_func")
	private Funcionario funcionario;


	



	public Calendar getData() {
		return data;
	}


	public void setData(Calendar data) {
		this.data = data;
	}


	public Calendar getDataFunc() {
		return dataFunc;
	}


	public void setDataFunc(Calendar dataFunc) {
		this.dataFunc = dataFunc;
	}


	public Cliente getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public int getIdLogAcesso() {
		return idLogAcesso;
	}


	public void setIdLogAcesso(int idLogAcesso) {
		this.idLogAcesso = idLogAcesso;
	}


	public String getAnotacoes() {
		return anotacoes;
	}


	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}


	public String getAcao() {
		return acao;
	}


	public void setAcao(String acao) {
		this.acao = acao;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
