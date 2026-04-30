package Controllers.Competiciones;

import DAO.CompeticionesDAO;
import Entidades.TablaCompeticiones;
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

public class PantallaCompeticionesController {

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
    private TableView<TablaCompeticiones> tablaTodos;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosBoxeador;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosCategoría;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosCompeticiones;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosEstado;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosFechaF;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosFechaI;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosLugar;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosResultado;

    @FXML
    private TableColumn<TablaCompeticiones, String> todosTipo;

    CompeticionesDAO compeDao = new CompeticionesDAO();

    @FXML
    void buscarCompeticiones(ActionEvent event) {

    }

    @FXML
    void irNuevaCompeticion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaNuevaCompeticion.fxml"));
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
        todosBoxeador.setCellValueFactory(new PropertyValueFactory<>("boxeadorCompleto"));
        todosCategoría.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        todosCompeticiones.setCellValueFactory(new PropertyValueFactory<>("nombreCompeticion"));
        todosLugar.setCellValueFactory(new PropertyValueFactory<>("lugarCompe"));
        todosFechaI.setCellValueFactory(new PropertyValueFactory<>("fechaInico"));
        todosFechaF.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
        todosTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCompe"));
        todosResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        todosEstado.setCellValueFactory(cellData -> {
            Boolean estado = cellData.getValue().getConfirmado();

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
        Task<List<TablaCompeticiones>> task = new Task<>() {
            @Override
            protected List<TablaCompeticiones> call() {
                return compeDao.listarCompe();
            }
        };

        task.setOnSucceeded(e -> {
            List<TablaCompeticiones> lista = task.getValue();
            tablaTodos.setItems(FXCollections.observableArrayList(lista));
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        //agregarDobleClick(tablaTodos);

        new Thread(task).start();


    }

}
