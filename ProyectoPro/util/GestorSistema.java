package util;

import estructuras.ContenedorEstructuras;
import estructuras.Operacion;
import excepciones.ExcepcionesSistema.ArchivoInvalidoException;
import excepciones.ExcepcionesSistema.ColaDeEsperaVaciaException;
import excepciones.ExcepcionesSistema.CupoLlenoException;
import excepciones.ExcepcionesSistema.EstudianteNoEncontradoException;
import excepciones.ExcepcionesSistema.HorarioConflictivoException;
import excepciones.ExcepcionesSistema.PilaDeshacerVaciaException;
import excepciones.ExcepcionesSistema.PreRequisitoNoAprobadoException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import modelo.Aulas;
import modelo.Campus;
import modelo.Estudiante;
import modelo.Facultad;
import modelo.Materia;
import modelo.Profesor;
import modelo.Reporte;

// Clase GestorSistema: conecta los modelos con las estructuras de datos y ejecuta toda la logica del sistema
public class GestorSistema {

    private ContenedorEstructuras contenedor;

    // Constructor: crea el contenedor de estructuras y carga el mapa base del campus
    public GestorSistema() {
        this.contenedor = new ContenedorEstructuras();
        cargarCampusBase();
    }

    public ContenedorEstructuras getContenedor() {
        return contenedor;
    }

    // =========================================================
    // 1. REGISTRO, CONSULTA Y BAJA DE ENTIDADES
    // =========================================================

    // Registra un estudiante nuevo en el contenedor
    public boolean registrarEstudiante(Estudiante estudiante) {
        return contenedor.agregarEstudiante(estudiante);
    }

    // Busca un estudiante por su ID
    public Estudiante buscarEstudiante(String id) throws EstudianteNoEncontradoException {
        return contenedor.buscarEstudiante(id);
    }

    // Elimina un estudiante y guarda la accion en la pila de deshacer por si se necesita restaurar
    public Estudiante eliminarEstudiante(String id) throws EstudianteNoEncontradoException {
        Estudiante eliminado = contenedor.eliminarEstudiante(id);
        Operacion op = new Operacion(
                Operacion.ELIMINAR_ESTUDIANTE,
                "Eliminacion del estudiante " + eliminado.getNombre() + " (ID: " + eliminado.getId() + ")",
                eliminado);
        contenedor.registrarOperacion(op);
        return eliminado;
    }

    // Registra un profesor nuevo
    public boolean registrarProfesor(Profesor profesor) {
        return contenedor.agregarProfesor(profesor);
    }

    // Busca un profesor por su ID
    public Profesor buscarProfesor(String id) {
        return contenedor.buscarProfesor(id);
    }

    // Registra una facultad en el arreglo fijo (maximo 5)
    public boolean registrarFacultad(Facultad facultad) {
        return contenedor.agregarFacultad(facultad);
    }

    // Registra una materia nueva
    public boolean registrarMateria(Materia materia) {
        return contenedor.agregarMateria(materia);
    }

    // Busca una materia por su codigo
    public Materia buscarMateria(String codigo) {
        return contenedor.buscarMateria(codigo);
    }

    // Registra un aula nueva en el TreeMap
    public boolean registrarAula(Aulas aula) {
        return contenedor.agregarAula(aula);
    }

    // Busca un aula por su nombre
    public Aulas buscarAula(String nombre) {
        return contenedor.buscarAula(nombre);
    }

    // Imprime todos los estudiantes registrados
    public void listarEstudiantes() {
        System.out.println("=== LISTA DE ESTUDIANTES ===");
        if (contenedor.getEstudiantes().isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : contenedor.getEstudiantes().values()) {
            System.out.println("  - " + e);
        }
    }

    // Imprime todos los profesores registrados
    public void listarProfesores() {
        System.out.println("=== LISTA DE PROFESORES ===");
        if (contenedor.getProfesores().isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }
        for (Profesor p : contenedor.getProfesores().values()) {
            System.out.println("  - " + p);
        }
    }

