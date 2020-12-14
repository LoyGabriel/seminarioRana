package ranasas;
import java.util.concurrent.Semaphore;

public class Main {
	public static Semaphore semaforo = new Semaphore(1);
	public static int intentos = 0; //Numero de saltos
	public static int intentosTotales = 0; //Numeros de veces se entran a los threads
	
	public static Rana r1 = new Rana("R1>",1,0);
	public static Rana r2 = new Rana("R2>",1,1);
	public static Rana r3 = new Rana("R3>",1,2);
	public static Rana r4 = new Rana("R4>",1,3);
	public static Rana vacio = new Rana("_",0,0);
	public static Rana r5 = new Rana("<S1",-1,5);
	public static Rana r6 = new Rana("<S2",-1,6);
	public static Rana r7 = new Rana("<S3",-1,7);
	public static Rana r8 = new Rana("<S4",-1,8);
	public static Rana[] vector= {r1,r2,r3,r4,vacio,r5,r6,r7,r8};
	
	public static void main(String[] args) {
		for(int i=0; i<vector.length;i++){
			vector[i].start();
		}
	}

	public static void mostrarPosiciones() {
		/*
		 * System.out.println(intentosTotales); System.out.println(intentos);
		 */
		for(int i=0; i<vector.length;i++){
			System.out.print(	vector[i].caracterAMostrar + " ");
		}
		//Habria que checkear si todos llegaron e imprimir cuantos intentos e intentosTotales se hicieron
	}

}