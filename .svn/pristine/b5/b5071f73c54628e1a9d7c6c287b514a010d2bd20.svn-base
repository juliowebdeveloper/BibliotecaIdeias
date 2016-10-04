package br.com.bibideais.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.bibideais.entity.Pavilhao;
import br.com.bibideais.service.PavilhaoBO;

@FacesConverter(forClass=Pavilhao.class, value="pavConverter")
public class PavilhaoConverter  implements Converter {
	public static List<Pavilhao> pavilhoes;
	 
	 
    static {
    	pavilhoes = new PavilhaoBO().buscarTodosAlphabetic();
    }

    
    
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(s);

                for (Pavilhao lt : pavilhoes) {
                    if (lt.getIdPavilhao() == number) {
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
            return String.valueOf(((Pavilhao) o).getIdPavilhao());
        }
    }
}