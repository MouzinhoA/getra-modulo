package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Parceiro;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainParceiroSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ParceiroDAO parceiroDao = new ParceiroDAOImpl(emf);
            ContaPagarDAO contaDao = new ContaPagarDAOImpl(emf);

            Parceiro parceiro = new Parceiro();
            parceiro.setNome_razao_social("Empresa ABC LTDA " + System.currentTimeMillis());
            parceiro.setCpf_cnpj("12.345.678/0001-90");
            parceiro.setEmail_contato("contato@empresa.com");
            parceiro.setDados_bancarios_pix("12345678900");
            parceiroDao.save(parceiro);
            System.out.println("Parceiro salvo com sucesso: " + parceiro);

            ContaPagar conta = new ContaPagar();
            conta.setDescricao("Conta teste");
            conta.setValor(288.0);
            conta.setStatus("PENDENTE");
            conta.setParceiro(parceiro);
            contaDao.save(conta);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}