package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Participacion_Compe")
public class ParticipacionCompe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participacion", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "resultado", nullable = false, length = 15)
    private String resultado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_boxeador", nullable = false)
    private Boxeador idBoxeador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_competicion", nullable = false)
    private Competicion idCompeticion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Boxeador getIdBoxeador() {
        return idBoxeador;
    }

    public void setIdBoxeador(Boxeador idBoxeador) {
        this.idBoxeador = idBoxeador;
    }

    public Competicion getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(Competicion idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

}