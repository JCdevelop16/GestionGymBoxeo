package Controllers.Entrenamiento;

import DAO.BoxeadorDAO;
import DAO.EntrenadorDAO;
import DAO.EntrenamientosDAO;
import Entidades.Boxeador;
import Entidades.Entrenador;
import Entidades.TablaEntrenamientos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.util.List;

public class FichaEntrenamientosController {

    @FXML
    private ComboBox<String> HoraI;

    @FXML
    private Button botonEditar;

    @FXML
    private Button botonVolver;

    @FXML
    private ComboBox<Boxeador> boxeador;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private Button competicionesButton;

    @FXML
    private ComboBox<Entrenador> entrenador;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private TextField estado;

    @FXML
    private DatePicker fecha;

    @FXML
    private ComboBox<String> horaF;

    @FXML
    private TextField lugar;

    @FXML
    private ComboBox<String> minF;

    @FXML
    private ComboBox<String> minI;

    @FXML
    private TextField tipo;

    @FXML
    private Button botonEliminar;

    private Entrenador entrenadorElegido;
    private Boxeador boxElegido;
    private TablaEntrenamientos tablaEntrenamientos;
    EntrenamientosDAO entrenoDao = new EntrenamientosDAO();
    BoxeadorDAO boxDao = new BoxeadorDAO();
    EntrenadorDAO entDao = new EntrenadorDAO();

    public void setEntrenamiento(TablaEntrenamientos tabEntreno){

        this.tablaEntrenamientos = tabEntreno;

        tipo.setText(tabEntreno.getTipo());
        lugar.setText(tabEntreno.getLugar());
        estado.setText(estadoToText(tablaEntrenamientos.getEstadoAsistencia()));

        fecha.setValue(java.time.LocalDate.parse(tabEntreno.getFecha()));

        // ComboBox entrenador: seleccionar el correcto
        for (Entrenador e : entrenador.getItems()) {
            if (e.getNombre().equals(tabEntreno.getNombreEntrenador())
                    && e.getApellidos().equals(tabEntreno.getApellidosEntrenador())) {
                entrenador.setValue(e);
                break;
            }
        }

        // Boxeador (no editable)
        for (Boxeador b : boxeador.getItems()) {
            if (b.getNombre().equals(tabEntreno.getNombreBoxeador())
                    && b.getApellidos().equals(tabEntreno.getApellidosBoxeador())) {
                boxeador.setValue(b);
                break;
            }
        }
        HoraI.setValue(String.format("%02d", tablaEntrenamientos.getHoraInicio().getHour()));
        minI.setValue(String.format("%02d", tablaEntrenamientos.getHoraInicio().getMinute()));

        horaF.setValue(String.format("%02d", tablaEntrenamientos.getHoraFinal().getHour()));
        minF.setValue(String.format("%02d", tablaEntrenamientos.getHoraFinal().getMinute()));

    }

    @FXML
    private void activarEdicion(boolean activar) {
        tipo.setEditable(activar);
        lugar.setEditable(activar);

        HoraI.setDisable(!activar);
        minI.setDisable(!activar);
        horaF.setDisable(!activar);
        minF.setDisable(!activar);

        fecha.setDisable(!activar);
        entrenador.setDisable(!activar);
        aplicarEstiloTextFields(activar);

    }

    private void aplicarEstiloTextFields(boolean activar) {

        if (activar) {
            // modo edición
            tipo.setStyle("-fx-control-inner-background: white;");
            lugar.setStyle("-fx-control-inner-background: white;");
            HoraI.setStyle("-fx-control-inner-background: white;");
            minI.setStyle("-fx-control-inner-background: white;");
            horaF.setStyle("-fx-control-inner-background: white;");
            minF.setStyle("-fx-control-inner-background: white;");
            fecha.setStyle("-fx-control-inner-background: white;");
            entrenador.setStyle("-fx-control-inner-background: white;");
        } else {
            // modo solo lectura (gris o default)
            tipo.setStyle("-fx-control-inner-background: grey;");
            lugar.setStyle("-fx-control-inner-background: grey;");
            HoraI.setStyle("-fx-control-inner-background: grey;");
            minI.setStyle("-fx-control-inner-background: grey;");
            horaF.setStyle("-fx-control-inner-background: grey;");
            minF.setStyle("-fx-control-inner-background: grey;");
            fecha.setStyle("-fx-control-inner-background: grey;");
            entrenador.setStyle("-fx-control-inner-background: grey;");

        }
    }

