package modelo;

// Clase Profesor: hereda de Persona y agrega departamento y salario
public class Profesor extends Persona {

    private String departamento;
    private double salario;

    // Constructor: valida que el salario no sea negativo
    public Profesor(String nombre, String id, String email, String departamento, double salario) {
        super(nombre, id, email);
        this.departamento = departamento;
        this.salario = (salario >= 0) ? salario : 0.0;
    }

    // Getters y setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        }
    }

    // Imprime los datos completos del profesor incluyendo el salario formateado
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Departamento: " + departamento);

        System.out.printf("Salario: $%.2f%n", salario);
    }

    @Override
    public String toString() {
        return getId() + " - " + getNombre() + " - Departamento: " + departamento;
    }
}