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



    }
}
