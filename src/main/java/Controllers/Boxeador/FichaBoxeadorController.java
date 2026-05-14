package Controllers.Boxeador;

import DAO.BoxeadorDAO;
import Entidades.Boxeador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
    private Button botonImg;

    @FXML
    private ComboBox<String> comboActivo;

    @FXML
    private TextField apellidos;

    @FXML
    private ComboBox<String> comboCategoría;

    @FXML
    private TextField dni;

    @FXML
    private DatePicker fechaN;

    @FXML
    private ToggleGroup grupoGenero;


    @FXML
    private RadioButton seleccionHombre;

    @FXML
    private RadioButton seleccionMujer;

    @FXML
    private TextField nombre;

    @FXML
    private TextField peso;

    @FXML
    private TextField telefono;

    @FXML
    private ComboBox<String> ComboTipo;

    @FXML
    private Button entrenadoresButton;

    @FXML
    private Button botonEliminar;

    BoxeadorDAO boxeadorDAO = new BoxeadorDAO();
    private Boxeador boxeadorActual;

    public void setBoxeador(Boxeador boxeador) {

        this.boxeadorActual = boxeador;

        nombre.setText(boxeador.getNombre());
        apellidos.setText(boxeador.getApellidos());
        dni.setText(boxeador.getDni());
        telefono.setText(boxeador.getTelefono());
        peso.setText(String.valueOf(boxeador.getPeso()));

        String fechaTexto = boxeador.getFechaNacimiento();

        if (fechaTexto != null && !fechaTexto.isEmpty()) {

            LocalDate fecha;

            if (fechaTexto.contains("/")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fecha = LocalDate.parse(fechaTexto, formatter);
            } else {
                fecha = LocalDate.parse(fechaTexto); // yyyy-MM-dd
            }

            fechaN.setValue(fecha);
        }

        comboCategoría.setValue(boxeador.getCategoria());
        comboActivo.setValue(boxeador.getActivo() ? "Sí" : "No");
        ComboTipo.setValue(boxeador.getTipoBox());

        String genero = boxeador.getGenero();

        if (genero != null) {
            genero = genero.trim();

            if (genero.equalsIgnoreCase("Masculino")) {
                seleccionHombre.setSelected(true);
            } else if (genero.equalsIgnoreCase("Femenino")) {
                seleccionMujer.setSelected(true);
            }
        }

        String base = boxeador.getNombre().toLowerCase();
        String[] extensiones = {".jpeg", ".jpg", ".png"};

        File file = null;

        for (String ext : extensiones) {

            File test = new File(
                    System.getProperty("user.dir") + "/IMG/" + base + ext
            );

            if (test.exists()) {
                file = test;
                break;
            }
        }

        if (file != null) {
            imagen.setImage(new Image(file.toURI().toString()));
        } else {
            setImagenDefault();
        }

        activarEdicion(false);
    }

    private void setImagenDefault() {
        URL urlDefault = getClass().getResource("/IMG/sinfoto.jpeg");

        if (urlDefault != null) {
            imagen.setImage(new Image(urlDefault.toExternalForm()));
        }
    }

    @FXML
    private void activarEdicion(boolean activar) {
        nombre.setEditable(activar);
        apellidos.setEditable(activar);
        dni.setEditable(activar);
        telefono.setEditable(activar);
        peso.setEditable(activar);

        fechaN.setDisable(!activar);

        comboCategoría.setDisable(!activar);
        comboActivo.setDisable(!activar);
        ComboTipo.setDisable(!activar);

        seleccionHombre.setDisable(!activar);
        seleccionMujer.setDisable(!activar);
        botonImg.setDisable(!activar);

        aplicarEstiloTextFields(activar);
    }

    private void aplicarEstiloTextFields(boolean activar) {
        String estilo = activar
                ? "-fx-control-inner-background: white; -fx-text-fill: black;"
                : "-fx-control-inner-background: #4A4A4A; -fx-text-fill: #ECECEC;";

        nombre.setStyle(estilo);
        apellidos.setStyle(estilo);
        dni.setStyle(estilo);
        telefono.setStyle(estilo);
        peso.setStyle(estilo);
    }

    @FXML
    void validarEditar(ActionEvent event) {

        if (botonEditar.getText().equals("Editar")) {

            activarEdicion(true);
            botonEditar.setText("Guardar");

        } else {

            activarEdicion(false);
            botonEditar.setText("Editar");

            String generoSeleccionado = null;
            if (grupoGenero.getSelectedToggle() != null) {
                RadioButton rb = (RadioButton) grupoGenero.getSelectedToggle();
                generoSeleccionado = rb.getText();
            }

            String activoSeleccionado = comboActivo.getValue();
            String categoriaSeleccionada = comboCategoría.getValue();
            String tipoSeleccionado = ComboTipo.getValue();

            boolean hayCambios =
                    !nombre.getText().equals(boxeadorActual.getNombre()) ||
                            !apellidos.getText().equals(boxeadorActual.getApellidos()) ||
                            !dni.getText().equals(boxeadorActual.getDni()) ||
                            !telefono.getText().equals(boxeadorActual.getTelefono()) ||
                            !peso.getText().equals(String.valueOf(boxeadorActual.getPeso())) ||
                            !categoriaSeleccionada.equals(boxeadorActual.getCategoria()) ||
                            !java.util.Objects.equals(generoSeleccionado, boxeadorActual.getGenero()) ||
                            !tipoSeleccionado.equals(boxeadorActual.getTipoBox()) ||
                            !fechaN.getValue().equals(boxeadorActual.getFechaNacimiento()) ||
                            !activoSeleccionado.equals(boxeadorActual.getActivo() ? "Sí" : "No");

            if (!hayCambios) {
                System.out.println("Sin cambios, no se guarda nada.");
                return;
            }

            if (!validarCampos()) {
                return;
            }

            boxeadorActual.setNombre(nombre.getText());
            boxeadorActual.setApellidos(apellidos.getText());
            boxeadorActual.setDni(dni.getText());
            boxeadorActual.setTelefono(telefono.getText());
            boxeadorActual.setPeso(BigDecimal.valueOf(Double.parseDouble(peso.getText())));
            boxeadorActual.setCategoria(categoriaSeleccionada);
            boxeadorActual.setGenero(generoSeleccionado);
            boxeadorActual.setTipoBox(tipoSeleccionado);
            boxeadorActual.setFechaNacimiento(String.valueOf(fechaN.getValue()));
            boxeadorActual.setActivo(activoSeleccionado.equals("Sí"));

            boxeadorDAO.actualizarBoxeador(boxeadorActual);


        }
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
    void irVerEntrenamientos(ActionEvent event) {

    }

    @FXML
    void irVerEntrenadores(ActionEvent event) throws IOException {

    }

    @FXML
    void volver(ActionEvent event) throws IOException {

        irVerBoxeadores(event);

    }

    @FXML
    void cambiarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) botonImg.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            try {

                File carpeta = new File(System.getProperty("user.dir"), "IMG");

                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                String nombreArchivo = boxeadorActual.getNombre()
                        + ".jpeg";

                File destino = new File(carpeta, nombreArchivo);

                Files.copy(
                        file.toPath(),
                        destino.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );

                boxeadorActual.setFotoUrl(nombreArchivo);

                //Mostrar imagen correctamente
                imagen.setImage(
                        new javafx.scene.image.Image(destino.toURI().toString())
                );

            } catch (IOException e) {
                e.printStackTrace();
                mostrarError("Error al cambiar la imagen");
            }
        }
    }

    @FXML
    void eliminar(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar a " + boxeadorActual.getNombre() + " " + boxeadorActual.getApellidos() + "?",
                ButtonType.YES, ButtonType.NO);

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.YES) {
                boxeadorDAO.eliminarBoxeador(boxeadorActual);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaBoxeadores.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) botonEliminar.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                    mostrarError("Error al volver a la lista.");
                }
            }
        });
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

    }

}
