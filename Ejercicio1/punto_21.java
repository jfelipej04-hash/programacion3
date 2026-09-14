package Ejercicio1;

import java.util.Random;

public class punto_21 {
    public static void main(String[] args) {
        
        int M = 5;
        int[][] matriz = new int[M][M];
        int[] B = new int[M];

        Random rand = new Random();

        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                matriz[i][j] = rand.nextInt(20) + 1;
            }
        }

        for(int i = 0; i < M; i++){
            int suma = 0;
            for (int j = 0; j < M; j++){
                if(matriz[i][j] % 2 == 0){
                    suma += matriz[i][j];
                }
            }
            B[i] = suma;
        }

        for(int i = 0; i < M; i++){
        System.out.print("[");
        for(int j = 0; j < M; j++){ 
         System.out.print(matriz[i][j]);
        if(j< M-1){
            System.out.print(", ");
        }
        }
        System.out.println("]");
        }
        
        
        System.out.println("[");
        for(int i = 0; i < M; i++){
            System.out.print(B[i]);
            if(i < M-1)System.out.print(", ");
        }
        System.out.print("]");



    }
}
