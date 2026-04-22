package DAO;

import Entidades.Boxeador;
import jakarta.persistence.*;

import javax.swing.*;
import java.util.List;

public class BoxeadorDAO {

    public List<Boxeador> listarBoxeadores() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Boxeador> lista = em.createQuery("select b from Boxeador b", Boxeador.class)
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

    public void actualizarBoxeador(String nombre, Boxeador boxeador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Boxeador boxeadorEditado = (Boxeador) em.createQuery(
                            "select b from Boxeador b where b.nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .getSingleResult();

            boxeadorEditado.setNombre(boxeador.getNombre() != null ? boxeador.getNombre() : boxeadorEditado.getNombre());
            boxeadorEditado.setApellidos(boxeador.getApellidos() != null ? boxeador.getApellidos() : boxeadorEditado.getApellidos());
            boxeadorEditado.setDni(boxeador.getDni() != null ? boxeador.getDni() : boxeadorEditado.getDni());
            boxeadorEditado.setTelefono(boxeador.getTelefono() != null ? boxeador.getTelefono() : boxeadorEditado.getTelefono());
            boxeadorEditado.setPeso(boxeador.getPeso() != null ? boxeador.getPeso() : boxeadorEditado.getPeso());
            boxeadorEditado.setCategoria(boxeador.getCategoria() != null ? boxeador.getCategoria() : boxeadorEditado.getCategoria());
            boxeadorEditado.setGenero(boxeador.getGenero() != null ? boxeador.getGenero() : boxeadorEditado.getGenero());
            boxeadorEditado.setTipoBox(boxeador.getTipoBox() != null ? boxeador.getTipoBox() : boxeadorEditado.getTipoBox());
            boxeadorEditado.setFechaNacimiento(String.valueOf(boxeador.getFechaNacimiento() != null ? boxeador.getFechaNacimiento() : boxeadorEditado.getFechaNacimiento()));
            if (boxeador.getActivo() != null)
                boxeadorEditado.setActivo(boxeador.getActivo());

            em.clear(); // ← limpia todos los objetos del contexto
            em.merge(boxeadorEditado); // ← re-adjunta solo el que queremos guardar

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(boxeadorEditado);
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
