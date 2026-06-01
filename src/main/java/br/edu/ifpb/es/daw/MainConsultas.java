package br.edu.ifpb.es.daw;

import java.util.List;

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

public class MainConsultas {
    public static void main(String[] args) throws DawException {

        try (EntityManagerFactory emf =
                     Persistence.createEntityManagerFactory("daw")) {

            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);

            System.out.println("CONSULTA 1");
            Cliente cliente =
                    clienteDao.buscarPorCpfCnpj("12345678900");
            System.out.println(cliente);

            System.out.println("CONSULTA 2");
            List<Fatura> faturas =
                    faturaDao.buscarPorCliente(cliente);

            faturas.forEach(System.out::println);

            System.out.println("CONSULTA 3");
            List<Recorrencia> recorrencias =
                    recorrenciaDao.buscarPorPeriodicidadeEStatus(
                            "MENSAL",
                            true);

            recorrencias.forEach(System.out::println);

            System.out.println("CONSULTA 4");
            Double soma =
                    faturaDao.somarValorTotalFaturas();

            System.out.println(soma);

            System.out.println("CONSULTA 5");
            Long idFatura = faturas.get(0).getId();

            Fatura fatura =
                    faturaDao.buscarComCliente(idFatura);


            System.out.println(fatura);
            System.out.println(fatura.getCliente());
        }
    }

}
