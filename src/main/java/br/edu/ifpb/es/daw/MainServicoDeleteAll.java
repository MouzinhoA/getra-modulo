package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ServicoDAO;
import br.edu.ifpb.es.daw.dao.impl.ServicoDAOImpl;
import br.edu.ifpb.es.daw.entities.Servico;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainServicoDeleteAll {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ServicoDAO dao = new ServicoDAOImpl(emf);
            for (Servico s : dao.getAll()) {
                dao.delete(s.getId());
            }
            System.out.println("Todos os serviços foram removidos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}