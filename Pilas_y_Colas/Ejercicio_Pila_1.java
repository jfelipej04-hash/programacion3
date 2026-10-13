package Pilas_y_Colas;

import java.util.Stack;

public class Ejercicio_Pila_1 {

    public static void main(String[] args) {
        
       String[] pruebas = {

        "comprobar&raborpmoc",
        "hola&aloh",
        "funcion&funcion"


       };

       for (int p = 0; p < pruebas.length; p++) {
            String cadena = pruebas[p];
            
            System.out.println("\nEvaluando la cadena: " + cadena);

        
        
        int separador = cadena.indexOf('&');

        if (separador == -1) {
                System.out.println("Resultado: false (No tiene '&')");
            } else {

            String x = cadena.substring(0, separador);
            String y = cadena.substring(separador + 1);

            Stack<Character> pila = new Stack<>();

            for (int i = 0; i < x.length(); i++) {
                    pila.push(x.charAt(i));
                }

            String inversa = "";
                while (!pila.isEmpty()) {
                    inversa += pila.pop();
                }

                boolean resultado = inversa.equals(y);
                System.out.println("Resultado: " + resultado);

            }        
    
        }
        
    }   
}
