package modelo;

import excepciones.ExcepcionesSistema.HorarioConflictivoException;

// Clase Aulas: representa un salon y usa una matriz booleana de 7x24 para controlar su horario
public class Aulas {

    // Nombres de los dias para imprimir el horario de forma entendible (0 = Domingo ... 6 = Sabado)
    private static final String[] NOMBRES_DIAS = {
        "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"
    };

    private String nombre;
    private int capacidad;
    // true = hora ocupada, false = hora libre
    private boolean[][] horario;

    // Constructor: crea el aula con todas sus horas libres al principio
    public Aulas(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.horario = new boolean[7][24];
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean[][] getHorario() {
        return horario;
    }

    // Valida que el dia (0-6), la hora (0-23) y la duracion no se salgan de la matriz
    private boolean esRangoValido(int dia, int hora, int duracion) {
        return dia >= 0 && dia < 7 && hora >= 0 && duracion > 0 && (hora + duracion) <= 24;
    }

    // Consulta si una sola hora esta libre
    public boolean consultarDisponibilidad(int dia, int hora) {
        return consultarDisponibilidad(dia, hora, 1);
    }

    // Revisa si todo el bloque de horas pedido esta libre
    public boolean consultarDisponibilidad(int dia, int hora, int duracion) {
        if (!esRangoValido(dia, hora, duracion)) {
            return false;
        }
        for (int h = hora; h < hora + duracion; h++) {
            if (horario[dia][h]) {
                return false;
            }
        }
        return true;
    }

    // Marca las horas como ocupadas; si ya hay algo reservado o el rango es malo, lanza excepcion
    public void reservar(int dia, int hora, int duracion) throws HorarioConflictivoException {
        if (!esRangoValido(dia, hora, duracion)) {
            throw new HorarioConflictivoException("Dia, hora o duracion fuera de rango.");
        }
        if (!consultarDisponibilidad(dia, hora, duracion)) {
            throw new HorarioConflictivoException("El aula " + nombre + " ya esta ocupada en ese horario.");
        }
        for (int h = hora; h < hora + duracion; h++) {
            horario[dia][h] = true;
        }
    }

    // Vuelve a dejar libres las horas indicadas
    public void liberar(int dia, int hora, int duracion) {
        if (!esRangoValido(dia, hora, duracion)) {
            return;
        }
        for (int h = hora; h < hora + duracion; h++) {
            horario[dia][h] = false;
        }
    }

    // Recorre la matriz e imprime solo las horas que estan reservadas
    public void mostrarHorario() {
        System.out.println("Horario del Aula " + nombre + " (Capacidad: " + capacidad + "):");
        boolean hayReservas = false;

        for (int d = 0; d < 7; d++) {
            for (int h = 0; h < 24; h++) {
                if (horario[d][h]) {
                    System.out.println("  - " + NOMBRES_DIAS[d] + " a las " + h + ":00 -> OCUPADO");
                    hayReservas = true;
                }
            }
        }

        if (!hayReservas) {
            System.out.println("  Todas las horas estan disponibles.");
        }
    }

    @Override
    public String toString() {
        return nombre + " (Capacidad: " + capacidad + ")";
    }
}