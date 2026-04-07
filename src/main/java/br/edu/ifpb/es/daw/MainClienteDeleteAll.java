package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ClienteDAO;
import br.edu.ifpb.es.daw.dao.impl.ClienteDAOImpl;
import br.edu.ifpb.es.daw.entities.Cliente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MainClienteDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            
            ClienteDAO dao = new ClienteDAOImpl(emf);
            
            List<Cliente> clientes = dao.getAll();
            
            System.out.println("Iniciando a remoção de " + clientes.size() + " clientes...");

            for (Cliente c : clientes) {
                dao.delete(c.getId());
            }

            System.out.println("Todos os clientes foram removidos com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao deletar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}