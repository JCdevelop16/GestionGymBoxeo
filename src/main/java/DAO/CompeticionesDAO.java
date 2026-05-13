package DAO;

import Entidades.Boxeador;
import Entidades.Competicion;
import Entidades.ParticipacionCompe;
import Entidades.TablaCompeticiones;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CompeticionesDAO {

    public List<TablaCompeticiones> listarCompe(){
        EntityManager em = JPAUtil.getEntityManager();

        List<TablaCompeticiones> lista = em.createQuery(
                "SELECT new Entidades.TablaCompeticiones(" +
                        " b.nombre, b.apellidos, b.categoria, c.nombre, c.lugar, c.fechaInicio, " +
                        " c.fechaFin, c.tipo, p.resultado, p.confirmado) " +
                        " FROM ParticipacionCompe p " +
                        " JOIN p.idBoxeador b " +
                        " JOIN p.idCompeticion c", TablaCompeticiones.class
        ).getResultList();
        return lista;
    }

    public void crearCompeticion(TablaCompeticiones tabCompe, Boxeador box){
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Competicion compe = new Competicion();
            compe.setNombre(tabCompe.getNombreCompeticion());
            compe.setLugar(tabCompe.getLugarCompe());
            compe.setFechaInicio(tabCompe.getFechaInico());
            compe.setFechaFin(tabCompe.getFechaFinal());
            compe.setTipo(tabCompe.getTipoCompe());
            em.persist(compe);

            ParticipacionCompe patCompe = new ParticipacionCompe();
            patCompe.setResultado(tabCompe.getResultado());
            patCompe.setConfirmado(tabCompe.getConfirmado());
            patCompe.setIdBoxeador(box);
            patCompe.setIdCompeticion(compe);
            em.persist(patCompe);

            em.getTransaction().commit();

            // ← Enviar notificación a Android
            enviarNotificacion("competiciones", "Nueva competición",
                    "Se ha añadido la competición " + compe.getNombre()
                            + " en " + compe.getLugar());

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizarCompeticion(TablaCompeticiones tabCompe, String nombreOriginal, String fechaOriginal) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Boxeador box = em.createQuery(
                            "SELECT b FROM Boxeador b WHERE b.nombre = :nombre AND b.apellidos = :apellidos",
                            Boxeador.class)
                    .setParameter("nombre", tabCompe.getNombreBoxeador())
                    .setParameter("apellidos", tabCompe.getApellidosBoxeador())
                    .getSingleResult();

            Competicion compe = em.createQuery(
                            "SELECT c FROM Competicion c WHERE c.nombre = :nombre AND c.fechaInicio = :fechaInicio",
                            Competicion.class)
                    .setParameter("nombre", nombreOriginal)
                    .setParameter("fechaInicio", fechaOriginal)
                    .getSingleResult();

            compe.setNombre(tabCompe.getNombreCompeticion());
            compe.setLugar(tabCompe.getLugarCompe());
            compe.setFechaInicio(tabCompe.getFechaInico());
            compe.setFechaFin(tabCompe.getFechaFinal());
            compe.setTipo(tabCompe.getTipoCompe());
            em.merge(compe);

            ParticipacionCompe participacion = em.createQuery(
                            "SELECT p FROM ParticipacionCompe p WHERE p.idCompeticion = :compe AND p.idBoxeador = :boxeador",
                            ParticipacionCompe.class)
                    .setParameter("compe", compe)
                    .setParameter("boxeador", box)
                    .getSingleResult();

            participacion.setResultado(tabCompe.getResultado());
            participacion.setConfirmado(tabCompe.getConfirmado());
            em.merge(participacion);

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

    public void eliminarCompeticion(String nombreCompeticion, String fechaInicio, String nombreBoxeador, String apellidosBoxeador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Boxeador box = em.createQuery(
                            "SELECT b FROM Boxeador b WHERE b.nombre = :nombre AND b.apellidos = :apellidos", Boxeador.class)
                    .setParameter("nombre", nombreBoxeador)
                    .setParameter("apellidos", apellidosBoxeador)
                    .getSingleResult();

            Competicion compe = em.createQuery(
                            "SELECT c FROM Competicion c WHERE c.nombre = :nombre AND c.fechaInicio = :fechaInicio", Competicion.class)
                    .setParameter("nombre", nombreCompeticion)
                    .setParameter("fechaInicio", fechaInicio)
                    .getSingleResult();

            ParticipacionCompe participacion = em.createQuery(
                            "SELECT p FROM ParticipacionCompe p WHERE p.idCompeticion = :compe AND p.idBoxeador = :boxeador", ParticipacionCompe.class)
                    .setParameter("compe", compe)
                    .setParameter("boxeador", box)
                    .getSingleResult();

            em.remove(participacion);
            em.remove(compe);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}