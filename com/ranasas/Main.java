package ranasas;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {
	public static Semaphore semaforo = new Semaphore(1);
	public static int saltos = 0; // Numero de saltos
	public static int intentos = 0; // Numeros de veces se entran a los threads

	public static Rana r1 = new Rana("R1>", 1, 0);
	public static Rana r2 = new Rana("R2>", 1, 1);
	public static Rana r3 = new Rana("R3>", 1, 2);
	public static Rana r4 = new Rana("R4>", 1, 3);
	public static Rana r5 = new Rana("R5>", 1, 4);
	public static Rana vacio = new Rana("_", 0, 0);
	public static Rana r6 = new Rana("<S1", -1, 6);
	public static Rana r7 = new Rana("<S2", -1, 7);
	public static Rana r8 = new Rana("<S3", -1, 8);
	public static Rana r9 = new Rana("<S4", -1, 9);
	public static Rana r10 = new Rana("<S5", -1, 10);
	public static Rana[] vector = { r1, r2, r3, r4,r5, vacio, r6, r7, r8, r9,r10 };

	public static void main(String[] args) {
		for (int i = 0; i < vector.length; i++) {
			vector[i].start();
		}
	}

	public static void mostrarPosiciones() {

		for (int i = 0; i < vector.length; i++) {
			System.out.print(vector[i].caracterAMostrar + " ");
		}

		if (terminoElJuego()) {
			print("El total de saltos fue: " + saltos);
			print("Y numero de intentos fue: " + intentos);
		}

	}

	private static boolean terminoElJuego() {
		return Arrays.stream(vector).allMatch(n -> n.llegue);
	}

	private static void print(String mensaje) {
		System.out.println(mensaje);
	}

}