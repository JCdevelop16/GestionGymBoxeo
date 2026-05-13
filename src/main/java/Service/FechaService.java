package Service;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaService {

    private static final DateTimeFormatter FORMATO_SALIDA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_ENTRADA = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd

    // Si todas tus fechas vienen en el mismo formato, usa este
    public static <T> void aplicarFormatoFecha(TableColumn<T, String> columna) {
        columna.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    try {
                        LocalDate fecha = LocalDate.parse(item, FORMATO_ENTRADA);
                        setText(fecha.format(FORMATO_SALIDA));
                    } catch (Exception e) {
                        setText(item);
                    }
                }
            }
        });
    }


}