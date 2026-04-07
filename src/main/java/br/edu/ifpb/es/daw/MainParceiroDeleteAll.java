package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainParceiroDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ParceiroDAO dao = new ParceiroDAOImpl(emf);

            List<Parceiro> parceiros = dao.getAll();

            for (Parceiro p : parceiros) {
                dao.delete(p.getId());
            }

            System.out.println("Todos os parceiros foram removidos. Total: " + parceiros.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}