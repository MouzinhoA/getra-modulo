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

import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.model.Recorrencia;
import br.edu.ifpb.es.daw.model.Servico;
import br.edu.ifpb.es.daw.repository.RecorrenciaRepository;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.RecorrenciaMapper;

@Service
public class RecorrenciaService {

	private final RecorrenciaRepository repository;
	private final RecorrenciaMapper mapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public RecorrenciaService(RecorrenciaMapper mapper, RecorrenciaRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public RecorrenciaResponseDTO criar(RecorrenciaSalvarRequestDTO dto) {
		Recorrencia objNovo = mapper.from(dto);
		Recorrencia objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<RecorrenciaResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Recorrencia ensureExists(UUID lookupId) {
		Optional<Recorrencia> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Recorrencia' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public RecorrenciaResponseDTO buscarPor(UUID lookupId) {
		Recorrencia obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public RecorrenciaResponseDTO atualizar(UUID lookupId, RecorrenciaSalvarRequestDTO dto) {
		Recorrencia objExistente = ensureExists(lookupId);
		objExistente.setValorCobrado(dto.valorCobrado());
		objExistente.setPeriodicidade(Recorrencia.Periodicidade.valueOf(dto.periodicidade()));
		objExistente.setDiaVencimento(dto.diaVencimento());
		objExistente.setStatus(dto.status());
		objExistente.setCliente(entityManager.getReference(Cliente.class, dto.idCliente()));
		objExistente.setServico(entityManager.getReference(Servico.class, dto.idServico()));
		Recorrencia objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Recorrencia> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<RecorrenciaResponseDTO> buscar(RecorrenciaBuscarDTO dto) {
		Page<Recorrencia> recPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return recPage.map(mapper::from);
	}

	@Transactional
	public RecorrenciaResponseDTO ativar(UUID lookupId) {
		Recorrencia objExistente = ensureExists(lookupId);
		if (Boolean.TRUE.equals(objExistente.getStatus())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatus(true);
		Recorrencia objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public RecorrenciaResponseDTO desativar(UUID lookupId) {
		Recorrencia objExistente = ensureExists(lookupId);
		if (Boolean.FALSE.equals(objExistente.getStatus())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatus(false);
		Recorrencia objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}
}
