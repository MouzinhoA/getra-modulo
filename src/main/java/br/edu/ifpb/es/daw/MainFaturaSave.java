package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.FaturaDAO;
import br.edu.ifpb.es.daw.dao.impl.FaturaDAOImpl;
import br.edu.ifpb.es.daw.entities.Fatura;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class MainFaturaSave {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            FaturaDAO dao = new FaturaDAOImpl(emf);

            Fatura f = new Fatura();
            f.setValorTotal(350.00);
            f.setDataVencimento(LocalDate.now().plusDays(15));
            f.setStatus(true);
            f.setTipoPagamentoPreferencial("BOLETO");
            f.setLinhaDigitavelBoleto("237933120600000000010600606300081300000000");
            f.setQrCodePix("chave-pix-teste-" + System.nanoTime());
            f.setIdExternoGateway("FAT-" + System.nanoTime());

            f.setIdCliente(1L);
            f.setIdRecorrencia(1L);
            f.setIdUsuario(1L);

            dao.save(f);
            System.out.println("Fatura salva com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}