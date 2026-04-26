package br.edu.ifpb.es.daw.dao.bidirecional.impl; 

import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Recorrencia; 
import jakarta.persistence.EntityManagerFactory;

public class RecorrenciaDAOImpl extends AbstractDAOImpl<Recorrencia, Long> implements RecorrenciaDAO {
    
    public RecorrenciaDAOImpl(EntityManagerFactory emf) {
        super(Recorrencia.class, emf);
    }
}
