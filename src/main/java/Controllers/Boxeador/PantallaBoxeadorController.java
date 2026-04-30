package Controllers.Boxeador;

import DAO.BoxeadorDAO;
import Entidades.Boxeador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.util.List;

public class PantallaBoxeadorController {

    @FXML
    private Button BuscarC;

    @FXML
    private Button BuscarSemi;

    @FXML
    private TableView<Boxeador> TablePesado;

    @FXML
    private TableColumn<Boxeador, String> pesadoActivo;

    @FXML
    private TableColumn<Boxeador, String> pesadoApellidos;

    @FXML
    private TableColumn<Boxeador, String> pesadoCategoria;

    @FXML
    private TableColumn<Boxeador, String> pesadoFechaN;

    @FXML
    private TableColumn<Boxeador, String> pesadoNombre;

    @FXML
    private TableColumn<Boxeador, String> pesadoPeso;

    @FXML
    private TableColumn<Boxeador, String> pesadoSexo;

    @FXML
    private TableColumn<Boxeador, String> pesadoTipo;

    @FXML
    private Button botonBuscar;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button buscarGallo;

    @FXML
    private Button buscarLigero;

    @FXML
    private Button buscarMedio;

    @FXML
    private Button buscarMosca;

    @FXML
    private Button buscarPesado;

    @FXML
    private Button buscarPluma;

    @FXML
    private Button buscarWelter;

    @FXML
    private Button calendarioButton;

    @FXML
    private Button competicionesButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private TextField labelBusquedaCrucero;

    @FXML
    private TextField labelBusquedaGallo;

    @FXML
    private TextField labelBusquedaLigero;

    @FXML
    private TextField labelBusquedaMedio;

    @FXML
    private TextField labelBusquedaMosca;

    @FXML
    private TextField labelBusquedaPesado;

    @FXML
    private TextField labelBusquedaPluma;

    @FXML
    private TextField labelBusquedaSemi;

    @FXML
    private TextField labelBusquedaTodos;

    @FXML
    private TextField labelBusquedaWelter;

    @FXML
    private TableView<Boxeador> tablaCrucero;

    @FXML
    private TableColumn<Boxeador, String> cruceroActivo;

    @FXML
    private TableColumn<Boxeador, String> cruceroApellidos;

    @FXML
    private TableColumn<Boxeador, String> cruceroCategoria;

    @FXML
    private TableColumn<Boxeador, String> cruceroFechaN;

    @FXML
    private TableColumn<Boxeador, String> cruceroNombre;

    @FXML
    private TableColumn<Boxeador, String> cruceroPeso;

    @FXML
    private TableColumn<Boxeador, String> cruceroSexo;

    @FXML
    private TableColumn<Boxeador, String> cruceroTipo;


    @FXML
    private TableView<Boxeador> tablaSemipesado;

    @FXML
    private TableColumn<Boxeador, String> semiPesadoActivo;

    @FXML
    private TableColumn<Boxeador, String> semipesadoApellidos;

    @FXML
    private TableColumn<Boxeador, String> semipesadoCategoria;

    @FXML
    private TableColumn<Boxeador, String> semipesadoFechaN;

    @FXML
    private TableColumn<Boxeador, String> semipesadoNombre;

    @FXML
    private TableColumn<Boxeador, String> semipesadoPeso;

    @FXML
    private TableColumn<Boxeador, String> semipesadoSexo;

    @FXML
    private TableColumn<Boxeador, String> semipesadoTipo;

    @FXML
    private TableView<Boxeador> tablaTodos;

    @FXML
    private TableColumn<Boxeador, String> todosActivo;

    @FXML
    private TableColumn<Boxeador, String> todosApellidos;

    @FXML
    private TableColumn<Boxeador, String> todosCategoria;

    @FXML
    private TableColumn<Boxeador, String> todosFechaN;

    @FXML
    private TableColumn<Boxeador, String> todosNombre;

    @FXML
    private TableColumn<Boxeador, String> todosPeso;

    @FXML
    private TableColumn<Boxeador, String> todosTipo;

    @FXML
    private TableColumn<Boxeador, String> todosSexo;

    @FXML
    private TableView<Boxeador> tablaMedio;

    @FXML
    private TableColumn<Boxeador, String> medioActivo;

    @FXML
    private TableColumn<Boxeador, String> medioApellidos;

    @FXML
    private TableColumn<Boxeador, String> medioCategoria;

    @FXML
    private TableColumn<Boxeador, String> medioFechaN;

    @FXML
    private TableColumn<Boxeador, String> medioNombre;

    @FXML
    private TableColumn<Boxeador, String> medioPeso;

    @FXML
    private TableColumn<Boxeador, String> medioSexo;

    @FXML
    private TableColumn<Boxeador, String> medioTipo;


    @FXML
    private TableView<Boxeador> tableGallo;

    @FXML
    private TableColumn<Boxeador, String> galloActivo;

