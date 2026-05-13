package Controllers;

import DAO.UsuarioDAO;
import Service.ContraseniaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestiongymboxeo.BoxeoApplication;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField contraseña;

    @FXML
    private Label mensajeError;

    @FXML
    private TextField usuario;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    void login(ActionEvent event) throws IOException {

        String user = usuario.getText().trim();
        String pass = contraseña.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            mensajeError.setText("Rellena todos los campos");
            return;
        }

        String hashBBDD = usuarioDAO.obtenerHash(user);

        if (hashBBDD == null || !ContraseniaService.verificar(pass, hashBBDD)) {
            mensajeError.setText("Usuario o contraseña incorrectos");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaInicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) usuario.getScene().getWindow();
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setScene(scene);
    }
}