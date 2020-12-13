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
    
    
    public boolean estaEnRango(int numero) {
    	return numero >= 0 && numero <= Main.vector.length - 1;
    }
    
    public void moverse(int aDonde, int desdeDonde) {
    	Main.intentos ++;
		Main.vector[aDonde] = this;
		Main.vector[desdeDonde] = new Rana("_",0,0);
    }
    
    
    public void run(){
    	while(caracterAMostrar != "_" && !llegue) {
			
    	try {
    		Main.semafono.acquire();
    		//Guardo donde estoy
    		int posicion = Arrays.asList(Main.vector).indexOf(this);
    		
    		//Caso donde podria salta un espacio
    		if( estaEnRango(posicion + movimiento) && Main.vector[posicion + movimiento].caracterAMostrar == "_") {
    			int posicionVacio = posicion + movimiento;
    			System.out.println("Soy la rana " + caracterAMostrar +" y puedo saltar 1 espacio");

    			//Caso en cual saltaria a su posicion ganadora
    			if(Math.abs(posicionVacio - posicionInicial) == ((Main.vector.length + 1) / 2))
    			{	
    				moverse(posicionVacio, posicion);
    				System.out.println("Ya llegue");
    				llegue = true;
    			}
    			
    			//Caso donde no le conviene
    			else if
    				(
    				(estaEnRango(posicionVacio+movimiento) &&   Main.vector[posicionVacio+movimiento].movimiento == movimiento) ||
    				((estaEnRango(posicionVacio+movimiento) && estaEnRango(posicion-movimiento) && Main.vector[posicionVacio+movimiento].movimiento == Main.vector[posicion-movimiento].movimiento) 
    				)
    				){
    				System.out.println("Pero no me conviene");
    			}
    			//Caso donde le conviene
    			else {
    				System.out.println("Y me conviene");
    				moverse(posicionVacio, posicion);
    			}
    		}

    		//Caso donde podria saltar dos espacios 
    		else if( estaEnRango(posicion + (2*movimiento)) && Main.vector[posicion + (2*movimiento)].caracterAMostrar == "_" ) {
    			int posicionVacio = posicion + (2*movimiento);
    			System.out.println("Soy la rana " + caracterAMostrar +" y puedo saltar 2 espacios");
    			
    			//Caso en cual saltaria a su posicion ganadora
    			if(Math.abs(posicionVacio - posicionInicial) == ((Main.vector.length+1) / 2))
    			{	
    				moverse(posicionVacio, posicion);
    				System.out.println("Ya llegue");
    				llegue = true;
    			}
    			
    			//Caso donde no le conviene
    			else if(
    			  (estaEnRango(posicionVacio+movimiento) && Main.vector[posicionVacio+movimiento].movimiento == movimiento) || 
    			  (estaEnRango(posicionVacio-movimiento) && Main.vector[posicionVacio-movimiento].movimiento == movimiento)) {
    				System.out.println("Pero no me conviene");
    			}
    			//Caso donde le conviene
      			else {
    				System.out.println("Y me conviene");
    				moverse(posicionVacio, posicion);
    			}
    		}
    		//Caso donde no puede saltar
    		else {
    			System.out.println("Soy la rana " + caracterAMostrar +" y no puedo saltar");
    			
    		}
    		Main.mostrarPosiciones();
    		try{
        		sleep(500);
        	} catch(InterruptedException e) {
        		System.out.println("ERROR");
        	}
    		Main.intentosTotales++;
    		Main.semafono.release();
    		System.out.println("------");
    		
    		}catch(InterruptedException e) {}
   
    	}
	}
}