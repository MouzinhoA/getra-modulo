package br.edu.ifpb.es.daw.dao.impl; 

import br.edu.ifpb.es.daw.dao.ClienteDAO; 
import br.edu.ifpb.es.daw.entities.Cliente; 
import jakarta.persistence.EntityManagerFactory;

public class ClienteDAOImpl extends AbstractDAOImpl<Cliente, Long> implements ClienteDAO {
    public ClienteDAOImpl(EntityManagerFactory emf) {
        super(Cliente.class, emf);
    }
}