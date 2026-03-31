package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.ParceiroDAO;
import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManagerFactory;

public class ParceiroDAOImpl
        extends AbstractDAOImpl<Parceiro, Long>
        implements ParceiroDAO {

    public ParceiroDAOImpl(EntityManagerFactory emf) {
        super(Parceiro.class, emf);
    }
}