package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.bidirecional.ClienteDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.FaturaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.NotaFiscalDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.RecorrenciaDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.UsuarioDAO;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.NotaFiscalDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.RecorrenciaDAOImpl;
import br.edu.ifpb.es.daw.dao.bidirecional.impl.UsuarioDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.entities.Fatura;
import br.edu.ifpb.es.daw.entities.NotaFiscal;
import br.edu.ifpb.es.daw.entities.Recorrencia;
import br.edu.ifpb.es.daw.entities.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class MainFaturaSave {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            FaturaDAO faturaDao = new FaturaDAOImpl(emf);
            ClienteDAO clienteDao = new ClienteDAOImpl(emf);
            RecorrenciaDAO recorrenciaDao = new RecorrenciaDAOImpl(emf);
            UsuarioDAO usuarioDao = new UsuarioDAOImpl(emf);
            NotaFiscalDAO notaFiscalDao = new NotaFiscalDAOImpl(emf);

            Cliente cliente = new Cliente();
            cliente.setNomeRazaoSocial("Getra");
            clienteDao.save(cliente);

            Recorrencia recorrencia = new Recorrencia();
            recorrencia.setValorCobrado(5000.0);
            recorrenciaDao.save(recorrencia);

            Usuario usuario = new Usuario();
            usuario.setNome("Getra");
            usuarioDao.save(usuario);

            Fatura fatura = new Fatura();
            fatura.setValorTotal(350.00);
            fatura.setDataVencimento(LocalDate.now().plusDays(15));
            fatura.setStatus(true);
            fatura.setTipoPagamentoPreferencial("BOLETO");
            fatura.setLinhaDigitavelBoleto("237933120600000000010600606300081300000000");
            fatura.setQrCodePix("chave-pix-teste-" + System.nanoTime());
            fatura.setIdExternoGateway("FAT-" + System.nanoTime());

            fatura.setCliente(cliente);
            fatura.setRecorrencia(recorrencia);
            fatura.setUsuario(usuario);

            faturaDao.save(fatura);

            NotaFiscal notaFiscal = new NotaFiscal();
            notaFiscal.setNumero("5");
            notaFiscal.setFatura(fatura);
            notaFiscalDao.save(notaFiscal);

            System.out.println(fatura);
            System.out.println(notaFiscal);
            System.out.println(cliente);
            System.out.println(recorrencia);
            System.out.println(usuario);
        }
    }
}