    // Imprime las facultades guardadas en el arreglo
    public void listarFacultades() {
        System.out.println("=== LISTA DE FACULTADES ===");
        int total = contenedor.getCantidadFacultades();
        if (total == 0) {
            System.out.println("No hay facultades registradas.");
            return;
        }
        Facultad[] facultades = contenedor.getFacultades();
        for (int i = 0; i < total; i++) {
            System.out.println("  " + (i + 1) + ". " + facultades[i]);
        }
    }

    // Imprime todas las materias registradas
    public void listarMaterias() {
        System.out.println("=== LISTA DE MATERIAS ===");
        if (contenedor.getMaterias().isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }
        for (Materia m : contenedor.getMaterias().values()) {
            System.out.println("  - " + m);
        }
    }

    // Imprime todas las aulas (salen ordenadas alfabeticamente gracias al TreeMap)
    public void listarAulas() {
        System.out.println("=== LISTA DE AULAS (ORDENADAS) ===");
        if (contenedor.getAulas().isEmpty()) {
            System.out.println("No hay aulas registradas.");
            return;
        }
        for (Aulas a : contenedor.getAulas().values()) {
            System.out.println("  - " + a);
        }
    }

    // =========================================================
    // 2. PRE-REQUISITOS, HISTORIAL, INSCRIPCIONES Y COLA
    // =========================================================

    // Agrega un codigo de pre-requisito a una materia existente
    public boolean agregarPreRequisitoAMateria(String codigoMateria, String codigoPreRequisito) {
        Materia materia = contenedor.buscarMateria(codigoMateria);
        if (materia == null) {
            System.out.println("Error: No se encontro la materia " + codigoMateria);
            return false;
        }
        materia.agregarPreRequisito(codigoPreRequisito);
        return true;
    }

    // Muestra en consola los pre-requisitos configurados para una materia
    public void mostrarPreRequisitosMateria(String codigoMateria) {
        Materia materia = contenedor.buscarMateria(codigoMateria);
        if (materia == null) {
            System.out.println("Error: No se encontro la materia " + codigoMateria);
            return;
        }

        System.out.println("=== PRE-REQUISITOS DE " + materia.getCodigo() + " - " + materia.getNombre() + " ===");
        if (materia.getPreRequisitos().isEmpty()) {
            System.out.println("Esta materia no tiene pre-requisitos.");
            return;
        }
        for (String pre : materia.getPreRequisitos()) {
            System.out.println("  - " + pre);
        }
    }

    // Agrega una materia aprobada al historial del estudiante para que pueda cumplir pre-requisitos
    public boolean agregarMateriaHistorialEstudiante(String idEstudiante, String codigoMateria)
            throws EstudianteNoEncontradoException {
        Estudiante estudiante = contenedor.buscarEstudiante(idEstudiante);
        estudiante.agregarMateriaHistorial(codigoMateria);
        return true;
    }

    // Inscribe al estudiante en la materia y registra la operacion en la pila de deshacer
    public boolean inscribirEstudianteEnMateria(String idEstudiante, String codigoMateria)
            throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException, CupoLlenoException {

        Estudiante estudiante = contenedor.buscarEstudiante(idEstudiante);
        Materia materia = contenedor.buscarMateria(codigoMateria);

        if (materia == null) {
            System.out.println("Error: No se encontro la materia con codigo " + codigoMateria);
            return false;
        }

        boolean inscrito = materia.inscribirEstudiante(estudiante);
        if (inscrito) {
            Operacion op = new Operacion(
                    Operacion.INSCRIPCION,
                    "Inscripcion de " + estudiante.getNombre() + " en " + materia.getNombre(),
                    estudiante.getId(),
                    materia.getCodigo());
            contenedor.registrarOperacion(op);
        }
        return inscrito;
    }

