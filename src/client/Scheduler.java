package client;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */

public class Scheduler {
  //private Toolkit toolkit;

  static Timer timer;
  

  public Scheduler(int seconds,Comunicator udp) {
//    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(udp), seconds * 1000);
   
  }

  
  
  
  
  
  public Scheduler() {

}






class RemindTask extends TimerTask {
	  Comunicator udp;
	  
	  public RemindTask(Comunicator udp){
		  this.udp=udp;
	  }
	  
	  
	  
    public void run() {
      udp.enviarMensaje();
     // toolkit.beep();
      //System.exit(0);
      
    
    }
  }
  
  
/**
 * Crea una tarea programada, recibe un comunicator 
 */
  
public void scheduleTask(Comunicator comuni,int segundos){
	
			   
	 new Scheduler(segundos,comuni);
	
	
	System.out.println("Tarea programada en "+segundos+" segundos! ");
}

 }
