package Controllers;

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
import java.nio.file.Path;
import java.nio.file.Paths;
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

    BoxeadorDAO boxeadorDAO = new BoxeadorDAO();
    private Boxeador boxeadorActual;
    private String nombreOriginal;

    public void setBoxeador(Boxeador boxeador) {

        this.boxeadorActual = boxeador;

        // TextFields
        nombre.setText(boxeador.getNombre());
        apellidos.setText(boxeador.getApellidos());
        dni.setText(boxeador.getDni());
        telefono.setText(boxeador.getTelefono());
        peso.setText(String.valueOf(boxeador.getPeso()));

        // DatePicker
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

        // ComboBox
        comboCategoría.setValue(boxeador.getCategoria());
        comboActivo.setValue(boxeador.getActivo() ? "Sí" : "No");
        ComboTipo.setValue(boxeador.getTipoBox());

        // RadioButton (ToggleGroup)
        String genero = boxeador.getGenero();

        if (genero != null) {
            genero = genero.trim();

            if (genero.equalsIgnoreCase("Masculino")) {
                seleccionHombre.setSelected(true);
            } else if (genero.equalsIgnoreCase("Femenino")) {
                seleccionMujer.setSelected(true);
            }
        }

        // Imagen
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

        // Bloquear edición al cargar
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

        if (activar) {
            // modo edición
            nombre.setStyle("-fx-control-inner-background: white;");
            apellidos.setStyle("-fx-control-inner-background: white;");
            dni.setStyle("-fx-control-inner-background: white;");
            telefono.setStyle("-fx-control-inner-background: white;");
            peso.setStyle("-fx-control-inner-background: white;");
        } else {
            // modo solo lectura (gris o default)
            nombre.setStyle("-fx-control-inner-background: grey;");
            apellidos.setStyle("-fx-control-inner-background: grey;");
            dni.setStyle("-fx-control-inner-background: grey;");
            telefono.setStyle("-fx-control-inner-background: grey;");
            peso.setStyle("-fx-control-inner-background: grey;");
        }
    }

    @FXML
    void validarEditar(ActionEvent event) {

        if (botonEditar.getText().equals("Editar")) {

            nombreOriginal = nombre.getText();

            activarEdicion(true);
            botonEditar.setText("Guardar");

        } else {

            activarEdicion(false);
            botonEditar.setText("Editar");

            // Obtener valores actuales correctamente
            String generoSeleccionado = null;
            if (grupoGenero.getSelectedToggle() != null) {
                RadioButton rb = (RadioButton) grupoGenero.getSelectedToggle();
                generoSeleccionado = rb.getText();
            }

            String activoSeleccionado = comboActivo.getValue();
            String categoriaSeleccionada = comboCategoría.getValue();
            String tipoSeleccionado = ComboTipo.getValue();

            // Validar cambios reales
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

            // Aca se validan los cambios antes de guardar
            if (!validarCampos()) {
                return;
            }

            // Actualizar objeto
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

            // Guardar en BD
            boxeadorDAO.actualizarBoxeador(nombreOriginal, boxeadorActual);

            System.out.println("Cambios guardados correctamente.");
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

        // Nombre y apellidos (solo texto)
        if (!nombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Nombre no válido");
            return false;
        }

        if (!apellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarError("Apellidos no válidos");
            return false;
        }

        // DNI (formato español)
        if (!dni.getText().matches("\\d{8}[A-Za-z]")) {
            mostrarError("DNI no válido");
            return false;
        }

        // Teléfono (9 dígitos)
        if (!telefono.getText().matches("\\d{9}")) {
            mostrarError("Teléfono no válido");
            return false;
        }

        // Peso (número con 2 decimales)
        if (!peso.getText().matches("\\d+(\\.\\d{1,2})?")) {
            mostrarError("Peso no válido");
            return false;
        }

        return true;
    }

    @FXML
    void irVerBoxeadores(ActionEvent event) {

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

                // 🔥 1. Carpeta real del proyecto (NO resources)
                File carpeta = new File(System.getProperty("user.dir"), "IMG");

                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                // 🔥 2. Nombre seguro (mejor que nombre directo)
                String nombreArchivo = boxeadorActual.getId()
                        + ".jpeg"; // o .jpg según prefieras

                File destino = new File(carpeta, nombreArchivo);

                // 🔥 3. Copiar y sobrescribir
                Files.copy(
                        file.toPath(),
                        destino.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );

                // 🔥 4. Guardar en BD
                boxeadorActual.setFotoUrl(nombreArchivo);

                // 🔥 5. Mostrar imagen correctamente
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
