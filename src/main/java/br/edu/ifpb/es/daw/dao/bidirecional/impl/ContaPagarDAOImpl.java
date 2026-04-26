package br.edu.ifpb.es.daw.dao.bidirecional.impl;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import jakarta.persistence.EntityManagerFactory;

public class ContaPagarDAOImpl extends AbstractDAOImpl<ContaPagar, Long> implements ContaPagarDAO {

    public ContaPagarDAOImpl(EntityManagerFactory emf) {
        super(ContaPagar.class, emf);
    }
}