package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;



public class BuscadorDatosBd {
	  private  Connection c;
      private  Statement statemente;
      private  ResultSet resulsete;
      private  String base="shiteckhibernate";
      private  String host="localhost";
      private  String cadena="jdbc:mysql://"+host+"/"+base;
      private  String driver="com.mysql.jdbc.Driver";
     
      
    		
    		

    		public String dameFechaDeHoy(){
    			 SimpleDateFormat formateador = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("es_ES"));
    			 Date fechaDate = new Date();
    	          String fecha=formateador.format(fechaDate);
    		
    		return fecha;
    		}
    		public String dameAnio(){
    			 SimpleDateFormat formateador = new SimpleDateFormat("yyyy", new Locale("es_ES"));
    			 Date fechaDate = new Date();
    	         String fecha=formateador.format(fechaDate);
    		
    		return fecha;
    		}

    		public int insertarOmodif(String sentenciaSql) throws SQLException {
    			int status=0;
    			BuscadorDatosBd con = new BuscadorDatosBd();
    			System.out.println(sentenciaSql);

    			try {
    				con.conectar();
    				con.statemente.executeUpdate(sentenciaSql);
    				con.commit();
    				

    				con.desconectar();
    				status=1;

    			} catch (SQLException e) {
    				System.out.println("Error en insertarOmodificar"+e.getMessage());
    				if(e.getMessage().contains("Duplicate entry")){
    					System.out.println("Entrada duplicada cambie la clave primaria e intente de nuevo");
    					JOptionPane.showMessageDialog(null, "Entrada duplicada cambie la clave primaria e intente de nuevo");
    				con.rollBack();
    				}
    			  
    				//e.printStackTrace();
    				
    				
    				con.desconectar();
    				status=-1;
    			}
    			
    			catch(Exception e){
    				  JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
    				  status=-1;
    				
    			  }
    			return status;

    		}

    		public ArrayList<ArrayList<String>> consultar(String SentenciaSql) {
    			ResultSet res =null;
    			ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();//creo una matriz
    			String aux=null;
    			
    			BuscadorDatosBd con = new BuscadorDatosBd();
    			
    			
    			try {
    				con.conectar();
    				con.resulsete=con.statemente.executeQuery(SentenciaSql);
    				res = con.resulsete;
    				ResultSetMetaData rmd = res.getMetaData(); //guardo los datos referentes al resultset
    				
    				 
    		            while ( res.next()){
    		                    ArrayList<String> columnas = new ArrayList<String>();
    		                     for (int i=1; i<=rmd.getColumnCount(); i++) {
    		                         aux=res.getString(i);            
    		                        	 
    		                    	 columnas.add(aux);
    		                     }
    		                     matriz.add(columnas);
    		            }
    		        con.desconectar();

    				

    			} catch (Exception e) {
    				JOptionPane.showMessageDialog(null,"Error en metodosSql.consultar"+e.getMessage());
    				System.out.println("Error en metodosSql.consultar"+e.getMessage());
    				System.out.println(e.getLocalizedMessage());
    				return null;
    				
    			}

    			return matriz;
    			

    		}
    		
    		
    		
    		
    		public Object[] consultarNombresColumnas(String SentenciaSql) {
    			ResultSet res =null;
    			Object []nombreColumnas=null;
    			
    			
    			BuscadorDatosBd con = new BuscadorDatosBd();
    			
    			
    			try {
    				con.conectar();
    				con.resulsete=con.statemente.executeQuery(SentenciaSql);
    				res = con.resulsete;
    				ResultSetMetaData rmd = res.getMetaData(); //guardo los datos referentes al resultset
    				nombreColumnas= new Object[rmd.getColumnCount()];
    		            
    		           for(int i=0;i<nombreColumnas.length;i++){
    		        	   nombreColumnas[i]=rmd.getColumnName(i+1).toUpperCase();
    		           }
    		        con.desconectar();
    			

    				

    			} catch (Exception e) {
    				System.out.println("Error en metodosSql.consultar"+e.getMessage());
    				System.out.println(e.getLocalizedMessage());
    				
    			}

    			return nombreColumnas;
    			

    		}
    		
    			
    		
    		public ArrayList<String>consultarUnaColumna(String SentenciaSql) {
    			ResultSet res =null;
    			ArrayList<String> arreglo = new ArrayList<String>();//creo una matriz
    			
    			
    			BuscadorDatosBd con = new BuscadorDatosBd();
    			System.out.println(SentenciaSql);
    			
    			
    			try {
    				con.conectar();
    				con.resulsete=con.statemente.executeQuery(SentenciaSql);
    				res = con.resulsete;
    				
    				
    				
    				 
    		            while ( res.next()){
    		            	
    		                arreglo.add(res.getString(1));
    		            }
    		        con.desconectar();

    				

    			} catch (Exception e) {
    				System.out.println("Error en metodosSql.consultarUnaColumna"+e.getMessage());
    				
    			}

    			return arreglo;
    			

    		}
    		
    		public String dameNroTeDoyMes(int numeroDeMes){
    			String mes="NO EXISTE ESE MES";
    			if(numeroDeMes >=1 && numeroDeMes <=12){
    			switch(numeroDeMes){
    			case 1:mes="ENERO";break;
    			case 2:mes="FEBRERO";break;
    			case 3:mes="MARZO";break;
    			case 4:mes="ABRIL";break;
    			case 5:mes="MAYO";break;
    			case 6:mes="JUNIO";break;
    			case 7:mes="JULIO";break;
    			case 8:mes="AGOSTO";break;
    			case 9:mes="SEPTIEMBRE";break;
    			case 10:mes="OCTUBRE";break;
    			case 11:mes="NOVIEMBRE";break;
    			case 12:mes="DICIEMBRE";break;
    			}
    			}
    			
    			return mes;
    		}
    		
    		
    		
    		
    		
    		
    		

    		
    		

    	

      
      public String getBase() {
			return base;
		}



		public void setBase(String base) {
			this.base = base;
		}



		public String getHost() {
			return host;
		}



		
		public void setHost(String host) {
			this.host = host;
		}



		public BuscadorDatosBd(){
      	
      }
      
      
     
      private int conectar(){
      	int status=0;
          try{
          	
              Class.forName(driver);
              c=DriverManager.getConnection(cadena,"root","root");
               statemente=c.createStatement();
               c.setAutoCommit(false);
             
             status=1;

          }catch(ClassNotFoundException e1){
           System.out.println("Error en los drivers");
           status=0;
          
          }
          catch(SQLException e2){
              System.out.println("Error en la conexion");
              
              System.out.println(e2.getLocalizedMessage());
              status=0;
              

          }
          return status;

  }
      
      public void commit(){
      	try {
				c.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
      public Connection getConection() {
			return c;
		}



		public void setConnection(Connection c) {
			this.c = c;
		}



		public void rollBack() throws SQLException{
      	c.rollback();
      }
     
      private  void desconectar(){
      
      	
				try {
					if (c != null){
						c.close();
						 
						
						
						
					
					}
					else{
						System.out.println("Ya estaba desconectado");
						
					}
					
					
					
					
					
				} catch (SQLException e) {
					
					System.out.println("Desconectado incorrecto");
					e.printStackTrace();
				}
				
			
      }

}
