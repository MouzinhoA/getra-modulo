package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Servico;
import br.edu.ifpb.es.daw.rest.dto.ServicoBuscarDTO;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	Optional<Servico> findByLookupId(UUID lookupId);

	@Query("SELECT s FROM Servico s WHERE (:nome IS NULL OR LOWER(s.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
	Page<Servico> buscarPor(String nome, Pageable pageable);

	@Query("SELECT s FROM Servico s WHERE (:#{#dto.nome} IS NULL OR LOWER(s.nome) LIKE LOWER(CONCAT('%', :#{#dto.nome}, '%')))")
	Page<Servico> buscarPor(ServicoBuscarDTO dto, Pageable pageable);
}
