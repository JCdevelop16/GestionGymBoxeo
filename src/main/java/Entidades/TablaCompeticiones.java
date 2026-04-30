package Entidades;

public class TablaCompeticiones {

    private String nombreBoxeador;
    private String apellidosBoxeador;
    private String categoria;
    private String nombreCompeticion;
    private String lugarCompe;
    private String fechaInico;
    private String fechaFinal;
    private String tipoCompe;
    private String resultado;
    private Boolean confirmado;

    public TablaCompeticiones(){}

    public TablaCompeticiones(String nombreBoxeador, String apellidosBoxeador, String categoria, String nombreCompeticion, String lugarCompe,
                              String fechaInico, String fechaFinal, String tipoCompe, String resultado, Boolean confirmado) {
        this.nombreBoxeador = nombreBoxeador;
        this.apellidosBoxeador = apellidosBoxeador;
        this.categoria = categoria;
        this.nombreCompeticion = nombreCompeticion;
        this.lugarCompe = lugarCompe;
        this.fechaInico = fechaInico;
        this.fechaFinal = fechaFinal;
        this.tipoCompe = tipoCompe;
        this.resultado = resultado;
        this.confirmado = confirmado;
    }

    public String getNombreBoxeador() {
        return nombreBoxeador;
    }

    public void setNombreBoxeador(String nombreBoxeador) {
        this.nombreBoxeador = nombreBoxeador;
    }

    public String getApellidosBoxeador() {
        return apellidosBoxeador;
    }

    public void setApellidosBoxeador(String apellidosBoxeador) {
        this.apellidosBoxeador = apellidosBoxeador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreCompeticion() {
        return nombreCompeticion;
    }

    public void setNombreCompeticion(String nombreCompeticion) {
        this.nombreCompeticion = nombreCompeticion;
    }

    public String getLugarCompe() {
        return lugarCompe;
    }

    public void setLugarCompe(String lugarCompe) {
        this.lugarCompe = lugarCompe;
    }

    public String getFechaInico() {
        return fechaInico;
    }

    public void setFechaInico(String fechaInico) {
        this.fechaInico = fechaInico;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getTipoCompe() {
        return tipoCompe;
    }

    public void setTipoCompe(String tipoCompe) {
        this.tipoCompe = tipoCompe;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public String getBoxeadorCompleto() {
        return nombreBoxeador + " " + apellidosBoxeador;
    }
}
