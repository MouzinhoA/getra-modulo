package br.edu.ifpb.es.daw.dao.bidirecional.impl; 

import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente; 
import jakarta.persistence.EntityManagerFactory;

public class ClienteDAOImpl extends AbstractDAOImpl<Cliente, Long> implements ClienteDAO {
    
    public ClienteDAOImpl(EntityManagerFactory emf) {
        super(Cliente.class, emf);
    }
}