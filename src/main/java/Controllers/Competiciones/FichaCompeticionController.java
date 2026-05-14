package Controllers.Competiciones;

import DAO.CompeticionesDAO;
import Entidades.TablaCompeticiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.time.LocalDate;

public class FichaCompeticionController {

    @FXML
    private Button botonEditar;

    @FXML
    private Button botonVolver;

    @FXML
    private ComboBox<String> boxeador;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private TextField categoria;

    @FXML
    private TextField competicion;

    @FXML
    private Button competicionesButton;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private TextField estado;

    @FXML
    private DatePicker fechaF;

    @FXML
    private DatePicker fechaI;

    @FXML
    private TextField lugar;

    @FXML
    private ComboBox<String> resultado;

    @FXML
    private TextField tipo;

    @FXML
    private Button botonEliminar;

    private TablaCompeticiones tablaCompeticiones;
    private CompeticionesDAO compeDao = new CompeticionesDAO();
    private String nombreOriginal;
    private String fechaInicioOriginal;

    @FXML
    void initialize() {
        resultado.getItems().addAll("Sin resultado", "Empate", "Victoria", "Derrota");
    }

    public void setCompeticion(TablaCompeticiones tablaCompeticiones) {
        this.tablaCompeticiones = tablaCompeticiones;
        this.nombreOriginal = tablaCompeticiones.getNombreCompeticion();
        this.fechaInicioOriginal = tablaCompeticiones.getFechaInico();

        competicion.setText(tablaCompeticiones.getNombreCompeticion());
        lugar.setText(tablaCompeticiones.getLugarCompe());
        tipo.setText(tablaCompeticiones.getTipoCompe());
        categoria.setText(tablaCompeticiones.getCategoria());
        resultado.setValue(tablaCompeticiones.getResultado());
        estado.setText(tablaCompeticiones.getConfirmado() != null && tablaCompeticiones.getConfirmado() ? "Confirmado" : "No confirmado");
        boxeador.getItems().clear();
        boxeador.getItems().add(tablaCompeticiones.getBoxeadorCompleto());
        boxeador.setValue(tablaCompeticiones.getBoxeadorCompleto());

        if (tablaCompeticiones.getFechaInico() != null)
            fechaI.setValue(LocalDate.parse(tablaCompeticiones.getFechaInico()));
        if (tablaCompeticiones.getFechaFinal() != null)
            fechaF.setValue(LocalDate.parse(tablaCompeticiones.getFechaFinal()));

        activarEdicion(false);
    }

    private void activarEdicion(boolean activar) {
        competicion.setEditable(activar);
        lugar.setEditable(activar);
        resultado.setDisable(!activar);
        fechaI.setDisable(!activar);
        fechaF.setDisable(!activar);
        aplicarEstiloTextFields(activar);
    }

    private void aplicarEstiloTextFields(boolean activar) {
        String estilo = activar
                ? "-fx-control-inner-background: white; -fx-text-fill: black;"
                : "-fx-control-inner-background: #4A4A4A; -fx-text-fill: #ECECEC;";

        competicion.setStyle(estilo);
        lugar.setStyle(estilo);
        tipo.setStyle(estilo);
        resultado.setStyle(estilo);
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

        if (competicion.getText().isEmpty()
                || lugar.getText().isEmpty()
                || tipo.getText().isEmpty()
                || resultado.getValue() == null
                || fechaI.getValue() == null
                || fechaF.getValue() == null) {
            System.out.println("Debes rellenar todos los campos");
            return;
        }

        try {
            LocalDate inicio = fechaI.getValue();
            LocalDate fin = fechaF.getValue();

            if (!inicio.isBefore(fin)) {
                System.out.println("La fecha de inicio debe ser anterior a la fecha final");
                return;
            }

            tablaCompeticiones.setNombreCompeticion(competicion.getText());
            tablaCompeticiones.setLugarCompe(lugar.getText());
            tablaCompeticiones.setTipoCompe(tipo.getText());
            tablaCompeticiones.setResultado(resultado.getValue());
            tablaCompeticiones.setFechaInico(inicio.toString());
            tablaCompeticiones.setFechaFinal(fin.toString());

            compeDao.actualizarCompeticion(tablaCompeticiones, nombreOriginal, fechaInicioOriginal);

            activarEdicion(false);
            botonEditar.setText("Editar");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar la competición");
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaCompeticiones.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        competicionesButton = (Button) event.getSource();
        Stage stage = (Stage) competicionesButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void eliminar(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar la competición " + tablaCompeticiones.getNombreCompeticion() + "?",
                ButtonType.YES, ButtonType.NO);

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.YES) {
                compeDao.eliminarCompeticion(
                        nombreOriginal,
                        fechaInicioOriginal,
                        tablaCompeticiones.getNombreBoxeador(),
                        tablaCompeticiones.getApellidosBoxeador()
                );
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaCompeticiones.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) botonEliminar.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}