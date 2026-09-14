package Ejercicio1;

import java.util.Scanner;

public class Punto_22 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("ingrese el numero de filas: ");
        int n = sc.nextInt();
        System.out.println("ingrese el numero de columnas: ");
        int m = sc.nextInt();

        int[][] matriz = new int[n][m];

        System.out.println("\nIngrese los elementos de la matriz: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextInt();
            }
        }

         System.out.println("\nMatriz ingresada:");
        for(int i = 0; i < n; i++){
            System.out.print("[");
            for(int j = 0; j < m; j++){
                System.out.print(matriz[i][j]);
                if(j < m-1) System.out.print(", ");
            }
            System.out.println("]");
        }

        int max = matriz [0][0];
        int min = matriz [0][0];
        int maxFila = 0, maxCol = 0;
        int minFila = 0, minCol = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matriz[i][j] > max){
                    max = matriz[i][j];
                    maxFila = i;
                    maxCol = j;
                }
                if (matriz[i][j] < min){
                    min = matriz[i][j];
                    minFila = i;
                    minCol = j;
                }
            }
        }

        


    }
    
}
