package caballos;

public class Caballo extends Thread {
  int metrosRecorridos = 0;
  static boolean seguirCorriendo = true;
  int nroCaballo;

  public Caballo(int _nroCaballo) { nroCaballo = _nroCaballo; }

  public void run(){
    while (metrosRecorridos < 50 && seguirCorriendo) {
      metrosRecorridos++;
       System.out.println("Caballo " + nroCaballo + " metrosRecorridos " + metrosRecorridos);

      try{
        sleep(100);
      } catch(InterruptedException e) {
        System.out.println("ERROR");
      }

      if (metrosRecorridos == 50 ) {
        seguirCorriendo = false;
        System.out.println("Llego el caballo " + nroCaballo);
      }
    }
  
  }
}
