package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
class Comunicator
{
	private String ip;
	private String message;
	private int port;
	
	
public Comunicator() {
		
	}
private String parsearMensaje(String mensajeCrudo){
	//ArrayList<Integer>comasPos=new ArrayList<Integer>();
	List <Integer> comasPos=new ArrayList<Integer>();
	for(int i=0;i<mensajeCrudo.length();i++){
		if(mensajeCrudo.charAt(i)==','){
			comasPos.add(i);
		}
	}
		
	
	String materia=mensajeCrudo.substring(comasPos.get(0)+1,comasPos.get(1));
	String horaIn=mensajeCrudo.substring(comasPos.get(1)+1,comasPos.get(2));
	String horaOut=mensajeCrudo.substring(comasPos.get(2)+1,mensajeCrudo.lastIndexOf("]"));
	String parseado="Materia:"+materia+"\n"+"Hora comienzo:"+horaIn+"\n"+"Hora fin:"+horaOut+"\n";
	
	return parseado;
	
}

private DatagramSocket crearSocket(){
	DatagramSocket socketCliente = null;
	try {
	socketCliente = new DatagramSocket();
	} catch (IOException e)
	{
	System.out.println("Error al crear el objeto socket client");
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
 * Método encagado de enviar el message del constructor
 */
public void enviarMensaje(){


DatagramSocket socketCliente = crearSocket();

crearPaqueteyEnviar(socketCliente);


}


private void crearPaqueteyEnviar(DatagramSocket socketCliente) {
	byte [] datos = new byte[message.getBytes().length];


	datos = message.getBytes();


	DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, entenderIp(), port);
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
	
	return message;
}


public void setMessage(String mensaje) {
	this.message = parsearMensaje(mensaje);
}


public int getPuerto() {
	return port;
}


public void setPortNumber(int puerto) {
	this.port = puerto;
}
}

