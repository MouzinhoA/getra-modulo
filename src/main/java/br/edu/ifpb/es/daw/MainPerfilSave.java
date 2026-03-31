package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.Perfil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainPerfilSave {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        Perfil perfil = new Perfil();
        perfil.setId(1L);
        perfil.setNome("ADMIN");
        perfil.setPermissoes("CRUD_TOTAL");

        em.getTransaction().begin();
        em.persist(perfil);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Perfil salvo");
    }
}