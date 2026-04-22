package Utils;

import javafx.scene.image.Image;

import java.io.File;

public class ImageUtil {
    private static final String IMG_PATH =
            System.getProperty("user.dir") + "/IMG/";

    public static File getImageFile(String nombreImagen) {
        return new File(IMG_PATH + nombreImagen);
    }

    public static Image loadImage(String nombreImagen) {
        File file = getImageFile(nombreImagen);
        return new Image(file.toURI().toString());
    }

    public static String getBasePath() {
        return IMG_PATH;
    }
}
