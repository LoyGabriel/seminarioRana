package ranasas;
import java.util.concurrent.Semaphore;

public class Main {
	
	public static Rana[] vector= new Rana[9];
	public static Semaphore semafono = new Semaphore(1);
	public static int intentos = 0; //Numero de saltos
	public static int intentosTotales = 0; //Numeros de veces se entran a los threads
	
	public static void main(String[] args) {
		Rana r1 = new Rana("1",1,0);
    	Rana r2 = new Rana("2",1,1);
    	Rana r3 = new Rana("3",1,2);
    	Rana r4 = new Rana("4",1,3);
    	Rana r5 = new Rana("5",-1,5);
    	Rana r6 = new Rana("6",-1,6);
    	Rana r7 = new Rana("7",-1,7);
    	Rana r8 = new Rana("8",-1,8);
    	Rana vacio = new Rana("_",0,0);
    	
    	vector[0] = r1;
    	vector[1] = r2;
    	vector[2] = r3;
    	vector[3] = r4;
    	vector[4] = vacio;
    	vector[5] = r5;
    	vector[6] = r6;
    	vector[7] = r7;
    	vector[8] = r8;

    	r1.start();
        r2.start();
        r3.start();
        r4.start();
        r5.start();
        r6.start();
        r7.start();
        r8.start();
        vacio.start();
	}

	public static void mostrarPosiciones() {
		/*
		 * System.out.println(intentosTotales); System.out.println(intentos);
		 */
		System.out.println(
				vector[0].caracterAMostrar + 
				vector[1].caracterAMostrar + 
				vector[2].caracterAMostrar + 
				vector[3].caracterAMostrar + 
				vector[4].caracterAMostrar + 
				vector[5].caracterAMostrar +
				vector[6].caracterAMostrar +
				vector[7].caracterAMostrar +
				vector[8].caracterAMostrar
				);
		//Habria que checkear si todos llegaron e imprimir cuantos intentos e intentosTotales se hicieron
	}

}