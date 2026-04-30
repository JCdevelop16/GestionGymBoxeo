package DAO;

import Entidades.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EntrenamientosDAO {

    public List<TablaEntrenamientos> listarEntrenamientos(){
        EntityManager em = JPAUtil.getEntityManager();

        List<TablaEntrenamientos> listaEntrenamientos = em.createQuery(
                "SELECT new Entidades.TablaEntrenamientos(" +
                        "e.fecha, e.tipo, e.horaInicio, e.horaFinal, e.lugar, " +
                        "ent.nombre, b.nombre, b.apellidos, ent.apellidos, a.estado) " +
                        "FROM Entrenamiento e " +
                        "JOIN e.idEntrenador ent " +
                        "JOIN AsistenciaEntreno a ON a.idEntrenamiento = e " +
                        "JOIN a.idBoxeador b",
                TablaEntrenamientos.class
        ).getResultList();
        return listaEntrenamientos;
    }

    public void crearEntrenamiento(TablaEntrenamientos entrenamiento, Boxeador box, Entrenador ent){
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Entrenamiento entrenamientoNuevo = new Entrenamiento();
            entrenamientoNuevo.setFecha(entrenamiento.getFecha());
            entrenamientoNuevo.setHoraInicio(entrenamiento.getHoraInicio());
            entrenamientoNuevo.setHoraFinal(entrenamiento.getHoraFinal());
            entrenamientoNuevo.setTipo(entrenamiento.getTipo());
            entrenamientoNuevo.setLugar(entrenamiento.getLugar());
            entrenamientoNuevo.setIdEntrenador(ent);

            em.persist(entrenamientoNuevo);

            AsistenciaEntreno asisEntreno = new AsistenciaEntreno();
            asisEntreno.setEstado(entrenamiento.getEstadoAsistencia());
            asisEntreno.setIdBoxeador(box);
            asisEntreno.setIdEntrenamiento(entrenamientoNuevo);

            em.persist(asisEntreno);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public void actualizarEntreniamientos(TablaEntrenamientos tabEntreno){
        EntityManager em = JPAUtil.getEntityManager();





    }
}
