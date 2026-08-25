package com.guilherme.api13_08_2026;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PokemonService pokemonService;

    
    public UsuarioController(UsuarioService usuarioService, PokemonService pokemonService
    ) {
		this.usuarioService = usuarioService;
        this.pokemonService = pokemonService;
	}

	//adicionar usuario via POST
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {

        UsuarioResponseDTO usuarioCriado = usuarioService.criarUsuario(usuarioDTO);

        return ResponseEntity.status(201).body(usuarioCriado);
    }
    
    //listar usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {

        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();

        return ResponseEntity.ok(usuarios);
    }
    
    //consultar usuario
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable int id) {

        UsuarioResponseDTO usuario = usuarioService.buscarUsuario(id);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuarios/{id}/pokemons")
    public ResponseEntity<List<PokemonResponseDTO>> buscarPokemonsUsuario(
            @PathVariable int id
    ) {

        usuarioService.buscarUsuario(id);

        List<PokemonResponseDTO> pokemons =
                pokemonService.buscarPorTreinador(id);

        return ResponseEntity.ok(pokemons);
    }
    
    //Consultar usuario por nome
    @GetMapping("/usuarios/buscar")
    public List<UsuarioResponseDTO> buscarPorNome(@RequestParam String nome) {
        return usuarioService.buscarPorNome(nome);
    }
    
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {

    	usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable int id,  @Valid @RequestBody UsuarioRequestDTO usuarioAtualizadoDTO) {

        UsuarioResponseDTO usuario = usuarioService.atualizarUsuario(id, usuarioAtualizadoDTO);

        return ResponseEntity.ok(usuario);
    }
}