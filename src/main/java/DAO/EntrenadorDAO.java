package DAO;

import Entidades.Boxeador;
import Entidades.Entrenador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class EntrenadorDAO {

    public List<Entrenador> listarEntrenadores(){

        EntityManager em = JPAUtil.getEntityManager();
        List<Entrenador> lista = em.createQuery("select e from Entrenador e", Entrenador.class)
                .getResultList();
        em.close();
        return lista;

    }

    public void crearEntrenador(Entrenador entrenador){
        EntityManager em = JPAUtil.getEntityManager();
        Entrenador nuevoEntrenador = new Entrenador();
        nuevoEntrenador.setNombre(entrenador.getNombre());
        nuevoEntrenador.setApellidos(entrenador.getApellidos());
        nuevoEntrenador.setDni(entrenador.getDni());
        nuevoEntrenador.setTelefono(entrenador.getTelefono());
        nuevoEntrenador.setEspecialidad(entrenador.getEspecialidad());

        em.getTransaction().begin();
        em.persist(nuevoEntrenador);
        em.getTransaction().commit();

    }

    public void actualizarEntrenador(Entrenador entrenador){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(entrenador);
            tx.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

    }

    public void eliminarEntrenador(Entrenador entrenador) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Entrenador e = em.find(Entrenador.class, entrenador.getId());
            if (e != null) {
                em.remove(e);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

}
