package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.NotaFiscal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainNotaFiscalSave {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("daw");

        EntityManager em = emf.createEntityManager();

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNumero("NF-001");
        notaFiscal.setDataEmissao(LocalDate.now());
        notaFiscal.setValorTotal(new BigDecimal("150.00"));
        notaFiscal.setStatusApi("PENDENTE");
        notaFiscal.setIdExtGovApi("EXT123");
        notaFiscal.setLinkXml("http://link/xml");
        notaFiscal.setLinkPdf("http://link/pdf");

        em.getTransaction().begin();
        em.persist(notaFiscal);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Nota Fiscal salva");
    }
}