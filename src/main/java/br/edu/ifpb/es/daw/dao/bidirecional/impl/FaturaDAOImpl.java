package br.edu.ifpb.es.daw.dao.bidirecional.impl; 

import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Fatura; 
import jakarta.persistence.EntityManagerFactory;

public class FaturaDAOImpl extends AbstractDAOImpl<Fatura, Long> implements FaturaDAO {
    
    public FaturaDAOImpl(EntityManagerFactory emf) {
        super(Fatura.class, emf);
    }
}
