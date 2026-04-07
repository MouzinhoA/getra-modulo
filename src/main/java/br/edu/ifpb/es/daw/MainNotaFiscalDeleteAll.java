package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainNotaFiscalDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            NotaFiscalDAO dao = new NotaFiscalDAOImpl(emf);

            List<NotaFiscal> notas = dao.getAll();

            for (NotaFiscal n : notas) {
                dao.delete(n.getId());
            }

            System.out.println("Todas as notas fiscais foram removidas. Total: " + notas.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}