package Controllers.Boxeador;

import DAO.BoxeadorDAO;
import Entidades.Boxeador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PantallaNuevoBoxeadorController {


    @FXML
    private ComboBox<String> ComboTipo;

    @FXML
    private TextField apellidos;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonImg;

    @FXML
    private Button botonVolver;

    @FXML
    private Button boxeadoresButton;

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
    private DatePicker fechaN;

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

    BoxeadorDAO boxeadorDAO = new BoxeadorDAO();
    Boxeador nuevoBoxeador;
    private String nombreImagen = "sinfoto.png";

    @FXML
    void cambiarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                File carpeta = new File(System.getProperty("user.dir"), "IMG");

                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                String baseNombre = nombre.getText().trim();

                if (baseNombre.isEmpty()) {
                    mostrarError("Escribe el nombre antes de seleccionar la imagen");
                    return;
                }

                String extension = file.getName()
                        .substring(file.getName().lastIndexOf("."));

                nombreImagen = baseNombre + extension;

                File destino = new File(carpeta, nombreImagen);

                Files.copy(file.toPath(),
                        destino.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

                imagen.setImage(
                        new javafx.scene.image.Image(destino.toURI().toString())
                );

            } catch (Exception e) {
                e.printStackTrace();
                mostrarError("Error al guardar la imagen");
            }
        }
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
    void irVerCompeticiones(ActionEvent event) {

    }

    @FXML
    void irVerEntrenadores(ActionEvent event) {

    }

    @FXML
    void irVerEntrenamientos(ActionEvent event) {

    }

    @FXML
    void crearNuevoBoxeador(ActionEvent event) throws IOException {
        if (!validarCampos()) return;

        nuevoBoxeador = new Boxeador();

        nuevoBoxeador.setNombre(nombre.getText());
        nuevoBoxeador.setApellidos(apellidos.getText());
        nuevoBoxeador.setDni(dni.getText());
        nuevoBoxeador.setTelefono(telefono.getText());
        nuevoBoxeador.setPeso(BigDecimal.valueOf(Double.parseDouble(peso.getText())));
        nuevoBoxeador.setCategoria(comboCategoría.getValue());

        String genero = "";
        if (seleccionHombre.isSelected()) genero = "Masculino";
        else if (seleccionMujer.isSelected()) genero = "Femenino";

        nuevoBoxeador.setGenero(genero);
        nuevoBoxeador.setTipoBox(ComboTipo.getValue());
        nuevoBoxeador.setFechaNacimiento(String.valueOf(fechaN.getValue()));
        nuevoBoxeador.setActivo(comboActivo.getValue().equals("Si"));

        if (nombreImagen == null || nombreImagen.isEmpty()) {
            nombreImagen = "sinfoto.png"; // asegúrate de tenerla en tu carpeta IMG
        }
        nuevoBoxeador.setFotoUrl(nombreImagen);

        boxeadorDAO.crearNuevoBoxeador(nuevoBoxeador);

        irVerBoxeadores(event);


    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos() {
        if (!nombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Nombre no válido");
            return false;
        }

        if (!apellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Apellidos no válidos");
            return false;
        }

        if (!dni.getText().matches("\\d{8}[A-Za-z]")) {
            mostrarError("DNI no válido");
            return false;
        }

        if (!telefono.getText().matches("\\d{9}")) {
            mostrarError("Teléfono no válido");
            return false;
        }

        if (!peso.getText().matches("\\d+(\\.\\d{1,2})?")) {
            mostrarError("Peso no válido");
            return false;
        }

        return true;
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

        comboCategoría.getItems().addAll(
                "Pesado",
                "Medio",
                "Ligero",
                "Mosca",
                "Gallo",
                "Pluma",
                "Wélter",
                "Crucero",
                "Semipesado"
        );

        comboActivo.getItems().addAll(
                "Si",
                "No"
        );

        ComboTipo.getItems().addAll(
                "Sin Tipo",
                "Amateur",
                "Profesional"
        );

        seleccionHombre.setToggleGroup(grupoGenero);
        seleccionMujer.setToggleGroup(grupoGenero);



    }

}
