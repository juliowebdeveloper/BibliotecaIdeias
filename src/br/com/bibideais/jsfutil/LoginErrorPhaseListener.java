package br.com.bibideais.jsfutil;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.bibideais.entity.Funcionario;
 

 
@SuppressWarnings({"serial"})
public class LoginErrorPhaseListener implements PhaseListener, Serializable {
 
	
    
    @Override
    public void afterPhase(PhaseEvent event) {
        //If you have a login, so you have a user in session. Try to retrieve this value
        //and it will return null if the user is not logged in or theres no more session
        //and...
    	
    	/*
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

       // boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
        boolean isLoginPage = (currentPage.contains("index.xhtml"));
        
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object currentUser = session.getAttribute("funcionario");
        
        Funcionario func = (Funcionario) currentUser;
        
        try{
        	
        	
       
        if (!isLoginPage && func == null) {
          //NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
          //FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8085/BibliotecaIdeias/index.xhtml");
         // FacesContext.getCurrentInstance().getExternalContext().dispatch("http://localhost:8085/BibliotecaIdeias/index.xhtml");
         // facesContext.getApplication().getNavigationHandler().handleNavigation(
                  //facesContext, null, "../index.xhtml?faces-redirect=true");
         // nh.handleNavigation(facesContext, null, "loginPage");
        	//facesContext.responseComplete();
        	//resp.sendRedirect("/mypage.jsf");
        	
   
        	NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "loginPage");
          
        	
        	//facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "../index.xhtml?faces-redirect=true");
        	//FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8085/BibliotecaIdeias/index.xhtml");
        	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        	externalContext.redirect(externalContext.encodeResourceURL(externalContext.getRequestContextPath()+"http://localhost:8085/BibliotecaIdeias/index.xhtml"));
        	
        	return;
        	
        }else if (isLoginPage && func != null) {
            // Se o usuário logado tentar acessar a página de login ele é
            // redirecionado para a página inicial
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "sucesso");
            
        }
        
        
        
       
        }catch(Exception e){
        	e.printStackTrace();
        }*/
        
        
      }

     
    

    
 
    @SuppressWarnings({ "unchecked" })
    @Override
    public void beforePhase(PhaseEvent event) {
       FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

       // boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
        boolean isLoginPage = (currentPage.contains("index.xhtml"));
        
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object currentUser = session.getAttribute("funcionario");
        
        Funcionario func = (Funcionario) currentUser;
        
        try{
        	
        	
       
        if (!isLoginPage && func == null) {
        	
        	FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8085/BibliotecaIdeias/index.xhtml");
        	
        	return;
        	
        }else if (isLoginPage && func != null) {
           
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "sucesso");
            
        }
        
        
        
       
        }catch(Exception e){
        	e.printStackTrace();
        }
        
    }
 
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
    
    
    
   
    
}