package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity
public class Funcionario implements Serializable, Comparable<Funcionario>{

	
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "username", length = 20, unique = true)
	private String username;
	
	@Column(length = 120)
	private String nomeCompleto;
	
	@Column(length = 100, unique = false)
	private String email;
	

	
	@Column(name = "password", length = 40)
	private String password;
	
	@Column(name = "enable", columnDefinition = "BOOLEAN")
	private boolean enable;

	
	@ManyToMany
	@JoinTable(name = "FUNC_AUTH", joinColumns = @JoinColumn(name = "FUNC_id"), inverseJoinColumns = @JoinColumn(name = "AUTH_authority"))
	private List<Authority> authorities;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Funcionario o) {
		return getNomeCompleto().compareTo(o.getNomeCompleto());
	}
	
	
	
	
	
	
}
