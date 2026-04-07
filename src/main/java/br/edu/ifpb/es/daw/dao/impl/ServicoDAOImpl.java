package br.edu.ifpb.es.daw.dao.impl; 

import br.edu.ifpb.es.daw.dao.ServicoDAO; 
import br.edu.ifpb.es.daw.entities.Servico; 
import jakarta.persistence.EntityManagerFactory;

public class ServicoDAOImpl extends AbstractDAOImpl<Servico, Long> implements ServicoDAO {
    public ServicoDAOImpl(EntityManagerFactory emf) {
        super( Servico.class, emf);
    }
}
