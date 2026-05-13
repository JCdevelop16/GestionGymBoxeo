package Entidades;

import javafx.fxml.FXML;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TablaEntrenamientos {

    private String fecha;
    private String tipo;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private Boolean estadoAsistencia;
    private String lugar;
    private String nombreEntrenador;
    private String nombreBoxeador;
    private String apellidosBoxeador;
    private String apellidosEntrenador;
    private int idEntrenador;
    private int idBoxeador;

    public TablaEntrenamientos(){}

    public  TablaEntrenamientos(String fecha, String tipo,
                                LocalTime horaInicio, LocalTime horaFinal, String lugar,
                                String nombreEntrenador, String nombreBoxeador,
                                String apellidosBoxeador, String apellidosEntrenador, Boolean estadoAsistencia,
                                int idEntrenador, int idBoxeador) {

        this.fecha = fecha;
        this.tipo = tipo;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.lugar = lugar;
        this.nombreEntrenador = nombreEntrenador;
        this.nombreBoxeador = nombreBoxeador;
        this.apellidosBoxeador = apellidosBoxeador;
        this.apellidosEntrenador = apellidosEntrenador;
        this.estadoAsistencia = estadoAsistencia;
        this.idBoxeador = idBoxeador;
        this.idEntrenador = idEntrenador;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public String getNombreBoxeador() {
        return nombreBoxeador;
    }

    public String getApellidosBoxeador() {
        return apellidosBoxeador;
    }

    public String getApellidosEntrenador() {
        return apellidosEntrenador;
    }

    public String getBoxeadorCompleto() {
        return nombreBoxeador + " " + apellidosBoxeador;
    }

    public String getEntrenadorCompleto() {
        return nombreEntrenador + " " + apellidosEntrenador;
    }

    // 🔹 Texto bonito para UI
    public Boolean getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public void setEstadoAsistencia(Boolean estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
    }

    public void setNombreBoxeador(String nombreBoxeador) {
        this.nombreBoxeador = nombreBoxeador;
    }

    public void setApellidosBoxeador(String apellidosBoxeador) {
        this.apellidosBoxeador = apellidosBoxeador;
    }

    public void setApellidosEntrenador(String apellidosEntrenador) {
        this.apellidosEntrenador = apellidosEntrenador;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getIdBoxeador() {
        return idBoxeador;
    }

    public void setIdBoxeador(int idBoxeador) {
        this.idBoxeador = idBoxeador;
    }

    // 🔧 Método auxiliar
    private String formatearHora(LocalTime hora) {
        if (hora == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return hora.format(formatter);
    }
}
