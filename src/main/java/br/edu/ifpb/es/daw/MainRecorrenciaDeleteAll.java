package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainRecorrenciaDeleteAll {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            RecorrenciaDAO dao = new RecorrenciaDAOImpl(emf);
            for (Recorrencia r : dao.getAll()) {
                dao.delete(r.getId());
            }
            System.out.println("Todas as recorrências foram removidas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}