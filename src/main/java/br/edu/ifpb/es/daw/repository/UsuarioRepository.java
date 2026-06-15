package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.rest.dto.UsuarioBuscarDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLookupId(UUID lookupId);

	@Query("SELECT u FROM Usuario u WHERE (:nome IS NULL OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%')))"
			+ " AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')))")
	Page<Usuario> buscarPor(String nome, String email, Pageable pageable);

	@Query("SELECT u FROM Usuario u WHERE (:#{#dto.nome} IS NULL OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :#{#dto.nome}, '%')))"
			+ " AND (:#{#dto.email} IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :#{#dto.email}, '%')))")
	Page<Usuario> buscarPor(UsuarioBuscarDTO dto, Pageable pageable);
}