    // Retira al estudiante de la materia, guarda la operacion y si hay cola de espera asigna el cupo automaticamente
    public boolean retirarEstudianteDeMateria(String idEstudiante, String codigoMateria)
            throws EstudianteNoEncontradoException {

        Estudiante estudiante = contenedor.buscarEstudiante(idEstudiante);
        Materia materia = contenedor.buscarMateria(codigoMateria);

        if (materia == null) {
            System.out.println("Error: No se encontro la materia con codigo " + codigoMateria);
            return false;
        }

        boolean retirado = materia.retirarEstudiante(estudiante);
        if (retirado) {
            Operacion op = new Operacion(
                    Operacion.RETIRO,
                    "Retiro de " + estudiante.getNombre() + " de " + materia.getNombre(),
                    estudiante.getId(),
                    materia.getCodigo());
            contenedor.registrarOperacion(op);

            // Al liberarse un cupo, se asigna automaticamente al primer estudiante en cola
            if (!materia.getColaEspera().isEmpty() && materia.tieneCupoDisponible()) {
                try {
                    Estudiante siguiente = materia.atenderSiguienteEnCola();
                    System.out.println("Cupo liberado. Asignando cupo automaticamente a "
                            + siguiente.getNombre() + " (primer estudiante en cola).");
                    Operacion opCola = new Operacion(
                            Operacion.INSCRIPCION,
                            "Asignacion automatica desde cola: " + siguiente.getNombre() + " en " + materia.getNombre(),
                            siguiente.getId(),
                            materia.getCodigo());
                    contenedor.registrarOperacion(opCola);
                } catch (ColaDeEsperaVaciaException | CupoLlenoException e) {
                    System.out.println("Aviso cola de espera: " + e.getMessage());
                }
            }
        }
        return retirado;
    }

    // Atiende manualmente al siguiente estudiante en la cola de espera de una materia
    public Estudiante atenderColaDeEspera(String codigoMateria)
            throws ColaDeEsperaVaciaException, CupoLlenoException {

        Materia materia = contenedor.buscarMateria(codigoMateria);
        if (materia == null) {
            System.out.println("Error: No se encontro la materia con codigo " + codigoMateria);
            return null;
        }

        Estudiante atendido = materia.atenderSiguienteEnCola();
        if (atendido != null) {
            Operacion op = new Operacion(
                    Operacion.INSCRIPCION,
                    "Atencion cola de espera: " + atendido.getNombre() + " en " + materia.getNombre(),
                    atendido.getId(),
                    materia.getCodigo());
            contenedor.registrarOperacion(op);
        }
        return atendido;
    }

    // Imprime en orden de llegada los estudiantes que estan esperando cupo en una materia
    public void mostrarColaDeEspera(String codigoMateria) {
        Materia materia = contenedor.buscarMateria(codigoMateria);
        if (materia == null) {
            System.out.println("Error: No se encontro la materia " + codigoMateria);
            return;
        }

        Queue<Estudiante> cola = materia.getColaEspera();
        System.out.println("=== COLA DE ESPERA: " + materia.getNombre() + " ===");
        if (cola.isEmpty()) {
            System.out.println("No hay estudiantes en cola de espera.");
            return;
        }

        int turno = 1;
        for (Estudiante e : cola) {
            System.out.println("  Posicion " + turno + ": " + e.getId() + " - " + e.getNombre());
            turno++;
        }
        System.out.println("Total en espera: " + cola.size());
    }

    // =========================================================
    // 3. GESTION DE NOTAS
    // =========================================================

    // Valida rangos, registra la nota en la matriz del estudiante y guarda la nota anterior para poder deshacer
    public boolean registrarNota(String idEstudiante, int semestre, int indiceMateria, double nota)
            throws EstudianteNoEncontradoException {

        if (semestre < 1 || semestre > 10 || indiceMateria < 1 || indiceMateria > 20) {
            System.out.println("Error: Semestre (1-10) o numero de materia (1-20) fuera de rango.");
            return false;
        }
        if (nota < 0.0 || nota > 5.0) {
            System.out.println("Error: La nota debe estar entre 0.0 y 5.0.");
            return false;
        }

        Estudiante estudiante = contenedor.buscarEstudiante(idEstudiante);
        Double notaAnterior = estudiante.obtenerNota(semestre, indiceMateria - 1);
        estudiante.registrarNota(semestre, indiceMateria - 1, nota);

        Operacion op = new Operacion(
                Operacion.NOTA,
                "Nota registrada a " + estudiante.getNombre() + " (Sem " + semestre + ", Mat " + indiceMateria + "): " + nota,
                estudiante.getId(),
                semestre,
                indiceMateria,
                notaAnterior,
                nota);
        contenedor.registrarOperacion(op);
        return true;
    }

    // =========================================================
    // 4. HORARIOS Y RESERVA DE AULAS
    // =========================================================

