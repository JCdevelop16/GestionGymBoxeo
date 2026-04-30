package Controllers.Entrenamiento;

import DAO.EntrenamientosDAO;
import Entidades.TablaEntrenamientos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.util.List;

public class PantallaEntrenamientosController {

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonNuevo;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private Button competicionesButton;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private Tab etiquetaTodos;

    @FXML
    private TextField labelBusquedaTodos;

    @FXML
    private TableView<TablaEntrenamientos> tablaTodos;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosBoxeador;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosEntrenador;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosEstado;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosFecha;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosHoraFinal;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosHoraInicio;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosLugar;

    @FXML
    private TableColumn<TablaEntrenamientos, String> todosTipo;

    EntrenamientosDAO entrenamientosDAO = new EntrenamientosDAO();

    @FXML
    void buscarEntrenamientos(ActionEvent event) {

    }

    @FXML
    void irNuevoEntrenamientos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaNuevoEntrenamiento.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        botonNuevo = (Button) event.getSource();
        Stage stage = (Stage) botonNuevo.getScene().getWindow();
        stage.setScene(scene);

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
    void initialize() {

        todosFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        todosHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        todosHoraFinal.setCellValueFactory(new PropertyValueFactory<>("horaFinal"));
        todosTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        todosLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        todosBoxeador.setCellValueFactory(new PropertyValueFactory<>("boxeadorCompleto"));
        todosEntrenador.setCellValueFactory(new PropertyValueFactory<>("entrenadorCompleto"));
        todosEstado.setCellValueFactory(cellData -> {
            Boolean estado = cellData.getValue().getEstadoAsistencia();

            String texto;

            if (estado == null) {
                texto = "Pendiente";
            } else if (estado) {
                texto = "Asistirá";
            } else {
                texto = "No asistirá";
            }

            return new SimpleStringProperty(texto);
        });

        // 🔄 CARGA EN SEGUNDO PLANO
        Task<List<TablaEntrenamientos>> task = new Task<>() {
            @Override
            protected List<TablaEntrenamientos> call() {
                return entrenamientosDAO.listarEntrenamientos();
            }
        };

        task.setOnSucceeded(e -> {
            List<TablaEntrenamientos> lista = task.getValue();
            tablaTodos.setItems(FXCollections.observableArrayList(lista));
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        //agregarDobleClick(tablaTodos);

        new Thread(task).start();

    }

}
