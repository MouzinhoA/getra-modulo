package br.edu.ifpb.es.daw.dao.bidirecional.impl; 

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

public class ClienteDAOImpl extends AbstractDAOImpl<Cliente, Long> implements ClienteDAO {

    public ClienteDAOImpl(EntityManagerFactory emf) {
        super(Cliente.class, emf);
    }

    // Consulta parametrizada: entidade
    @Override
    public Cliente buscarPorCpfCnpj(String cpfCnpj) throws PersistenciaDawException {

        try (EntityManager em = getEntityManager()) {

            String jpql =
                    "SELECT c FROM Cliente c WHERE c.cpfCnpj = :cpf";

            return em.createQuery(jpql, Cliente.class)
                    .setParameter("cpf", cpfCnpj)
                    .getSingleResult();

        } catch (PersistenceException e) {
            throw new PersistenciaDawException(
                    "Falha buscar cliente por CPF/CNPJ", e);
        }
    }
}