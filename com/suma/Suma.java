package suma;
public class Suma extends Thread {
  int x1, y1, z1, id;

  public Suma(int _x1,int _y1, int _id) {
    x1 = _x1;
    y1 = _y1;
    id = _id;
  }
  
  public void run() {
    z1 = x1 + y1;
    System.out.println("suma " + id + " es " + z1);
  }
}

