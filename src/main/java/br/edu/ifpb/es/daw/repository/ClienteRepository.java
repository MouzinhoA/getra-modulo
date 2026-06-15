package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.rest.dto.ClienteBuscarDTO;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByLookupId(UUID lookupId);

	@Query("SELECT c FROM Cliente c WHERE (:nomeRazaoSocial IS NULL OR LOWER(c.nomeRazaoSocial) LIKE LOWER(CONCAT('%', :nomeRazaoSocial, '%')))"
			+ " AND (:cpfCnpj IS NULL OR c.cpfCnpj = :cpfCnpj)")
	Page<Cliente> buscarPor(String nomeRazaoSocial, String cpfCnpj, Pageable pageable);

	@Query("SELECT c FROM Cliente c WHERE (:#{#dto.nomeRazaoSocial} IS NULL OR LOWER(c.nomeRazaoSocial) LIKE LOWER(CONCAT('%', :#{#dto.nomeRazaoSocial}, '%')))"
			+ " AND (:#{#dto.cpfCnpj} IS NULL OR c.cpfCnpj = :#{#dto.cpfCnpj})")
	Page<Cliente> buscarPor(ClienteBuscarDTO dto, Pageable pageable);
}
