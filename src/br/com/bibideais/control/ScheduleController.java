package br.com.bibideais.control;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.bibideais.entity.Calendario;
import br.com.bibideais.jsfutil.MessageUtil;
import br.com.bibideais.service.CalendarioBO;

@ManagedBean(name ="scheduleController")
@ViewScoped
public class ScheduleController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private ScheduleModel eventModel;  
    
    private ScheduleEvent event = new DefaultScheduleEvent();  
    
    private CalendarioBO cbo;
    
    private List<Calendario> calendarios;
    
   private Calendario ca;
   
   //usado para update
   private Calendario oldca;
  
    public ScheduleController() {  
    	
        eventModel = new DefaultScheduleModel();  
        cbo = new CalendarioBO();
        this.preencherCalendario();
        
      
        
    }  
    
    
    public void preencherCalendario(){
        calendarios = cbo.buscarTodasCalendarios();
        eventModel.clear();
        for (Calendario c : calendarios) {
        	DefaultScheduleEvent evt = new DefaultScheduleEvent(c.getDescricao(), c.getDataInicio().getTime(), c.getDataFim().getTime(), "calendarButton");
        	//eventModel.addEvent(new DefaultScheduleEvent(c.getDescricao(), c.getDataInicio().getTime(), c.getDataFim().getTime(), c.isDiaTodo()));
           evt.setAllDay(c.isDiaTodo());
           eventModel.addEvent(evt);
		}
        
    
        
    }
      
    public void addEvent(ActionEvent actionEvent) {  
        
		if(event.getId() == null)  {
			try{
				ca = new Calendario();
	        	ca.setDescricao(event.getTitle());
	        	Calendar datainicio = Calendar.getInstance();
	        	Calendar datafim = Calendar.getInstance();

	        	datainicio.setTime(event.getStartDate());
	        	datafim.setTime(event.getEndDate());
	        	ca.setDataInicio(datainicio);
	        	ca.setDataFim(datafim);
	        	ca.setDiaTodo(event.isAllDay());
	        	
	        	cbo = new CalendarioBO();
	        	cbo.adicionarCalendario(ca);
	        	
	            eventModel.addEvent(event);  
			}catch(Exception e ){
				e.printStackTrace();
			}
			
		} else  {
            eventModel.updateEvent(event);  
		}
        event = new DefaultScheduleEvent();  
    }  
      
    
    public void updateEvent(){
    	try{
    		 Calendar datainicio = Calendar.getInstance();
    	     	Calendar datafim = Calendar.getInstance();
    	     	datainicio.setTime(event.getStartDate());
    	     	datafim.setTime(event.getEndDate());
    	    	cbo = new CalendarioBO();
    	    	cbo.update(event.getTitle(), datainicio, datafim, event.isAllDay(), oldca);
    	    	this.preencherCalendario();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    
    public void deleteEvent(){
    	MessageUtil mu = new MessageUtil();
    	try{
   		 Calendar datainicio = Calendar.getInstance();
   	     	Calendar datafim = Calendar.getInstance();
   	     	datainicio.setTime(event.getStartDate());
   	     	datafim.setTime(event.getEndDate());
   	    	cbo = new CalendarioBO();
   	    	cbo.delete(event.getTitle(), datainicio, datafim, event.isAllDay(), oldca);
   	    	
   	    	this.preencherCalendario();
   	    	mu.sendInfoMessageToUser("Evento Excluído", null);
   	}catch(Exception e){
   		mu.sendErrorMessageToUser("Erro", "O evento não pode ser excluído");
   		e.printStackTrace();
   	}
    }
    

    public void onEventSelect(SelectEvent selectEvent) {  
    	oldca = new Calendario();
        event = (ScheduleEvent) selectEvent.getObject();  
        Calendar datainicio = Calendar.getInstance();
    	Calendar datafim = Calendar.getInstance();
    	datainicio.setTime(event.getStartDate());
    	datafim.setTime(event.getEndDate());
        oldca.setDiaTodo(event.isAllDay());
        oldca.setDataInicio(datainicio);
        oldca.setDataFim(datafim);
        oldca.setDescricao(event.getTitle());
      
    }  
      
    
    public Date getRandomDate(Date base) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(base);  
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month  
          
        return date.getTime();  
    }  
      
    public Date getInitialDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);  
          
        return calendar.getTime();  
    }  
      
    public ScheduleModel getEventModel() {  
        return eventModel;  
    }  
      
  
    public ScheduleEvent getEvent() {  
        return event;  
    }  
  
    public void setEvent(ScheduleEvent event) {  
        this.event = event;  
    }  
      
  
    public void onDateSelect(SelectEvent selectEvent) {  
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());  
    }  
      
    public void onEventMove(ScheduleEntryMoveEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

	public CalendarioBO getCbo() {
		return cbo;
	}

	public void setCbo(CalendarioBO cbo) {
		this.cbo = cbo;
	}

	public List<Calendario> getCalendarios() {
		return calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Calendario getCa() {
		return ca;
	}

	public void setCa(Calendario ca) {
		this.ca = ca;
	}

	public Calendario getOldca() {
		return oldca;
	}

	public void setOldca(Calendario oldca) {
		this.oldca = oldca;
	} 
    
    
    
    
}  
	
	

