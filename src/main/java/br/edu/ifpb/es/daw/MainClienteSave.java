package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainClienteSave {
    public static void main(String[] args) throws DawException{
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);

            Cliente cliente = new Cliente();
            cliente.setNomeRazaoSocial("Cliente Teste");
            cliente.setCpfCnpj("" + System.nanoTime());
            cliente.setEmail("teste" + System.nanoTime() + "@gmail.com");
            cliente.setTelefone("(83) 99999-9999");
            cliente.setEndereco("Rua das Oliveiras, 123, João Pessoa - PB");
            clienteDao.save(cliente);

            Fatura fatura = new Fatura();
            fatura.setValorTotal(1000.0);
            fatura.setCliente(cliente);
            faturaDao.save(fatura);

            Recorrencia recorrencia = new Recorrencia();
            recorrencia.setValorCobrado(5000.0);
            recorrencia.setPeriodicidade("MENSAL");
            recorrencia.setDiaVencimento(5);
            recorrencia.setStatus(true);
            recorrencia.setCliente(cliente);
            recorrenciaDao.save(recorrencia);

            System.out.println(cliente);
            System.out.println(fatura);
            System.out.println(recorrencia);
        }
    }
}