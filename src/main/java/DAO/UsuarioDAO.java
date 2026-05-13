package DAO;

import Entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.gestiongymboxeo.BoxeoApplication;

public class UsuarioDAO {

    public String obtenerHash(String nombreUsuario) {
        EntityManager em = BoxeoApplication.emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT u.password FROM Usuario u WHERE u.usuario = :usuario", String.class)
                    .setParameter("usuario", nombreUsuario)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}