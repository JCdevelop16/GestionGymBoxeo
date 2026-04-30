package DAO;

import Entidades.Boxeador;
import jakarta.persistence.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BoxeadorDAO {

    public List<Boxeador> listarBoxeadores() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Boxeador> lista = em.createQuery("select b from Boxeador b", Boxeador.class)
                .getResultList();
        em.close();
        return lista;
    }

    public List<Boxeador> listarBoxeadoresCompetidores() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Boxeador> lista = em.createQuery("SELECT b FROM Boxeador b WHERE b.tipoBox IN ('Profesional', 'Amateur')", Boxeador.class)
                .getResultList();
        em.close();
        return lista;
    }

    public Boxeador buscarBoxeador(String nombre){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Boxeador box = (Boxeador) em.createQuery(
                            "select b from Boxeador b where b.nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            return box;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void actualizarBoxeador(Boxeador boxeador) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(boxeador);
            tx.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void crearNuevoBoxeador(Boxeador boxeador){
        EntityManager em = JPAUtil.getEntityManager();

        Boxeador nuevoBoxeador = new Boxeador();
        nuevoBoxeador.setNombre(boxeador.getNombre());
        nuevoBoxeador.setApellidos(boxeador.getApellidos());
        nuevoBoxeador.setDni(boxeador.getDni());
        nuevoBoxeador.setFechaNacimiento(boxeador.getFechaNacimiento());
        nuevoBoxeador.setPeso(boxeador.getPeso());
        nuevoBoxeador.setCategoria(boxeador.getCategoria());
        nuevoBoxeador.setTelefono(boxeador.getTelefono());
        nuevoBoxeador.setFotoUrl(boxeador.getFotoUrl());
        nuevoBoxeador.setActivo(boxeador.getActivo());
        nuevoBoxeador.setId(null);
        nuevoBoxeador.setTipoBox(boxeador.getTipoBox());
        nuevoBoxeador.setGenero(boxeador.getGenero());

        em.getTransaction().begin();
        em.persist(nuevoBoxeador);
        em.getTransaction().commit();

    }

}
