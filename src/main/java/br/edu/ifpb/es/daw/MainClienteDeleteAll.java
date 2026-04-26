package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainClienteDeleteAll {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);

            List<NotaFiscal> notas = notaFiscalDao.getAll();
            for (NotaFiscal nota : notas) {
                notaFiscalDao.delete(nota.getId());
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

        }
    }
}