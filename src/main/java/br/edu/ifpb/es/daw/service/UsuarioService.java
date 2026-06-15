package br.edu.ifpb.es.daw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.es.daw.model.Perfil;
import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.repository.UsuarioRepository;
import br.edu.ifpb.es.daw.rest.dto.UsuarioBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.UsuarioResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.UsuarioSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.UsuarioMapper;

@Service
public class UsuarioService {

	private final UsuarioRepository repository;
	private final UsuarioMapper mapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public UsuarioService(UsuarioMapper mapper, UsuarioRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public UsuarioResponseDTO criar(UsuarioSalvarRequestDTO dto) {
		Usuario objNovo = mapper.from(dto);
		Usuario objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<UsuarioResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Usuario ensureExists(UUID lookupId) {
		Optional<Usuario> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Usuario' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public UsuarioResponseDTO buscarPor(UUID lookupId) {
		Usuario obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public UsuarioResponseDTO atualizar(UUID lookupId, UsuarioSalvarRequestDTO dto) {
		Usuario objExistente = ensureExists(lookupId);
		objExistente.setNome(dto.nome());
		objExistente.setEmail(dto.email());
		if (dto.senha() != null && !dto.senha().isBlank()) {
			objExistente.setSenhaHash(dto.senha());
		}
		objExistente.setAtivo(dto.ativo());
		objExistente.setPerfil(entityManager.getReference(Perfil.class, dto.idPerfil()));
		Usuario objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Usuario> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<UsuarioResponseDTO> buscar(UsuarioBuscarDTO dto) {
		Page<Usuario> usuarioPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return usuarioPage.map(mapper::from);
	}

	@Transactional
	public UsuarioResponseDTO ativar(UUID lookupId) {
		Usuario objExistente = ensureExists(lookupId);
		if (Boolean.TRUE.equals(objExistente.getAtivo())) {
			return mapper.from(objExistente);
		}
		objExistente.setAtivo(true);
		Usuario objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public UsuarioResponseDTO desativar(UUID lookupId) {
		Usuario objExistente = ensureExists(lookupId);
		if (Boolean.FALSE.equals(objExistente.getAtivo())) {
			return mapper.from(objExistente);
		}
		objExistente.setAtivo(false);
		Usuario objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}
}
