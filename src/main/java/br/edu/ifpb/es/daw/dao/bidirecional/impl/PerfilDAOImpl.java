package br.edu.ifpb.es.daw.dao.bidirecional.impl;

import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Perfil;
import jakarta.persistence.EntityManagerFactory;

public class PerfilDAOImpl extends AbstractDAOImpl<Perfil, Long> implements PerfilDAO {

    public PerfilDAOImpl(EntityManagerFactory emf) {
        super(Perfil.class, emf);
    }
}