    // Consulta si un aula esta libre en un dia y hora exactos
    public boolean consultarDisponibilidadAula(String nombreAula, int dia, int hora) {
        Aulas aula = contenedor.buscarAula(nombreAula);
        if (aula == null) {
            System.out.println("Error: No se encontro el aula " + nombreAula);
            return false;
        }
        return aula.consultarDisponibilidad(dia, hora);
    }

    // Muestra todas las reservas actuales de un aula
    public void mostrarHorarioAula(String nombreAula) {
        Aulas aula = contenedor.buscarAula(nombreAula);
        if (aula == null) {
            System.out.println("Error: No se encontro el aula " + nombreAula);
            return;
        }
        aula.mostrarHorario();
    }

    // Reserva un bloque de horas en un aula y registra la accion en la pila de deshacer
    public boolean reservarAula(String nombreAula, int dia, int hora, int duracion)
            throws HorarioConflictivoException {

        Aulas aula = contenedor.buscarAula(nombreAula);
        if (aula == null) {
            System.out.println("Error: No se encontro el aula " + nombreAula);
            return false;
        }

        aula.reservar(dia, hora, duracion);

        Operacion op = new Operacion(
                Operacion.RESERVA_AULA,
                "Reserva del aula " + aula.getNombre() + " (dia " + dia + ", hora " + hora + ", duracion " + duracion + "h)",
                aula.getNombre(),
                dia,
                hora,
                duracion);
        contenedor.registrarOperacion(op);
        return true;
    }

    // Libera un bloque de horas en un aula y guarda la operacion para poder deshacerla
    public boolean liberarAula(String nombreAula, int dia, int hora, int duracion) {
        Aulas aula = contenedor.buscarAula(nombreAula);
        if (aula == null) {
            System.out.println("Error: No se encontro el aula " + nombreAula);
            return false;
        }
        if (dia < 0 || dia >= 7 || hora < 0 || duracion <= 0 || (hora + duracion) > 24) {
            System.out.println("Error: Dia, hora o duracion fuera de rango.");
            return false;
        }

        aula.liberar(dia, hora, duracion);

        Operacion op = new Operacion(
                Operacion.LIBERAR_AULA,
                "Liberacion del aula " + aula.getNombre() + " (dia " + dia + ", hora " + hora + ", duracion " + duracion + "h)",
                aula.getNombre(),
                dia,
                hora,
                duracion);
        contenedor.registrarOperacion(op);
        return true;
    }

    // =========================================================
    // 5. DESHACER Y REHACER (PILAS)
    // =========================================================

    // Revierte la ultima operacion realizada segun su tipo (hace la accion contraria)
    public String deshacerUltimaOperacion() throws PilaDeshacerVaciaException {
        Operacion op = contenedor.sacarParaDeshacer();
        String tipo = op.getTipo();

        try {
            if (tipo.equals(Operacion.INSCRIPCION)) {
                Estudiante est = contenedor.buscarEstudiante(op.getIdEstudiante());
                Materia mat = contenedor.buscarMateria(op.getCodigoMateria());
                if (mat != null) {
                    mat.retirarEstudiante(est);
                }
            } else if (tipo.equals(Operacion.RETIRO)) {
                Estudiante est = contenedor.buscarEstudiante(op.getIdEstudiante());
                Materia mat = contenedor.buscarMateria(op.getCodigoMateria());
                if (mat != null) {
                    mat.inscribirEstudiante(est);
                }
            } else if (tipo.equals(Operacion.NOTA)) {
                Estudiante est = contenedor.buscarEstudiante(op.getIdEstudiante());
                est.registrarNota(op.getSemestre(), op.getIndiceMateria() - 1, op.getNotaAnterior());
            } else if (tipo.equals(Operacion.RESERVA_AULA)) {
                Aulas aula = contenedor.buscarAula(op.getNombreAula());
                if (aula != null) {
                    aula.liberar(op.getDia(), op.getHora(), op.getDuracion());
                }
            } else if (tipo.equals(Operacion.LIBERAR_AULA)) {
                Aulas aula = contenedor.buscarAula(op.getNombreAula());
                if (aula != null) {
                    aula.reservar(op.getDia(), op.getHora(), op.getDuracion());
                }
            } else if (tipo.equals(Operacion.ELIMINAR_ESTUDIANTE)) {
                Estudiante est = op.getEstudianteEliminado();
                if (est != null) {
                    contenedor.agregarEstudiante(est);
                }
            }
        } catch (Exception e) {
            return "Se deshizo parcialmente (" + op.getDescripcion() + "): " + e.getMessage();
        }

        return "Operacion deshecha: " + op.getDescripcion();
    }

