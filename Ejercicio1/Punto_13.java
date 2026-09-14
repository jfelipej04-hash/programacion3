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






        
    }
}
