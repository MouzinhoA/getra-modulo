package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ContaPagarDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.PerfilDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ContaPagarDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.PerfilDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.ContaPagar;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.Perfil;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainUsuarioSave {

    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {

            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            PerfilDAO perfilDao = new PerfilDAOImpl(emf);
            ContaPagarDAO contaDao = new ContaPagarDAOImpl(emf);

            Perfil perfil = new Perfil();
            perfil.setId(1L);
            perfil.setNome("ADMIN");
            perfil.setPermissoes("CRUD_TOTAL");
            perfilDao.save(perfil);

            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setNome("Aline Mouzinho");
            usuario.setEmail("mouzinhoA@email.com");
            usuario.setSenha_hash("abc123hash");
            usuario.setAtivo(true);
            usuario.setPerfil(perfil);
            usuarioDao.save(usuario);

            Fatura fatura = new Fatura();
            fatura.setValorTotal(1000.0);
            fatura.setUsuario(usuario);
            faturaDao.save(fatura);

            ContaPagar conta = new ContaPagar();
            conta.setId(1L); 
            conta.setDescricao("Conta teste");
            conta.setValor(200.0);
            conta.setStatus("PENDENTE");
            conta.setUsuario(usuario);
            contaDao.save(conta);


            System.out.println(perfil);
            System.out.println(fatura);
            System.out.println(usuario);
            System.out.println(conta);

        }
    }
}