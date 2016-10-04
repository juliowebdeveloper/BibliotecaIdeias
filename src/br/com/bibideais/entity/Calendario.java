package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Calendario implements Serializable {

	
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int idCalendario;
	
	 	private String descricao;
	 
	 	@Temporal(TemporalType.TIMESTAMP)
	    private Calendar dataInicio;
	 
	 	@Temporal(TemporalType.TIMESTAMP)
	    private Calendar dataFim;
	 
	    private boolean diaTodo;
	    
	    
	    
	    

		public int getIdCalendario() {
			return idCalendario;
		}

		public void setIdCalendario(int idCalendario) {
			this.idCalendario = idCalendario;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Calendar getDataInicio() {
			return dataInicio;
		}

		public void setDataInicio(Calendar dataInicio) {
			this.dataInicio = dataInicio;
		}

		public Calendar getDataFim() {
			return dataFim;
		}

		public void setDataFim(Calendar dataFim) {
			this.dataFim = dataFim;
		}

		public boolean isDiaTodo() {
			return diaTodo;
		}

		public void setDiaTodo(boolean diaTodo) {
			this.diaTodo = diaTodo;
		}
	
	
}
