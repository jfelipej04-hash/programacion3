package Ejercicio1;

public class Punto_10 {

    public static void main(String[] args) {
        
        int[] A = {7, 3, 6, 9, 1, 5, 4};
        int[] B = new int[4];

        for(int i = 0; i < B.length; i++) {
            B[i] = A[i] + A[A.length -1 - i];
        }        



    }
    
}
