package Controllers;

import DAO.EntrenadorDAO;
import Entidades.Boxeador;
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
    private Button boxeadoresButton;

    @FXML
    private Button calendarioButton;

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
//                    verFichaEntrenador(seleccionado);
                }
            });
            return fila;
        });
    }

    //METODO PARA FILTRA POR TIPO DE BOXEADOR
    private List<Boxeador> filtrar(List<Boxeador> lista, String categoria) {
        return lista.stream()
                .filter(b -> b.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    @FXML
    void initialize() {

        // 🔗 COLUMNAS
        todosNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        todosTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        todosEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

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
