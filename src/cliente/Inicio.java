package cliente;


public class Inicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Temporizador r=new Temporizador();
		
		r.ejecutarMensajes();

	/*BuscadorDatosBd bd=new BuscadorDatosBd();
	System.out.println(bd.consultar("SELECT * FROM shiteckhibernate.auditoria").get(0));*/	
	}

}
