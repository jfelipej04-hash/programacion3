package estructuras;

import excepciones.ExcepcionesSistema.EstudianteNoEncontradoException;
import excepciones.ExcepcionesSistema.PilaDeshacerVaciaException;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeMap;
import modelo.Aulas;
import modelo.Campus;
import modelo.Estudiante;
import modelo.Facultad;
import modelo.Materia;
import modelo.Profesor;
import modelo.Reporte;

// Aqui guardamos todas las estructuras de datos del sistema en un solo lugar
public class ContenedorEstructuras {

    // Mapas para buscar rapido por ID o codigo, TreeMap para aulas ordenadas y arreglo fijo para 5 facultades
    private HashMap<String, Estudiante> estudiantes;
    private HashMap<String, Profesor> profesores;
    private HashMap<String, Materia> materias;
    private TreeMap<String, Aulas> aulas;
    private Facultad[] facultades;
    private int cantidadFacultades;
    private Campus campus;

    // Pilas para manejar el deshacer y rehacer de operaciones
    private Stack<Operacion> pilaDeshacer;
    private Stack<Operacion> pilaRehacer;

    // Pilas para navegar hacia atras y adelante en los reportes generados
    private Stack<Reporte> historialReportesAtras;
    private Stack<Reporte> historialReportesAdelante;
    private Reporte reporteActual;

    // Constructor: inicializa todas las estructuras vacias
    public ContenedorEstructuras() {
        this.estudiantes = new HashMap<>();
        this.profesores = new HashMap<>();
        this.materias = new HashMap<>();
        this.aulas = new TreeMap<>();
        this.facultades = new Facultad[5];
        this.cantidadFacultades = 0;
        this.campus = new Campus();

        this.pilaDeshacer = new Stack<>();
        this.pilaRehacer = new Stack<>();

        this.historialReportesAtras = new Stack<>();
        this.historialReportesAdelante = new Stack<>();
        this.reporteActual = null;
    }

    // Getters para acceder a las estructuras desde el gestor
    public HashMap<String, Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public HashMap<String, Profesor> getProfesores() {
        return profesores;
    }

    public HashMap<String, Materia> getMaterias() {
        return materias;
    }

    public TreeMap<String, Aulas> getAulas() {
        return aulas;
    }

    public Facultad[] getFacultades() {
        return facultades;
    }

    public int getCantidadFacultades() {
        return cantidadFacultades;
    }

    public Campus getCampus() {
        return campus;
    }

    public Stack<Operacion> getPilaDeshacer() {
        return pilaDeshacer;
    }

    public Stack<Operacion> getPilaRehacer() {
        return pilaRehacer;
    }

    public Reporte getReporteActual() {
        return reporteActual;
    }

    // Agrega un estudiante al HashMap validando que el ID no este vacio ni repetido
    public boolean agregarEstudiante(Estudiante estudiante) {
        if (estudiante == null || estudiante.getId() == null || estudiante.getId().trim().isEmpty()) {
            return false;
        }
        String clave = estudiante.getId().trim().toUpperCase();
        if (estudiantes.containsKey(clave)) {
            return false;
        }
        estudiantes.put(clave, estudiante);
        return true;
    }

    // Busca un estudiante por su ID; si no existe lanza la excepcion
    public Estudiante buscarEstudiante(String id) throws EstudianteNoEncontradoException {
        if (id == null || id.trim().isEmpty()) {
            throw new EstudianteNoEncontradoException("El ID del estudiante no puede estar vacio.");
        }
        Estudiante encontrado = estudiantes.get(id.trim().toUpperCase());
        if (encontrado == null) {
            throw new EstudianteNoEncontradoException("No se encontro ningun estudiante con ID: " + id);
        }
        return encontrado;
    }

    // Elimina un estudiante del HashMap y lo devuelve para poder guardarlo en la pila de deshacer
    public Estudiante eliminarEstudiante(String id) throws EstudianteNoEncontradoException {
        if (id == null || id.trim().isEmpty()) {
            throw new EstudianteNoEncontradoException("El ID del estudiante no puede estar vacio.");
        }
        Estudiante eliminado = estudiantes.remove(id.trim().toUpperCase());
        if (eliminado == null) {
            throw new EstudianteNoEncontradoException("No se encontro ningun estudiante con ID: " + id);
        }
        return eliminado;
    }

