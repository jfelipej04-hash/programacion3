package Arraylist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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


        List<String> historialImpresion = new ArrayList<>();

        historialImpresion.add("Taller_1");
        historialImpresion.add("Taller_2");
        historialImpresion.add("Taller_3");

        System.out.println("Historial de impresion (ArrayList) = " + historialImpresion);

        System.out.println("Documento en la posicion 1 (indice 1) = " + historialImpresion.get(1));

        historialImpresion.add(1, "Taller_Urgente");
        System.out.println("insertar Taller_Urgente en la posicion 1 = " + historialImpresion);

        historialImpresion.set(0, "Taller_1_Modificado");
        System.out.println("Modificar la posicion 0 = " + historialImpresion);

        System.out.println("En que posicion esta Taller_3? = " + historialImpresion.indexOf("Taller_3"));

        for (int i = 0; i < historialImpresion.size(); i++) {
            System.out.println("Elemento en el indice " + i + " : " + historialImpresion.get(i));
        }

        historialImpresion.remove("Taller_2"); 
        System.out.println("Eliminar Taller_2 = " + historialImpresion);

        historialImpresion.remove(0); 
        System.out.println("Eliminar el elemento en el indice 0 = " + historialImpresion);

    }
}
