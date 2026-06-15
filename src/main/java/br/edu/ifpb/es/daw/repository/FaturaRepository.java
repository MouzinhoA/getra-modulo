package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Fatura;
import br.edu.ifpb.es.daw.rest.dto.FaturaBuscarDTO;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

	Optional<Fatura> findByLookupId(UUID lookupId);

	@Query("SELECT f FROM Fatura f WHERE (:status IS NULL OR f.status = :status)"
			+ " AND (:idCliente IS NULL OR f.cliente.id = :idCliente)")
	Page<Fatura> buscarPor(String status, Long idCliente, Pageable pageable);

	@Query("SELECT f FROM Fatura f WHERE (:#{#dto.status} IS NULL OR f.status = :#{#dto.status})"
			+ " AND (:#{#dto.idCliente} IS NULL OR f.cliente.id = :#{#dto.idCliente})")
	Page<Fatura> buscarPor(FaturaBuscarDTO dto, Pageable pageable);
}
