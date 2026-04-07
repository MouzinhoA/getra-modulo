package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainUsuarioSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            UsuarioDAO dao = new UsuarioDAOImpl(emf);

            Usuario usuario = new Usuario();
            usuario.setIdPerfil(1L);
            usuario.setNome("Aline Mouzinho");
            usuario.setEmail("mouzinhoA@email.com");
            usuario.setSenha_hash("abc123hash");
            usuario.setAtivo(true);

            dao.save(usuario);
            System.out.println("Usuário salvo com sucesso: " + usuario.getNome());

        } catch (Exception e) {
            System.err.println("Erro ao salvar usuário!");
            e.printStackTrace();
        }
    }
}