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

        int[] totalMes = new int [12];
        for(int i = 0; i < 12; i++){
            int suma = 0;
            for(int j = 0; j < 4; j++){
                suma += produccion[i][j];
            }
            totalMes[i] = suma;
        }

        int sumaAnual = 0;
        for(int i = 0; i < 12; i++){
            sumaAnual += totalMes[i];
        }
        double promedio = (double) sumaAnual / 12;


        
    }
}
