package cliente;

import junit.framework.Assert;

import org.junit.Test;



public class ClaseDeTest {
@Test

public void pruebaTest(){
	Temporizador r=new Temporizador();
	Assert.assertEquals(4, r.prueba(1,3));
	Assert.assertEquals(9, r.prueba(6,3));
}
}
