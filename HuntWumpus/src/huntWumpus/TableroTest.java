package huntWumpus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableroTest {


	private String[][] tablero;
	private Tablero t; 
	
	@Before
	public void setUp() throws Exception {
		t = new Tablero();
		t.setTamano(5);
		int tamano = t.getTamano();
		tablero = new String[tamano][tamano];
		t.setTablero(tablero);
		tablero = t.getTablero();
	}
	
	@Test
	public void introducirElementoPozoTablero(){
		
		int posicionX = 0;
		int posicionY = 0;	
		t.introducirElementoTablero(1, Tablero.POZO, Tablero.BRISA_POZO);
		
		
		for (int i=0; i<tablero.length; i++){
        	for (int j=0; j<tablero.length; j++){
        		if (tablero[i][j] == Tablero.POZO){
        			posicionX = i;
        			posicionY = j;
        		}
        		
        	}
		}
		
		Assert.assertEquals(tablero[posicionX][posicionY], Tablero.POZO);
		if (tablero.length - 1 < posicionY){
			Assert.assertEquals(tablero[posicionX][posicionY+1], Tablero.BRISA_POZO);	
		}
		if (posicionY > 0){
			Assert.assertEquals(tablero[posicionX][posicionY-1], Tablero.BRISA_POZO);
		}
		if (tablero.length - 1 < posicionX){
			Assert.assertEquals(tablero[posicionX+1][posicionY], Tablero.BRISA_POZO);
		}
		
		if (posicionX > 0){
			Assert.assertEquals(tablero[posicionX-1][posicionY], Tablero.BRISA_POZO);
		}
		
	}
	
	@Test
	public void introducirElementoOroTablero(){
		
		int posicionX = 0;
		int posicionY = 0;	
		t.introducirElementoTablero(1, Tablero.ORO, Tablero.BRILLO_ORO);		
		for (int i=0; i<tablero.length; i++){
        	for (int j=0; j<tablero.length; j++){
        		if (tablero[i][j] == Tablero.ORO){
        			posicionX = i;
        			posicionY = j;
        		}
        	}
		}
		
		Assert.assertEquals(tablero[posicionX][posicionY], Tablero.ORO);
		if (tablero.length - 1 < posicionY){
			Assert.assertEquals(tablero[posicionX][posicionY+1], Tablero.BRILLO_ORO);	
		}
		if (posicionY > 0){
			Assert.assertEquals(tablero[posicionX][posicionY-1], Tablero.BRILLO_ORO);
		}
		if (tablero.length - 1 < posicionX){
			Assert.assertEquals(tablero[posicionX+1][posicionY], Tablero.BRILLO_ORO);
		}
		
		if (posicionX > 0){
			Assert.assertEquals(tablero[posicionX-1][posicionY], Tablero.BRILLO_ORO);
		}
		
	}
	
	@Test
	public void introducirElementoWumpusTablero(){
		
		int posicionX = 0;
		int posicionY = 0;	
		t.introducirElementoTablero(1, Tablero.WUMPUS, Tablero.HEDOR_WUMPUS);		
		for (int i=0; i<tablero.length; i++){
        	for (int j=0; j<tablero.length; j++){
        		if (tablero[i][j] == Tablero.WUMPUS){
        			posicionX = i;
        			posicionY = j;
        		}
        	}
		}
		
		Assert.assertEquals(tablero[posicionX][posicionY], Tablero.WUMPUS);
		if (tablero.length - 1 < posicionY){
			Assert.assertEquals(tablero[posicionX][posicionY+1], Tablero.HEDOR_WUMPUS);	
		}
		if (posicionY > 0){
			Assert.assertEquals(tablero[posicionX][posicionY-1], Tablero.HEDOR_WUMPUS);
		}
		if (tablero.length - 1 < posicionX){
			Assert.assertEquals(tablero[posicionX+1][posicionY], Tablero.HEDOR_WUMPUS);
		}
		
		if (posicionX > 0){
			Assert.assertEquals(tablero[posicionX-1][posicionY], Tablero.HEDOR_WUMPUS);
		}
		
	}


}
