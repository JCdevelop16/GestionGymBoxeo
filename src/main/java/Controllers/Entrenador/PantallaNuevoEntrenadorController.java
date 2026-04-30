package Controllers.Entrenador;

import DAO.EntrenadorDAO;
import Entidades.Entrenador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;

public class PantallaNuevoEntrenadorController {

    @FXML
    private TextField apellidos;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonVolver;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private Button competicionesButton;

    @FXML
    private TextField dni;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private TextField especialidad;

    @FXML
    private TextField nombre;

    @FXML
    private TextField telefono;

    EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos() {

        // Nombre y apellidos (solo texto)
        if (!nombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Nombre no válido");
            return false;
        }

        if (!apellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Apellidos no válidos");
            return false;
        }

        // DNI (formato español)
        if (!dni.getText().matches("\\d{8}[A-Za-z]")) {
            mostrarError("DNI no válido");
            return false;
        }

        // Teléfono (9 dígitos)
        if (!telefono.getText().matches("\\d{9}")) {
            mostrarError("Teléfono no válido");
            return false;
        }

        // Especialidad
        if (!especialidad.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Especialidad no válida");
            return false;
        }

        return true;
    }

    @FXML
    void crearNuevoEntrenador(ActionEvent event) throws IOException {
        if (!validarCampos()) return;

        Entrenador entrenador = new Entrenador();

        entrenador.setNombre(nombre.getText());
        entrenador.setApellidos(apellidos.getText());
        entrenador.setDni(dni.getText());
        entrenador.setTelefono(telefono.getText());
        entrenador.setEspecialidad(especialidad.getText());

        entrenadorDAO.crearEntrenador(entrenador);

        irVerEntrenadores(event);

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
    void volver(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenadores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        entrenadoresButton = (Button) event.getSource();
        Stage stage = (Stage) entrenadoresButton.getScene().getWindow();
        stage.setScene(scene);

    }

}
