package modelo;

import excepciones.ExcepcionesSistema.ColaDeEsperaVaciaException;
import excepciones.ExcepcionesSistema.CupoLlenoException;
import excepciones.ExcepcionesSistema.PreRequisitoNoAprobadoException;
import java.util.LinkedList;
import java.util.Queue;

// Clase Materia: maneja los datos de una asignatura, sus pre-requisitos, inscritos y cola de espera
public class Materia {

    private String codigo;
    private String nombre;
    private int creditos;
    private int cupoMaximo;
    // Lista de codigos de materias que el estudiante debe haber aprobado antes
    private LinkedList<String> preRequisitos;
    // Lista de estudiantes que si alcanzaron cupo en la materia
    private LinkedList<Estudiante> estudiantesInscritos;
    // Cola FIFO para guardar en orden de llegada a los estudiantes cuando no hay cupo
    private Queue<Estudiante> colaEspera;

    // Constructor: valida que creditos y cupo sean mayores a 0 e inicializa las listas y la cola
    public Materia(String codigo, String nombre, int creditos, int cupoMaximo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = (creditos > 0) ? creditos : 1;
        this.cupoMaximo = (cupoMaximo > 0) ? cupoMaximo : 1;
        this.preRequisitos = new LinkedList<>();
        this.estudiantesInscritos = new LinkedList<>();
        this.colaEspera = new LinkedList<>();
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        if (creditos > 0) {
            this.creditos = creditos;
        }
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo > 0) {
            this.cupoMaximo = cupoMaximo;
        }
    }

    public LinkedList<String> getPreRequisitos() {
        return preRequisitos;
    }

    public LinkedList<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public Queue<Estudiante> getColaEspera() {
        return colaEspera;
    }

    // Agrega un pre-requisito a la materia evitando duplicados
    public void agregarPreRequisito(String codigoPreRequisito) {
        if (codigoPreRequisito != null && !codigoPreRequisito.trim().isEmpty()) {
            String codigoLimpio = codigoPreRequisito.trim().toUpperCase();
            if (!preRequisitos.contains(codigoLimpio)) {
                preRequisitos.add(codigoLimpio);
            }
        }
    }

    // Revisa si todavia hay espacio para inscribir mas estudiantes
    public boolean tieneCupoDisponible() {
        return estudiantesInscritos.size() < cupoMaximo;
    }

    // Verifica si un estudiante ya esta inscrito en esta materia
    public boolean estaInscrito(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }
        for (Estudiante e : estudiantesInscritos) {
            if (e.getId().equalsIgnoreCase(estudiante.getId())) {
                return true;
            }
        }
        return false;
    }

    // Revisa que el estudiante tenga todos los pre-requisitos en su historial; si le falta uno, lanza excepcion
    public void validarPreRequisitos(Estudiante estudiante) throws PreRequisitoNoAprobadoException {
        for (String pre : preRequisitos) {
            if (!estudiante.tieneEnHistorial(pre)) {
                throw new PreRequisitoNoAprobadoException(
                        "El estudiante " + estudiante.getNombre() + " no ha aprobado el pre-requisito: " + pre);
            }
        }
    }

    // Inscribe al estudiante si cumple pre-requisitos y hay cupo; si no hay cupo, lo manda a la cola de espera
    public boolean inscribirEstudiante(Estudiante estudiante)
            throws PreRequisitoNoAprobadoException, CupoLlenoException {
        if (estudiante == null || estaInscrito(estudiante)) {
            return false;
        }

        validarPreRequisitos(estudiante);

        if (!tieneCupoDisponible()) {
            agregarAColaDeEspera(estudiante);
            throw new CupoLlenoException(
                    "La materia " + nombre + " no tiene cupos. " + estudiante.getNombre()
                            + " fue agregado a la cola de espera.");
        }

        estudiantesInscritos.add(estudiante);
        return true;
    }

    // Retira a un estudiante de la lista de inscritos si estaba registrado
    public boolean retirarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }

        Estudiante encontrado = null;
        for (Estudiante e : estudiantesInscritos) {
            if (e.getId().equalsIgnoreCase(estudiante.getId())) {
                encontrado = e;
                break;
            }
        }

        if (encontrado != null) {
            estudiantesInscritos.remove(encontrado);
            return true;
        }
        return false;
    }

    // Mete al estudiante en la cola de espera sin dejar que se repita
    public void agregarAColaDeEspera(Estudiante estudiante) {
        if (estudiante == null) {
            return;
        }
        for (Estudiante e : colaEspera) {
            if (e.getId().equalsIgnoreCase(estudiante.getId())) {
                return;
            }
        }
        colaEspera.add(estudiante);
    }

    // Saca al primero de la cola de espera y lo inscribe si hay cupo disponible
    public Estudiante atenderSiguienteEnCola() throws ColaDeEsperaVaciaException, CupoLlenoException {
        if (colaEspera.isEmpty()) {
            throw new ColaDeEsperaVaciaException("La cola de espera de " + nombre + " esta vacia.");
        }
        if (!tieneCupoDisponible()) {
            throw new CupoLlenoException("No hay cupos disponibles en " + nombre + " para atender la cola.");
        }
        Estudiante siguiente = colaEspera.poll();
        estudiantesInscritos.add(siguiente);
        return siguiente;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + creditos + " creditos) | Cupos: "
                + estudiantesInscritos.size() + "/" + cupoMaximo;
    }
}