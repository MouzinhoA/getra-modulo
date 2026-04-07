package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.Cliente;
import br.edu.ifpb.es.daw.dao.ClienteDAO;
import br.edu.ifpb.es.daw.dao.impl.ClienteDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainClienteSave {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ClienteDAO dao = new ClienteDAOImpl(emf);

            Cliente cliente = new Cliente();
            cliente.setNomeRazaoSocial("Cliente Teste");
            cliente.setCpfCnpj("" + System.nanoTime());
            cliente.setEmail("teste" + System.nanoTime() + "@gmail.com");
            cliente.setTelefone("(83) 99999-9999");
            cliente.setEndereco("Rua das Oliveiras, 123, João Pessoa - PB");

            dao.save(cliente);
            System.out.println("Cliente salvo com sucesso: " + cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}