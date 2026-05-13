package Service;

import org.mindrot.jbcrypt.BCrypt;

public class ContraseniaService {

    public static String hashear(String contraseña) {
        return BCrypt.hashpw(contraseña, BCrypt.gensalt());
    }

    public static boolean verificar(String contraseña, String hash) {
        return BCrypt.checkpw(contraseña, hash);
    }
}