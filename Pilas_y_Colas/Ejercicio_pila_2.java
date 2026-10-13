package Pilas_y_Colas;

import java.util.Stack;

public class Ejercicio_pila_2 {

    public static void main(String[] args) {
        
    

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

            for (int i = 0; i < expr.length(); i++) {
                char actual = expr.charAt(i);

                if (actual == '(' || actual == '[' || actual == '{') {
                    pila.push(actual);

                }else if (actual == ')' || actual == ']' || actual == '}') {
                    if (pila.isEmpty()) {
                        equilibrado = false;
                        break;
                    }

                    char tope = pila.pop();

                    if (actual == ')' && tope != '(') { equilibrado = false; break; }
                    if (actual == ']' && tope != '[') { equilibrado = false; break; }
                    if (actual == '}' && tope != '{') { equilibrado = false; break; }
                }
            }

            if (!pila.isEmpty()) {
                equilibrado = false;
            }

            System.out.println("Resultado (¿Está equilibrado?): " + equilibrado);
        }

    }
}

