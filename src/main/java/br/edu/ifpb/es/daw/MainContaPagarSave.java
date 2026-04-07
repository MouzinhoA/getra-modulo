package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.ContaPagar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainContaPagarSave {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        ContaPagar conta = new ContaPagar();
        conta.setId(1L);
        conta.setIdUsuario(10L);
        conta.setDescricao("Conta de Luz");
        conta.setValor(150.75);
        conta.setForma_pagamento("PIX");
        conta.setStatus("PENDENTE");
        conta.setIdParceiro(5L);
        conta.setData_vencimento("2026-04-10");
        conta.setData_pagamento(null);

        em.getTransaction().begin();
        em.persist(conta);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Conta à pagar salva");
    }
}