    // Agrega un profesor al HashMap evitando IDs repetidos
    public boolean agregarProfesor(Profesor profesor) {
        if (profesor == null || profesor.getId() == null || profesor.getId().trim().isEmpty()) {
            return false;
        }
        String clave = profesor.getId().trim().toUpperCase();
        if (profesores.containsKey(clave)) {
            return false;
        }
        profesores.put(clave, profesor);
        return true;
    }

    // Busca un profesor por ID
    public Profesor buscarProfesor(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        return profesores.get(id.trim().toUpperCase());
    }

    // Agrega una materia al HashMap usando su codigo en mayusculas como clave
    public boolean agregarMateria(Materia materia) {
        if (materia == null || materia.getCodigo() == null || materia.getCodigo().trim().isEmpty()) {
            return false;
        }
        String clave = materia.getCodigo().trim().toUpperCase();
        if (materias.containsKey(clave)) {
            return false;
        }
        materias.put(clave, materia);
        return true;
    }

    // Busca una materia por su codigo
    public Materia buscarMateria(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        return materias.get(codigo.trim().toUpperCase());
    }

    // Agrega un aula al TreeMap para que siempre queden ordenadas alfabeticamente
    public boolean agregarAula(Aulas aula) {
        if (aula == null || aula.getNombre() == null || aula.getNombre().trim().isEmpty()) {
            return false;
        }
        String clave = aula.getNombre().trim().toUpperCase();
        if (aulas.containsKey(clave)) {
            return false;
        }
        aulas.put(clave, aula);
        return true;
    }

    // Busca un aula por su nombre
    public Aulas buscarAula(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return aulas.get(nombre.trim().toUpperCase());
    }

    // Agrega una facultad al arreglo fijo revisando que no supere el limite de 5 ni se repita
    public boolean agregarFacultad(Facultad facultad) {
        if (facultad == null || facultad.getCodigo() == null || facultad.getCodigo().trim().isEmpty()) {
            return false;
        }
        if (cantidadFacultades >= facultades.length) {
            return false;
        }
        if (buscarFacultad(facultad.getCodigo()) != null) {
            return false;
        }
        facultades[cantidadFacultades] = facultad;
        cantidadFacultades++;
        return true;
    }

    // Recorre el arreglo de facultades para buscar una por codigo
    public Facultad buscarFacultad(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        String codigoBuscado = codigo.trim();
        for (int i = 0; i < cantidadFacultades; i++) {
            if (facultades[i].getCodigo().equalsIgnoreCase(codigoBuscado)) {
                return facultades[i];
            }
        }
        return null;
    }

    // Guarda una nueva operacion en la pila de deshacer y limpia la de rehacer
    public void registrarOperacion(Operacion operacion) {
        if (operacion != null) {
            pilaDeshacer.push(operacion);
            pilaRehacer.clear();
        }
    }

    // Saca la ultima operacion de deshacer y la pasa a la pila de rehacer
    public Operacion sacarParaDeshacer() throws PilaDeshacerVaciaException {
        if (pilaDeshacer.isEmpty()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para deshacer.");
        }
        Operacion op = pilaDeshacer.pop();
        pilaRehacer.push(op);
        return op;
    }

    // Saca la ultima operacion de rehacer y la devuelve a la pila de deshacer
    public Operacion sacarParaRehacer() throws PilaDeshacerVaciaException {
        if (pilaRehacer.isEmpty()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para rehacer.");
        }
        Operacion op = pilaRehacer.pop();
        pilaDeshacer.push(op);
        return op;
    }

    // Abre un reporte nuevo, manda el actual al historial de atras y limpia el de adelante
    public void abrirReporte(Reporte nuevoReporte) {
        if (nuevoReporte == null) {
            return;
        }
        if (reporteActual != null) {
            historialReportesAtras.push(reporteActual);
        }
        reporteActual = nuevoReporte;
        historialReportesAdelante.clear();
    }

    // Retrocede al reporte anterior usando las pilas
    public Reporte irReporteAnterior() {
        if (historialReportesAtras.isEmpty()) {
            return null;
        }
        if (reporteActual != null) {
            historialReportesAdelante.push(reporteActual);
        }
        reporteActual = historialReportesAtras.pop();
        return reporteActual;
    }

    // Avanza al reporte siguiente si antes se habia retrocedido
    public Reporte irReporteSiguiente() {
        if (historialReportesAdelante.isEmpty()) {
            return null;
        }
        if (reporteActual != null) {
            historialReportesAtras.push(reporteActual);
        }
        reporteActual = historialReportesAdelante.pop();
        return reporteActual;
    }
}