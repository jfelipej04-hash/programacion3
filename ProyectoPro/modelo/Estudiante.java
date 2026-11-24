package modelo;

import java.util.LinkedList;

// Clase Estudiante: hereda de Persona y maneja el semestre, la matriz de notas y el historial de materias
public class Estudiante extends Persona {
    private int semestre;
    // Matriz de 10 semestres por maximo 20 materias en cada uno
    private Double [][] notas;
    // Lista enlazada para guardar los codigos de materias que ya aprobo
    private LinkedList<String> historialMaterias;

    // Constructor: inicializa los datos de Persona, el semestre, la matriz vacia y el historial
    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        this.semestre = semestre;
        this.notas = new Double[10][20];
        this.historialMaterias = new LinkedList<>();
    }

    // Getters y setters
    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Double[][] getNotas() {
        return notas;
    }

    public void setNotas(Double[][] notas) {
        this.notas = notas;
    }

    public LinkedList<String> getHistorialMaterias() {
        return historialMaterias;
    }

    public void setHistorialMaterias(LinkedList<String> historialMaterias) {
        this.historialMaterias = historialMaterias;
    }

    // Agrega el codigo de una materia aprobada al historial evitando duplicados
    public void agregarMateriaHistorial(String codigoMateria) {
        if (codigoMateria != null && !codigoMateria.trim().isEmpty()) {
            String codigoLimpio = codigoMateria.trim().toUpperCase();
            if (!tieneEnHistorial(codigoLimpio)) {
                historialMaterias.add(codigoLimpio);
            }
        }
    }

    // Busca y elimina una materia del historial si existe
    public boolean eliminarMateriaHistorial(String codigoMateria) {
        if (codigoMateria == null) {
            return false;
        }

        String codigoEncontrado = null;

        for (String codigo : historialMaterias) {
            if (codigo.equalsIgnoreCase(codigoMateria.trim())) {
                codigoEncontrado = codigo;
                break;
            }
        }

        if (codigoEncontrado != null) {
            historialMaterias.remove(codigoEncontrado);
            return true;
        }
        return false;
    }

    // Revisa si el estudiante ya tiene una materia especifica en su historial (sirve para validar pre-requisitos)
    public boolean tieneEnHistorial(String codigoMateria) {
        if (codigoMateria == null) {
            return false;
        }

        for (String codigo : historialMaterias) {
            if (codigo.equalsIgnoreCase(codigoMateria.trim())) {
                return true;
            }
        }
        return false;
    }

    // Registra una nota en la primera posicion libre del semestre indicado
    public int registrarNota(int semestreNumero, double nota) {
        int fila = semestreNumero - 1; 

        if (fila < 0 || fila >= 10 || nota < 0.0 || nota > 5.0) {
            return -1;
        }

        for (int j = 0; j < 20; j++) {
            if (notas[fila][j] == null) {
                notas[fila][j] = nota;
                return j; 
            }
        }

        return -1;
    }

    // Registra o actualiza una nota en una posicion exacta (semestre e indice de materia)
    public boolean registrarNota(int semestreNumero, int indiceMateria, Double nota) {
        int fila = semestreNumero - 1;

        if (fila < 0 || fila >= 10 || indiceMateria < 0 || indiceMateria >= 20) {
            return false;
        }

        if (nota != null && (nota < 0.0 || nota > 5.0)) {
            return false;
        }

        notas[fila][indiceMateria] = nota;
        return true;
    }

    // Devuelve la nota guardada en un semestre y posicion especifica
    public Double obtenerNota(int semestreNumero, int indiceMateria) {
        int fila = semestreNumero - 1;
        if (fila < 0 || fila >= 10 || indiceMateria < 0 || indiceMateria >= 20) {
            return null;
        }
        return notas[fila][indiceMateria];
    }

    // Calcula el promedio solo de las notas registradas en un semestre
    public double calcularPromedioSemestre(int semestreNumero) {
        int fila = semestreNumero - 1;
        if (fila < 0 || fila >= 10) {
            return 0.0;
        }

        double suma = 0.0;
        int cantidad = 0;

        for (int j = 0; j < 20; j++) {
            if (notas[fila][j] != null) {
                suma += notas[fila][j];
                cantidad++;
            }
        }

        if (cantidad == 0) {
            return 0.0;
        }
        return suma / cantidad;
    }

    // Recorre toda la matriz para calcular el promedio acumulado de la carrera
    public double calcularPromedioAcumulado() {
        double sumaTotal = 0.0;
        int cantidadTotal = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null) {
                    sumaTotal += notas[i][j];
                    cantidadTotal++;
                }
            }
        }

        if (cantidadTotal == 0) {
            return 0.0;
        }
        return sumaTotal / cantidadTotal;
    }

    // Cuenta cuantas notas registradas son mayores o iguales a 3.0
    public int contarMateriasAprobadas() {
        int aprobadas = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null && notas[i][j] >= 3.0) {
                    aprobadas++;
                }
            }
        }
        return aprobadas;
    }

    // Cuenta cuantas notas registradas son menores a 3.0
    public int contarMateriasReprobadas() {
        int reprobadas = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null && notas[i][j] < 3.0) {
                    reprobadas++;
                }
            }
        }
        return reprobadas;
    }

    // Verifica si el estudiante tiene al menos una nota registrada en la matriz
    public boolean tieneNotasRegistradas() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    // Muestra toda la informacion del estudiante por consola
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Semestre: " + semestre);
        System.out.println("Historial de Materias: " + historialMaterias);
    }

    @Override
    public String toString() {
        return getId() + " - " + getNombre() + " - Semestre: " + semestre;
    }
    
}