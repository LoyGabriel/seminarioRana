package suma;

public class Main {
  public static void main(String[] args) {
    int[] vect1 = {1,2,3};
    int[] vect2 = {4,5,6};

    Suma sum1 = new Suma(vect1[0],vect2[0], 1);
    Suma sum2 = new Suma(vect1[1],vect2[1], 2);
    Suma sum3 = new Suma(vect1[2],vect2[2], 3);

    sum1.start();
    sum2.start();
    sum3.start();

    try{
      sum1.join();
      sum2.join();
      sum3.join();
    } catch(InterruptedException e) {
        System.out.println("Error");
    }
  }
}
