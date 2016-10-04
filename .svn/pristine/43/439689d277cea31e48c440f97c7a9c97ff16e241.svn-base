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
public class Projeto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idProjeto;
	
	private String nome;
	
	private String evento;
	
	private String codigo;
	
	private String arquiteto;
	
	private String metragem;
	
	private String ano;
	
	private String segmento;

	@ManyToOne
	@JoinColumn(name ="id_cliente")
	private Cliente cliente;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projeto")
	private List<VersaoProjeto> versoes;
	
	
	
	
	public long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
	}


	public String getArquiteto() {
		return arquiteto;
	}

	public void setArquiteto(String arquiteto) {
		this.arquiteto = arquiteto;
	}


	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMetragem() {
		return metragem;
	}

	public void setMetragem(String metragem) {
		this.metragem = metragem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<VersaoProjeto> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<VersaoProjeto> versoes) {
		this.versoes = versoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	

}
