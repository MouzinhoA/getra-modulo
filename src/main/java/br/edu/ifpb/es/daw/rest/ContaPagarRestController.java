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

import br.edu.ifpb.es.daw.rest.dto.ContaPagarBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.ContaPagarService;

@RestController
@RequestMapping("/conta-pagar")
public class ContaPagarRestController {

	private final ContaPagarService contaPagarService;

	@Autowired
    public ContaPagarRestController(ContaPagarService contaPagarService) {
        this.contaPagarService = contaPagarService;
    }

	@GetMapping
	public ResponseEntity<List<ContaPagarResponseDTO>> listar() {
		List<ContaPagarResponseDTO> resultado = contaPagarService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ContaPagarResponseDTO> adicionar(@RequestBody @Valid ContaPagarSalvarRequestDTO dto) {
		ContaPagarResponseDTO resultado = contaPagarService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<ContaPagarResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		ContaPagarResponseDTO resultado = contaPagarService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<ContaPagarResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid ContaPagarSalvarRequestDTO dto) {
		ContaPagarResponseDTO resultado = contaPagarService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		contaPagarService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<ContaPagarResponseDTO>> buscar(ContaPagarBuscarDTO dto) {
		Page<ContaPagarResponseDTO> resultado = contaPagarService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/pagar")
	public ResponseEntity<ContaPagarResponseDTO> pagar(@PathVariable UUID lookupId) {
		ContaPagarResponseDTO resultado = contaPagarService.pagar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/estornar")
	public ResponseEntity<ContaPagarResponseDTO> estornar(@PathVariable UUID lookupId) {
		ContaPagarResponseDTO resultado = contaPagarService.estornar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
