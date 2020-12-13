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
    public void moverse(int desdeDonde, int aDonde) {
    	Main.intentos ++;
		Main.vector[aDonde] = this;
		Main.vector[desdeDonde] = new Rana("_",0,0);
    }
    
    
    public void run(){
    	while(caracterAMostrar != "_" && !llegue) {
    	try {
    		Main.semafono.acquire();
    		/**Guardo donde estoy*/
    		int miPosicion = Arrays.asList(Main.vector).indexOf(this);

    		/**Caso donde podria salta un espacio*/
    		if(this.puedoSaltarUnCasilleroDesde(miPosicion)) {
    			int dondeQuieroSaltar = miPosicion + movimiento;
    			System.out.println("Soy la rana " + caracterAMostrar +" y puedo saltar 1 espacio");

    			/**Caso en cual saltaria a su posicion ganadora*/
    			if(this.esLaPosicionGanadora(dondeQuieroSaltar)){
    				moverse(miPosicion, dondeQuieroSaltar);
					print("Ya llegue");
    				llegue = true;
    			}
				/**Caso donde no le conviene*/
    			else if (this.noLeConvieneSaltarUnaPosicion(dondeQuieroSaltar, miPosicion)){
					print("Pero no me conviene");
    			}
				/**Caso donde le conviene*/
    			else {
					print("Y me conviene");
    				moverse(miPosicion,dondeQuieroSaltar);
    			}
    		}

    		//Caso donde podria saltar dos espacios 
    		else if(puedeSaltarDosCasilleros(miPosicion)) {
    			int dondeQuieroSaltar = miPosicion + (2*movimiento);
    			print("Soy la rana " + caracterAMostrar +" y puedo saltar 2 espacios");
    			
    			//Caso en cual saltaria a su posicion ganadora
    			if(esLaPosicionGanadora(dondeQuieroSaltar)){
    				moverse(miPosicion,dondeQuieroSaltar);
					print("Ya llegue");
    				llegue = true;
    			}
    			//Caso donde no le conviene
    			else if(noLeConvieneSaltarDos(dondeQuieroSaltar)) {
    				print("Pero no me conviene");
    			}
    			//Caso donde le conviene
      			else {
    				print("Y me conviene");
    				moverse(miPosicion,dondeQuieroSaltar);
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


    //Dos casilleros
	private boolean puedeSaltarDosCasilleros(int miPosicion) {
		int dondeQuiereSaltar = miPosicion + (2*movimiento);
		return estaEnRango(dondeQuiereSaltar) && Main.vector[dondeQuiereSaltar].caracterAMostrar == "_";
	}

	/**Metodo clave en la logica, determina si puede saltar o no.
	 * verifica si esta en rango (si una vez que salta va a seguir dentro del vector)y si en la siguiente posicion donde se encuentra uno del mismo tipo o distinto
	 * O
	 * verifica si esta en rango  y si lo que quiero saltar y tengo atras es del mismo tipo
	 * */
	private boolean noLeConvieneSaltarDos(int dondeQuieroSaltar) {
    	return  (estaEnRango(dondeQuieroSaltar+movimiento) && Main.vector[dondeQuieroSaltar+movimiento].movimiento == movimiento) ||
				(estaEnRango(dondeQuieroSaltar-movimiento) && Main.vector[dondeQuieroSaltar-movimiento].movimiento == movimiento);
	}


	
	
	//Un casillero
	
	private boolean puedoSaltarUnCasilleroDesde(int posicion){
		int dondeQuiereSaltar = posicion + movimiento;
		return estaEnRango(dondeQuiereSaltar) && Main.vector[dondeQuiereSaltar].caracterAMostrar == "_";
	}

	/**Metodo clave en la logica, determina si puede saltar o no.
	 * verifica si esta en rango (si una vez que salta va a seguir dentro del vector)y si en la siguiente posicion donde se encuentra uno del mismo tipo o distinto
	 * O
	 * verifica si esta en rango  y si lo que quiero saltar y tengo atras es del mismo tipo
	 * */
	private boolean noLeConvieneSaltarUnaPosicion(int dondeQuieroSaltar, int miPosicion) {
		return estaEnRango(dondeQuieroSaltar+movimiento) && Main.vector[dondeQuieroSaltar+movimiento].movimiento == movimiento
		          || (estaEnRango(miPosicion-movimiento) && Main.vector[dondeQuieroSaltar+movimiento].movimiento == Main.vector[miPosicion-movimiento].movimiento);
	}

	
	private boolean esLaPosicionGanadora(int dondeQuieroSaltar) {
		int saltosQueDi = Math.abs(dondeQuieroSaltar - posicionInicial);
		int saltosQueTengoQueDar = (Main.vector.length + 1) / 2;
    	return saltosQueDi == saltosQueTengoQueDar;
	}


	private void print(String mensaje){
    	System.out.println(mensaje);
	}
}