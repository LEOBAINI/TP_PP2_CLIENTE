package cliente;

import java.io.*;
import java.net.*;
class Comunicador
{
	String ip;
	String mensaje;
	int puerto;
	public Comunicador(String ip,String mensaje,int puerto){
		this.ip=ip;
		this.mensaje=mensaje;
		this.puerto=puerto;
	}
	
private DatagramSocket crearSocket(){
	DatagramSocket socketCliente = null;
	try {
	socketCliente = new DatagramSocket();
	} catch (IOException e)
	{
	System.out.println("Error al crear el objeto socket cliente");
	System.exit ( 0 );
	}
	return socketCliente;
	
}
private InetAddress entenderIp(){
	InetAddress DireccionIP = null;
	try {

	DireccionIP = InetAddress.getByName(ip);
	} catch (IOException e)
	{
	System.out.println("Error al recuperar la IP del proceso");
	System.exit ( 0 );
	}
	return DireccionIP;
}
/**
 * Método encagado de enviar el mensaje del constructor
 */
public void enviarMensaje(){


DatagramSocket socketCliente = crearSocket();

crearPaqueteyEnviar(socketCliente);


}


private void crearPaqueteyEnviar(DatagramSocket socketCliente) {
	byte [] datos = new byte[mensaje.getBytes().length];


	datos = mensaje.getBytes();


	DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, entenderIp(), puerto);
	try {
		socketCliente.send(enviarPaquete);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	socketCliente.close();

	
}

public String getIp() {
	return ip;
}


public void setIp(String ip) {
	this.ip = ip;
}


public String getMensaje() {
	return mensaje;
}


public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}


public int getPuerto() {
	return puerto;
}


public void setPuerto(int puerto) {
	this.puerto = puerto;
}
}

