package br.edu.ifpb.es.daw.dao.bidirecional;

import br.edu.ifpb.es.daw.dao.DAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.Recorrencia;

import java.util.List;

public interface RecorrenciaDAO extends DAO<Recorrencia, Long> {
    List<Recorrencia> buscarPorPeriodicidadeEStatus(
            String periodicidade,
            Boolean status)
            throws PersistenciaDawException;
}
