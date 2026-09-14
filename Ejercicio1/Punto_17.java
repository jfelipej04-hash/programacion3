package Ejercicio1;

public class Punto_17 {
    public static void main(String[] args) {
        
        int [][] M = {{8, 3, 1}, {4, 6, 9}, {5, 7, 2}};

        int filas = M.length;
        int columnas = M[0].length;

        for(int i = 0; i < filas; i++){
            int sumaFila = 0;
            for(int j = 0; j < columnas; j++){
                sumaFila += M[i][j];
            }
        }



    }

}
