package Ejercicio1;

public class punto_12 {
    public static void main(String[] args) {
        
        int[] A = {7, 5, 3, 6, 7, 2, 7, 4, 7};
        int x = 7;

        int contador = 0;
        for(int i = 0; i < A.length; i++){
            if (A[i] == x) {
                contador++;
            }
        }

        int[] B = new int[contador];

        int j = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x){
                B[j++] = i + 1;
            }
        }

        System.out.println("Arreglo A:");
        for (int i = 0; i < A.length; i++) {
        System.out.println("A[" + (i+1) + "] = " + A[i]);
        }

        System.out.println("\nValor buscado: " + x);

        for (int i = 0; i < B.length; i++) {
        System.out.println("B[" + (i+1) + "] = " + B[i]);
        }
    }
}
