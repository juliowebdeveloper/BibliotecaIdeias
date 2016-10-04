package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Fornecedor implements Serializable, Comparable<Fornecedor>{

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFornecedor;
	
	@Column(length = 100)
	private String razaoSocial;
	

	@Column(length = 200)
	private String produto;
	
	
	@Column(length = 200, nullable = false)
	private String segmento;
	
	
	private String endereco;
	
	@Column(length = 10)
	private String numero;

	
	private String complemento;

	
	private String bairro;
	
	private String cep;
	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean isFornecedorBi;
	
	
	private String email;
	
	private String email2;
	
	private String email3;
	
	
	private String telefone;
	
	private String telefone2;
	
	private String telefone3;
	
	private String nextel;
	
	private String celular;
	
	
	private String nomeContato;
	
	private String cargo;
	
	private String site;
	
	
	private String anotacoes;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;

	
	
	
	
	public Fornecedor (){}

	public int getIdFornecedor() {
		return idFornecedor;
	}


	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}




	public String getProduto() {
		return produto;
	}


	public void setProduto(String produto) {
		this.produto = produto;
	}


	public String getSegmento() {
		return segmento;
	}


	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}




	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public boolean isFornecedorBi() {
		return isFornecedorBi;
	}


	public void setFornecedorBi(boolean isFornecedorBi) {
		this.isFornecedorBi = isFornecedorBi;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}



	@Override
	public int compareTo(Fornecedor o) {
		return getRazaoSocial().compareTo(o.getRazaoSocial());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getNextel() {
		return nextel;
	}

	public void setNextel(String nextel) {
		this.nextel = nextel;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Fornecedor(String razaoSocial, String produto, String segmento,
			String endereco, String numero, String complemento, String bairro,
			String cep, Cidade cidade, boolean isFornecedorBi, String email,
			String telefone, String telefone2, String telefone3, String nextel,
			String celular, String nomeContato, String cargo, String site, String anotacoes) {
		super();
		this.razaoSocial = razaoSocial;
		this.produto = produto;
		this.segmento = segmento;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.isFornecedorBi = isFornecedorBi;
		this.email = email;
		this.telefone = telefone;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.nextel = nextel;
		this.celular = celular;
		this.nomeContato = nomeContato;
		this.cargo = cargo;
		this.site = site;
		this.anotacoes = anotacoes;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	
	
	
}
