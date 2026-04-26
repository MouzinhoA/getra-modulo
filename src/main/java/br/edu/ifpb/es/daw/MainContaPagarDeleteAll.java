package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainContaPagarDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ContaPagarDAO dao = new ContaPagarDAOImpl(emf);

            List<ContaPagar> contas = dao.getAll();

            for (ContaPagar c : contas) {
                dao.delete(c.getId());
            }

            System.out.println("Total de contas removidas: " + contas.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}