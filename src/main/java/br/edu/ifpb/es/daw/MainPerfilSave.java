package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.entities.Perfil;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainPerfilSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            PerfilDAO dao = new PerfilDAOImpl(emf);

            Perfil perfil = new Perfil();
            perfil.setNome("ADMIN");
            perfil.setPermissoes("CRUD_TOTAL");

            dao.save(perfil);
            System.out.println("Perfil salvo com sucesso: " + perfil.getNome());
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar perfil: " + e.getMessage());
            e.printStackTrace();
        }
    }
}