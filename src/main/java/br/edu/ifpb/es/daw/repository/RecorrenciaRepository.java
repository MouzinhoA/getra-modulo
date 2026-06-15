package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Recorrencia;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaBuscarDTO;

@Repository
public interface RecorrenciaRepository extends JpaRepository<Recorrencia, Long> {

	Optional<Recorrencia> findByLookupId(UUID lookupId);

	@Query("SELECT r FROM Recorrencia r WHERE (:periodicidade IS NULL OR r.periodicidade = :periodicidade)"
			+ " AND (:status IS NULL OR r.status = :status)")
	Page<Recorrencia> buscarPor(String periodicidade, Boolean status, Pageable pageable);

	@Query("SELECT r FROM Recorrencia r WHERE (:#{#dto.periodicidade} IS NULL OR r.periodicidade = :#{#dto.periodicidade})"
			+ " AND (:#{#dto.status} IS NULL OR r.status = :#{#dto.status})")
	Page<Recorrencia> buscarPor(RecorrenciaBuscarDTO dto, Pageable pageable);
}
