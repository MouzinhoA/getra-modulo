package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ServicoDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ServicoDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import br.edu.ifpb.es.daw.entities.Servico;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainRecorrenciaDeleteAll {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            ServicoDAO servicoDao = new ServicoDAOImpl(emf);
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);

            List<NotaFiscal> notaFiscals = notaFiscalDao.getAll();
            for (NotaFiscal notaFiscal : notaFiscals) {
                notaFiscalDao.delete(notaFiscal.getId());
            }

            List<Fatura> faturas = faturaDao.getAll();
            for (Fatura fatura : faturas) {
                faturaDao.delete(fatura.getId());
            }

            List<Recorrencia> recorrencias = recorrenciaDao.getAll();
            for (Recorrencia recorrencia : recorrencias) {
                recorrenciaDao.delete(recorrencia.getId());
            }

            List<Cliente> clientes = clienteDao.getAll();
            for (Cliente cliente : clientes) {
                clienteDao.delete(cliente.getId());
            }

            List<Servico> servicos = servicoDao.getAll();
            for (Servico servico : servicos) {
                servicoDao.delete(servico.getId());
            }
        }
    }
}
