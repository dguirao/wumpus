package huntWumpus;

import java.util.Scanner;

public class Tablero extends Juego {
	
	static final String ORO = "O";
	static final String BRILLO_ORO = "BO";
	static final String POZO = "P";
	static final String BRISA_POZO = "B";
	static final String WUMPUS = "W";
	static final String HEDOR_WUMPUS = "H";
	static final String CAZADOR_ESTE = ">";
	static final String CAZADOR_OESTE = "<";
	static final String CAZADOR_NORTE = "^";
	static final String CAZADOR_SUR = "v";
	static final String VACIO = "| |";
	static final String AVANZAR = "A";
	static final String GIRAR_IZQ = "I";
	static final String GIRAR_DCHA = "D";
	static final String TIRAR_FLECHA = "F";
	static final String SALIR = "S";
	
	
	private int tamano;
	private int numPozos;
	private String[][] tablero;
	private String[][] tableroOculto;
	static boolean jugando=true;
	static String mensaje="---";
	 
	public Tablero(){}
	
	public void iniciar(){
		Scanner linea = new Scanner (System.in);
		System.out.println("Introduce el numero de filas/columnas del tablero: ");		
		tamano = Integer.parseInt(linea.nextLine());
		
		boolean numPozosValidos = true;
		while (numPozosValidos){
			System.out.println("Introduce el numero de pozos: ");
			numPozos = Integer.parseInt(linea.nextLine());
			if (numPozos <= (tamano / 2)){
				numPozosValidos = false;
			} else {
				System.out.println("Demasiados pozos para las dimensiones del tablero (el numero de pozos debe ser como máximo la mitad de las filas y columnas)");
			}			
		}
		
		System.out.println("Introduce el numero de flechas del cazador: ");
		
		tablero = new String[tamano][tamano];
		tableroOculto = new String[tamano][tamano];
		
		Cazador caz = new Cazador();
		int flechas =Integer.parseInt(linea.nextLine());
		caz.setNumFlechas(flechas);
		caz.setOrientacion(Cazador.ESTE);
		caz.setPosYActual(0);
		caz.setPosXActual(tablero.length- 1);	
		
		System.out.println("¿Desea visualizar los pozos, el oro y el wumpus?: S:SI, N:NO");
		String visualizar = linea.nextLine().toUpperCase();
		
        introducirElementoTablero(numPozos, POZO, BRISA_POZO);
        introducirElementoTablero(1, WUMPUS, HEDOR_WUMPUS);
        introducirElementoTablero(1, ORO, BRILLO_ORO);
        introducirElementoTablero(1, CAZADOR_ESTE,null);
        
        
        while (jugando){       
        	pintarTablero(visualizar);
  
        	System.out.println("--------------------------------\n\n");        
    		System.out.println(AVANZAR +": AVANZAR ");
    		System.out.println(GIRAR_IZQ +": GIRAR IZQUIERDA ");
    		System.out.println(GIRAR_DCHA +": GIRAR DERECHA ");
    		System.out.println(TIRAR_FLECHA +": TIRAR FLECHA ");
    		System.out.println(SALIR + ": SALIR (cuando estés en la casilla de salida con el oro) \n");
    		System.out.println("¿Qué movimiento desea realizar?: ");    		
    		
    		String movUsuario = linea.nextLine();
    		System.out.println("\n");
    		if (movUsuario.toUpperCase().contains(Tablero.TIRAR_FLECHA)){
    			caz.tirarFlecha(tablero);    			
    		} 
    		else {
    			tablero = caz.moverCazador(tablero, movUsuario.toUpperCase());
    		} 	
    		if (jugando){
    			System.err.println(mensaje+"\n");
    		}
    		
        }
        System.err.println(mensaje);
    	linea.close();
		
	}
	
	/**
	 * Método que imprime por pantalla el tablero 
	 */
	
