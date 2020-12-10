package com.company;
import com.company.animales.*;

import java.util.Random;

public class Main {
    public static Animal[] vector= new Animal[7];
    public static Animal[] winVector= new Animal[7];

    public static void main(String[] args) {
	    // Inicializo el vector cargandole sapos y ranas
        initializeAll();
        //Comienzo a resolver el algoritmo
        resolveGame();
    }

    public static void initializeAll(){
        int k=1;
        for(int i=0;i<3;i++){
            Rana rana=new Rana(i,"R"+(i+1));
            vector[i]= rana;
            winVector[6-i]=rana;
        }
        vector[3]=null;
        winVector[3]=null;
        for(int i=4;i<7;i++){
            Sapo sapo= new Sapo(i,"S"+k);
            vector[i]= sapo;
            winVector[6-i]=sapo;
            k++;
        }
    }

    public static void resolveGame(){
        int contadorDeVueltas=0;
        while(vector!=winVector){
            boolean hasJumped=false;
            int random=getRandom();
            Animal aleatoryAnimal= vector[random];
            if(aleatoryAnimal!=null) {
                int oldAnimalPosition = aleatoryAnimal.getPositionInTheVector();
                hasJumped = aleatoryAnimal.jump(vector)>0;
                if (hasJumped) {
                    System.out.println("ANIMAL QUE SALTO: " + aleatoryAnimal.getName());
                    vector[aleatoryAnimal.getPositionInTheVector()] = aleatoryAnimal;
                    vector[oldAnimalPosition] = null;
                }
                if(hasLost()){
                    initializeAll();
                    System.out.println("Vuelve a arrancar");
                }
            }
            contadorDeVueltas++;
        }
        System.out.println("CANTIDAD DE VUELTAS: "+contadorDeVueltas);
        System.out.println("SE HA RESUELTO EL JUEGO DE MANERA ALEATORIA");
        printVector(vector);
    }


    public static void printVector(Animal[]vector){
        for(int i=0; i< vector.length;i++) {
            if(vector[i]!=null) {
                System.out.print(vector[i].getName()+" " );
            }else {
                System.out.print("- ");
            }
        }
    }

    public static int getRandom(){
        Random r = new Random();
        int low = 0;
        int high =7;
        return r.nextInt(high-low) + low;
    }

    //TODO corregir para que funcione posta
    public static boolean hasLost(){
        return (vector[0]==null&& !vector[1].isSapo())
                || (vector[6]==null&& vector[5].isSapo());
    }
}
