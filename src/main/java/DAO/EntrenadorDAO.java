package DAO;

import Entidades.Boxeador;
import Entidades.Entrenador;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EntrenadorDAO {

    public List<Entrenador> listarEntrenadores(){

        EntityManager em = JPAUtil.getEntityManager();
        List<Entrenador> lista = em.createQuery("select e from Entrenador e", Entrenador.class)
                .getResultList();
        em.close();
        return lista;

    }

}
