package br.edu.ifpb.es.daw.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.es.daw.rest.dto.ServicoBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ServicoResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ServicoSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.ServicoService;

@RestController
@RequestMapping("/servico")
public class ServicoRestController {

	private final ServicoService servicoService;

	@Autowired
    public ServicoRestController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

	@GetMapping
	public ResponseEntity<List<ServicoResponseDTO>> listar() {
		List<ServicoResponseDTO> resultado = servicoService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ServicoResponseDTO> adicionar(@RequestBody @Valid ServicoSalvarRequestDTO dto) {
		ServicoResponseDTO resultado = servicoService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<ServicoResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		ServicoResponseDTO resultado = servicoService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid ServicoSalvarRequestDTO dto) {
		ServicoResponseDTO resultado = servicoService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		servicoService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<ServicoResponseDTO>> buscar(ServicoBuscarDTO dto) {
		Page<ServicoResponseDTO> resultado = servicoService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
