package modelo;

// Clase abstracta base de la que heredan Estudiante y Profesor para no repetir datos comunes
public abstract class Persona {
    private String nombre;
    private String id;
    private String email;

    // Constructor con los datos basicos de cualquier persona en la universidad
    public Persona(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Metodo abstracto que cada clase hija debe implementar a su manera
    public abstract void mostrarInformacion();

    @Override
    public String toString() {
        return nombre + " - " + id + " - " + email;
    }
}