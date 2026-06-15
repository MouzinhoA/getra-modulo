package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.ContaPagar;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarBuscarDTO;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

	Optional<ContaPagar> findByLookupId(UUID lookupId);

	@Query("SELECT c FROM ContaPagar c WHERE (:descricao IS NULL OR LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))"
			+ " AND (:status IS NULL OR c.status = :status)")
	Page<ContaPagar> buscarPor(String descricao, String status, Pageable pageable);

	@Query("SELECT c FROM ContaPagar c WHERE (:#{#dto.descricao} IS NULL OR LOWER(c.descricao) LIKE LOWER(CONCAT('%', :#{#dto.descricao}, '%')))"
			+ " AND (:#{#dto.status} IS NULL OR c.status = :#{#dto.status})")
	Page<ContaPagar> buscarPor(ContaPagarBuscarDTO dto, Pageable pageable);
}
