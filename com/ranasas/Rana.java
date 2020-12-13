package ranasas;

import java.util.Arrays;



public class Rana extends Thread {
	int movimiento;
	String nro;
	int nroi;
	boolean llegue = false;
	boolean seEstaMoviendo = false;
	boolean quieroMoverme = false;
    public Rana(String _nro,int _movimiento,int _nroi){
    	nro = _nro;
        movimiento=_movimiento;
        nroi = _nroi;
        
    }
    
    public boolean estaEnRango(int numero) {
    	return numero >= 0 && numero <= 6;
    }
    
    
    public void run(){
    	
    	while(nro != "_" && !llegue) {
			
    	try {
    		Main.semafono.acquire();
    		//Guardo donde esto
    		int posicion = Arrays.asList(Main.vector).indexOf(this);
    		//Caso donde podria salta un espacio
    		if( estaEnRango(posicion + movimiento) && Main.vector[posicion + movimiento].nro == "_") {
    			int posicionVacio = posicion + movimiento;
    			System.out.println("Soy la rana " + nro +" y puedo saltar 1 espacio");
    			
    			System.out.println(Math.abs(posicionVacio - nroi));
    			//Caso donde no le conviene
    			if(Math.abs(posicionVacio - nroi) == 4)
    			{
    				System.out.println("Ya llegue");
    				Main.vector[posicionVacio] = this;
    				Main.vector[posicion] = new Rana("_",0,0);
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
    				Main.vector[posicionVacio] = this;
    				Main.vector[posicion] = new Rana("_",0,0);
    			}
    		}
    		
    		
    		
    		//Caso podria saltar dos espacios 
    		else if( estaEnRango(posicion + (2*movimiento))&&Main.vector[posicion + (2*movimiento)].nro == "_" ) {
    			int posicionVacio = posicion + (2*movimiento);
    			System.out.println("Soy la rana " + nro +" y puedo saltar 2 espacios");
    		
    			System.out.println(Math.abs(posicionVacio - nroi));
    			
    			if(Math.abs(posicionVacio - nroi) == 4)
    			{
    				System.out.println("Ya llegue");
    				Main.vector[posicionVacio] = this;
    				Main.vector[posicion] = new Rana("_",0,0);
    				llegue = true;
    			}
    			
    			//Caso donde no le conviene
    			else if(
    			  (estaEnRango(posicionVacio+movimiento) &&  !(Math.abs(posicionVacio) - nroi == 4) && Main.vector[posicionVacio+movimiento].movimiento == movimiento) || 
    			  (estaEnRango(posicionVacio-movimiento) && Main.vector[posicionVacio-movimiento].movimiento == movimiento)) {
    				System.out.println("Pero no me conviene");
    			}
    			//Caso donde le conviene
      			else {
    				System.out.println("Y me conviene");
    				Main.vector[posicionVacio] = this;
    				Main.vector[posicion] = new Rana("_",0,0);
    			}
    		}
    		//Caso donde no puede saltar
    		else {
    			System.out.println("Soy la rana " + nro +" y no puedo saltar");
    			
    		}
    		Main.mostrarPosiciones();
    		try{
        		sleep(500);
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
    	
    	/*System.out.println("Soy la rana antes de entar" + nro);
    	while(true)
    	{
    	   	if(Main.ningunaRanaSeEstaMoviendo())
        	{
    	   		seEstaMoviendo = true;
    	   		System.out.println("Soy la rana despues de entar" + nro);
    	   		try{
            		sleep(5000);
            	} catch(InterruptedException e) {
            		System.out.println("ERROR");
            	}
            	 seEstaMoviendo = false;
        	}
    	}
        	*/
   /*     	Main.mostrarPosiciones();*/
        	
        

