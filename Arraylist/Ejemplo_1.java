package Arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejemplo_1 {
    
    public static void main(String[] args) {
        
        List<String> botiquin = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        botiquin.add("Alcohol");
        botiquin.add("Gasas");
        botiquin.add("Banditas");
        botiquin.add("Vendas");
        botiquin.add("termometro");

        do {
            System.out.println("\n========== MENU BOTIQUIN DE PRIMEROS AUXILIOS ==========");
            System.out.println("1. Ver insumos del botiquin");
            System.out.println("2. Agregar nuevo insumo");
            System.out.println("3. Actualizar o reemplazar insumo");
            System.out.println("4. Usar / Retirar insumo");
            System.out.println("5. Buscar un insumo especifico");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- INVENTARIO ACTUAL ---");
                    if (botiquin.isEmpty()) {
                        System.out.println("El botiquin esta completamente vacio.");
                    } else {
                        System.out.println("Total de insumos guardados: " + botiquin.size());
                        for (int i = 0; i < botiquin.size(); i++) {
                            System.out.println("Posicion [" + i + "]: " + botiquin.get(i));
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nIngrese el nombre del nuevo insumo: ");
                    String nuevoInsumo = scanner.nextLine();
                    botiquin.add(nuevoInsumo);
                    System.out.println( nuevoInsumo + " guardado en el botiquin");
                    break;

                case 3:
                    if (botiquin.isEmpty()) {
                        System.out.println("\nNo hay insumos para reemplazar.");
                    } else {
                        System.out.print("\nIngrese la posicion (indice) del insumo que desea modificar: ");
                        int indiceMod = scanner.nextInt();
                        scanner.nextLine();

                        if (indiceMod >= 0 && indiceMod < botiquin.size()) {
                            System.out.print("Ingrese el nombre del insumo reemplazante: ");
                            String insumoReemplazo = scanner.nextLine();
                            

                            botiquin.set(indiceMod, insumoReemplazo);
                            System.out.println("Posicion (" + indiceMod + ") actualizada correctamente");
                        } else {
                            System.out.println("Posicion no valida. (Fuera de rango)");
                        }
                    }
                    break;

                case 4:
                    if (botiquin.isEmpty()) {
                        System.out.println("\nEl botiquin esta vacio. No hay nada que retirar.");
                    } else {
                        System.out.print("\nIngrese la posicion (indice) del insumo que va a consumir: ");
                        int indiceElim = scanner.nextInt();
                        scanner.nextLine();

                        if (indiceElim >= 0 && indiceElim < botiquin.size()) {
                       
                            String insumoRetirado = botiquin.remove(indiceElim);
                            System.out.println("Se ha retirado " + insumoRetirado + " del botiquin");
                        } else {
                            System.out.println("Posicion no valida.");
                        }
                    }
                    break;

                case 5:
                    System.out.print("\nIngrese el nombre del insumo a buscar: ");
                    String busqueda = scanner.nextLine();
                    
                    if (botiquin.contains(busqueda)) {
                        System.out.println("Si hay " + busqueda + " en el botiquin");
                    } else {
                        System.out.println("No se encontro '" + busqueda + "' en el botiquin.");
                    }
                    break;

                case 6:
                    System.out.println("\nSaliendo del sistema del botiquin. ");
                    break;

                default:
                    System.out.println("\nOpcion invalida, intente de nuevo.");
            }

        } while (opcion != 6);

        scanner.close();
    }


    }


