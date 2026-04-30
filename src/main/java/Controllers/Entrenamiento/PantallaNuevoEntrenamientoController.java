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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class PantallaNuevoEntrenamientoController {

    @FXML
    private ComboBox<String> HoraI;

    @FXML
    private Button botonGuardar;

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

    BoxeadorDAO boxDao = new BoxeadorDAO();
    EntrenadorDAO entDao = new EntrenadorDAO();
    EntrenamientosDAO entrenaDao = new EntrenamientosDAO();

    @FXML
    void crearNuevoEntrenamiento(ActionEvent event) throws IOException {

        TablaEntrenamientos tabEnt = new TablaEntrenamientos();
        Boxeador box = boxeador.getValue();
        Entrenador ent = entrenador.getValue();
        tabEnt.setTipo(tipo.getText());
        tabEnt.setLugar(lugar.getText());
        int horaI = Integer.parseInt(HoraI.getValue());
        int minutoI = Integer.parseInt(HoraI.getValue());
        LocalTime horaInicial = LocalTime.of(horaI, minutoI);
        tabEnt.setHoraInicio(horaInicial);
        int horaFin = Integer.parseInt(horaF.getValue());
        int minutoFin = Integer.parseInt(horaF.getValue());
        LocalTime horaFinal = LocalTime.of(horaFin, minutoFin);
        tabEnt.setHoraFinal(horaFinal);
        tabEnt.setFecha(String.valueOf(fecha.getValue()));
        tabEnt.setNombreEntrenador(ent.getNombre());
        tabEnt.setApellidosEntrenador(ent.getApellidos());
        tabEnt.setNombreBoxeador(box.getNombre());
        tabEnt.setApellidosBoxeador(box.getApellidos());
        entrenaDao.crearEntrenamiento(tabEnt, box, ent);

        irVerEntrenamientos(event);
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

        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaEntrenamientos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        entrenamientoButton = (Button) event.getSource();
        Stage stage = (Stage) entrenamientoButton.getScene().getWindow();
        stage.setScene(scene);


    }

    @FXML
    void initialize() {

        List<Boxeador> listaBoxeador = boxDao.listarBoxeadores();
        boxeador.getItems().addAll(listaBoxeador);
        List<Entrenador> listarEntrenador = entDao.listarEntrenadores();
        entrenador.getItems().addAll(listarEntrenador);
        ObservableList<String> horas = FXCollections.observableArrayList();
        for (int i = 0; i < 24; i++) {
            horas.add(String.valueOf(i));
        }
        horaF.getItems().addAll(horas);
        HoraI.getItems().addAll(horas);
        ObservableList<String> minutos = FXCollections.observableArrayList();

        for (int i = 0; i < 60; i += 15) {
            minutos.add(String.valueOf(i));
        }
        minI.getItems().addAll(minutos);
        minF.getItems().addAll(minutos);

    }

}
