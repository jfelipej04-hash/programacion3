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
        
            System.out.println("fila " + (i+1) + " : " + sumaFila);

        }

        
        for(int j = 0; j < columnas; j++){
            int sumaColumna = 0;
            for(int i = 0; i < filas; i++){
                sumaColumna += M[i][j];
            }
            System.out.println("columna " + (j+1) + " : " + sumaColumna);

        }


    }

}
