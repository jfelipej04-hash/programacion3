package modelo;

// Clase Reporte: arma los reportes academicos de un estudiante en formato texto a partir de sus notas
public class Reporte {

    private Estudiante estudiante;
    private int semestre;
    private String contenido;

    // Constructor para generar el reporte completo de todos los semestres
    public Reporte(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.semestre = 0;
        this.contenido = generar();
    }

    // Constructor para generar el reporte de un semestre especifico
    public Reporte(Estudiante estudiante, int semestre) {
        this.estudiante = estudiante;
        this.semestre = semestre;
        this.contenido = generar();
    }

    // Getters y setters
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getContenido() {
        return contenido;
    }

    // Genera el texto del reporte (completo o de un semestre) con promedios y resumen final
    public String generar() {
        if (estudiante == null) {
            this.contenido = "Error: No hay estudiante asociado al reporte.";
            return this.contenido;
        }

        String texto = "";
        Double[][] notas = estudiante.getNotas();

        texto += "=== REPORTE ACADEMICO ===\n";
        texto += "Estudiante: " + estudiante.getNombre() + " (ID: " + estudiante.getId() + ")\n";

        if (!estudiante.tieneNotasRegistradas()) {
            texto += "Sin notas registradas aun.\n";
            this.contenido = texto;
            return this.contenido;
        }

        if (semestre >= 1 && semestre <= 10) {
            texto += obtenerDetalleSemestre(notas, semestre);
        } else {
            for (int s = 1; s <= 10; s++) {
                if (tieneNotasEnSemestre(notas, s)) {
                    texto += obtenerDetalleSemestre(notas, s);
                }
            }
        }

        texto += "\n=== RESUMEN ===\n";
        texto += "Promedio acumulado: " + redondear(estudiante.calcularPromedioAcumulado()) + "\n";
        texto += "Materias aprobadas: " + estudiante.contarMateriasAprobadas() + "\n";
        texto += "Materias reprobadas: " + estudiante.contarMateriasReprobadas() + "\n";

        this.contenido = texto;
        return this.contenido;
    }

    // Genera un reporte enfocado unicamente en las notas menores a 3.0
    public String generarReporteReprobadas() {
        if (estudiante == null) {
            this.contenido = "Error: No hay estudiante asociado al reporte.";
            return this.contenido;
        }

        String texto = "";
        Double[][] notas = estudiante.getNotas();
        int totalReprobadas = 0;

        texto += "=== REPORTE DE MATERIAS REPROBADAS (< 3.0) ===\n";
        texto += "Estudiante: " + estudiante.getNombre() + " (ID: " + estudiante.getId() + ")\n";

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null && notas[i][j] < 3.0) {
                    texto += "  Semestre " + (i + 1)
                            + " - Materia " + (j + 1)
                            + ": " + redondear(notas[i][j]) + "\n";
                    totalReprobadas++;
                }
            }
        }

        if (totalReprobadas == 0) {
            texto += "El estudiante no tiene materias reprobadas.\n";
        } else {
            texto += "Total materias reprobadas: " + totalReprobadas + "\n";
        }

        this.contenido = texto;
        return this.contenido;
    }

    // Revisa si un semestre tiene al menos una nota registrada
    private boolean tieneNotasEnSemestre(Double[][] notas, int semestreNumero) {
        int fila = semestreNumero - 1;
        for (int j = 0; j < 20; j++) {
            if (notas[fila][j] != null) {
                return true;
            }
        }
        return false;
    }

    // Arma el bloque de texto con las notas y el promedio de un solo semestre
    private String obtenerDetalleSemestre(Double[][] notas, int semestreNumero) {
        int fila = semestreNumero - 1;
        String detalle = "\nSemestre " + semestreNumero + ":\n";

        boolean encontroNotas = false;
        for (int j = 0; j < 20; j++) {
            if (notas[fila][j] != null) {
                double nota = notas[fila][j];
                detalle += "  Materia " + (j + 1) + ": " + redondear(nota);
                if (nota < 3.0) {
                    detalle += " (REPROBADA)";
                }
                detalle += "\n";
                encontroNotas = true;
            }
        }

        if (!encontroNotas) {
            detalle += "  Sin notas registradas en este semestre.\n";
        } else {
            double promedioSemestre = estudiante.calcularPromedioSemestre(semestreNumero);
            detalle += "  Promedio: " + redondear(promedioSemestre) + "\n";
        }

        return detalle;
    }

    // Redondea los decimales a 2 cifras para que se vean limpios en consola
    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return contenido;
    }
}