    // Vuelve a aplicar la ultima operacion que se habia deshecho
    public String rehacerOperacion() throws PilaDeshacerVaciaException {
        Operacion op = contenedor.sacarParaRehacer();
        String tipo = op.getTipo();

        try {
            if (tipo.equals(Operacion.INSCRIPCION)) {
                Estudiante est = contenedor.buscarEstudiante(op.getIdEstudiante());
                Materia mat = contenedor.buscarMateria(op.getCodigoMateria());
                if (mat != null) {
                    mat.inscribirEstudiante(est);
                }
            } else if (tipo.equals(Operacion.RETIRO)) {
                Estudiante est = contenedor.buscarEstudiante(op.getIdEstudiante());
                Materia mat = contenedor.buscarMateria(op.getCodigoMateria());
                if (mat != null) {
                    mat.retirarEstudiante(est);
                }
            } else if (tipo.equals(Operacion.NOTA)) {
                Estudiante est = contenedor.buscarEstudiante(op.getIdEstudiante());
                est.registrarNota(op.getSemestre(), op.getIndiceMateria() - 1, op.getNotaNueva());
            } else if (tipo.equals(Operacion.RESERVA_AULA)) {
                Aulas aula = contenedor.buscarAula(op.getNombreAula());
                if (aula != null) {
                    aula.reservar(op.getDia(), op.getHora(), op.getDuracion());
                }
            } else if (tipo.equals(Operacion.LIBERAR_AULA)) {
                Aulas aula = contenedor.buscarAula(op.getNombreAula());
                if (aula != null) {
                    aula.liberar(op.getDia(), op.getHora(), op.getDuracion());
                }
            } else if (tipo.equals(Operacion.ELIMINAR_ESTUDIANTE)) {
                Estudiante est = op.getEstudianteEliminado();
                if (est != null) {
                    contenedor.eliminarEstudiante(est.getId());
                }
            }
        } catch (Exception e) {
            return "Se rehizo parcialmente (" + op.getDescripcion() + "): " + e.getMessage();
        }

        return "Operacion rehecha: " + op.getDescripcion();
    }

    // =========================================================
    // 6. REPORTES Y NAVEGACION (ATRAS / ADELANTE)
    // =========================================================

    // Genera el reporte completo de un estudiante y lo guarda en el historial de navegacion
    public Reporte generarReporteCompleto(String idEstudiante) throws EstudianteNoEncontradoException {
        Estudiante est = contenedor.buscarEstudiante(idEstudiante);
        Reporte reporte = new Reporte(est);
        contenedor.abrirReporte(reporte);
        return reporte;
    }

    // Genera el reporte de un solo semestre y lo guarda en el historial
    public Reporte generarReporteSemestre(String idEstudiante, int semestre) throws EstudianteNoEncontradoException {
        Estudiante est = contenedor.buscarEstudiante(idEstudiante);
        Reporte reporte = new Reporte(est, semestre);
        contenedor.abrirReporte(reporte);
        return reporte;
    }

    // Genera el reporte de materias reprobadas y lo guarda en el historial
    public Reporte generarReporteReprobadas(String idEstudiante) throws EstudianteNoEncontradoException {
        Estudiante est = contenedor.buscarEstudiante(idEstudiante);
        Reporte reporte = new Reporte(est);
        reporte.generarReporteReprobadas();
        contenedor.abrirReporte(reporte);
        return reporte;
    }

    // Retrocede al reporte anterior en la pila
    public Reporte irReporteAnterior() {
        return contenedor.irReporteAnterior();
    }

    // Avanza al reporte siguiente en la pila
    public Reporte irReporteSiguiente() {
        return contenedor.irReporteSiguiente();
    }

