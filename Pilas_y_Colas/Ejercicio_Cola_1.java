package Pilas_y_Colas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ejercicio_Cola_1 {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        Queue<Integer> carritosDisponibles = new LinkedList<>();
        for (int i = 1; i <= 25; i++) {
            carritosDisponibles.add(i);
        }

        Queue<String>[] cajas = new LinkedList[3];
        for (int i = 0; i < 3; i++) {
            cajas[i] = new LinkedList<>();
        }

        Queue<String> clientesEsperandoCarrito = new LinkedList<>();

        int opcion;
        int contadorClientes = 1;

        System.out.println(" SUPER MERCADO ");
        



    }

}
