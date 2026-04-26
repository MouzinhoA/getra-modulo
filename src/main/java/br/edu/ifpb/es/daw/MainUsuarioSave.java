package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainUsuarioSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);

            Usuario usuario = new Usuario();
            usuario.setIdPerfil(1L);
            usuario.setNome("Aline Mouzinho");
            usuario.setEmail("mouzinhoA@email.com");
            usuario.setSenha_hash("abc123hash");
            usuario.setAtivo(true);
            usuarioDao.save(usuario);

            Fatura fatura = new Fatura();
            fatura.setValorTotal(1000.0);
            fatura.setUsuario(usuario);
            faturaDao.save(fatura);

            //TODO CAMULIS: continuar perfil e conta a pagar aqui
            System.out.println(fatura);
            System.out.println(usuario);

        }
    }
}