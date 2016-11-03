

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posici�n guarda el n�mero -1
 * Si no hay una mina, se guarda cu�ntas minas hay alrededor.
 * Almacena la puntuaci�n de la partida
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {
	
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Inicializamos una nueva partida
		inicializarPartida();
		
	}
	
	
	/**M�todo para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habr� inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cu�ntas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		//Borro del tablero la informaci�n que pudiera haber anteriormente (los pongo todos a cero):
				for (int i = 0; i < tablero.length; i++) {
					for (int j = 0; j < tablero[0].length; j++) {
						tablero[i][j]= 0;
					}
				} 
		//Me creo LADO_TABLERO*LADO_TABLERO n�meros en un array list, uno para cada una de las posiciones del tablero:
		
		//Saco 20 posiciones sin repetir del array y les coloco una mina en el tablero:
		int fila, columna, cuentaMinas = 0;
		do {
			fila = (int) (Math.random()*10);
			columna= (int) (Math.random()*10);
			if (tablero[fila][columna] != -1) {
				tablero[fila][columna] = -1;
				cuentaMinas++;
			}  // si esa [f][c] ya ha salido, repetimos la vuelta al bucle
			
		} while (cuentaMinas < 20); //Si en la casilla ya hay una mina repite
		
		//Calculo para todas las posiciones que no tienen minas, cu�ntas minas hay alrededor.
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (tablero[i][j] != -1) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
		
		//Pongo la puntuaci�n a cero:
		puntuacion = 0;
		
	}
	
	/**C�lculo de las minas adjuntas:
	 * Para calcular el n�mero de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdr�n LADO_TABLERO-1.
	 * Por lo tanto, como mucho la i y la j valdr�n como poco 0.
	 * @param i: posici�n verticalmente de la casilla a rellenar
	 * @param j: posici�n horizontalmente de la casilla a rellenar
	 * @return : El n�mero de minas que hay alrededor de la casilla [i][j]
	 */
	private int calculoMinasAdjuntas(int i, int j){
		int minasAdjuntas = 0, filaSuperior, filaInferior, columnaIzq, columnaDer;
		filaSuperior = i-1;
		filaInferior = i+1;
		columnaIzq = j-1;
		columnaDer = j+1;
		
		if (j-1 < 0) columnaIzq = 0; 
		if (j+1 > LADO_TABLERO-1) columnaDer = LADO_TABLERO -1; 

		if (filaSuperior >= 0) {	
		for (int k = columnaIzq; k <= columnaDer; k++) {
			if (tablero[filaSuperior][k] == -1) 
				minasAdjuntas++;
			}
		}
		
		for (int k = columnaIzq; k <= columnaDer; k++) {
			if (tablero[i][k] == -1) {
				minasAdjuntas++;
		}
		}	
		if (filaInferior <= LADO_TABLERO -1) {
			for (int k = columnaIzq; k <= columnaDer; k++) {
				if (tablero[filaInferior][k] == -1) {
					minasAdjuntas++;
				}
			}
		}	
		return minasAdjuntas;
	}
	
	/**
	 * M�todo que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el GestorJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posici�n verticalmente de la casilla a abrir
	 * @param j: posici�n horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		if (tablero[i][j] != -1) {
			puntuacion++;
			return true;
		} else return false;
	}
	
	
	
	/**
	 * M�todo que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
	
	return puntuacion == LADO_TABLERO*LADO_TABLERO - MINAS_INICIALES;
	}
	
	
	/**
	 * M�todo que pinta por pantalla toda la informaci�n del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: "+puntuacion);
	}

	public int getMinasAlrededor (int i, int j) {
		return tablero[i][j];
	}
	
	/**
	 * M�todo que devuelve la puntuaci�n actual
	 * @return Un entero con la puntuaci�n actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}
