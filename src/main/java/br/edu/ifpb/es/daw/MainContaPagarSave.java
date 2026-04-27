package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainContaPagarSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ContaPagarDAO dao = new ContaPagarDAOImpl(emf);

            ContaPagar conta = new ContaPagar();
            conta.setDescricao("Conta de Luz " + System.currentTimeMillis());
            conta.setValor(150.75);
            conta.setForma_pagamento("PIX");
            conta.setStatus("PENDENTE");
            conta.setData_vencimento("2026-04-10");
            conta.setData_pagamento(null);

            dao.save(conta);
            System.out.println("Conta à pagar salva com sucesso: " + conta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}