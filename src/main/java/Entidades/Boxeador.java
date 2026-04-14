package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Table(name = "Boxeador")
public class Boxeador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boxeador", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Nationalized
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Nationalized
    @Column(name = "dni", nullable = false, length = 10)
    private String dni;

    @Nationalized
    @Column(name = "fecha_nacimiento", nullable = false, length = 15)
    private String fechaNacimiento;

    @Column(name = "peso", nullable = false, precision = 18)
    private BigDecimal peso;

    @Nationalized
    @Column(name = "categoria", nullable = false, length = 15)
    private String categoria;

    @Nationalized
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Nationalized
    @Column(name = "foto_url", nullable = false, length = 50)
    private String fotoUrl;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "id_usuario", nullable = true)
    private Integer idUsuario;

    @Nationalized
    @Column(name = "tipo_box", nullable = false, length = 20)
    private String tipoBox;
    @Nationalized
    @Column(name = "genero", length = 20)
    private String genero;

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoBox() {
        return tipoBox;
    }

    public void setTipoBox(String tipoBox) {
        this.tipoBox = tipoBox;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}