package Pilas_y_Colas;

import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Ejercicio_Pila_5 {

    public static void main(String[] args) {
        
    

    Scanner entrada = new Scanner(System.in);
    String frase;

        System.out.println("verificador de palindromos");
        System.out.println("Escribe una frase (o escribe 'salir' para terminar):\n");

        do {
            System.out.print("Ingrese una frase: ");
            frase = entrada.nextLine();
            if (!frase.equalsIgnoreCase("salir") && !frase.isEmpty()){

                Stack<Character> pila = new Stack<>();
                Queue<Character> listaCircular = new LinkedList<>();


            }

        }        


    } 
}
