package Ejercicio1;

public class punto_11 {
    public static void main(String[] args) {
        
        int[] A = {3, -4, 0, -8, 7, 9, 0, -1, 5, -3};

        int[] positivos = new int[A.length];
        int[] negativos = new int[A.length];
        int[] ceros = new int[A.length];

        int p = 0;
        int n = 0;
        int c = 0;

        for (int i = 0; i < A.length; i++){

            if(A[i] > 0){
                positivos[p++] = A[i];
            } else if (A[i] < 0){
                negativos[n++] = A[i];
            } else {
                ceros[c++] = A[i];
            }

        }

        System.out.println("\nPositivos: ");
        for (int i = 0; i < p; i++){
            System.out.println(positivos[i]);
        }
         System.out.println("Negativos: ");
        for (int i = 0; i < n; i++){
            System.out.println(negativos[i]);
        }

         System.out.println("ceros: ");
        for (int i = 0; i < c; i++){
            System.out.println(ceros[i]);
        }

    }
    
}
