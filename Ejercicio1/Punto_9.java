package Ejercicio1;

import java.util.Scanner;

public class Punto_9 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] arreglo = new int [10];
        int numero;
        int contador = 0;

        System.out.println("ingrese los elementos del arreglo: ");

        for (int i = 0; i < arreglo.length; i++) {
            System.out.println("arreglo[" + i + "] = ");
            arreglo[i] = scanner.nextInt();
        }

        System.out.println("\nArreglo ingresado : ");
            for (int i = 0; i < arreglo.length; i++) {
            System.out.println("arreglo[" + i + "] = " + arreglo[i]);

            }

        System.out.println("\nIngrese un numero para buscar en eñ arreglo: ");
        numero = scanner.nextInt();

        for (int i = 0; i < arreglo.length; i++){
            if (arreglo[i] == numero){
                contador++;
            }
        }

        System.out.println("\nEl numero " + numero + " aparece " + contador + " veces en el arreglo ");

    }

    
}
