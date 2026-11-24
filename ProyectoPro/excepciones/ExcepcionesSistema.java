package excepciones;

// Agrupa todas las excepciones personalizadas para controlar errores del sistema sin que se cierre
public class ExcepcionesSistema {

    // 1. Cuando el estudiante no ha aprobado los pre-requisitos de una materia
    public static class PreRequisitoNoAprobadoException extends Exception {
        public PreRequisitoNoAprobadoException(String mensaje) {
            super(mensaje);
        }
    }

    // 2. Cuando se intenta reservar un aula en un día y hora ya ocupados
    public static class HorarioConflictivoException extends Exception {
        public HorarioConflictivoException(String mensaje) {
            super(mensaje);
        }
    }

    // 3. Cuando una materia ya no tiene cupos disponibles
    public static class CupoLlenoException extends Exception {
        public CupoLlenoException(String mensaje) {
            super(mensaje);
        }
    }

    // 4. Cuando se busca un estudiante por ID y no existe
    public static class EstudianteNoEncontradoException extends Exception {
        public EstudianteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    // 5. Cuando se intenta atender una cola de espera vacía
    public static class ColaDeEsperaVaciaException extends Exception {
        public ColaDeEsperaVaciaException(String mensaje) {
            super(mensaje);
        }
    }

    // 6. Cuando se intenta deshacer o rehacer pero la pila está vacía
    public static class PilaDeshacerVaciaException extends Exception {
        public PilaDeshacerVaciaException(String mensaje) {
            super(mensaje);
        }
    }

    // 7. Cuando el archivo CSV no existe o tiene líneas con formato incorrecto
    public static class ArchivoInvalidoException extends Exception {
        public ArchivoInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}