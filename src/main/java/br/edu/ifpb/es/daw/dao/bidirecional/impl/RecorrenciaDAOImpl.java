package br.edu.ifpb.es.daw.dao.bidirecional.impl;

import java.util.List;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.impl.AbstractDAOImpl;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

public class RecorrenciaDAOImpl extends AbstractDAOImpl<Recorrencia, Long> implements RecorrenciaDAO {

    public RecorrenciaDAOImpl(EntityManagerFactory emf) {
        super(Recorrencia.class, emf);
    }

    // Consulta parametrizada: dois parametros
    @Override
    public List<Recorrencia> buscarPorPeriodicidadeEStatus(
            String periodicidade,
            Boolean status)
            throws PersistenciaDawException {

        try (EntityManager em = getEntityManager()) {

            String jpql =
                    """
                    SELECT r
                    FROM Recorrencia r
                    WHERE r.periodicidade = :periodicidade
                    AND r.status = :status
                    """;

            return em.createQuery(jpql, Recorrencia.class)
                    .setParameter("periodicidade", periodicidade)
                    .setParameter("status", status)
                    .getResultList();

        } catch (PersistenceException e) {
            throw new PersistenciaDawException(
                    "Falha ao buscar recorrências", e);
        }
    }
}