package br.com.bibideais.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CidadeBO;

@ManagedBean(name ="cadCityBean")
@RequestScoped
public class CadastroCidadeBean {


	public Cidade newcidade;
	
	public CidadeBO bo;
	
	public List<String> estados;
	
	public String selectedEstado;
	
	
	@PostConstruct
	public void init(){
		newcidade = new Cidade();
		estados = new ArrayList<String>();
		estados.add("Acre");
		estados.add("Alagoas");
		estados.add("Amap�");
		estados.add("Amazonas");
		estados.add("Bahia");
		estados.add("Cear�");
		estados.add("Distrito Federal");
		estados.add("Esp�rito Santo");
		estados.add("Goi�s");
		estados.add("Maranh�o");
		estados.add("Mato Grosso");
		estados.add("Mato Grosso do Sul");
		estados.add("Minas Gerais");
		estados.add("Par�");
		estados.add("Para�ba");
		estados.add("Paran�");
		estados.add("Pernambuco");
		estados.add("Piau�");
		estados.add("Rio de Janeiro");
		estados.add("Rio Grande do Norte");
		estados.add("Rio Grande do Sul");
		estados.add("Rond�nia");
		estados.add("Roraima");
		estados.add("Santa Catarina");
		estados.add("S�o Paulo");
		estados.add("Sergipe");
		estados.add("Tocantins");
		

		
	}
	
	
	
	public void cadastrarCidade(){
			MessageUtil mu = new MessageUtil();
			newcidade.setDistrito(selectedEstado);
		if(this.buscarCidadePorNomeDistrito(newcidade.getNome(), newcidade.getDistrito())!= null){
			mu.sendErrorMessageToUser("N�o Permitido", "J� existe uma cidade com esse nome nesse estado/distrito");
		}else{
			bo = new CidadeBO();
			newcidade.setId(bo.returnNextId());
			newcidade.setCodigoPais("BRA");
			bo.cadastrar(newcidade);
			mu.sendInfoMessageToUser("Cidade Cadastrada", "A cidade foi cadastrada e j� pode ser selecionada para outros cadastros");
			newcidade = new Cidade();
		}
		
		
	}
	
	
	public Cidade buscarCidadePorNomeDistrito(String nome, String distrito){
		bo = new CidadeBO();
		Cidade cidade = bo.buscarPorNomeDistrito(nome, distrito);
		return cidade;
	}



	public Cidade getNewcidade() {
		return newcidade;
	}



	public void setNewcidade(Cidade newcidade) {
		this.newcidade = newcidade;
	}



	public CidadeBO getBo() {
		return bo;
	}



	public void setBo(CidadeBO bo) {
		this.bo = bo;
	}



	public List<String> getEstados() {
		return estados;
	}



	public void setEstados(List<String> estados) {
		this.estados = estados;
	}



	public String getSelectedEstado() {
		return selectedEstado;
	}



	public void setSelectedEstado(String selectedEstado) {
		this.selectedEstado = selectedEstado;
	}
	
	
	
}
