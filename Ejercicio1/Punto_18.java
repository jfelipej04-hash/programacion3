package Ejercicio1;

public class Punto_18 {
    public static void main(String[] args) {
        
        int [][] matriz = new int [10][10];

for(int i = 0; i < 10; i++){
    for(int j = 0; j < 10; j++) {
        matriz[i][j] = (i+1) * (j+1);
    }
}


System.out.println("tabla de multiplicar del 1 al 10: \n");
    for(int i = 0; i < 10; i++){
        System.out.print("[");
    for(int j = 0; j < 10; j++){ 
         System.out.print(matriz[i][j]);
        if(j<9){
            System.out.print(", ");
        }
    }
        System.out.println("]");


    }
}

    }