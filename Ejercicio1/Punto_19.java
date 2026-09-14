package Ejercicio1;

public class Punto_19 {

    public static void main(String[] args) {

        int a = 5;
        int b = 5;

        int [][] matriz = new int[a][b];

        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                if(i == j){
                    matriz[i][j] = 1;
                }else {
                    matriz[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < a; i++){
        System.out.print("[");
        for(int j = 0; j < b; j++){ 
         System.out.print(matriz[i][j]);
        if(j< b-1){
            System.out.print(", ");
        }
    }
        System.out.println("]");


    }


    }
    
}
