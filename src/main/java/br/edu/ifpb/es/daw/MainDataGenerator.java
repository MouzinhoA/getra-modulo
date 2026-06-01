package br.edu.ifpb.es.daw;

import java.time.LocalDate;

import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainDataGenerator {
    public static void main(String[] args) throws DawException {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);

            Cliente cliente = new Cliente();
            cliente.setNomeRazaoSocial("Empresa Exemplo");
            cliente.setCpfCnpj("12345678900");
            cliente.setEmail("empresa@email.com");
            cliente.setTelefone("(83)99999-9999");
            cliente.setEndereco("João Pessoa - PB");

            clienteDao.save(cliente);

            Recorrencia recorrencia = new Recorrencia();
            recorrencia.setValorCobrado(500.0);
            recorrencia.setPeriodicidade(Recorrencia.Periodicidade.MENSAL);
            recorrencia.setDiaVencimento(10);
            recorrencia.setStatus(true);
            recorrencia.setCliente(cliente);

            recorrenciaDao.save(recorrencia);

            Fatura fatura1 = new Fatura();
            fatura1.setValorTotal(1000.0);
            fatura1.setDataVencimento(LocalDate.now().plusDays(10));
            fatura1.setStatus(true);
            fatura1.setCliente(cliente);
            fatura1.setRecorrencia(recorrencia);

            faturaDao.save(fatura1);

            Fatura fatura2 = new Fatura();
            fatura2.setValorTotal(2000.0);
            fatura2.setDataVencimento(LocalDate.now().plusDays(20));
            fatura2.setStatus(true);
            fatura2.setCliente(cliente);
            fatura2.setRecorrencia(recorrencia);

            faturaDao.save(fatura2);

            System.out.println("Os dados foram gerados :D .");
        }
    }
}
