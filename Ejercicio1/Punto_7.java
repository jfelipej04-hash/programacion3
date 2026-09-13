package Ejercicio1;

public class Punto_7 {

    int [] A = {7, 2, 4, 19, 22, 8, 14, 25, 9, 1}

    int contPares = 0, contImpares = 0;

    for (int num : A ){

        if (num % 2 == 0){
            contPares++;
        } else {
            contImpares++;
        }

    }

    int[] pares = new int [contPares];
    int[] impares = new int [contImpares];

    int indP = 0, indIm = 0;

    for (int num : A) {

        if (num % 2 == 0) {
            pares[indP++] = num;
        } else {
        impares[indIm++] = num;
        
        }
    }

    System.out.println("Arreglo original:");
    for (int i = 0; i < A.length; i++){
        System.out.println(A[i] + " ");
    }

    System.out.println("Arreglo original:");
    for (int i = 0; i < pares.length; i++){
        System.out.println(pares[i] + " ");
    }

    System.out.println("Arreglo original:");
    for (int i = 0; i < impares.length; i++){
        System.out.println(impares[i] + " ");
    }






    }
    
}
