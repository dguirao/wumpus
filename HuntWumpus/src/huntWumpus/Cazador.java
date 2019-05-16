package huntWumpus;

public class Cazador extends Juego {
	
	static final char NORTE = 'N';
	static final char SUR = 'S';
	static final char ESTE = 'E';
	static final char OESTE = 'O';
	
	private int posXActual;
	private int posYActual;	
	private char orientacion;
	private String percepcionActCaz;
	private String percepcionSigCaz;
	private boolean oroEncontrado = false;
	private int numFlechas;
	private boolean wumpusVivo = true;	
	
	public Cazador(){}
	
	/**
	 * Método que gestiona el movimiento del cazador según el movimiento que haya realizado el usuario 
	 **/

	public String[][] moverCazador(String[][] tablero, String movUsuario){
		
		if (movUsuario.contains(Tablero.SALIR)){
			if (posXActual == tablero.length - 1 && posYActual == 0 && oroEncontrado) {
				Tablero.mensaje = "HAS GANADO!!!";
				Tablero.jugando = false;
			} else{
				Tablero.mensaje = "NO PUEDES SALIR SIN EL ORO";
			}
		}
		else if (movUsuario.contains(Tablero.AVANZAR)){
			if (orientacion == ESTE && posYActual < tablero.length - 1){
				avanzar(tablero, posXActual, posYActual+1,Tablero.CAZADOR_ESTE );				
			}
			else if (orientacion == NORTE && posXActual > 0 ){
				avanzar(tablero, posXActual-1, posYActual,Tablero.CAZADOR_NORTE );				
			}
			else if (orientacion == SUR && posXActual < tablero.length - 1){
				avanzar(tablero, posXActual+1,posYActual,Tablero.CAZADOR_SUR);
			}
			else if (orientacion == OESTE && posYActual > 0){
				avanzar(tablero, posXActual,posYActual-1,Tablero.CAZADOR_OESTE);
			}
			else {
				System.err.println("HAS CHOCADO CONTRA UN MURO");
			}
		} else if (movUsuario.contains(Tablero.GIRAR_IZQ)){
			switch (orientacion){
				case ESTE:
					girar(tablero, NORTE, Tablero.CAZADOR_ESTE, Tablero.CAZADOR_NORTE);
					break;
				case NORTE:
					girar(tablero, OESTE, Tablero.CAZADOR_NORTE, Tablero.CAZADOR_OESTE);
					break;
				case OESTE:
					girar(tablero, SUR, Tablero.CAZADOR_OESTE, Tablero.CAZADOR_SUR);
					break;
				case SUR:
					girar(tablero, ESTE, Tablero.CAZADOR_SUR, Tablero.CAZADOR_ESTE);
					break;
			}			
			
		} else if (movUsuario.contains(Tablero.GIRAR_DCHA)){
			switch (orientacion){
				case ESTE:
					girar(tablero, SUR, Tablero.CAZADOR_ESTE, Tablero.CAZADOR_SUR);
					break;
				case NORTE:
					girar(tablero, ESTE, Tablero.CAZADOR_NORTE, Tablero.CAZADOR_ESTE);
					break;
				case OESTE:
					girar(tablero, NORTE, Tablero.CAZADOR_OESTE, Tablero.CAZADOR_NORTE);
					break;
				case SUR:
					girar(tablero, OESTE, Tablero.CAZADOR_SUR, Tablero.CAZADOR_OESTE);
					break;
			}	
		}
		
		return tablero;
	}
	
	/**
	 * Método que avanza al cazador a la siguiente casilla	 
	 */
	
	public void avanzar(String[][] tablero, int posX, int posY, String iconoCazador){
		
		if (posXActual == tablero.length -1 && posYActual == 0){
			percepcionActCaz = Tablero.VACIO;			
		} else {
			percepcionActCaz = percepcionSigCaz;			
		}
		
		percepcionSigCaz =	obtenerPercepcion(tablero,posX,posY);
		
		tablero[posXActual][posYActual] = percepcionActCaz;	
		if (percepcionSigCaz.equals(Tablero.VACIO)){
			tablero[posX][posY] = iconoCazador ;
		} else {
			tablero[posX][posY] = iconoCazador + percepcionSigCaz;
		}
	
		posYActual = posY;
		posXActual = posX;
		
	}
	
	/**
	 * Método que gira al cazador	 
	 */
	
	public void girar(String[][] tablero, char orientacion, String iconoCazadorAct, String iconoCazadorSig){
		
		this.setOrientacion(orientacion);		
		if (tablero[posXActual][posYActual] == iconoCazadorAct){
			tablero[posXActual][posYActual] = iconoCazadorSig;
		} else {			
			tablero[posXActual][posYActual] = tablero[posXActual][posYActual].replace(iconoCazadorAct, iconoCazadorSig);	
		}
	}
	
