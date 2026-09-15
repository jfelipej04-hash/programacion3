package Ejercicio1;

import java.util.Random;

public class Punto_24 {
    public static void main(String[] args) {
        
        int[][] produccion = new int[12][4];

        Random rand = new Random();
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 4; j++){
                produccion[i][j] = rand.nextInt(200) + 50;
            }
        }

        


        
    }
}
