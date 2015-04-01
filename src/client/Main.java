package client;


public class Main {


	private static void start(){
		while(true){
		int portNumber = 5000;
		String hostIp = "255.255.255.255";
		int timeSched1 = 2;
		int timeSched2 = timeSched1*2;
		long sleepTime1 = timeSched1*1000;
		long sleepTime2 = timeSched2*1000;
		
		String query = "SELECT * FROM PP2.MATERIA where idmateria=1";
		String query2 = "SELECT * FROM PP2.MATERIA where idmateria=2";
		Scheduler clock = new Scheduler();
		DataBaseManager mgrDbase = new DataBaseManager();		
		String message = mgrDbase.excecuteQuery(query);	
		String message2 = mgrDbase.excecuteQuery(query2);	
		Comunicator com = new Comunicator();
		Comunicator com2 = new Comunicator();
		
		com.setIp(hostIp);
		com.setMessage(message);
		com.setPortNumber(portNumber);
		
		clock.scheduleTask(com,timeSched1);
		waitMillis(sleepTime1);
		com2.setIp(hostIp);
		com2.setMessage(message2);
		com2.setPortNumber(portNumber);		
		clock.scheduleTask(com2,timeSched2);
		waitMillis(sleepTime2);
		com=null;
		com2=null;
		mgrDbase=null;
		clock=null;
		System.gc();
		}
		

	}
	private static void waitMillis(long milis){
		try {
			java.lang.Thread.sleep(milis);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		start();
		
	}

}
