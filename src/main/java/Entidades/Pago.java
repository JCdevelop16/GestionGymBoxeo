package Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Table(name = "Pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagos", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "concepto", nullable = false, length = 50)
    private String concepto;

    @Column(name = "importe", nullable = false, precision = 18)
    private BigDecimal importe;

    @Nationalized
    @Column(name = "fecha", nullable = false, length = 10)
    private String fecha;

    @Nationalized
    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    @Nationalized
    @Column(name = "metodo_pago", nullable = false, length = 10)
    private String metodoPago;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

}