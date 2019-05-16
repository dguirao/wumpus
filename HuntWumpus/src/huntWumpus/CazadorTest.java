package huntWumpus;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CazadorTest {
	
	private Cazador caz = new Cazador();
	private String[][] tablero;

	@Before
	public void setUp() throws Exception {
		caz.setPosXActual(1);
		caz.setPosYActual(1);
		tablero = new String[5][5];
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void girarNorteTest() {
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.CAZADOR_ESTE;
		caz.girar(tablero, Cazador.NORTE, Tablero.CAZADOR_ESTE, Tablero.CAZADOR_NORTE);
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.CAZADOR_NORTE);
			
	}
	
	@Test
	public void girarOesteTest() {
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.CAZADOR_SUR;
		caz.girar(tablero, Cazador.OESTE, Tablero.CAZADOR_SUR, Tablero.CAZADOR_OESTE);
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.CAZADOR_OESTE);
			
	}
	
	@Test
	public void girarEsteTest() {
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.CAZADOR_SUR;
		caz.girar(tablero, Cazador.ESTE, Tablero.CAZADOR_SUR, Tablero.CAZADOR_ESTE);
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.CAZADOR_ESTE);
			
	}
	
	@Test
	public void giraSurTest() {
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.CAZADOR_OESTE;
		caz.girar(tablero, Cazador.SUR, Tablero.CAZADOR_OESTE, Tablero.CAZADOR_SUR);
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.CAZADOR_SUR);
			
	}
	
	@Test 
	public void avanzarTest(){

		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.CAZADOR_ESTE;
		tablero[caz.getPosXActual()][caz.getPosYActual()+1] = Tablero.VACIO;
		caz.setPercepcionSigCaz(Tablero.VACIO);
		caz.avanzar(tablero, caz.getPosXActual(), caz.getPosXActual()+1, Tablero.CAZADOR_ESTE);
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.CAZADOR_ESTE);
		
	}
	
	@Test 
	public void obtenerPercepcionWumpusTest(){
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.WUMPUS;
		caz.obtenerPercepcion(tablero, caz.getPosXActual(), caz.getPosYActual());
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.WUMPUS);
	}
	
	@Test 
	public void obtenerPercepcionOroTest(){
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.ORO;
		caz.obtenerPercepcion(tablero, caz.getPosXActual(), caz.getPosYActual());
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.ORO);
	}
	
	@Test 
	public void obtenerPercepcionPozoTest(){
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.POZO;
		caz.obtenerPercepcion(tablero, caz.getPosXActual(), caz.getPosYActual());
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.POZO);
	}
	
	@Test 
	public void obtenerPercepcionVacioTest(){
		
		tablero[caz.getPosXActual()][caz.getPosYActual()] = Tablero.VACIO;
		caz.obtenerPercepcion(tablero, caz.getPosXActual(), caz.getPosYActual());
		Assert.assertEquals(tablero[caz.getPosXActual()][caz.getPosYActual()], Tablero.VACIO);
	}	
}
