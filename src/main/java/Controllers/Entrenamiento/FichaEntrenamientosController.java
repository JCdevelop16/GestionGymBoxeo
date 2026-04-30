package Controllers.Entrenamiento;

import DAO.EntrenamientosDAO;
import Entidades.Boxeador;
import Entidades.Entrenador;
import Entidades.TablaEntrenamientos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FichaEntrenamientosController {

    @FXML
    private ComboBox<?> HoraI;

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

    private Entrenador entrenadorElegido;
    private Boxeador boxElegido;
    private TablaEntrenamientos tablaEntrenamientos;
    EntrenamientosDAO entrenoDao = new EntrenamientosDAO();

    public void setEntrenamiento(){

    }

    @FXML
    void irVerBoxeadores(ActionEvent event) {

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
    void validarEditar(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {

    }

}
