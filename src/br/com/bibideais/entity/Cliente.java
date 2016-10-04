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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
public class Cliente implements Serializable , Comparable<Cliente>{

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	
	
	//Nacional Internacional
	@Column(length = 15, nullable = true)
	private String nacionalidade;
	
	//Imagem do cartão de visitas.
	private String urlCartao;
	
	
	
	@Column (length = 80)
	private String nomeFantasia;
	
	@Column(unique = true, nullable = false)
	private String razaoSocial;
	
	
	
	//Segmentos
	@Column(length = 50)
	private String segmentoPrincipal;
	
	
	@Column(length = 200, nullable = false)
	private String site;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Ficha> fichas;
	
	

	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.EAGER)
	private List<ContatoCliente> contatos;
	
	
	
	//Verificar se é cliente
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean isClient;
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean recebeuTrabalhos;
	
	
	//Atendimento e usuario que cadastrou aquele cliente.
	
	@ManyToOne
	@JoinColumn(name ="id_funcatendimento")
	private Funcionario atendimento;
	
	@ManyToOne
	@JoinColumn(name ="id_funccadastrador")
	private Funcionario cadastrador;
	
	
	//Feiras que já participou - será feito o mapeamento via FeiraID e a busca das feiras via query
	@Transient
	private List<Feira> feiras;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="cliente")
	private List<Projeto> projetos;
	
	
	//Informações de endereço
	
	@Column(length = 100)
	private String rua;
	
	@Column(length = 10)
	private String numero;
	
	private String complemento;
	
	
	@Column(length = 50)
	private String bairro;
	
	@Column(length = 20)
	private String cep;	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	
	

	
	//Agencia, Fábrica, Distribuidor, revendedor
	@Column (length = 20)
	private String categoria;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<FotoCliente> fotosReferencia;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Briefing> briefings;
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;


	@Column (length = 4000)
	private String anotacoes;
	
	
	

	//Ano que recebeu trabalhos.
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name ="CLIENTE_ANO", joinColumns = @JoinColumn(name ="id_cliente", referencedColumnName = "idCliente"), inverseJoinColumns = @JoinColumn(name = "id_ano", referencedColumnName = "idAno"))
	private List<Ano> anostrabalhos;
	

	
	
	public int getIdCliente() {
		return idCliente;
	}




	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}




	public String getNacionalidade() {
		return nacionalidade;
	}




	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}




	public String getNomeFantasia() {
		return nomeFantasia;
	}




	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}




	public String segmentoPrincipal() {
		return razaoSocial;
	}




	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}







	public String getSite() {
		return site;
	}




	public void setSite(String site) {
		this.site = site;
	}




	public List<Ficha> getFichas() {
		return fichas;
	}




	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}




	public boolean isClient() {
		return isClient;
	}




	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}




	public boolean isRecebeuTrabalhos() {
		return recebeuTrabalhos;
	}




	public void setRecebeuTrabalhos(boolean recebeuTrabalhos) {
		this.recebeuTrabalhos = recebeuTrabalhos;
	}




	public Funcionario getAtendimento() {
		return atendimento;
	}




	public void setAtendimento(Funcionario atendimento) {
		this.atendimento = atendimento;
	}




	public Funcionario getCadastrador() {
		return cadastrador;
	}




	public void setCadastrador(Funcionario cadastrador) {
		this.cadastrador = cadastrador;
	}




	public List<Feira> getFeiras() {
		return feiras;
	}




	public void setFeiras(List<Feira> feiras) {
		this.feiras = feiras;
	}



	public String getRua() {
		return rua;
	}




	public void setRua(String rua) {
		this.rua = rua;
	}




	public String getNumero() {
		return numero;
	}




	public void setNumero(String numero) {
		this.numero = numero;
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




	public Cidade getCidade() {
		return cidade;
	}




	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}






	public String getCategoria() {
		return categoria;
	}




	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}




	public List<FotoCliente> getFotosReferencia() {
		return fotosReferencia;
	}




	public void setFotosReferencia(List<FotoCliente> fotosReferencia) {
		this.fotosReferencia = fotosReferencia;
	}




	public List<Briefing> getBriefings() {
		return briefings;
	}




	public void setBriefings(List<Briefing> briefings) {
		this.briefings = briefings;
	}




	public Calendar getDataCadastro() {
		return dataCadastro;
	}




	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}




	public String getSegmentoPrincipal() {
		return segmentoPrincipal;
	}




	public void setSegmentoPrincipal(String segmentoPrincipal) {
		this.segmentoPrincipal = segmentoPrincipal;
	}





	public List<ContatoCliente> getContatos() {
		return contatos;
	}




	public void setContatos(List<ContatoCliente> contatos) {
		this.contatos = contatos;
	}




	public String getAnotacoes() {
		return anotacoes;
	}




	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getUrlCartao() {
		return urlCartao;
	}




	public void setUrlCartao(String urlCartao) {
		this.urlCartao = urlCartao;
	}




	public List<Ano> getAnostrabalhos() {
		return anostrabalhos;
	}




	public void setAnostrabalhos(List<Ano> anostrabalhos) {
		this.anostrabalhos = anostrabalhos;
	}
	
	
	@Override
	public int compareTo(Cliente o) {
		return getRazaoSocial().toLowerCase().compareTo(o.getRazaoSocial().toLowerCase());
	}




	public String getComplemento() {
		return complemento;
	}




	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}




	public String getRazaoSocial() {
		return razaoSocial;
	}




	public List<Projeto> getProjetos() {
		return projetos;
	}




	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	
	
	
}
