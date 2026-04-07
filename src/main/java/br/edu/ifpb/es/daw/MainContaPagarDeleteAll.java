package br.edu.ifpb.es.daw;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainContaPagarDeleteAll {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM ContaPagar").executeUpdate();
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Todas as contas à pagar foram removidas!");
    }
}