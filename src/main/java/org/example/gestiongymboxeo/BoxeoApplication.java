package org.example.gestiongymboxeo;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BoxeoApplication extends Application {

    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ClubBoxing");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxeoApplication.class.getResource("PantallaInicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion Boxeo");
        stage.setScene(scene);
        stage.show();
    }
}
