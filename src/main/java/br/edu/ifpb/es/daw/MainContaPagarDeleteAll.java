package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Parceiro;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainContaPagarDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ContaPagarDAO contasDao = new ContaPagarDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            ParceiroDAO parceiroDao = new ParceiroDAOImpl(emf);
            PerfilDAO perfilDao = new PerfilDAOImpl(emf);

            List<ContaPagar> contas = contasDao.getAll();
            for (ContaPagar c : contas) {
                contasDao.delete(c.getId());
            }

            List<Usuario> usuarios = usuarioDao.getAll();
            for (Usuario u : usuarios) {
                usuarioDao.delete(u.getId());
            }

            List<Parceiro> parceiros = parceiroDao.getAll();
            for (Parceiro p : parceiros) {
                parceiroDao.delete(p.getId());
            }

            List<Perfil> perfis = perfilDao.getAll();
            for (Perfil p : perfis) {
                perfilDao.delete(p.getId());
            }


            System.out.println("Todos os dados removidos.");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}