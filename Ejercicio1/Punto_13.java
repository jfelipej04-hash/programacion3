package Ejercicio1;

public class Punto_13 {
    public static void main(String[] args) {

        int[] A = {5, 1, 9, 7, 10, 12, 15};

        int suma = 0;
        for (int i = 0; i < A.length; i++){
            suma += A[i];
        }
        double media = (double) suma / A.length;

        int mayores = 0, menores = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] > media){
                mayores++;
            }else if (A[i] < media){
                menores++;
            }
        }

        int[] Mayores = new int[mayores];
        int[] Menores = new int[menores];

        int tmay = 0, tmen = 0;
        for(int i = 0; i < A.length; i++){
            if (A[i] > media){
                Mayores[tmay++] = A[i];
            }else if (A[i] < media){
                Menores[tmen++] = A[i];
            }
        } 

        System.out.println("Media = " + media);

         System.out.println("\nMayores que la media:");
        for (int i = 0; i < Mayores.length; i++) {
            System.out.println(Mayores[i]);
        }

         System.out.println("\nMenores que la media:");
        for (int i = 0; i < Menores.length; i++) {
            System.out.println(Menores[i]);
        }


        
    }
}
