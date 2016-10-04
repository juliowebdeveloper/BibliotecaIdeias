package br.com.bibideais.jsfutil;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageUtil {

	public void sendInfoMessageToUser(String message, String detail) {

		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message, detail);
		addMessageToJsfContext(facesMessage);
	}

	public void sendErrorMessageToUser(String message, String detail) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message,detail);
		addMessageToJsfContext(facesMessage);
	}

	public void sendWarnMessageToUser(String message, String detail) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message, detail);
		addMessageToJsfContext(facesMessage);
	}

	public void sendFatalMessageToUser(String message, String detail) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_FATAL, message, detail);
		addMessageToJsfContext(facesMessage);
	}

	
	private FacesMessage createMessage(Severity severity, String mensagemErro, String detail) {
		return new FacesMessage(severity, mensagemErro, detail);

	}

	private void addMessageToJsfContext(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	
}