    // =========================================================
    // 7. CAMPUS (GRAFO Y DIJKSTRA)
    // =========================================================

    // Carga 5 edificios iniciales y sus distancias para que el mapa funcione apenas arranca el programa
    public void cargarCampusBase() {
        Campus campus = contenedor.getCampus();
        campus.agregarEdificio("Biblioteca");
        campus.agregarEdificio("Ingenieria");
        campus.agregarEdificio("Cafeteria");
        campus.agregarEdificio("Administracion");
        campus.agregarEdificio("Laboratorios");

        campus.conectarEdificios("Biblioteca", "Ingenieria", 120);
        campus.conectarEdificios("Biblioteca", "Administracion", 80);
        campus.conectarEdificios("Ingenieria", "Laboratorios", 60);
        campus.conectarEdificios("Ingenieria", "Cafeteria", 150);
        campus.conectarEdificios("Administracion", "Cafeteria", 100);
        campus.conectarEdificios("Cafeteria", "Laboratorios", 90);
    }

    // Agrega un nuevo edificio al grafo del campus
    public boolean agregarEdificioCampus(String nombre) {
        return contenedor.getCampus().agregarEdificio(nombre);
    }

    // Crea un camino con distancia en metros entre dos edificios
    public boolean conectarEdificiosCampus(String origen, String destino, int distancia) {
        return contenedor.getCampus().conectarEdificios(origen, destino, distancia);
    }

    // Muestra la lista de edificios del campus
    public void mostrarEdificiosCampus() {
        contenedor.getCampus().mostrarEdificios();
    }

    // Muestra todas las conexiones del campus
    public void mostrarMapaCampus() {
        contenedor.getCampus().mostrarMapaCampus();
    }

    // Calcula la ruta mas corta entre dos edificios usando Dijkstra
    public String calcularRutaMasCorta(String origen, String destino) {
        return contenedor.getCampus().calcularRutaMasCorta(origen, destino);
    }

    // =========================================================
    // 8. ARCHIVOS CSV (ESTUDIANTES, MATERIAS Y BATCH CON COLA)
    // =========================================================

