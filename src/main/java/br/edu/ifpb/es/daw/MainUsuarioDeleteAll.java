package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainUsuarioDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            UsuarioDAO dao = new UsuarioDAOImpl(emf);

            List<Usuario> usuarios = dao.getAll();

            for (Usuario u : usuarios) {
                dao.delete(u.getId());
            }

            System.out.println("Todos os usuários foram removidos. Total: " + usuarios.size());

        } catch (Exception e) {
            System.err.println("Erro ao remover usuários!");
            e.printStackTrace();
        }
    }
}