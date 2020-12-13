package ranasas;

import java.util.Arrays;

public class Rana extends Thread {
	int movimiento;
	String caracterAMostrar;
	int posicionInicial;
	boolean llegue = false;

    public Rana(String _caracter,int _movimiento,int _posicionInicial){
    	caracterAMostrar = _caracter;
        movimiento=_movimiento;
        posicionInicial = _posicionInicial; 
    }
    
    /**Retorna si el valor esta dentro de la longitud del vector */
    public boolean estaEnRango(int numero) {
    	return numero >= 0 && numero <= Main.vector.length - 1;
    }

    /**Ejecuta el cambio de posiciones entre la rana que salto y el lugar vacio*/
    public void moverse(int aDonde, int desdeDonde) {
    	Main.intentos ++;
		Main.vector[aDonde] = this;
		Main.vector[desdeDonde] = new Rana("_",0,0);
    }
    
    
    public void run(){
    	while(caracterAMostrar != "_" && !llegue) {
    	try {
    		Main.semafono.acquire();
    		/**Guardo donde estoy*/
    		int posicion = Arrays.asList(Main.vector).indexOf(this);

    		/**Caso donde podria salta un espacio*/
    		if( this.puedeSaltarUnCasillero(posicion)) {
    			int posicionVacio = posicion + movimiento;
    			System.out.println("Soy la rana " + caracterAMostrar +" y puedo saltar 1 espacio");

    			/**Caso en cual saltaria a su posicion ganadora*/
    			if(this.llegaAPosicionGanadora(posicionVacio)){
    				moverse(posicionVacio, posicion);
					print("Ya llegue");
    				llegue = true;
    			}
				/**Caso donde no le conviene*/
    			else if (this.convieneSaltar(posicionVacio, posicion)){
					print("Pero no me conviene");
    			}
				/**Caso donde le conviene*/
    			else {
					print("Y me conviene");
    				moverse(posicionVacio, posicion);
    			}
    		}

    		//Caso donde podria saltar dos espacios 
    		else if(puedeSaltarDosCasilleros(posicion)) {
    			int posicionVacio = posicion + (2*movimiento);
    			print("Soy la rana " + caracterAMostrar +" y puedo saltar 2 espacios");
    			
    			//Caso en cual saltaria a su posicion ganadora
    			if(llegaAPosicionGanadora(posicionVacio)){
    				moverse(posicionVacio, posicion);
					print("Ya llegue");
    				llegue = true;
    			}
    			//Caso donde no le conviene
    			else if(convieneSaltarDos(posicionVacio)) {
    				print("Pero no me conviene");
    			}
    			//Caso donde le conviene
      			else {
    				print("Y me conviene");
    				moverse(posicionVacio, posicion);
    			}
    		}
    		//Caso donde no puede saltar
    		else {
    			print("Soy la rana " + caracterAMostrar +" y no puedo saltar");
    		}
    		Main.mostrarPosiciones();
    		try{
        		sleep(500);
        	} catch(InterruptedException e) {
        		print("ERROR");
        	}
    		Main.intentosTotales++;
    		Main.semafono.release();
    		print("------");
    		
    		}catch(InterruptedException e) {}

    	}
	}

	private boolean convieneSaltarDos(int posicionVacio) {
    	return  (estaEnRango(posicionVacio+movimiento) && Main.vector[posicionVacio+movimiento].movimiento == movimiento) ||
				(estaEnRango(posicionVacio-movimiento) && Main.vector[posicionVacio-movimiento].movimiento == movimiento);
	}

	private boolean puedeSaltarDosCasilleros(int posicion) {
		return estaEnRango(posicion + (2*movimiento)) && Main.vector[posicion + (2*movimiento)].caracterAMostrar == "_";
	}

//TODO: completar la documentacion aca que no entendi esta validacion
	/**Metodo clave en la logica, determina si puede saltar o no.
	 * verifica si esta en rango (si una vez que salta va a seguir dentro del vector)y si en la siguiente posicion donde se ubicara esta vacio o no
	 * O
	 * verifica si esta en rango  y
	 * */
	private boolean convieneSaltar(int posicionVacio, int posicion) {
		return (estaEnRango(posicionVacio+movimiento) && Main.vector[posicionVacio+movimiento].movimiento == movimiento)
				|| (estaEnRango(posicionVacio+movimiento)
				&& estaEnRango(posicion-movimiento)
				&& Main.vector[posicionVacio+movimiento].movimiento == Main.vector[posicion-movimiento].movimiento);
	}

	private boolean llegaAPosicionGanadora(int posicionVacio) {
    	return Math.abs(posicionVacio - posicionInicial) == ((Main.vector.length + 1) / 2);
	}

	private boolean puedeSaltarUnCasillero(int posicion){
		return estaEnRango(posicion + movimiento) && Main.vector[posicion + movimiento].caracterAMostrar == "_";
	}

	private void print(String mensaje){
    	System.out.println(mensaje);
	}
}