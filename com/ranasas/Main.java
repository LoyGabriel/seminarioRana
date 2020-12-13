package ranasas;
import com.company.animales.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
	
	public static Rana[] vector= new Rana[7];
	public static Semaphore semafono = new Semaphore(1);
	public static int intentos = 0;
	
	public static void main(String[] args) {
		Rana r1 = new Rana("R",1,0);
    	Rana r2 = new Rana("R",1,1);
    	Rana r3 = new Rana("R",1,2);
    	Rana r4 = new Rana("S",-1,4);
    	Rana r5 = new Rana("S",-1,5);
    	Rana r6 = new Rana("S",-1,6);
    	Rana vacio = new Rana("_",0,0);
    	
    	vector[0] = r1;
    	vector[1] = r2;
    	vector[2] = r3;
    	vector[3] = vacio;
    	vector[4] = r4;
    	vector[5] = r5;
    	vector[6] = r6;

    	r1.start();
        r2.start();
        r3.start();
        r4.start();
        r5.start();
        r6.start();
        vacio.start();
	}

	public static void mostrarPosiciones() {
		System.out.println(
				vector[0].nro + 
				vector[1].nro + 
				vector[2].nro + 
				vector[3].nro + 
				vector[4].nro + 
				vector[5].nro +
				vector[6].nro 
				);
	}

}