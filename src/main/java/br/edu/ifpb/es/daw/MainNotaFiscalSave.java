package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MainNotaFiscalSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            NotaFiscalDAO dao = new NotaFiscalDAOImpl(emf);

            NotaFiscal nf = new NotaFiscal();
            nf.setNumero("NF-" + System.nanoTime());
            nf.setDataEmissao(LocalDate.now());
            nf.setValorTotal(new BigDecimal("150.00"));
            nf.setStatusApi("PENDENTE");
            nf.setIdExtGovApi("EXT-" + System.nanoTime());
            nf.setLinkXml("http://link/xml/" + System.nanoTime());
            nf.setLinkPdf("http://link/pdf/" + System.nanoTime());

            dao.save(nf);
            System.out.println("Nota Fiscal salva com sucesso: " + nf);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}