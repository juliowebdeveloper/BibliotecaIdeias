package br.com.bibideais.jsfutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator{

public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {

    String enteredEmail = (String)object;
    Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
    Matcher matcher = pattern.matcher(enteredEmail.toLowerCase());

    boolean matchFound = matcher.matches();

    if (!matchFound) {
        FacesMessage message = new FacesMessage();
        message.setDetail("Formato de E-mail incorreto!");
        message.setSummary("Formato de E-mail incorreto!");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }
  }
}