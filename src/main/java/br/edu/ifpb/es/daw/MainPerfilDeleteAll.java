package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainPerfilDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            PerfilDAO perfilDao = new PerfilDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);

            List<Perfil> perfis = perfilDao.getAll();
            for (Perfil p : perfis) {
                perfilDao.delete(p.getId());
            }

            List<Usuario> usuarios = usuarioDao.getAll();
            for (Usuario u : usuarios) {
                usuarioDao.delete(u.getId());
            }

            System.out.println("Todos os perfis foram removidos. Total: " + perfis.size());

        } catch (Exception e) {
            System.err.println("Erro ao remover perfis: " + e.getMessage());
            e.printStackTrace();
        }
    }
}