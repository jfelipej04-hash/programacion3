package Ejercicio1;

public class Punto_20 {
    public static void main(String[] args) {
        
        int a = 10;
        int b = 10;
        int[][] matriz = new int[a][b]

        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                if(j >= i){
                    matriz[i][j] = 0;
                }else{
                    matriz[i][j] = 1;
                }
                
            }    


    }
}
