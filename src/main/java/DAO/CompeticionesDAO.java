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
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
