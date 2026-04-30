package Controllers.Entrenador;

import Controllers.Boxeador.FichaBoxeadorController;
import DAO.EntrenadorDAO;
import Entidades.Entrenador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class PantallaEntrenadoresController {

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
    private TableView<Entrenador> tablaTodos;

    @FXML
    private TableColumn<Entrenador, String> todosEspecialidad;

    @FXML
    private TableColumn<Entrenador, String> todosNombre;

    @FXML
    private TableColumn<Entrenador, String> todosApellidos;

    @FXML
    private TableColumn<Entrenador, String> todosDni;

    @FXML
    private TableColumn<Entrenador, String> todosTelefono;

    private EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    @FXML
    void irVerBoxeadores(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaBoxeadores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        boxeadoresButton = (Button) event.getSource();
        Stage stage = (Stage) boxeadoresButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void irNuevoEntrenador(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaNuevoEntrenador.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        botonNuevo = (Button) event.getSource();
        Stage stage = (Stage) botonNuevo.getScene().getWindow();
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
    void buscarEntrenador(ActionEvent event) {
        buscarEnTabla(labelBusquedaTodos, tablaTodos);
    }

    // 🔍 BUSCADOR
    private void buscarEnTabla(TextField campo, TableView<Entrenador> tabla) {

        String texto = campo.getText().trim().toLowerCase();
        ObservableList<Entrenador> lista = tabla.getItems();

        if (texto.isEmpty()) return;

        lista.stream()
                .filter(ent -> ent.getNombre().toLowerCase().contains(texto)
                        || ent.getTelefono().toLowerCase().contains(texto)
                        || ent.getEspecialidad().toLowerCase().contains(texto))
                .findFirst()
                .ifPresent(ent -> {
                    tabla.getSelectionModel().select(ent);
                    tabla.scrollTo(ent);
                });
    }

    // 🖱 DOBLE CLICK
    private void agregarDobleClick(TableView<Entrenador> tabla) {
        tabla.setRowFactory(tv -> {
            TableRow<Entrenador> fila = new TableRow<>();
            fila.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !fila.isEmpty()) {
                    Entrenador seleccionado = fila.getItem();
                    verFichaEntrenador(seleccionado);
                }
            });
            return fila;
        });
    }

    private void verFichaEntrenador(Entrenador entrenador){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(
                    BoxeoApplication.class.getResource("FichaEntrenador.fxml")
            );
            Scene scene = new Scene(fxmlLoader.load());

            // Pasarle el boxeador al controlador
            FichaEntrenadorController controller = fxmlLoader.getController();
            controller.setEntrenador(entrenador);
            Stage stage = (Stage) tablaTodos.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

        // 🔗 COLUMNAS
        todosNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        todosTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        todosEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        todosApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        todosDni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        // 🔄 CARGA EN SEGUNDO PLANO
        Task<List<Entrenador>> task = new Task<>() {
            @Override
            protected List<Entrenador> call() {
                return entrenadorDAO.listarEntrenadores();
            }
        };

        task.setOnSucceeded(e -> {
            List<Entrenador> lista = task.getValue();
            tablaTodos.setItems(FXCollections.observableArrayList(lista));
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        agregarDobleClick(tablaTodos);

        new Thread(task).start();
    }


}
