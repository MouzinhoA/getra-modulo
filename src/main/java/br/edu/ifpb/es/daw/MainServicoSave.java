package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ServicoDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ServicoDAOImpl;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import br.edu.ifpb.es.daw.entities.Servico;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainServicoSave {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ServicoDAO servicoDao = new ServicoDAOImpl(emf);
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);

            Servico servico = new Servico();
            servico.setNome("Organização empresarial" + System.nanoTime());
            servico.setDescricao("Oferta de organização empresarial em Segurança do Trabalho");
            servico.setValorPadrao(199.90);
            servicoDao.save(servico);

            Recorrencia recorrencia = new Recorrencia();
            recorrencia.setValorCobrado(5000.0);
            recorrencia.setServico(servico);
            recorrenciaDao.save(recorrencia);

            System.out.println(servico);
            System.out.println(recorrencia);

        }
    }
}