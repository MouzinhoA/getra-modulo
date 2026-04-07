package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainParceiroSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ParceiroDAO dao = new ParceiroDAOImpl(emf);

            Parceiro parceiro = new Parceiro();
            parceiro.setNome_razao_social("Empresa ABC LTDA " + System.currentTimeMillis());
            parceiro.setCpf_cnpj("12.345.678/0001-90");
            parceiro.setEmail_contato("contato@empresa.com");
            parceiro.setDados_bancarios_pix("12345678900");

            dao.save(parceiro);
            System.out.println("Parceiro salvo com sucesso: " + parceiro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}