package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Competicion")
public class Competicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competicion", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Nationalized
    @Column(name = "lugar", nullable = false, length = 50)
    private String lugar;

    @Nationalized
    @Column(name = "fecha_inicio", nullable = false, length = 10)
    private String fechaInicio;

    @Nationalized
    @Column(name = "fecha_fin", nullable = false, length = 10)
    private String fechaFin;

    @Nationalized
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}