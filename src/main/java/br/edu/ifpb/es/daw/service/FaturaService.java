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

import br.edu.ifpb.es.daw.exception.EstadoInválidoException;
import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.model.Fatura;
import br.edu.ifpb.es.daw.model.Recorrencia;
import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.repository.FaturaRepository;
import br.edu.ifpb.es.daw.rest.dto.FaturaBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.FaturaResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.FaturaSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.FaturaMapper;

@Service
public class FaturaService {

	private final FaturaRepository repository;
	private final FaturaMapper mapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public FaturaService(FaturaMapper mapper, FaturaRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public FaturaResponseDTO criar(FaturaSalvarRequestDTO dto) {
		Fatura objNovo = mapper.from(dto);
		Fatura objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<FaturaResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Fatura ensureExists(UUID lookupId) {
		Optional<Fatura> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Fatura' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public FaturaResponseDTO buscarPor(UUID lookupId) {
		Fatura obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public FaturaResponseDTO atualizar(UUID lookupId, FaturaSalvarRequestDTO dto) {
		Fatura objExistente = ensureExists(lookupId);
		if (Boolean.TRUE.equals(objExistente.getStatus())) {
			throw new EstadoInválidoException("Não pode editar uma fatura já paga.");
		}
		objExistente.setValorTotal(dto.valorTotal());
		objExistente.setDataVencimento(dto.dataVencimento());
		objExistente.setDataPagamento(dto.dataPagamento());
		objExistente.setStatus("PAGO".equalsIgnoreCase(dto.status()));
		objExistente.setTipoPagamentoPreferencial(dto.meioPagamento());
		objExistente.setCliente(entityManager.getReference(Cliente.class, dto.idCliente()));
		objExistente.setUsuario(entityManager.getReference(Usuario.class, dto.idUsuario()));
		if (dto.idRecorrencia() != null) {
			objExistente.setRecorrencia(entityManager.getReference(Recorrencia.class, dto.idRecorrencia()));
		} else {
			objExistente.setRecorrencia(null);
		}
		Fatura objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Fatura> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<FaturaResponseDTO> buscar(FaturaBuscarDTO dto) {
		Page<Fatura> faturaPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return faturaPage.map(mapper::from);
	}

	@Transactional
	public FaturaResponseDTO pagar(UUID lookupId) {
		Fatura objExistente = ensureExists(lookupId);
		if (Boolean.TRUE.equals(objExistente.getStatus())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatus(true);
		Fatura objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public FaturaResponseDTO pendente(UUID lookupId) {
		Fatura objExistente = ensureExists(lookupId);
		if (Boolean.FALSE.equals(objExistente.getStatus())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatus(false);
		Fatura objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}
}
