package estructuras;
import modelo.Estudiante;

// Clase para guardar los datos de cada accion y poder deshacerla o rehacerla despues
public class Operacion {
    // Constantes para identificar que tipo de operacion se hizo
    public static final String RETIRO = "RETIRO";
    public static final String NOTA = "NOTA";
    public static final String RESERVA_AULA = "RESERVA_AULA";
    public static final String LIBERAR_AULA = "LIBERAR_AULA";
    public static final String ELIMINAR_ESTUDIANTE = "ELIMINAR_ESTUDIANTE";
    public static final String INSCRIPCION = "INSCRIPCION";

    private String tipo;
    private String descripcion;

    // Datos usados para inscripciones y retiros
    private String idEstudiante;
    private String codigoMateria;

    // Datos usados para registro de notas (guarda la nota anterior y la nueva)
    private int semestre;
    private int indiceMateria;
    private Double notaAnterior;
    private Double notaNueva;

    // Datos usados para reservas/liberaciones de aulas y eliminacion de estudiantes
    private String nombreAula;
    private int dia;
    private int hora;
    private int duracion;
    private Estudiante estudianteEliminado;

    // Constructor para operaciones de inscripcion o retiro de materias
    public Operacion(String tipo, String descripcion, String idEstudiante, String codigoMateria) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
    }

    // Constructor para operaciones de registro de notas
    public Operacion(String tipo, String descripcion, String idEstudiante,
            int semestre, int indiceMateria, Double notaAnterior, Double notaNueva) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idEstudiante = idEstudiante;
        this.semestre = semestre;
        this.indiceMateria = indiceMateria;
        this.notaAnterior = notaAnterior;
        this.notaNueva = notaNueva;
    }

    // Constructor para operaciones de reserva o liberacion de aulas
    public Operacion(String tipo, String descripcion, String nombreAula, int dia, int hora, int duracion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.nombreAula = nombreAula;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
    }

    // Constructor para guardar un estudiante eliminado y poder restaurarlo si se deshace
    public Operacion(String tipo, String descripcion, Estudiante estudianteEliminado) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estudianteEliminado = estudianteEliminado;
    }

    // Getters de todos los atributos
    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getIndiceMateria() {
        return indiceMateria;
    }

    public Double getNotaAnterior() {
        return notaAnterior;
    }

    public Double getNotaNueva() {
        return notaNueva;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public int getDuracion() {
        return duracion;
    }

    public Estudiante getEstudianteEliminado() {
        return estudianteEliminado;
    }

    @Override
    public String toString() {
        return "[" + tipo + "] " + descripcion;
    }
}