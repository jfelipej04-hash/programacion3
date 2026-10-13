package Pilas_y_Colas;

import java.util.Stack;

public class Ejercicio_pila_2 {

    String[] expresiones = {

        "((a+b)*5) - 7",
        "2*[(a+b)/2.5 + x - 7*y",
        "{[a + (b * c)] - d}"

    };

    for (int p = 0; p < expresiones.length; p++) {
            String expr = expresiones[p];
            System.out.println("\nEvaluando expresión: " + expr);

            Stack<Character> pila = new Stack<>();
            boolean equilibrado = true;
    
}
