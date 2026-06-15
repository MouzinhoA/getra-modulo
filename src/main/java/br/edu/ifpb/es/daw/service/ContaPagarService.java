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
import br.edu.ifpb.es.daw.model.ContaPagar;
import br.edu.ifpb.es.daw.model.Parceiro;
import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.repository.ContaPagarRepository;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.ContaPagarMapper;

@Service
public class ContaPagarService {

	private final ContaPagarRepository repository;
	private final ContaPagarMapper mapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public ContaPagarService(ContaPagarMapper mapper, ContaPagarRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public ContaPagarResponseDTO criar(ContaPagarSalvarRequestDTO dto) {
		ContaPagar objNovo = mapper.from(dto);
		ContaPagar objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<ContaPagarResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private ContaPagar ensureExists(UUID lookupId) {
		Optional<ContaPagar> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'ContaPagar' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public ContaPagarResponseDTO buscarPor(UUID lookupId) {
		ContaPagar obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public ContaPagarResponseDTO atualizar(UUID lookupId, ContaPagarSalvarRequestDTO dto) {
		ContaPagar objExistente = ensureExists(lookupId);
		if ("PAGO".equalsIgnoreCase(objExistente.getStatus())) {
			throw new EstadoInválidoException("Não pode editar uma conta a pagar já paga.");
		}
		objExistente.setDescricao(dto.descricao());
		objExistente.setValor(dto.valor());
		objExistente.setStatus(dto.status());
		objExistente.setFormaPagamento(dto.formaPagamento());
		objExistente.setDataVencimento(dto.dataVencimento());
		objExistente.setDataPagamento(dto.dataPagamento());
		objExistente.setParceiro(entityManager.getReference(Parceiro.class, dto.idParceiro()));
		objExistente.setUsuario(entityManager.getReference(Usuario.class, dto.idUsuario()));
		ContaPagar objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<ContaPagar> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<ContaPagarResponseDTO> buscar(ContaPagarBuscarDTO dto) {
		Page<ContaPagar> contaPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return contaPage.map(mapper::from);
	}

	@Transactional
	public ContaPagarResponseDTO pagar(UUID lookupId) {
		ContaPagar objExistente = ensureExists(lookupId);
		if ("PAGO".equalsIgnoreCase(objExistente.getStatus())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatus("PAGO");
		ContaPagar objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public ContaPagarResponseDTO estornar(UUID lookupId) {
		ContaPagar objExistente = ensureExists(lookupId);
		if ("ABERTO".equalsIgnoreCase(objExistente.getStatus())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatus("ABERTO");
		ContaPagar objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}
}