    @FXML
    private TableColumn<Boxeador, String> galloApellidos;

    @FXML
    private TableColumn<Boxeador, String> galloCategoria;

    @FXML
    private TableColumn<Boxeador, String> galloFechaN;

    @FXML
    private TableColumn<Boxeador, String> galloNombre;

    @FXML
    private TableColumn<Boxeador, String> galloPeso;

    @FXML
    private TableColumn<Boxeador, String> galloSexo;

    @FXML
    private TableColumn<Boxeador, String> galloTipo;


    @FXML
    private TableView<Boxeador> tableLigero;

    @FXML
    private TableColumn<Boxeador, String> ligeroActivo;

    @FXML
    private TableColumn<Boxeador, String> ligeroApellidos;

    @FXML
    private TableColumn<Boxeador, String> ligeroCategoria;

    @FXML
    private TableColumn<Boxeador, String> ligeroFechaN;

    @FXML
    private TableColumn<Boxeador, String> ligeroNombre;

    @FXML
    private TableColumn<Boxeador, String> ligeroPeso;

    @FXML
    private TableColumn<Boxeador, String> ligeroSexo;

    @FXML
    private TableColumn<Boxeador, String> ligeroTipo;

    @FXML
    private TableView<Boxeador> tableMosca;

    @FXML
    private TableColumn<Boxeador, String> moscaActivo;

    @FXML
    private TableColumn<Boxeador, String> moscaApellido;

    @FXML
    private TableColumn<Boxeador, String> moscaCategoria;

    @FXML
    private TableColumn<Boxeador, String> moscaFechaN;

    @FXML
    private TableColumn<Boxeador, String> moscaNombre;

    @FXML
    private TableColumn<Boxeador, String> moscaPeso;

    @FXML
    private TableColumn<Boxeador, String> moscaSexo;

    @FXML
    private TableColumn<Boxeador, String> moscaTipo;

    @FXML
    private TableView<Boxeador> tablePluma;

    @FXML
    private TableColumn<Boxeador, String> plumaActivo;

    @FXML
    private TableColumn<Boxeador, String> plumaApellidos;

    @FXML
    private TableColumn<Boxeador, String> plumaCategoria;

    @FXML
    private TableColumn<Boxeador, String> plumaFechaN;

    @FXML
    private TableColumn<Boxeador, String> plumaNombre;

    @FXML
    private TableColumn<Boxeador, String> plumaPeso;

    @FXML
    private TableColumn<Boxeador, String> plumaSexo;

    @FXML
    private TableColumn<Boxeador, String> plumaTipo;

    @FXML
    private TableView<Boxeador> tableWelter;

    @FXML
    private TableColumn<Boxeador, String> welterActivo;

    @FXML
    private TableColumn<Boxeador, String> welterApellidos;

    @FXML
    private TableColumn<Boxeador, String> welterCategoria;

    @FXML
    private TableColumn<Boxeador, String> welterFechaN;

    @FXML
    private TableColumn<Boxeador, String> welterNombre;

    @FXML
    private TableColumn<Boxeador, String> welterPeso;

    @FXML
    private TableColumn<Boxeador, String> welterSexo;

    @FXML
    private TableColumn<Boxeador, String> welterTipo;


    @FXML
    private Tab etiquetaCrucero;

    @FXML
    private Tab etiquetaGallo;

    @FXML
    private Tab etiquetaLigero;

    @FXML
    private Tab etiquetaMedio;

    @FXML
    private Tab etiquetaMosca;

    @FXML
    private Tab etiquetaPesado;

    @FXML
    private Tab etiquetaPluma;

    @FXML
    private Tab etiquetaSemipesado;

    @FXML
    private Tab etiquetaTodos;

    @FXML
    private Tab etiquetaWelter;

    @FXML
    private Button botonNuevo;

    BoxeadorDAO boxeadorDAO = new BoxeadorDAO();

    @FXML
    void buscarBoxeador(ActionEvent event) {

        Object evento = event.getSource();

        if (evento == botonBuscar) {
            buscarEnTabla(labelBusquedaTodos, tablaTodos);
        } else if (evento == buscarPesado) {
            buscarEnTabla(labelBusquedaPesado, TablePesado);
        } else if (evento == BuscarSemi) {
            buscarEnTabla(labelBusquedaSemi, tablaSemipesado);
        } else if (evento == BuscarC) {
            buscarEnTabla(labelBusquedaCrucero, tablaCrucero);
        } else if (evento == buscarMedio) {
            buscarEnTabla(labelBusquedaMedio, tablaMedio);
        } else if (evento == buscarWelter) {
            buscarEnTabla(labelBusquedaWelter, tableWelter);
        } else if (evento == buscarLigero) {
            buscarEnTabla(labelBusquedaLigero, tableLigero);
        } else if (evento == buscarPluma) {
            buscarEnTabla(labelBusquedaPluma, tablePluma);
        } else if (evento == buscarGallo) {
            buscarEnTabla(labelBusquedaGallo, tableGallo);
        } else if (evento == buscarMosca) {
            buscarEnTabla(labelBusquedaMosca, tableMosca);
        }

    }

