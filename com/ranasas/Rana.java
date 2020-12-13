package ranasas;

import java.util.Arrays;

public class Rana extends Thread {
	int movimiento;
	String nro;
	int nroi;
	boolean llegue = false;

    public Rana(String _nro,int _movimiento,int _nroi){
    	nro = _nro;
        movimiento=_movimiento;
        nroi = _nroi; 
    }
    
    public boolean estaEnRango(int numero) {
    	return numero >= 0 && numero <= Main.vector.length - 1;
    }
    
    public void moverse(int aDonde, int desdeDonde) {
		Main.vector[aDonde] = this;
		Main.vector[desdeDonde] = new Rana("_",0,0);
    }
    
    
    public void run(){
    	while(nro != "_" && !llegue) {
			
    	try {
    		
    		Main.semafono.acquire();
    		
    		//Guardo donde estoy
    		int posicion = Arrays.asList(Main.vector).indexOf(this);
    		
    		//Caso donde podria salta un espacio
    		if( estaEnRango(posicion + movimiento) && Main.vector[posicion + movimiento].nro == "_") {
    			int posicionVacio = posicion + movimiento;
    			System.out.println("Soy la rana " + nro +" y puedo saltar 1 espacio");

    			//Caso donde no le conviene
    			if(Math.abs(posicionVacio - nroi) == ((Main.vector.length + 1) / 2))
    			{	
    				moverse(posicionVacio, posicion);
    				System.out.println("Ya llegue");
    				llegue = true;
    			}
    			
    			else if
    				(
    				(estaEnRango(posicionVacio+movimiento) &&   Main.vector[posicionVacio+movimiento].movimiento == movimiento) ||
    				((estaEnRango(posicionVacio+movimiento) && estaEnRango(posicion-movimiento) && Main.vector[posicionVacio+movimiento].movimiento == Main.vector[posicion-movimiento].movimiento) 
    				)
    				){
    				System.out.println("Pero no me conviene");
    			}
    			//Caso donde conviene
    			else {
    				System.out.println("Y me conviene");
    				moverse(posicionVacio, posicion);
    			}
    		}

    		//Caso podria saltar dos espacios 
    		else if( estaEnRango(posicion + (2*movimiento)) && Main.vector[posicion + (2*movimiento)].nro == "_" ) {
    			int posicionVacio = posicion + (2*movimiento);
    			System.out.println("Soy la rana " + nro +" y puedo saltar 2 espacios");
    			
    			if(Math.abs(posicionVacio - nroi) == ((Main.vector.length+1) / 2))
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
    			System.out.println("Soy la rana " + nro +" y no puedo saltar");
    			
    		}
    		Main.mostrarPosiciones();
    		try{
        		sleep(100);
        	} catch(InterruptedException e) {
        		System.out.println("ERROR");
        	}
    		
    		Main.semafono.release();
    		System.out.println("------");
    		
    	}catch(InterruptedException e) {
    		
    	}
    	
    	}
	}
}