package Controllers.Entrenador;

import DAO.EntrenadorDAO;
import Entidades.Entrenador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;

public class FichaEntrenadorController {

    @FXML
    private TextField apellidos;

    @FXML
    private Button botonEditar;

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

    @FXML
    private Button botonEliminar;

    EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
    private Entrenador entrenadorActual;
    private int idEntrenador;

    public void setEntrenador(Entrenador entrenador){
        this.entrenadorActual = entrenador;

        nombre.setText(entrenador.getNombre());
        apellidos.setText(entrenador.getApellidos());
        dni.setText(entrenador.getDni());
        telefono.setText(entrenador.getTelefono());
        especialidad.setText(entrenador.getEspecialidad());

    }

    private void activarEdicion(Boolean activar){
        nombre.setEditable(activar);
        apellidos.setEditable(activar);
        dni.setEditable(activar);
        telefono.setEditable(activar);
        especialidad.setEditable(activar);

        aplicarEstiloTextFields(activar);
    }

    private void aplicarEstiloTextFields(boolean activar) {
        String estilo = activar
                ? "-fx-control-inner-background: white; -fx-text-fill: black;"
                : "-fx-control-inner-background: #4A4A4A; -fx-text-fill: #ECECEC;";

        nombre.setStyle(estilo);
        apellidos.setStyle(estilo);
        dni.setStyle(estilo);
        telefono.setStyle(estilo);
        especialidad.setStyle(estilo);
    }

    @FXML
    void validarEditar(ActionEvent event) {

        if(botonEditar.getText().equals("Editar")){
            idEntrenador = entrenadorActual.getId();
            activarEdicion(true);
            botonEditar.setText("Guardar");
        }else{
            activarEdicion(false);
            botonEditar.setText("Editar");

            Boolean hayCambios =
                    !nombre.getText().equals(entrenadorActual.getNombre()) ||
                            !apellidos.getText().equals(entrenadorActual.getApellidos())||
                            !dni.getText().equals(entrenadorActual.getDni()) ||
                            !telefono.getText().equals(entrenadorActual.getTelefono()) ||
                            !especialidad.getText().equals(entrenadorActual.getEspecialidad());

            if(!hayCambios){
                System.out.println("Sin cambios, no se guarda nada");
                return;
            }

            if(!validarCampos()){
                return;
            }

            entrenadorActual.setNombre(nombre.getText());
            entrenadorActual.setApellidos(apellidos.getText());
            entrenadorActual.setDni(dni.getText());
            entrenadorActual.setTelefono(telefono.getText());
            entrenadorActual.setEspecialidad(especialidad.getText());

            entrenadorDAO.actualizarEntrenador(entrenadorActual);

        }

    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos() {

        if (!nombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Nombre no válido");
            return false;
        }

        if (!apellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Apellidos no válidos");
            return false;
        }

        if (!dni.getText().matches("\\d{8}[A-Za-z]")) {
            mostrarError("DNI no válido");
            return false;
        }

        if (!telefono.getText().matches("\\d{9}")) {
            mostrarError("Teléfono no válido");
            return false;
        }

        // Especialidad solo texto
        if (!especialidad.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("especialidad no válido");
            return false;
        }

        return true;
    }

    @FXML
    void irVerBoxeadores(ActionEvent event) {

    }

    @FXML
    void irVerCompeticiones(ActionEvent event) {

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
    void irVerEntrenamientos(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        irVerEntrenadores(event);
    }

    @FXML
    void eliminar(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar a " + entrenadorActual.getNombre() + " " + entrenadorActual.getApellidos() + "?",
                ButtonType.YES, ButtonType.NO);

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.YES) {
                entrenadorDAO.eliminarEntrenador(entrenadorActual);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenadores.fxml"));
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
