package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainUsuarioSave {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setIdPerfil(1L);
        usuario.setNome("Aline Mouzinho");
        usuario.setEmail("mouzinhoA@email.com");
        usuario.setSenha_hash("abc123hash");
        usuario.setAtivo(true);

        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Usuário salvo");
    }
}