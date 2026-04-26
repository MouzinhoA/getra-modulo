package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MainNotaFiscalSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);

            Fatura fatura = new Fatura();
            fatura.setValorTotal(1000.0);
            faturaDao.save(fatura);

            NotaFiscal notaFiscal = new NotaFiscal();
            notaFiscal.setNumero("NF-" + System.nanoTime());
            notaFiscal.setDataEmissao(LocalDate.now());
            notaFiscal.setValorTotal(new BigDecimal("150.00"));
            notaFiscal.setStatusApi("PENDENTE");
            notaFiscal.setIdExtGovApi("EXT-" + System.nanoTime());
            notaFiscal.setLinkXml("http://link/xml/" + System.nanoTime());
            notaFiscal.setLinkPdf("http://link/pdf/" + System.nanoTime());
            notaFiscal.setFatura(fatura);
            notaFiscalDao.save(notaFiscal);

            System.out.println(fatura);
            System.out.println(notaFiscal);

        }
    }
}