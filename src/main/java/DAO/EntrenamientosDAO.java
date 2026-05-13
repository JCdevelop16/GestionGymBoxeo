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
                        "ent.nombre, b.nombre, b.apellidos, ent.apellidos, a.estado, ent.id, b.id) " +
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

            // ← Enviar notificación a Android
            enviarNotificacion("entrenamientos", "Nuevo entrenamiento",
                    "Se ha añadido un entrenamiento de " + entrenamientoNuevo.getTipo()
                            + " el " + entrenamientoNuevo.getFecha());

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizarEntrenamientos(TablaEntrenamientos tabEntreno){
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Entrenador ent = em.find(Entrenador.class, tabEntreno.getIdEntrenador());
            Boxeador box = em.find(Boxeador.class, tabEntreno.getIdBoxeador());

            Entrenamiento entrenamiento = em.createQuery(
                            "SELECT e FROM Entrenamiento e WHERE e.idEntrenador = :entrenador AND e.fecha = :fecha",
                            Entrenamiento.class)
                    .setParameter("entrenador", ent)
                    .setParameter("fecha", tabEntreno.getFecha())
                    .getSingleResult();

            entrenamiento.setFecha(tabEntreno.getFecha());
            entrenamiento.setHoraInicio(tabEntreno.getHoraInicio());
            entrenamiento.setHoraFinal(tabEntreno.getHoraFinal());
            entrenamiento.setTipo(tabEntreno.getTipo());
            entrenamiento.setLugar(tabEntreno.getLugar());
            entrenamiento.setIdEntrenador(ent);
            em.merge(entrenamiento);

            AsistenciaEntreno asistencia = em.createQuery(
                            "SELECT a FROM AsistenciaEntreno a WHERE a.idEntrenamiento = :entreno AND a.idBoxeador = :boxeador",
                            AsistenciaEntreno.class)
                    .setParameter("entreno", entrenamiento)
                    .setParameter("boxeador", box)
                    .getSingleResult();

            asistencia.setEstado(tabEntreno.getEstadoAsistencia());
            em.merge(asistencia);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private void enviarNotificacion(String topic, String titulo, String cuerpo) {
        try {
            String json = String.format(
                    "{\"topic\":\"%s\",\"titulo\":\"%s\",\"cuerpo\":\"%s\"}",
                    topic, titulo, cuerpo
            );
            java.net.HttpURLConnection con = (java.net.HttpURLConnection)
                    new java.net.URL("http://localhost:8080/api/notificacion/enviar").openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.getOutputStream().write(json.getBytes());
            con.getResponseCode();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarEntrenamiento(TablaEntrenamientos tabEntreno) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Entrenador ent = em.find(Entrenador.class, tabEntreno.getIdEntrenador());
            Boxeador box = em.find(Boxeador.class, tabEntreno.getIdBoxeador());

            Entrenamiento entrenamiento = em.createQuery(
                            "SELECT e FROM Entrenamiento e WHERE e.idEntrenador = :entrenador AND e.fecha = :fecha",
                            Entrenamiento.class)
                    .setParameter("entrenador", ent)
                    .setParameter("fecha", tabEntreno.getFecha())
                    .getSingleResult();

            AsistenciaEntreno asistencia = em.createQuery(
                            "SELECT a FROM AsistenciaEntreno a WHERE a.idEntrenamiento = :entreno AND a.idBoxeador = :boxeador",
                            AsistenciaEntreno.class)
                    .setParameter("entreno", entrenamiento)
                    .setParameter("boxeador", box)
                    .getSingleResult();

            em.remove(asistencia);
            em.remove(entrenamiento);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}