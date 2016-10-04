package br.com.bibideais.teste;

import java.util.List;

import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Entidade;
import br.com.bibideais.service.CidadeBO;
import br.com.bibideais.service.EntidadeBO;

public class EntidadeTeste {
	public static void main(String[] args) {
		EntidadeTeste t = new EntidadeTeste();
		//t.inserirEntidade();
		t.buscarEntidadesAlfa();
	}




	public void inserirEntidade(){	
		Cidade cidade = new Cidade();
		cidade = new CidadeBO().buscarPorId(206);
		Entidade ent = new Entidade("União Brasileira de Eventos", "Rua: Frei caneca", "91", "(11)3120-7099", "ubrafe@ubrafe.com.br", null, null, null, true, "11º andar", "Cerqueira Cesar", null,cidade);
		EntidadeBO bo = new EntidadeBO();
		bo.cadastrar(ent);
	
	
	}
	
	public void buscarEntidadesAlfa(){
		List<Entidade> ents = new EntidadeBO().localizarTodasAlfabetic();
		for (Entidade entidade : ents) {
			System.out.println(entidade.getNome());
		}
	}
	
	
	
	
	
	
}
