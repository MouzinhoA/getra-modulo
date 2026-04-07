package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PerfilDAO;
import br.edu.ifpb.es.daw.dao.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.entities.Perfil;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainPerfilDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            PerfilDAO dao = new PerfilDAOImpl(emf);

            List<Perfil> perfis = dao.getAll();

            for (Perfil p : perfis) {
                dao.delete(p.getId());
            }

            System.out.println("Todos os perfis foram removidos. Total: " + perfis.size());

        } catch (Exception e) {
            System.err.println("Erro ao remover perfis: " + e.getMessage());
            e.printStackTrace();
        }
    }
}