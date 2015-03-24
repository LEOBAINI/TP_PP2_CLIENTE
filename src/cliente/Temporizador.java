package cliente;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */

public class Temporizador {
  private Toolkit toolkit;

  static Timer timer;

  public Temporizador(int seconds,Comunicador udp) {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(udp), seconds * 1000);
   
  }

  
  
  
  
  
  public Temporizador() {

}






class RemindTask extends TimerTask {
	  Comunicador udp;
	  
	  public RemindTask(Comunicador udp){
		  this.udp=udp;
	  }
	  
	  
	  /**
	   * Si detecta la palabra CHAU... en un String, hace ejecutarMensajes(); en un ciclo infinito.
	   */
    public void run() {
      udp.enviarMensaje();
      toolkit.beep();
      if(udp.getMensaje().equals("CHAU...")){
    	  System.out.println("Recomenzando ciclo...");
    	  ejecutarMensajes();
    	
      }
    
    }
  }
  
  
/**
 * Crea un comunicador y envia mensajes precargados cada 2 segundos  
 */
  
public void ejecutarMensajes(){
	 String[] mensaje={"Hola","esto","es","un","timer","andando","CHAU..."};
	    int segundos=5;
	 
	    for(int i=0;i<=6;i++){
			   Comunicador udp=new Comunicador("localhost",mensaje[i],5000);
			   
			   new Temporizador(segundos,udp);
			   segundos=segundos+2;
			   System.out.println("Tarea programada en "+segundos+" segundos! ");
	     
	   
	  }
}
public int prueba(int pri,int seg){
	return pri+seg;
}
 }
