package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainParceiroSave {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        Parceiro parceiro = new Parceiro();
        parceiro.setId(1L);
        parceiro.setNome_razao_social("Empresa ABC LTDA");
        parceiro.setCpf_cnpj("12.345.678/0001-90");
        parceiro.setEmail_contato("contato@empresa.com");
        parceiro.setDados_bancarios_pix("12345678900");

        em.getTransaction().begin();
        em.persist(parceiro);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Parceiro salvo");
    }
}