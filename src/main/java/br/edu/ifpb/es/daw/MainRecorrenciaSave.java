package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainRecorrenciaSave {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            RecorrenciaDAO dao = new RecorrenciaDAOImpl(emf);

            Recorrencia r = new Recorrencia();
            r.setValorCobrado(100.0);
            r.setPeriodicidade("MENSAL");
            r.setDiaVencimento(5);
            r.setStatus(true);

            r.setIdCliente(1L);
            r.setIdServico(1L);

            dao.save(r);
            System.out.println("Recorrência salva com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}