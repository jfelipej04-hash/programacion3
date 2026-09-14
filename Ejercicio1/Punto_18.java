package Ejercicio1;

public class Punto_18 {
    public static void main(String[] args) {
        
        int [][] matriz = new int [10][10];

for(int i = 0; i < 10; i++){
    for(int j = 0; j < 10; j++) {
        matriz[i][j] = (i+1) * (j+1);
    }
}


System.out.println("tabla de multiplicar del 1 al 10: \n");
    for(int i = 0; i < 10; i++){
    for(int j = 0; j < 10; j++) 
         System.out.printf("%4d", matriz[i][j]);
    }
    System.out.println();


    }
    

    }