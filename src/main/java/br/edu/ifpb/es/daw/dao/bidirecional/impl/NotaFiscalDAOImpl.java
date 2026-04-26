package br.edu.ifpb.es.daw.dao.bidirecional.impl;

import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import jakarta.persistence.EntityManagerFactory;

public class NotaFiscalDAOImpl extends AbstractDAOImpl<NotaFiscal, Long> implements NotaFiscalDAO {

    public NotaFiscalDAOImpl(EntityManagerFactory emf) {
        super(NotaFiscal.class, emf);
    }
}