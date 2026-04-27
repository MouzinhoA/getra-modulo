package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.ParceiroDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ParceiroDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Parceiro;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainContaPagarSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ContaPagarDAO contaDao = new ContaPagarDAOImpl(emf);
            ParceiroDAO parceiroDao = new ParceiroDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            PerfilDAO perfilDao = new PerfilDAOImpl(emf);


            Perfil perfil = new Perfil();
            perfil.setNome("USER");
            perfil.setPermissoes("ADMIN");
            perfilDao.save(perfil);


            Usuario usuario = new Usuario();
            usuario.setNome("João");
            usuario.setEmail("joao@email.com");
            usuario.setSenha_hash("hash123");
            usuario.setAtivo(true);
            usuario.setPerfil(perfil);
            usuarioDao.save(usuario);


            Parceiro parceiro = new Parceiro();
            parceiro.setNome_razao_social("Empresa XYZ " + System.nanoTime());
            parceiro.setCpf_cnpj("123456789");
            parceiro.setEmail_contato("empresa@email.com");
            parceiro.setDados_bancarios_pix("pix-chave");
            parceiroDao.save(parceiro);


            ContaPagar conta = new ContaPagar();
            conta.setDescricao("Conta de Luz " + System.currentTimeMillis());
            conta.setValor(150.75);
            conta.setForma_pagamento("PIX");
            conta.setStatus("PENDENTE");
            conta.setData_vencimento("2026-04-10");
            conta.setData_pagamento(null);


            conta.setUsuario(usuario);
            conta.setParceiro(parceiro);

            contaDao.save(conta);

            System.out.println(usuario);
            System.out.println(parceiro);
            System.out.println(conta);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}