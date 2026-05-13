package Controllers.Competiciones;

import DAO.BoxeadorDAO;
import DAO.CompeticionesDAO;
import Entidades.Boxeador;
import Entidades.TablaCompeticiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.util.List;

public class PantallaNuevaCompeticionController {

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonVolver;

    @FXML
    private ComboBox<Boxeador> boxeador;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private TextField competicion;

    @FXML
    private Button competicionesButton;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private DatePicker fechaF;

    @FXML
    private DatePicker fechaI;

    @FXML
    private TextField lugar;

    @FXML
    private ComboBox<String> resultado;

    BoxeadorDAO boxDao = new BoxeadorDAO();
    CompeticionesDAO compeDao = new CompeticionesDAO();

    @FXML
    void crearNuevaCompeticiones(ActionEvent event) throws IOException {
        TablaCompeticiones tabEntreno = new TablaCompeticiones();
        Boxeador box = boxeador.getValue();
        tabEntreno.setNombreBoxeador(box.getNombre());
        tabEntreno.setApellidosBoxeador(box.getApellidos());
        tabEntreno.setCategoria(box.getCategoria());
        tabEntreno.setNombreCompeticion(competicion.getText());
        tabEntreno.setLugarCompe(lugar.getText());
        tabEntreno.setFechaInico(String.valueOf(fechaI.getValue()));
        tabEntreno.setFechaFinal(String.valueOf(fechaF.getValue()));
        tabEntreno.setTipoCompe(box.getTipoBox());
        tabEntreno.setResultado(resultado.getValue());

        compeDao.crearCompeticion(tabEntreno, box);

        irVerCompeticiones(event);
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
        irVerCompeticiones(event);
    }

    @FXML
    void initialize() {
        List<Boxeador> listaBoxeador = boxDao.listarBoxeadoresCompetidores();
        boxeador.getItems().addAll(listaBoxeador);

        resultado.getItems().addAll("Sin resultado", "Empate", "Victoria", "Derrota");
    }

}