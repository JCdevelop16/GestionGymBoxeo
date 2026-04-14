package Controllers;

import Entidades.Boxeador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class PantallaNuevoBoxeadorController {


    @FXML
    private ComboBox<String> ComboTipo;

    @FXML
    private TextField apellidos;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonSeleccionarImg;

    @FXML
    private Button botonVolver;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private Button calendarioButton;

    @FXML
    private ComboBox<String> comboActivo;

    @FXML
    private ComboBox<String> comboCategoría;

    @FXML
    private Button competicionesButton;

    @FXML
    private TextField dni;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private TextField fechaN;

    @FXML
    private ToggleGroup grupoGenero;

    @FXML
    private ImageView imagen;

    @FXML
    private TextField nombre;

    @FXML
    private TextField peso;

    @FXML
    private RadioButton seleccionHombre;

    @FXML
    private RadioButton seleccionMujer;

    @FXML
    private TextField telefono;

    @FXML
    void guardar(ActionEvent event) {

        Boxeador boxeador = new Boxeador();
        boxeador.setNombre(nombre.getText());


    }

    @FXML
    void irVerBoxeadores(ActionEvent event) {

    }

    @FXML
    void irVerCalendario(ActionEvent event) {

    }

    @FXML
    void irVerCompeticiones(ActionEvent event) {

    }

    @FXML
    void irVerEntrenadores(ActionEvent event) {

    }

    @FXML
    void irVerEntrenamientos(ActionEvent event) {

    }

    @FXML
    void seleccionarImagen(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {

    }

}
