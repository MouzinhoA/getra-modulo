package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ServicoDAO;
import br.edu.ifpb.es.daw.dao.impl.ServicoDAOImpl;
import br.edu.ifpb.es.daw.entities.Servico;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainServicoSave {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ServicoDAO dao = new ServicoDAOImpl(emf);

            Servico s = new Servico();
            
            s.setNome("Organização empresarial" + System.nanoTime());
            s.setDescricao("Oferta de organização empresarial em Segurança do Trabalho");
            s.setValorPadrao(199.90);

            dao.save(s);
            System.out.println("Serviço salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}