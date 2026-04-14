package Controllers;

import DAO.BoxeadorDAO;
import Entidades.Boxeador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;


public class FichaBoxeadorController {

    @FXML
    private Button botonCompeticiones;

    @FXML
    private Button botonEntrenamiento;

    @FXML
    private Button boxeadoresButton;

    @FXML
    private Button calendarioButton;


    @FXML
    private Button competicionesButton;


    @FXML
    private Button entrenamientoButton;

    @FXML
    private ImageView imagen;

    @FXML
    private Button botonVolver;

    @FXML
    private Button botonEditar;

    @FXML
    private TextField activo;

    @FXML
    private TextField apellidos;

    @FXML
    private TextField categoria;

    @FXML
    private TextField dni;

    @FXML
    private TextField fechaN;

    @FXML
    private TextField genero;

    @FXML
    private TextField nombre;

    @FXML
    private TextField peso;

    @FXML
    private TextField telefono;

    @FXML
    private TextField tipo;

    @FXML
    private Button entrenadoresButton;

    BoxeadorDAO boxeadorDAO = new BoxeadorDAO();
    private Boxeador boxeadorActual;
    private String nombreOriginal;

    public void setBoxeador(Boxeador boxeador) {
        this.boxeadorActual = boxeador;
        nombre.setText(boxeador.getNombre());
        apellidos.setText(boxeador.getApellidos());
        fechaN.setText(boxeador.getFechaNacimiento().toString());
        dni.setText(boxeador.getDni());
        telefono.setText(boxeador.getTelefono());
        peso.setText(String.valueOf(boxeador.getPeso()));
        categoria.setText(boxeador.getCategoria());
        genero.setText(boxeador.getGenero());
        activo.setText(boxeador.getActivo() ? "Sí" : "No");
        tipo.setText(boxeador.getTipoBox());
        String nombreImg = boxeador.getNombre().toLowerCase();
        URL url = getClass().getResource("/IMG/" + nombreImg + ".jpeg");

        if (url != null) {
            imagen.setImage(new Image(url.toExternalForm()));
        } else {
            URL urlDefault = getClass().getResource("/IMG/sinfoto.jpeg");
            if (urlDefault != null) {
                imagen.setImage(new Image(urlDefault.toExternalForm()));
            }
        }

    }

    @FXML
    void validarEditar(ActionEvent event) {
        if (botonEditar.getText().equals("Editar")) {
            // Guardar el nombre original por si cancela o no cambia nada
            nombreOriginal = nombre.getText();

            nombre.setEditable(true);
            apellidos.setEditable(true);
            fechaN.setEditable(true);
            dni.setEditable(true);
            telefono.setEditable(true);
            peso.setEditable(true);
            categoria.setEditable(true);
            genero.setEditable(true);
            activo.setEditable(true);
            tipo.setEditable(true);
            botonEditar.setText("Guardar");

        } else {
            nombre.setEditable(false);
            apellidos.setEditable(false);
            fechaN.setEditable(false);
            dni.setEditable(false);
            telefono.setEditable(false);
            peso.setEditable(false);
            categoria.setEditable(false);
            genero.setEditable(false);
            activo.setEditable(false);
            tipo.setEditable(false);
            botonEditar.setText("Editar");

            // Comprobar si algo ha cambiado
            boolean hayCambios =
                    !nombre.getText().equals(boxeadorActual.getNombre()) ||
                            !apellidos.getText().equals(boxeadorActual.getApellidos()) ||
                            !dni.getText().equals(boxeadorActual.getDni()) ||
                            !telefono.getText().equals(boxeadorActual.getTelefono()) ||
                            !peso.getText().equals(String.valueOf(boxeadorActual.getPeso())) ||
                            !categoria.getText().equals(boxeadorActual.getCategoria()) ||
                            !genero.getText().equals(boxeadorActual.getGenero()) ||
                            !tipo.getText().equals(boxeadorActual.getTipoBox()) ||
                            !fechaN.getText().equals(boxeadorActual.getFechaNacimiento()) ||
                            !activo.getText().equals(boxeadorActual.getActivo() ? "Sí" : "No");

            if (!hayCambios) {
                System.out.println("Sin cambios, no se guarda nada.");
                return; // ← no hace nada si no cambió nada
            }

            // Solo llega aquí si hay cambios reales
            Boxeador boxeadorActualizado = boxeadorActual;
            // ... tus setters condicionales
            boxeadorDAO.actualizarBoxeador(nombreOriginal, boxeadorActualizado);
        }

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
    void irVerEntrenamientos(ActionEvent event) {

    }

    @FXML
    void verCompeticiones(ActionEvent event) {

    }

    @FXML
    void verEntrenamiento(ActionEvent event) {

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
    void volver(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    BoxeoApplication.class.getResource("PantallaBoxeadores.fxml")
            );
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) botonVolver.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {


    }

}
