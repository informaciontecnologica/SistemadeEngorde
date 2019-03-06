package ar.com.informaciontecn.sistemadeengorde.model;

public class Personas {
    private int id;
    private String nombre;
    private int documento;

    public Personas(int id, String nombre, int documento) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }
}
