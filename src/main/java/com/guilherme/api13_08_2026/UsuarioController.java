package com.guilherme.api13_08_2026;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class UsuarioController {

    private UsuarioService usuarioService;

    
    public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	//adicionar usuario via POST
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {

        Usuario usuarioCriado = usuarioService.criarUsuario(usuario);

        return ResponseEntity.status(201).body(usuarioCriado);
    }
    
    //listar usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        return ResponseEntity.ok(usuarios);
    }
    
    //consultar usuario
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {

        Usuario usuario = usuarioService.buscarUsuario(id);

        return ResponseEntity.ok(usuario);
    }
    
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {

    	usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id,  @RequestBody Usuario usuarioAtualizado) {

        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);

        return ResponseEntity.ok(usuario);
    }
}