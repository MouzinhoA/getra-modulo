package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainPerfilDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            PerfilDAO perfilDao = new PerfilDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            ContaPagarDAO contaPagarDao = new ContaPagarDAOImpl(emf);

            List<NotaFiscal> notaFiscals = notaFiscalDao.getAll();
            for (NotaFiscal notaFiscal : notaFiscals) {
                notaFiscalDao.delete(notaFiscal.getId());
            }

            List<Fatura> faturas = faturaDao.getAll();
            for (Fatura fatura : faturas) {
                faturaDao.delete(fatura.getId());
            }

            List<ContaPagar> contas = contaPagarDao.getAll();
            for (ContaPagar contaPagar : contas) {
                contaPagarDao.delete(contaPagar.getId());
            }

            List<Usuario> usuarios = usuarioDao.getAll();
            for (Usuario usuario : usuarios) {
                usuarioDao.delete(usuario.getId());
            }

            List<Perfil> perfis = perfilDao.getAll();
            for (Perfil perfil : perfis) {
                perfilDao.delete(perfil.getId());
            }

        }
    }
}
