package br.com.bibideais.teste;

import br.com.bibideais.jsfutil.ImagesUtil;

public class ImagesUtilTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImagesUtil u = new ImagesUtil();
		//String ad = u.gerarIdentificadorImagem("haddd.jpg");
		//System.out.println(ad);
		//String ad = u.gerarIdentificadorPlanilhaCustos("PLANILHA_CUSTOS.xls", "169-0");
		String file = "B_Hcodigo çççççççççááááEspecificação ééééé_Funcional- RAS 3";
		String ad = u.gerarIdentificadorBriefingFile("PLANILHA_CUSTOS.xls", "169-0");
		String urlBrif = ImagesUtil.urlBriefings + u.gerarIdentificadorBriefingFile("B_Hcodigo Especificação ééééé_Funcional- RAS 3", "169-0");
		String urlBrifa = ImagesUtil.urlBriefings + u.gerarIdentificadorPlanilhaCustos(file, "169-0");

		System.out.println(urlBrif);
		String filename = u.retornarBriefingFileName(urlBrif);
		System.out.println(filename);
		System.out.println(urlBrifa);
		
	/*	String tes = ImagesUtil.urlManuaisFeira;
		String tes1 = ImagesUtil.urlMapaFeira;
		
		System.out.println(tes);
		System.out.println(tes1);*/
		
	}

}
