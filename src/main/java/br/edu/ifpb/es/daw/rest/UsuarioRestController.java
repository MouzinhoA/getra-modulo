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

import br.edu.ifpb.es.daw.rest.dto.UsuarioBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.UsuarioResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.UsuarioSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

	private final UsuarioService usuarioService;

	@Autowired
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> listar() {
		List<UsuarioResponseDTO> resultado = usuarioService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody @Valid UsuarioSalvarRequestDTO dto) {
		UsuarioResponseDTO resultado = usuarioService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<UsuarioResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		UsuarioResponseDTO resultado = usuarioService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid UsuarioSalvarRequestDTO dto) {
		UsuarioResponseDTO resultado = usuarioService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		usuarioService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<UsuarioResponseDTO>> buscar(UsuarioBuscarDTO dto) {
		Page<UsuarioResponseDTO> resultado = usuarioService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/ativar")
	public ResponseEntity<UsuarioResponseDTO> ativar(@PathVariable UUID lookupId) {
		UsuarioResponseDTO resultado = usuarioService.ativar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/desativar")
	public ResponseEntity<UsuarioResponseDTO> desativar(@PathVariable UUID lookupId) {
		UsuarioResponseDTO resultado = usuarioService.desativar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
