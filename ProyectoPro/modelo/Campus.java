package modelo;

import java.util.LinkedList;

// Clase Campus: representa el mapa de edificios como un grafo usando matriz de adyacencia y Dijkstra
public class Campus {
    private String[] edificios;
    // Guarda las distancias en metros entre cada par de edificios (0 significa que no hay camino directo)
    private int[][] matrizAdyacencia;
    private int cantidadEdificios;
    private int capacidadMaxima;

    // Constructor por defecto con espacio para 20 edificios
    public Campus() {
        this(20);
    }

    // Constructor que permite definir la capacidad maxima de edificios
    public Campus(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            capacidadMaxima = 20;
        }
        this.capacidadMaxima = capacidadMaxima;
        this.edificios = new String[capacidadMaxima];
        this.matrizAdyacencia = new int[capacidadMaxima][capacidadMaxima];
        this.cantidadEdificios = 0;
    }

    // Getters
    public String[] getEdificios() {
        return edificios;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public int getCantidadEdificios() {
        return cantidadEdificios;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    // Busca la posicion de un edificio en el arreglo por su nombre (sin importar mayusculas)
    public int buscarIndiceEdificio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return -1;
        }

        String nombreBuscado = nombre.trim();
        for (int i = 0; i < cantidadEdificios; i++) {
            if (edificios[i].equalsIgnoreCase(nombreBuscado)) {
                return i;
            }
        }
        return -1;
    }

    // Agrega un nuevo edificio al campus si hay espacio y no esta repetido
    public boolean agregarEdificio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        if (cantidadEdificios >= capacidadMaxima) {
            return false;
        }
        if (buscarIndiceEdificio(nombre) != -1) {
            return false;
        }

        edificios[cantidadEdificios] = nombre.trim();
        cantidadEdificios++;
        return true;
    }

    // Conecta dos edificios en ambas direcciones con su distancia en metros (grafo no dirigido)
    public boolean conectarEdificios(String origen, String destino, int distancia) {
        int i = buscarIndiceEdificio(origen);
        int j = buscarIndiceEdificio(destino);

        if (i == -1 || j == -1 || i == j || distancia <= 0) {
            return false;
        }

        matrizAdyacencia[i][j] = distancia;
        matrizAdyacencia[j][i] = distancia;
        return true;
    }

    // Lista todos los edificios registrados
    public void mostrarEdificios() {
        System.out.println("=== EDIFICIOS DEL CAMPUS ===");
        if (cantidadEdificios == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }

        for (int i = 0; i < cantidadEdificios; i++) {
            System.out.println((i + 1) + ". " + edificios[i]);
        }
    }

    // Muestra todas las conexiones existentes entre edificios sin repetir pares
    public void mostrarMapaCampus() {
        System.out.println("=== MAPA DE CONEXIONES DEL CAMPUS ===");
        boolean hayConexiones = false;

        for (int i = 0; i < cantidadEdificios; i++) {
            for (int j = i + 1; j < cantidadEdificios; j++) {
                if (matrizAdyacencia[i][j] > 0) {
                    System.out.println("  - " + edificios[i] + " <-> " + edificios[j]
                            + " (" + matrizAdyacencia[i][j] + " m)");
                    hayConexiones = true;
                }
            }
        }

        if (!hayConexiones) {
            System.out.println("No hay caminos registrados entre edificios.");
        }
    }

    // Aplica el algoritmo de Dijkstra para encontrar el camino mas corto entre dos edificios
    public String calcularRutaMasCorta(String origen, String destino) {
        int indiceOrigen = buscarIndiceEdificio(origen);
        int indiceDestino = buscarIndiceEdificio(destino);

        if (indiceOrigen == -1) {
            return "Error: El edificio de origen '" + origen + "' no existe en el campus.";
        }
        if (indiceDestino == -1) {
            return "Error: El edificio de destino '" + destino + "' no existe en el campus.";
        }
        if (indiceOrigen == indiceDestino) {
            return "Ya te encuentras en " + edificios[indiceOrigen] + " (Distancia: 0 metros).";
        }

        // Arreglos auxiliares para distancias minimas, nodos visitados y reconstruccion de la ruta
        int[] distancias = new int[cantidadEdificios];
        boolean[] visitados = new boolean[cantidadEdificios];
        int[] anteriores = new int[cantidadEdificios];

        for (int i = 0; i < cantidadEdificios; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
            anteriores[i] = -1;
        }

        // La distancia desde el origen hasta sí mismo siempre es 0
        distancias[indiceOrigen] = 0;

        // 2. Ciclo principal de Dijkstra
        for (int paso = 0; paso < cantidadEdificios; paso++) {

            int u = obtenerVerticeMenorDistancia(distancias, visitados);

            if (u == -1) {
                break;
            }

            visitados[u] = true;

            // Actualiza las distancias de los edificios vecinos que aun no se han visitado
            for (int v = 0; v < cantidadEdificios; v++) {
                if (!visitados[v] && matrizAdyacencia[u][v] > 0 && distancias[u] != Integer.MAX_VALUE) {
                    int nuevaDistancia = distancias[u] + matrizAdyacencia[u][v];

                    if (nuevaDistancia < distancias[v]) {
                        distancias[v] = nuevaDistancia;
                        anteriores[v] = u;
                    }
                }
            }
        }

        if (distancias[indiceDestino] == Integer.MAX_VALUE) {
            return "No existe un camino disponible entre "
                    + edificios[indiceOrigen] + " y " + edificios[indiceDestino] + ".";
        }

        // Reconstruye el camino desde el destino hacia atras usando el arreglo de anteriores
        LinkedList<String> ruta = new LinkedList<>();
        int actual = indiceDestino;
        while (actual != -1) {
            ruta.addFirst(edificios[actual]);
            actual = anteriores[actual];
        }

        String resultado = "=== RUTA MAS CORTA ===\n";
        resultado += "Origen: " + edificios[indiceOrigen] + "\n";
        resultado += "Destino: " + edificios[indiceDestino] + "\n";
        resultado += "Camino: ";

        int contador = 0;
        for (String nombreEdificio : ruta) {
            resultado += nombreEdificio;
            if (contador < ruta.size() - 1) {
                resultado += " -> ";
            }
            contador++;
        }

        resultado += "\nDistancia total: " + distancias[indiceDestino] + " metros";
        return resultado;
    }

    // Busca el edificio no visitado que tenga la menor distancia acumulada hasta el momento
    private int obtenerVerticeMenorDistancia(int[] distancias, boolean[] visitados) {
        int menorDistancia = Integer.MAX_VALUE;
        int indiceMenor = -1;

        for (int i = 0; i < cantidadEdificios; i++) {
            if (!visitados[i] && distancias[i] < menorDistancia) {
                menorDistancia = distancias[i];
                indiceMenor = i;
            }
        }
        return indiceMenor;
    }
}