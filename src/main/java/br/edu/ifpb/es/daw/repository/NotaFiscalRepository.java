package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.NotaFiscal;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalBuscarDTO;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

	Optional<NotaFiscal> findByLookupId(UUID lookupId);

	@Query("SELECT n FROM NotaFiscal n WHERE (:numero IS NULL OR n.numero = :numero)"
			+ " AND (:statusApi IS NULL OR n.statusApi = :statusApi)")
	Page<NotaFiscal> buscarPor(String numero, String statusApi, Pageable pageable);

	@Query("SELECT n FROM NotaFiscal n WHERE (:#{#dto.numero} IS NULL OR n.numero = :#{#dto.numero})"
			+ " AND (:#{#dto.statusApi} IS NULL OR n.statusApi = :#{#dto.statusApi})")
	Page<NotaFiscal> buscarPor(NotaFiscalBuscarDTO dto, Pageable pageable);
}
