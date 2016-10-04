package br.com.bibideais.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.bibideais.dao.OrganizadoraDAO;
import br.com.bibideais.entity.Organizadora;

@FacesConverter(value="orgConverter")
public class OrgConverter implements Converter{

	 public static List<Organizadora> organizadoras;
	 
	 
	 //TODO
	 //Exibir apenas os emails dos clientes bi la na busca de feiras.
	 
	 
	    static {
	    	
	       organizadoras = new OrganizadoraDAO().buscarTodasAlfabetic();
	       
	      
	       
	    }

	    public OrgConverter (){
		       //organizadoras = new OrganizadoraDAO().buscarTodasAlfabetic();

	    }
	    
	    
	    
	    
	    
	    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
	        if (s.trim().equals("")) {
	            return null;
	        } else {
	            try {
	                int number = Integer.parseInt(s);

	                for (Organizadora lt : organizadoras) {
	                    if (lt.getIdOrganizadora() == number) {
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
	            return String.valueOf(((Organizadora) o).getIdOrganizadora());
	        }
	    }
	
	
	
}
