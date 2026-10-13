package Pilas_y_Colas;

import java.util.LinkedList;
import java.util.Queue;

public class EjemploCola {

    //simular la funcion de una impresora
    //offer() agregar una peticion de impresion a la cola
    // poll() imprimir y retirar el primer archivo
    //peek() mostrar el archivo siguiente para imprimirlo
    //size() contar los archivos que estan en la cola
    //isEmpty() Ver si aun quedan archivos en la cola
    //contains() Buscar si un archivo en especifico esta en la cola

    public static void main(String[] args) {
        
        Queue<String> impresora = new LinkedList<>();

        impresora.offer("Taller_1");
        impresora.offer("Taller_2");
        impresora.offer("Taller_3");

        System.out.println(" cola de impresion = " + impresora);

        System.out.println(" Ver el primer documento que se imprimira = " + impresora.peek());

        System.out.println(" Imprimir y retirar el primer documento de la cola = " + impresora.poll());

        System.out.println(" Cuantos documentos siguen en la cola? = " + impresora.size());

        System.out.println(" La cola esta vacia? = " + impresora.isEmpty());

        System.out.println(" El taller 3 esta en la cola? = " + impresora.contains("Taller_3"));

        System.out.println("\n imprimir los demas documentos ");
        while (!impresora.isEmpty()) {

            System.out.println( "imprimir : " + impresora.poll());
            
        }

        System.out.println(" Cola restante: " + impresora);


    }


}
