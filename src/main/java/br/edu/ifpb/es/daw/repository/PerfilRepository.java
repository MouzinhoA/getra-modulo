package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Perfil;
import br.edu.ifpb.es.daw.rest.dto.PerfilBuscarDTO;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Optional<Perfil> findByLookupId(UUID lookupId);

	@Query("SELECT p FROM Perfil p WHERE (:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
	Page<Perfil> buscarPor(String nome, Pageable pageable);

	@Query("SELECT p FROM Perfil p WHERE (:#{#dto.nome} IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :#{#dto.nome}, '%')))")
	Page<Perfil> buscarPor(PerfilBuscarDTO dto, Pageable pageable);
}
