package br.com.bibideais.teste;

import java.util.List;

import br.com.bibideais.dao.CidadeDAO;
import br.com.bibideais.entity.Cidade;
import br.com.bibideais.entity.Pais;

public class CidadeTeste {

	
	public static void main(String[] args) {
		CidadeTeste t = new CidadeTeste();
		//t.listarTodasCidades();
		//t.buscarCidadesPorPais();
		////
		//t.buscarDistritosPorPais();
		//t.buscarPorNomeEDistrito();
		t.returnLastId();
	}
	
	public void returnLastId(){
		CidadeDAO dao = new CidadeDAO();
		int id = dao.returnLastId();
		System.out.println(id);
	}
	
	public void buscarDistritosPorPais(){
		CidadeDAO dao = new CidadeDAO();
		Pais p = new Pais();
		List<String> distritos = dao.buscarNomesDistritos("BRA");
		for (String string : distritos) {
			System.out.println(string);
		}
	}
	
	
	public void buscarPorNomeEDistrito(){
		CidadeDAO dao = new CidadeDAO();
		Cidade c = new Cidade();
				c = dao.buscarPorNomeExatoDistrito("São Paulo", "ROraima");
		if(c != null){
			System.out.println("Trouxe a cidade, codigo da mesma : " + c.getId() + c.getNome());
		}else{
			System.out.println("Não trouxe");
		}
	}
	
	public void listarTodasCidades(){
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> cidades = dao.localizarAll();
		for (Cidade cidade : cidades) {
			System.out.println(cidade.getCodigoPais());
		}
	}
	
	public void buscarCidadesPorPais(){
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> cits = dao.buscarPorCodigoPais("BRa");
		for (Cidade cidade : cits) {
			System.out.println(cidade.getDistrito() + cidade.getNome());
		}
	}
	
}
