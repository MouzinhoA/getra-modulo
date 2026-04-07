package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.FaturaDAO;
import br.edu.ifpb.es.daw.dao.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.entities.Fatura;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainFaturaDeleteAll {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            FaturaDAO dao = new FaturaDAOImpl(emf);
            List<Fatura> faturas = dao.getAll();
            
            for (Fatura f : faturas) {
                dao.delete(f.getId());
            }
            System.out.println("Total de faturas removidas: " + faturas.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}