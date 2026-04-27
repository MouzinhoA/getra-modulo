package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainPerfilSave {

    public static void main(String[] args) throws DawException{
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            PerfilDAO perfilDao = new PerfilDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);

            Perfil perfil = new Perfil();
            perfil.setId(1L);
            perfil.setNome("ADMIN");
            perfil.setPermissoes("CRUD_TOTAL");
            perfilDao.save(perfil);

            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setNome("Camila");
            usuario.setEmail("camila@email.com");
            usuario.setSenha_hash("123hash");
            usuario.setAtivo(true);
            usuario.setPerfil(perfil);
            usuarioDao.save(usuario);

            System.out.println(perfil);
            System.out.println(usuario);
            
        }
    }
}