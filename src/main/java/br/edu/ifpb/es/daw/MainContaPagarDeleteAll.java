package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.Parceiro;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainContaPagarDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ContaPagarDAO contasDao = new ContaPagarDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            ParceiroDAO parceiroDao = new ParceiroDAOImpl(emf);
            PerfilDAO perfilDao = new PerfilDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);

            List<ContaPagar> contas = contasDao.getAll();
            for (ContaPagar contaPagar : contas) {
                contasDao.delete(contaPagar.getId());
            }

            List<Fatura> faturas = faturaDao.getAll();
            for (Fatura fatura : faturas) {
                faturaDao.delete(fatura.getId());
            }

            List<Usuario> usuarios = usuarioDao.getAll();
            for (Usuario usuario : usuarios) {
                usuarioDao.delete(usuario.getId());
            }

            List<Parceiro> parceiros = parceiroDao.getAll();
            for (Parceiro parceiro : parceiros) {
                parceiroDao.delete(parceiro.getId());
            }

            List<Perfil> perfis = perfilDao.getAll();
            for (Perfil perfil : perfis) {
                perfilDao.delete(perfil.getId());
            }

        }
    }
}