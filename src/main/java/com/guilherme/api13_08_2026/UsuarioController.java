package com.guilherme.api13_08_2026;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    @PostMapping("/usuarios")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }
    
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
    
    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {
                return usuario;
            }

        }

        return null;
    }
    
}