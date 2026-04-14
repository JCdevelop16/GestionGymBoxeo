package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Asistencia_entreno")
public class AsistenciaEntreno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_boxeador", nullable = false)
    private Boxeador idBoxeador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entrenamiento", nullable = false)
    private Entrenamiento idEntrenamiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boxeador getIdBoxeador() {
        return idBoxeador;
    }

    public void setIdBoxeador(Boxeador idBoxeador) {
        this.idBoxeador = idBoxeador;
    }

    public Entrenamiento getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(Entrenamiento idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

}