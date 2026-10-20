package Pilas_y_Colas;
import java.util.Stack;


public class EjemploPila {
    
    //Este ejemplo esta basado en un taller de software donde en un taller de motos se presentaba un pasillo estrecho.
    //push() Colocar una moto al fondo del pasillo
    //pop() Sacar la moto que esta bloqueando el pasillo( la ultima que entró)
    //peek() Mirar cual fue la ultima moto que entro (sin mover ninguna moto)
    //size() Contar cuantas motos hay
    //isEmpty() Comprobar si el pasillo esta vació

    public static void main(String[] args) {

        Stack<String> pasillo = new Stack<>();

        pasillo.push("Moto_1");
        pasillo.push("Moto_2");
        pasillo.push("Moto_3");

        System.out.println("Motos en el pasillo : " + pasillo);

        System.out.println(" Cual fue la ultima moto en ingresar? = " + pasillo.peek());

        System.out.println(" Sacar la ultima moto que ingreso al taller =" + pasillo.pop());

        System.out.println(" Cuantas motos quedaron ? = " + pasillo.size());

        System.out.println( " Esta vació el pasillo? = " + pasillo.isEmpty());

        System.out.println( " En que posicion esta la moto 1? = " + pasillo.search("Moto_1"));

        System.out.println(" Existe la moto 2? = " +pasillo.contains("Moto_2"));

        pasillo.clear();

        System.out.println(" El pasillo esta vació? =" + pasillo.isEmpty());
        



    }
}
