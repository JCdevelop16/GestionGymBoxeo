package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Notifiacion")
public class Notifiacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Nationalized
    @Lob
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Nationalized
    @Column(name = "fecha", nullable = false, length = 10)
    private String fecha;

    @Column(name = "leida", nullable = false)
    private Boolean leida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_boxeador", nullable = false)
    private Boxeador idBoxeador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    public Boxeador getIdBoxeador() {
        return idBoxeador;
    }

    public void setIdBoxeador(Boxeador idBoxeador) {
        this.idBoxeador = idBoxeador;
    }

}