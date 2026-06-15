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

import br.edu.ifpb.es.daw.rest.dto.PerfilBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.PerfilResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.PerfilSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilRestController {

	private final PerfilService perfilService;

	@Autowired
    public PerfilRestController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

	@GetMapping
	public ResponseEntity<List<PerfilResponseDTO>> listar() {
		List<PerfilResponseDTO> resultado = perfilService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PerfilResponseDTO> adicionar(@RequestBody @Valid PerfilSalvarRequestDTO dto) {
		PerfilResponseDTO resultado = perfilService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<PerfilResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		PerfilResponseDTO resultado = perfilService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<PerfilResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid PerfilSalvarRequestDTO dto) {
		PerfilResponseDTO resultado = perfilService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		perfilService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<PerfilResponseDTO>> buscar(PerfilBuscarDTO dto) {
		Page<PerfilResponseDTO> resultado = perfilService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
