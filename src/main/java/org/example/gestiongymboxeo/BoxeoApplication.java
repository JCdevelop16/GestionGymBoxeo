package org.example.gestiongymboxeo;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class BoxeoApplication extends Application {

    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ClubBoxing");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion Boxeo");
        stage.setResizable(false);
        stage.setWidth(500);
        stage.setHeight(400);
        stage.setScene(scene);
        stage.show();

        //Creamos el hash para la contraseña
        /*String hash = BCrypt.hashpw("BoxingClub2026&", BCrypt.gensalt());
        System.out.println("Hash: " + hash);*/
    }
}
