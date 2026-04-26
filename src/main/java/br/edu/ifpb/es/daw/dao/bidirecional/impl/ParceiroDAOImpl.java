package br.edu.ifpb.es.daw.dao.bidirecional.impl;

import br.edu.ifpb.es.daw.dao.bidirecional.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManagerFactory;

public class ParceiroDAOImpl extends AbstractDAOImpl<Parceiro, Long> implements ParceiroDAO {

    public ParceiroDAOImpl(EntityManagerFactory emf) {
        super(Parceiro.class, emf);
    }
}