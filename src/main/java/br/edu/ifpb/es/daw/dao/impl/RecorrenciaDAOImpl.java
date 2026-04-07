package br.edu.ifpb.es.daw.dao.impl; 

import br.edu.ifpb.es.daw.dao.RecorrenciaDAO; 
import br.edu.ifpb.es.daw.entities.Recorrencia; 
import jakarta.persistence.EntityManagerFactory;

public class RecorrenciaDAOImpl extends AbstractDAOImpl<Recorrencia, Long> implements RecorrenciaDAO {
    public RecorrenciaDAOImpl(EntityManagerFactory emf) {
        super(Recorrencia.class, emf);
    }
}
