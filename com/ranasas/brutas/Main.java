package ranasas.brutas;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {

	public static Rana[] vector = new Rana[5];
	public static Semaphore semaforo = new Semaphore(1);
	public static int intentos = 0; // Numero de saltos
	public static int intentosTotales = 0; // Numeros de veces se entran a los threads
	static Rana r1 = new Rana("R1>", 1, 0);
	static Rana r2 = new Rana("R2>", 1, 1);
	static Rana r3 = new Rana("<S1", -1, 2);
	static Rana r4 = new Rana("<S2", -1, 4);
	/*
	 * static Rana r5 = new Rana("<S2", -1, 5); static Rana r6 = new Rana("<S3", -1,
	 * 6);
	 */
	static Rana vacio = new Rana("_", 0, 0);
	public static Rana[] vectorGanador = new Rana[5];

	public static void main(String[] args) {

		vector[0] = r1;
		vector[1] = r2;
		vector[2] = vacio;
		vector[3] = r3;
		vector[4] = r4;

		/*
		 * vector[0] = r4; vector[1] = r5; vector[2] = r6; vector[3] = vacio; vector[4]
		 * = r1; vector[5] = r2; vector[6] = r3;
		 */

		vectorGanador[0] = r3;
		vectorGanador[1] = r4;
		vectorGanador[2] = vacio;
		vectorGanador[3] = r1;
		vectorGanador[4] = r2;

		r1.start();
		r2.start();
		r3.start();
		r4.start();
		vacio.start();
	}

	public static void mostrarPosiciones() {
		/*
		 * System.out.println(intentosTotales); System.out.println(intentos);
		 */
		for (int i = 0; i < vector.length; i++) {
			System.out.print(vector[i].caracterAMostrar + " ");
		}
		// Habria que checkear si todos llegaron e imprimir cuantos intentos e
		// intentosTotales se hicieron
	}

	public static void reiniciarIntentarSaltar() {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i].caracterAMostrar != "_" && !vector[i].llegue) {
				vector[i].intenteSaltar = false;
			}
		}
	}

	public static void comprobarBloqueo() {
		/* Arrays.stream(vector).forEach((n -> System.out.print(n.intenteSaltar))); */
		System.out.print(intentosTotales);
		if (Arrays.equals(vector, vectorGanador)) {
			System.out.print("Ganaste");
			r1.stop();
			r2.stop();
			r3.stop();
			r4.stop();
			/* r7.start(); */
		} else if (Arrays.stream(vector).allMatch(n -> n.intenteSaltar)) {
			intentosTotales++;
			vector[0] = r1;
			vector[1] = r2;
			vector[2] = vacio;
			vector[3] = r3;
			vector[4] = r4;
			reiniciarIntentarSaltar();

		}
		;
	}

}