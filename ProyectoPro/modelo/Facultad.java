package modelo;

// Modelo sencillo para representar una facultad con su codigo y nombre
public class Facultad {
    
    private String codigo;
    private String nombre;

    // Constructor
    public Facultad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Setters y getters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override 
    public String toString() {
        return codigo + " - " + nombre;
    }
}