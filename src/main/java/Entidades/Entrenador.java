package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Entrenador")
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenador", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Nationalized
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Nationalized
    @Column(name = "especialidad", nullable = false, length = 30)
    private String especialidad;
    @Nationalized
    @Column(name = "dni", nullable = false, length = 10)
    private String dni;
    @Nationalized
    @Column(name = "apellidos", nullable = false, length = 30)
    private String apellidos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }

}