	private void pintarTablero(String visualizar){
		
        for (int i=0; i<tablero.length; i++){
        	for (int j=0; j<tablero.length; j++){
        		if (tablero[i][j] == null){
        			tablero[i][j]= VACIO;
        		}
        		if (visualizar.equals("S")){
        			System.out.print(tablero[i][j] + "\t");
        		}
            }
        	if (visualizar.equals("S")){
              System.out.println("\n");
        	} 
        }
        System.out.println("--------------------------------\n");
        if (visualizar.equals("N")){
        	pintarTableroOculto();
        }

	}
	
	/**
	 * Método que imprime por pantalla el tablero ocultando el pozo, el oro y el wumpus
	 */
	
	private void pintarTableroOculto(){
		for (int i=0; i<tablero.length; i++){	
	    	for (int j=0; j<tablero.length; j++){
	    		if (tableroOculto[i][j] == null || tablero[i][j].equals(VACIO)){ 
	    			tableroOculto[i][j]= VACIO;	    		
	    		} else if (tablero[i][j].contains(CAZADOR_ESTE)){
	    			tableroOculto[i][j]= CAZADOR_ESTE;
	    		} else if (tablero[i][j].contains(CAZADOR_OESTE)){
	    			tableroOculto[i][j]= CAZADOR_OESTE;
	    		} else if (tablero[i][j].contains(CAZADOR_NORTE)){
	    			tableroOculto[i][j]= CAZADOR_NORTE;
	    		} else if (tablero[i][j].contains(CAZADOR_SUR)){
	    			tableroOculto[i][j]= CAZADOR_SUR;
	    		} else {
	    			tableroOculto[i][j]= VACIO;
	    		}
	    		
	            System.out.print(tableroOculto[i][j] + "\t");
	        }
	          System.out.println("\n");
	    }
	}	
		
	
	/**
	 **	Método que introduce y posiciona el elemento que se le pasa en el tablero 
	 **/
	
	public void introducirElementoTablero(int numElementos, String caracterElemento, String caracterAdyacente){
		
		int posX = 0;
		int posY = 0;
		
		if (caracterElemento.equals(CAZADOR_ESTE)){
			if (tablero[tablero.length - 1][0] == null){
				tablero[tablero.length - 1][0] = CAZADOR_ESTE;
				tableroOculto[tablero.length - 1][0] = CAZADOR_ESTE;
			} else {
				tablero[tablero.length - 1][0] += CAZADOR_ESTE;
			}
			
		} else {
			for (int i=0; i < numElementos; i++){
				boolean generarNum = true;				
				while (generarNum){
					
					posX = (int) (Math.random() * (tamano - 1));
					posY = (int) (Math.random() * (tamano - 1));
					
					if (tablero[posX][posY] == null && posX < tablero.length-1 && posY > 0){
						/*if ((posX != tablero.length - 2 && posY == 0) ||
							 posX == tablero.length - 1 && posY	== 1)*/
						tablero[posX][posY] = caracterElemento;
						generarNum = false;
					} 
					
				}
				
				if ((posY - 1) >= 0 && tablero[posX][posY - 1] != caracterElemento){
					if (tablero[posX][posY-1] != null){
						tablero[posX][posY-1]  += caracterAdyacente;
					} else {				
						tablero[posX][posY-1] = caracterAdyacente;
					}	
				}
				if ((posX - 1) >= 0 && tablero[posX-1][posY] != caracterElemento){
					if (tablero[posX-1][posY] != null){
						tablero[posX-1][posY] += caracterAdyacente;
					} else {
						tablero[posX-1][posY] = caracterAdyacente;
					}
				}
				if ((posY + 1) <= tamano && tablero[posX][posY+1] != caracterElemento){
					if (tablero[posX][posY+1] != null){
						tablero[posX][posY+1]  += caracterAdyacente;
					} else {
						tablero[posX][posY+1] = caracterAdyacente;
					}	
				}
				if ((posX + 1) <= tamano&& tablero[posX+1][posY] != caracterElemento){
					if (tablero[posX+1][posY] != null){
						tablero[posX+1][posY]  += caracterAdyacente;
					} else {
						tablero[posX+1][posY] = caracterAdyacente;
					}	
				}
				
			}
		}			
			
	}
	
	/**
	 * Getters y Setters
	 **/
	
	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}
		
}