    @FXML
    void irVerBoxeadores(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaBoxeadores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        boxeadoresButton = (Button) event.getSource();
        Stage stage = (Stage) boxeadoresButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void irVerCompeticiones(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaCompeticiones.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        competicionesButton = (Button) event.getSource();
        Stage stage = (Stage) competicionesButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void irVerEntrenadores(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenadores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        entrenadoresButton = (Button) event.getSource();
        Stage stage = (Stage) entrenadoresButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void irVerEntrenamientos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenamientos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        entrenamientoButton = (Button) event.getSource();
        Stage stage = (Stage) entrenamientoButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void validarEditar(ActionEvent event) {
        if (botonEditar.getText().equals("Editar")) {
            activarEdicion(true);
            botonEditar.setText("Guardar");
            return;
        }

        if (tipo.getText().isEmpty()
                || lugar.getText().isEmpty()
                || fecha.getValue() == null
                || entrenador.getValue() == null
                || boxeador.getValue() == null
                || HoraI.getValue() == null
                || minI.getValue() == null
                || horaF.getValue() == null
                || minF.getValue() == null) {

            System.out.println("Debes rellenar todos los campos");
            return;
        }

        try {
            int horaInicio = Integer.parseInt(HoraI.getValue());
            int minutoInicio = Integer.parseInt(minI.getValue());

            int horaFinal = Integer.parseInt(horaF.getValue());
            int minutoFinal = Integer.parseInt(minF.getValue());

            java.time.LocalTime inicio = java.time.LocalTime.of(horaInicio, minutoInicio);
            java.time.LocalTime fin = java.time.LocalTime.of(horaFinal, minutoFinal);

            if (!inicio.isBefore(fin)) {
                System.out.println("La hora de inicio debe ser anterior a la hora final");
                return;
            }

            tablaEntrenamientos.setTipo(tipo.getText());
            tablaEntrenamientos.setLugar(lugar.getText());
            tablaEntrenamientos.setFecha(fecha.getValue().toString());
            tablaEntrenamientos.setHoraInicio(inicio);
            tablaEntrenamientos.setHoraFinal(fin);
            tablaEntrenamientos.setNombreEntrenador(entrenador.getValue().getNombre());
            tablaEntrenamientos.setApellidosEntrenador(entrenador.getValue().getApellidos());
            tablaEntrenamientos.setNombreBoxeador(boxeador.getValue().getNombre());
            tablaEntrenamientos.setApellidosBoxeador(boxeador.getValue().getApellidos());

            entrenoDao.actualizarEntrenamientos(tablaEntrenamientos);

            activarEdicion(false);
            botonEditar.setText("Editar");

        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de horas o minutos");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar el entrenamiento");
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenamientos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        entrenamientoButton = (Button) event.getSource();
        Stage stage = (Stage) entrenamientoButton.getScene().getWindow();
        stage.setScene(scene);

    }
    private String estadoToText(Boolean estado) {

        if (estado == null) return "No confirmado";
        if (estado) return "Asistirá";
        return "No asistirá";
    }

    @FXML
    void eliminar(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar el entrenamiento de " + tablaEntrenamientos.getTipo()
                        + " del " + tablaEntrenamientos.getFecha() + "?",
                ButtonType.YES, ButtonType.NO);

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.YES) {
                entrenoDao.eliminarEntrenamiento(tablaEntrenamientos);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenamientos.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) botonEliminar.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void initialize() {

        List<Boxeador> listaBoxeador = boxDao.listarBoxeadores();
        boxeador.getItems().addAll(listaBoxeador);
        List<Entrenador> listarEntrenador = entDao.listarEntrenadores();
        entrenador.getItems().addAll(listarEntrenador);
        ObservableList<String> horas = FXCollections.observableArrayList();
        for (int i = 0; i < 24; i++) {
            horas.add(String.format("%02d", i));
        }
        horaF.getItems().addAll(horas);
        HoraI.getItems().addAll(horas);
        ObservableList<String> minutos = FXCollections.observableArrayList();

        for (int i = 0; i < 60; i += 15) {
            minutos.add(String.format("%02d", i));
        }
        minI.getItems().addAll(minutos);
        minF.getItems().addAll(minutos);



    }

}
