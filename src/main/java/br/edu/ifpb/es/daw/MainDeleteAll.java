package br.edu.ifpb.es.daw;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainDeleteAll {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM NotaFiscal").executeUpdate();
        em.createQuery("DELETE FROM Fatura").executeUpdate();
        em.createQuery("DELETE FROM Recorrencia").executeUpdate();
        em.createQuery("DELETE FROM Servico").executeUpdate();
        em.createQuery("DELETE FROM Cliente").executeUpdate();

        em.createQuery("DELETE FROM ContaPagar").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.createQuery("DELETE FROM Perfil").executeUpdate();
        em.createQuery("DELETE FROM Parceiro").executeUpdate();

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Dados removidos :x ");
    }
}
