package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Convocatoria_Boxeador")
public class ConvocatoriaBoxeador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "confirmado", nullable = false)
    private Boolean confirmado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_convocatoria", nullable = false)
    private Convocatoria idConvocatoria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_boxeador", nullable = false)
    private Boxeador idBoxeador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Convocatoria getIdConvocatoria() {
        return idConvocatoria;
    }

    public void setIdConvocatoria(Convocatoria idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public Boxeador getIdBoxeador() {
        return idBoxeador;
    }

    public void setIdBoxeador(Boxeador idBoxeador) {
        this.idBoxeador = idBoxeador;
    }

}