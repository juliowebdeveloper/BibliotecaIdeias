package br.com.bibideais.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.ContatoCliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.jsfutil.FacesUtil;
import br.com.bibideais.service.ClienteBO;
import br.com.bibideais.service.FeiraBO;
/**
 * 
 * @author Shido
 * Trata  a visualização de uma unica feira.
 */
@ManagedBean(name ="viewFeiraBean")
@ViewScoped
public class ViewFeiraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idFeira;
	
	private Feira feira;
	
	private FeiraBO bo;
	
	
	private boolean disable = false;
	
	//Clientes daquela feira
	private List<Cliente> clientes;

	private List<ContatoCliente> contatosCliente;
	
	
	private Cliente clienteSelecionado;
	
	
	@PostConstruct
	public void init(){
		
		idFeira = FacesUtil.getRequestParameter("feiraid");
		disable = false;
		if(idFeira == null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		bo = new FeiraBO();
		feira = bo.buscarPorCodigo(Integer.parseInt(idFeira));
		clientes = new ClienteBO().buscarClientesFeira(feira);

	}


	
	
	
	
	public void verEmailClientesFeira(){
			
			ClienteBO bo = new ClienteBO();
			contatosCliente = new ArrayList<ContatoCliente>();
			List<ContatoCliente> c = bo.buscarContatos(clienteSelecionado);
			for (ContatoCliente contatoCliente : c) {
				contatosCliente.add(contatoCliente);
			}
			
		}
		

	
	
	

	public String editarFeira(){
		HttpServletRequest request = FacesUtil.getRequest();
		
		  HttpSession session = FacesUtil.getSession();
	        
	        if(session.getAttribute("editFeiraBean") != null){
	        	session.removeAttribute("editFeiraBean");
	        }
		request.setAttribute("feiraedit", feira);
		
		return "editfeira.xhtml";
		
	}




	public String getIdFeira() {
		return idFeira;
	}








	public void setIdFeira(String idFeira) {
		this.idFeira = idFeira;
	}








	public Feira getFeira() {
		return feira;
	}








	public void setFeira(Feira feira) {
		this.feira = feira;
	}








	public FeiraBO getBo() {
		return bo;
	}








	public void setBo(FeiraBO bo) {
		this.bo = bo;
	}








	public boolean isDisable() {
		return disable;
	}








	public void setDisable(boolean disable) {
		this.disable = disable;
	}








	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<Cliente> getClientes() {
		return clientes;
	}



	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}




	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}



	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}






	public List<ContatoCliente> getContatosCliente() {
		return contatosCliente;
	}






	public void setContatosCliente(List<ContatoCliente> contatosCliente) {
		this.contatosCliente = contatosCliente;
	}
	
	
}
