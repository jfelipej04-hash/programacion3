package Ejercicio1;

public class Punto_8 {
    
    public static void main(String[] args) {
        
        int[] numeros = new int [30];

        int max = 100;
        int min = 1;

         for (int i = 0; i < numeros.length; i++){
        numeros[i] = (int)(Math.random() * (max - min + 1) + min);
        System.out.println("numeros[" + i + "] = " + numeros[i]);

    }

    int mayor = numeros [0];
    int menor = numeros [0];

    for (int i = 1; i < numeros.length; i++){
        if (numeros[i] > mayor){
            mayor = numeros[i];
        }
        if (numeros[i] < menor){
            menor = numeros[i];
        }
    }

    int repMayor = 0, int repMenor = 0;

    for (int num : numeros){
        if (num == mayor){
            repMayor++;
        }
        if (num == menor){
            repMenor++;
        }
    }


}
