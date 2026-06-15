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

	@Query("SELECT f FROM Fatura f WHERE (:status IS NULL OR "
			+ "((:status = 'PAGO' AND f.status = TRUE) OR (:status = 'PENDENTE' AND (f.status IS NULL OR f.status = FALSE))))"
			+ " AND (:idCliente IS NULL OR f.cliente.id = :idCliente)")
	Page<Fatura> buscarPor(String status, Long idCliente, Pageable pageable);

	@Query("SELECT f FROM Fatura f WHERE (:#{#dto.status} IS NULL OR "
			+ "((:#{#dto.status} = 'PAGO' AND f.status = TRUE) OR (:#{#dto.status} = 'PENDENTE' AND (f.status IS NULL OR f.status = FALSE))))"
			+ " AND (:#{#dto.idCliente} IS NULL OR f.cliente.id = :#{#dto.idCliente})")
	Page<Fatura> buscarPor(FaturaBuscarDTO dto, Pageable pageable);
}
