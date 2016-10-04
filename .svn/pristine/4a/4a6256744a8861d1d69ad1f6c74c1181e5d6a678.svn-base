package br.com.bibideais.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.bibideais.entity.Cliente;
import br.com.bibideais.entity.Feira;
import br.com.bibideais.service.FeiraBO;

@FacesConverter(value="feirasTotaisConverter")
public class FeirasTotaisConverter implements Converter {
	
	public static List<Feira> feirasTotais;


 	public static Cliente cliente;
 	
 	
 	
	 public static List<Feira> retornarFeiras(Cliente c){
		 feirasTotais = new FeiraBO().subtrairFeirasCliente(c);
		 return feirasTotais;
	 }
	
		

		static {
		       feirasTotais = retornarFeiras(cliente);
		    }
		
		  
	    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
	        if (s.trim().equals("")) {
	            return null;
	        } else {
	            try {
	                int number = Integer.parseInt(s);

	                for (Feira lt : feirasTotais) {
	                    if (lt.getIdFeira() == number) {
	                        return lt;
	                    }
	                }
	            } catch(NumberFormatException exception) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid converter"));
	            }
	        }
	        return null;
	    }

	    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
	        if (o == null || o.equals("")) {
	            return "";
	        } else {
	            return String.valueOf(((Feira) o).getIdFeira());
	        }
	    }

		
}
