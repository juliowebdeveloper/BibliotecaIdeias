package br.com.bibideais.teste;

import java.util.List;

import br.com.bibideais.entity.Briefing;
import br.com.bibideais.entity.BriefingFiles;
import br.com.bibideais.service.BriefingBO;

public class BriefingTeste {

	
	public static void main(String[] args) {
		BriefingTeste t = new BriefingTeste();
		t.buscarFilesPorBriefing();
	}
	
	
	public void buscarFilesPorBriefing(){
		Briefing b = new BriefingBO().buscarPorId(7);
		List<BriefingFiles> bfs = new BriefingBO().buscarFilesPorBriefing(b);
		for (BriefingFiles briefingFiles : bfs) {
			System.out.println(briefingFiles.getUrlFile());
		}
	}
}
