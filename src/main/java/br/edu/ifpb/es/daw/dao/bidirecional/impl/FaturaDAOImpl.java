package br.edu.ifpb.es.daw.dao.bidirecional.impl;

import java.util.List;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

public class FaturaDAOImpl extends AbstractDAOImpl<Fatura, Long> implements FaturaDAO {

    public FaturaDAOImpl(EntityManagerFactory emf) {
        super(Fatura.class, emf);
    }

    // Consulta parametrizada: entidade
    @Override
    public List<Fatura> buscarPorCliente(Cliente cliente) throws PersistenciaDawException {

        try (EntityManager em = getEntityManager()) {

            String jpql =
                    "SELECT f FROM Fatura f WHERE f.cliente = :cliente";

            return em.createQuery(jpql, Fatura.class)
                    .setParameter("cliente", cliente)
                    .getResultList();

        } catch (PersistenceException e) {
            throw new PersistenciaDawException(
                    "Erro ao buscar faturas do cliente", e);
        }
    }

    // Consulta parametrizada: agregação
    @Override
    public Double somarValorTotalFaturas() throws PersistenciaDawException {

        try (EntityManager em = getEntityManager()) {

            String jpql =
                    "SELECT SUM(f.valorTotal) FROM Fatura f";

            return em.createQuery(jpql, Double.class)
                    .getSingleResult();

        } catch (PersistenceException e) {
            throw new PersistenciaDawException(
                    "Erro ao somar valores das faturas", e);
        }
    }

    // Consulta parametrizada: fetch lazy
    @Override
    public Fatura buscarComCliente(Long id) throws PersistenciaDawException {

        try (EntityManager em = getEntityManager()) {

            String jpql =
                    """
                    SELECT f
                    FROM Fatura f
                    JOIN FETCH f.cliente
                    WHERE f.id = :id
                    """;

            return em.createQuery(jpql, Fatura.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (PersistenceException e) {
            throw new PersistenciaDawException(
                    "Erro ao buscar fatura com cliente", e);
        }
    }
}