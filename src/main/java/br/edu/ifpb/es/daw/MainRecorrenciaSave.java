package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ServicoDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ServicoDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import br.edu.ifpb.es.daw.entities.Servico;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainRecorrenciaSave {
    public static void main(String[] args) throws Exception {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            ServicoDAO servicoDao = new ServicoDAOImpl(emf);

            Cliente cliente = new Cliente();
            cliente.setNomeRazaoSocial("Getra");
            clienteDao.save(cliente);

            Servico servico = new Servico();
            servico.setNome("Departamentalização");
            servicoDao.save(servico);

            Recorrencia recorrencia = new Recorrencia();
            recorrencia.setValorCobrado(100.0);
            recorrencia.setPeriodicidade("MENSAL");
            recorrencia.setDiaVencimento(5);
            recorrencia.setStatus(true);
            recorrencia.setCliente(cliente);
            recorrencia.setServico(servico);
            recorrenciaDao.save(recorrencia);

            Fatura fatura = new Fatura();
            fatura.setValorTotal(1000.0);
            fatura.setRecorrencia(recorrencia);
            faturaDao.save(fatura);

            System.out.println(cliente);
            System.out.println(servico);
            System.out.println(recorrencia);
            System.out.println(fatura);

        }
    }
}