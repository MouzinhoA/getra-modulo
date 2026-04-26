package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainUsuarioDeleteAll {

    public static void main(String[] args) throws Exception {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);

            List<NotaFiscal> notaFiscals = notaFiscalDao.getAll();
            for (NotaFiscal notaFiscal : notaFiscals) {
                notaFiscalDao.delete(notaFiscal.getId());
            }

            List<Fatura> faturas = faturaDao.getAll();
            for (Fatura fatura : faturas) {
                faturaDao.delete(fatura.getId());
            }

            List<Usuario> usuarios = usuarioDao.getAll();
            for (Usuario usuario : usuarios) {
                usuarioDao.delete(usuario.getId());
            }
        }
    }
}