	/**
	 * Método que obtiene la percepción de la posición del tablero que se le pasa como parámetro. 
	 */
	
	public String obtenerPercepcion(String[][] tablero, int posX, int posY){
		
		String percepcion = tablero[posX][posY];
		
		if (percepcion.contains(Tablero.CAZADOR_ESTE)){
			percepcion = tablero[posX][posY].replace(Tablero.CAZADOR_ESTE, "");
		}
		
		if (percepcion.contains(Tablero.WUMPUS)){
			if (wumpusVivo){
				Tablero.mensaje = "Te ha matado el wumpus. GAME OVER";
				Tablero.jugando = false;
			} else {
				Tablero.mensaje = "Estás de suerte, has caído en la casilla del wumpus pero esta muerto";
			}
		} 
		else if(percepcion.contains(Tablero.POZO)){
			Tablero.mensaje = "Has caído a un pozo. GAME OVER";
			Tablero.jugando = false;
		} 
		else if(percepcion.contains(Tablero.ORO)){
			if (!oroEncontrado){
				Tablero.mensaje = "HAS ENCONTRADO EL ORO. Regresa a la salida para ganar el juego";
				oroEncontrado = true;
			} else {
				Tablero.mensaje = "YA HAS RECOGIDO EL ORO. Regresa a la salida para ganar el juego ";
			}
			
		}
		else if (percepcion.contains(Tablero.VACIO)){
			Tablero.mensaje = "";	
		}
		
		if (percepcion.contains(Tablero.BRISA_POZO)){
			Tablero.mensaje = "SE PERCIBE LA BRISA DEL POZO";				
		}
		if (percepcion.contains(Tablero.BRILLO_ORO)){
			Tablero.mensaje = "SE PERCIBE EL BRILLO DEL ORO";
		}
		if (percepcion.contains(Tablero.HEDOR_WUMPUS)){
			Tablero.mensaje = "SE PERCIBE EL HEDOR DEL WUMPUS";
		}
		
		return percepcion;
	}
	
	/**
	 * Método que lanza las flechas del cazador 
	 */
	public void tirarFlecha(String[][] tablero){
		
		boolean wumpusEncontrado = false;
		
		if (numFlechas > 0){
			numFlechas--;
			if (orientacion == NORTE){			
				for (int i=posXActual-1; i >= 0; i--){					
					if (tablero[i][posYActual].equals(Tablero.WUMPUS)){
						wumpusEncontrado = true;
						break;
					}
				}
			} else if (orientacion == ESTE){ 
				for (int i=posYActual+1; i < tablero.length - 1; i++){
					if (tablero[posXActual][i].equals(Tablero.WUMPUS)){
						wumpusEncontrado = true;
						break;
					}
				}			
			} else if (orientacion == OESTE){
				for (int i=posYActual-1; i >= 0; i--){
					if (tablero[posXActual][i].equals(Tablero.WUMPUS)){
						wumpusEncontrado = true;
						break;
					}
				}	
				
			} else if (orientacion == SUR){
				for (int i=posXActual+1; i < tablero.length - 1; i++){				
					if (tablero[i][posYActual].equals(Tablero.WUMPUS)){
						wumpusEncontrado = true;
						break;
					}
				}
			}
			
			if (wumpusEncontrado){
				Tablero.mensaje = "HAS MATADO EL WUMPUS. PERCIBES SU GRITO";
				wumpusVivo = false;
			}	
			else { 
				Tablero.mensaje = "LA FLECHA HA ALCANZADO LA PARED. Flechas Restantes: " + numFlechas;
			}	
		} else {
			Tablero.mensaje = "NO QUEDAN FLECHAS";
		}
			
		
	}
		
	/**
	 * Getters y Setters
	 **/
	
	public int getPosXActual() {
		return posXActual;
	}

	public void setPosXActual(int posXActual) {
		this.posXActual = posXActual;
	}

	public int getPosYActual() {
		return posYActual;
	}

	public void setPosYActual(int posYActual) {
		this.posYActual = posYActual;
	}
	
	public char getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(char orientacion) {
		this.orientacion = orientacion;
	}
	
	public int getNumFlechas() {
		return numFlechas;
	}

	public void setNumFlechas(int numFlechas) {
		this.numFlechas = numFlechas;
	}
	
	public String getPercepcionActCaz() {
		return percepcionActCaz;
	}

	public void setPercepcionActCaz(String percepcionActCaz) {
		this.percepcionActCaz = percepcionActCaz;
	}

	public String getPercepcionSigCaz() {
		return percepcionSigCaz;
	}

	public void setPercepcionSigCaz(String percepcionSigCaz) {
		this.percepcionSigCaz = percepcionSigCaz;
	}

}
