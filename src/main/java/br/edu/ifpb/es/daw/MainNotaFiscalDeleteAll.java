package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import br.edu.ifpb.es.daw.entities.Fatura;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainNotaFiscalDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);

            List<NotaFiscal> notas = notaFiscalDao.getAll();
            for (NotaFiscal nota : notas) {
                notaFiscalDao.delete(nota.getId());
            }

            List<Fatura> faturas = faturaDao.getAll();
            for (Fatura fatura : faturas){
                faturaDao.delete(fatura.getId());
            }

        }
    }
}