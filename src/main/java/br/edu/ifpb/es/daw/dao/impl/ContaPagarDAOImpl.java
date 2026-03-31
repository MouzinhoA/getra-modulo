package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.ContaPagarDAO;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import jakarta.persistence.EntityManagerFactory;

public class ContaPagarDAOImpl
        extends AbstractDAOImpl<ContaPagar, Long>
        implements ContaPagarDAO {

    public ContaPagarDAOImpl(EntityManagerFactory emf) {
        super(ContaPagar.class, emf);
    }
}