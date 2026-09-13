package Ejercicio1;

public class punto_12 {
    public static void main(String[] args) {
        
        int[] A = {7, 5, 3, 6, 7, 2, 7, 4, 7}
        int x = 7;

        int contador = 0;
        for(int i = 0; i < A.length; i++){
            if (A[i] == x) {
                contador++;
            }
        }

    }
}