    // Lee el archivo estudiantes.csv linea por linea y registra cada estudiante en el sistema
    public int cargarEstudiantesCSV(String rutaArchivo) throws ArchivoInvalidoException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || !archivo.isFile()) {
            throw new ArchivoInvalidoException("El archivo CSV no existe: " + rutaArchivo);
        }

        int cargados = 0;
        Scanner lector = null;

        try {
            lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                if (linea.toLowerCase().startsWith("id,")) {
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length < 4) {
                    throw new ArchivoInvalidoException("Linea con formato incorrecto en estudiantes.csv: " + linea);
                }

                String id = partes[0].trim();
                String nombre = partes[1].trim();
                String correo = partes[2].trim();
                int semestre = Integer.parseInt(partes[3].trim());

                Estudiante est = new Estudiante(nombre, id, correo, semestre);
                if (contenedor.agregarEstudiante(est)) {
                    cargados++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new ArchivoInvalidoException("Error al leer estudiantes.csv: " + e.getMessage());
        } finally {
            if (lector != null) {
                lector.close();
            }
        }

        return cargados;
    }

    // Lee el archivo materia.csv y carga todas las materias con su cupo y creditos
    public int cargarMateriasCSV(String rutaArchivo) throws ArchivoInvalidoException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || !archivo.isFile()) {
            throw new ArchivoInvalidoException("El archivo CSV no existe: " + rutaArchivo);
        }

        int cargadas = 0;
        Scanner lector = null;

        try {
            lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                if (linea.toLowerCase().startsWith("codigo,")) {
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length < 4) {
                    throw new ArchivoInvalidoException("Linea con formato incorrecto en materia.csv: " + linea);
                }

                String codigo = partes[0].trim().toUpperCase();
                String nombre = partes[1].trim();
                int cupo = Integer.parseInt(partes[2].trim());
                int creditos = Integer.parseInt(partes[3].trim());

                Materia materia = new Materia(codigo, nombre, creditos, cupo);
                if (contenedor.agregarMateria(materia)) {
                    cargadas++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new ArchivoInvalidoException("Error al leer materia.csv: " + e.getMessage());
        } finally {
            if (lector != null) {
                lector.close();
            }
        }

        return cargadas;
    }

    // Guarda todas las materias actuales del sistema en un archivo CSV
    public void guardarMateriasCSV(String rutaArchivo) throws ArchivoInvalidoException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(rutaArchivo));
            pw.println("codigo,nombre,cupoMaximo,creditos");
            for (Materia m : contenedor.getMaterias().values()) {
                pw.println(m.getCodigo() + "," + m.getNombre() + "," + m.getCupoMaximo() + "," + m.getCreditos());
            }
        } catch (IOException e) {
            throw new ArchivoInvalidoException("No se pudo guardar el archivo CSV: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    // Lee inscripciones.csv, encola todas las solicitudes en una Queue FIFO y las procesa una por una
    public void cargarInscripcionesCSV(String rutaArchivo) throws ArchivoInvalidoException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || !archivo.isFile()) {
            throw new ArchivoInvalidoException("El archivo CSV no existe: " + rutaArchivo);
        }

        // Paso 1: Encolar todas las solicitudes del archivo en la cola batch
        Queue<String> colaSolicitudes = new LinkedList<>();
        Scanner lector = null;
        int numeroLinea = 0;

        try {
            lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                numeroLinea++;

                if (linea.isEmpty()) {
                    continue;
                }
                String lineaMin = linea.toLowerCase();
                if (numeroLinea == 1 && (lineaMin.contains("idestudiante")
                        || lineaMin.contains("id_estudiante")
                        || lineaMin.startsWith("id,")
                        || lineaMin.contains("codigo"))) {
                    continue;
                }

                colaSolicitudes.add(linea);
            }
        } catch (IOException e) {
            throw new ArchivoInvalidoException("Error al leer inscripciones.csv: " + e.getMessage());
        } finally {
            if (lector != null) {
                lector.close();
            }
        }

        // Paso 2: Procesar secuencialmente la cola FIFO
        int totalSolicitudes = colaSolicitudes.size();
        int procesadas = 0;
        int exitosas = 0;
        int enCola = 0;
        int fallidas = 0;

        System.out.println("=== PROCESAMIENTO MASIVO (BATCH) ===");
        System.out.println("Archivo: " + rutaArchivo);
        System.out.println("Se encolaron " + totalSolicitudes + " solicitudes.");
        System.out.println("Procesando cola...");

        while (!colaSolicitudes.isEmpty()) {
            String solicitud = colaSolicitudes.poll();
            procesadas++;

            String[] partes = solicitud.split(",");
            if (partes.length < 2) {
                System.out.println("[" + procesadas + "/" + totalSolicitudes + "] Formato invalido -> " + solicitud);
                fallidas++;
                continue;
            }

            String idEstudiante = partes[0].trim();
            String codigoMateria = partes[1].trim();

            try {
                boolean inscrito = inscribirEstudianteEnMateria(idEstudiante, codigoMateria);
                if (inscrito) {
                    System.out.println("[" + procesadas + "/" + totalSolicitudes + "] "
                            + idEstudiante + " -> " + codigoMateria + " : Exitosa");
                    exitosas++;
                } else {
                    System.out.println("[" + procesadas + "/" + totalSolicitudes + "] "
                            + idEstudiante + " -> " + codigoMateria + " : Fallida (ya inscrito o materia inexistente)");
                    fallidas++;
                }
            } catch (CupoLlenoException e) {
                System.out.println("[" + procesadas + "/" + totalSolicitudes + "] "
                        + idEstudiante + " -> " + codigoMateria + " : Cola de espera (" + e.getMessage() + ")");
                enCola++;
            } catch (EstudianteNoEncontradoException | PreRequisitoNoAprobadoException e) {
                System.out.println("[" + procesadas + "/" + totalSolicitudes + "] "
                        + idEstudiante + " -> " + codigoMateria + " : Fallida (" + e.getMessage() + ")");
                fallidas++;
            }
        }

        System.out.println("=== RESUMEN DE CARGA MASIVA CSV ===");
        System.out.println("Inscripciones exitosas: " + exitosas);
        System.out.println("Enviados a cola de espera: " + enCola);
        System.out.println("Fallidas / omitidas: " + fallidas);
    }
}