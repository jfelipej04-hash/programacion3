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

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. nuevo cliente");
            System.out.println("2. Un cliente paga en una caja");
            System.out.println("3. Ver estado actual (Carritos y Cajas)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:
                    String nombreCliente = "Cliente_" + contadorClientes++;
                    
                    if (!carritosDisponibles.isEmpty()){
                        int carritoId = carritosDisponibles.poll();
                        System.out.println("-> " + nombreCliente + " toma el carrito #" + carritoId);
                        
                        int cajaMenosGente = 0;
                        int minTamano = cajas[0].size();
                        for (int c = 1; c < 3; c++) {
                            if (cajas[c].size() < minTamano) {
                                minTamano = cajas[c].size();
                                cajaMenosGente = c;
                            }
                        }

                        cajas[cajaMenosGente].add(nombreCliente);
                        System.out.println("-> " + nombreCliente + " se forma en la Caja " + (cajaMenosGente + 1));

                        } else {
                            clientesEsperandoCarrito.add(nombreCliente);
                        System.out.println("-> No hay carritos disponibles. " + nombreCliente + " pasa a la lista de espera[cite: 5].");
                    }
                    
                    break;

                case 2:

                System.out.print("¿De qué caja sale el cliente que ya pagó? (1, 2 o 3): ");
                    int numCaja = entrada.nextInt() - 1;

                    if (numCaja >= 0 && numCaja < 3) {
                        if (!cajas[numCaja].isEmpty()) {
                            String clienteSaliendo = cajas[numCaja].poll();
                            System.out.println("-> " + clienteSaliendo + " pagó en la Caja " + (numCaja + 1) + " y desocupa su carrito[cite: 5].");

                            int carritoDevuelto = 25 - carritosDisponibles.size(); 
                            carritosDisponibles.add(carritoDevuelto);

                            if (!clientesEsperandoCarrito.isEmpty()) {
                                String clienteEnEspera = clientesEsperandoCarrito.poll();
                                int carritoAsignado = carritosDisponibles.poll();
                                int cajaMenosGente = 0;
                                int minTamano = cajas[0].size();
                                for (int c = 1; c < 3; c++) {
                                    if (cajas[c].size() < minTamano) {
                                        minTamano = cajas[c].size();
                                        cajaMenosGente = c;
                                    }
                                }
                                cajas[cajaMenosGente].add(clienteEnEspera);
                                System.out.println(" Había gente esperando " + clienteEnEspera + " toma el carrito #" + carritoAsignado + " y se forma en la Caja " + (cajaMenosGente + 1));
                            }

                        } else {
                            System.out.println("La Caja seleccionada está vacía.");
                        }
                    } else {
                        System.out.println("Número de caja no válido.");
                    }
                    break;

                    

            }



    }

}
