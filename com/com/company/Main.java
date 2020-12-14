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
        }
        vector[3]=null;
        for(int i=4;i<7;i++){
            Sapo sapo= new Sapo(i,"S"+k);
            vector[i]= sapo;
            k++;
        }
        initializeWinVector();
    }

    private static void initializeWinVector() {
        winVector[0]=vector[4];
        winVector[1]=vector[5];
        winVector[2]=vector[6];
        winVector[3]=vector[3];
        winVector[4]=vector[0];
        winVector[5]=vector[1];
        winVector[6]=vector[2];
    }

    public static void resolveGame(){
        int contadorDeVueltas=0;
        while(!hasWon()){
            boolean hasJumped=false;
            int random=getRandom();
            Animal aleatoryAnimal= vector[random];
            if(aleatoryAnimal!=null) {
                int oldAnimalPosition = aleatoryAnimal.getPositionInTheVector();
                hasJumped = aleatoryAnimal.jump(vector)!=0;
                if (hasJumped) {
                    System.out.println("ANIMAL QUE SALTO: " + aleatoryAnimal.getName());
                    vector[aleatoryAnimal.getPositionInTheVector()] = aleatoryAnimal;
                    vector[oldAnimalPosition] = null;
                    aleatoryAnimal.setJumpedAtLeastOneTime(true);
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

    private static boolean hasWon() {
        for(int i=0;i<winVector.length-1;i++){
            if(!winVector[i].equals(vector[i])){
                return false;
            }
        }
        return true;
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
        return r.nextInt(high);
    }

    public static boolean hasLost(){
        for(Animal animal: vector){
            if(animal!=null&&(animal.canJump(vector)||animal.canJumpTwo(vector))){
                return false;
            }
        }
        return true;
    }
}
