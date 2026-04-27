package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainParceiroDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            ParceiroDAO parceiroDao = new ParceiroDAOImpl(emf);
            ContaPagarDAO contaDao = new ContaPagarDAOImpl(emf);

            List<Parceiro> parceiros = parceiroDao.getAll();
            for (Parceiro p : parceiros) {
                parceiroDao.delete(p.getId());
            }

            List<ContaPagar> contas = contaDao.getAll();
            for (ContaPagar c : contas) {
                contaDao.delete(c.getId());
            }

            System.out.println("Todos os parceiros foram removidos. Total: " + parceiros.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}