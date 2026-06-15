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

import br.edu.ifpb.es.daw.rest.dto.ClienteBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ClienteResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ClienteSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

	private final ClienteService clienteService;

	@Autowired
    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> listar() {
		List<ClienteResponseDTO> resultado = clienteService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteResponseDTO> adicionar(@RequestBody @Valid ClienteSalvarRequestDTO dto) {
		ClienteResponseDTO resultado = clienteService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<ClienteResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		ClienteResponseDTO resultado = clienteService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid ClienteSalvarRequestDTO dto) {
		ClienteResponseDTO resultado = clienteService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		clienteService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<ClienteResponseDTO>> buscar(ClienteBuscarDTO dto) {
		Page<ClienteResponseDTO> resultado = clienteService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
