package br.edu.ifpb.es.daw.dao.bidirecional;

import br.edu.ifpb.es.daw.dao.DAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;

import java.util.List;

public interface FaturaDAO extends DAO<Fatura, Long> {
    List<Fatura> buscarPorCliente(Cliente cliente) throws PersistenciaDawException;

    Double somarValorTotalFaturas() throws PersistenciaDawException;

    Fatura buscarComCliente(Long id) throws PersistenciaDawException;
}