    private  void buscarEnTabla (TextField campo, TableView<Boxeador> tabla){

        String texto = campo.getText().trim().toLowerCase();
        ObservableList<Boxeador> lista = tabla.getItems();

        if(texto.isEmpty())return;

        lista.stream()
                .filter(box -> box.getNombre().toLowerCase().contains(texto)
                || box.getApellidos().toLowerCase().contains(texto))
                .findFirst()
                .ifPresent(box ->{
                    tabla.getSelectionModel().select(box);
                    tabla.scrollTo(box);
                });
    }

    private void agregarDobleClick(TableView<Boxeador> tabla) {
        tabla.setRowFactory(tv -> {
            TableRow<Boxeador> fila = new TableRow<>();
            fila.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !fila.isEmpty()) {
                    Boxeador boxeadorSeleccionado = fila.getItem();
                    irFichaBoxeador(boxeadorSeleccionado);
                }
            });
            return fila;
        });
    }

    private void irFichaBoxeador(Boxeador boxeador) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    BoxeoApplication.class.getResource("FichaBoxeador.fxml")
            );
            Scene scene = new Scene(fxmlLoader.load());

            // Pasarle el boxeador al controlador
            FichaBoxeadorController controller = fxmlLoader.getController();
            controller.setBoxeador(boxeador);
            Stage stage = (Stage) tablaTodos.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irNuevoBoxeador(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaNuevoBoxeador.fxml"));
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

    //METODO PARA FILTRA POR TIPO DE BOXEADOR
    private List<Boxeador> filtrar(List<Boxeador> lista, String categoria) {
        return lista.stream()
                .filter(b -> b.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    @FXML
    void initialize() {

        todosNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        todosApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        todosPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        todosActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        todosTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        todosFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        todosCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        todosSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        cruceroNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cruceroApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        cruceroPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        cruceroActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        cruceroTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        cruceroFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        cruceroCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        cruceroSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        semipesadoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        semipesadoApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        semipesadoPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        semiPesadoActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        semipesadoTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        semipesadoFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        semipesadoCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        semipesadoSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        medioNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        medioApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        medioPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        medioActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        medioTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        medioFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        medioCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        medioSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        welterNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        welterApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        welterPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        welterActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        welterTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        welterFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        welterCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        welterSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        ligeroNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ligeroApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        ligeroPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        ligeroActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        ligeroTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        ligeroFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        ligeroCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        ligeroSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        plumaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        plumaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        plumaPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        plumaActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        plumaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        plumaFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        plumaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        plumaSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        galloNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        galloApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        galloPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        galloActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        galloTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        galloFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        galloCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        galloSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        moscaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        moscaApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        moscaPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        moscaActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        moscaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        moscaFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        moscaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        moscaSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        pesadoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        pesadoApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        pesadoPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        pesadoActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        pesadoTipo.setCellValueFactory(new PropertyValueFactory<>("tipoBox"));
        pesadoFechaN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        pesadoCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        pesadoSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));

        Task<List<Boxeador>> task = new Task<>() {
            @Override
            protected List<Boxeador> call() {
                return boxeadorDAO.listarBoxeadores();
            }
        };

        task.setOnSucceeded(e -> {

            List<Boxeador> todos = task.getValue();

            // TABLA TODOS
            tablaTodos.setItems(FXCollections.observableArrayList(todos));

            // TABLA CRUCERO
            tablaCrucero.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "crucero")));

            // TABLA SEMIPESADO
            tablaSemipesado.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "semipesado")));

            // TABLA MEDIO
            tablaMedio.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "medio")
            ));

            // TABLA WELTER
            tableWelter.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "wélter")
            ));

            // TABLA LIGERO
            tableLigero.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "ligero")
            ));

            // TABLA PLUMA
            tablePluma.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "pluma")
            ));

            // TABLA GALLO
            tableGallo.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "gallo")
            ));

            // TABLA MOSCA
            tableMosca.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "mosca")
            ));

            // TABLA PESADO
            TablePesado.setItems(FXCollections.observableArrayList(
                    filtrar(todos, "pesado")
            ));


        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        agregarDobleClick(tablaTodos);
        agregarDobleClick(TablePesado);
        agregarDobleClick(tablaCrucero);
        agregarDobleClick(tablaSemipesado);
        agregarDobleClick(tablaMedio);
        agregarDobleClick(tableWelter);
        agregarDobleClick(tableLigero);
        agregarDobleClick(tablePluma);
        agregarDobleClick(tableGallo);
        agregarDobleClick(tableMosca);

        new Thread(task).start();


    }

}
