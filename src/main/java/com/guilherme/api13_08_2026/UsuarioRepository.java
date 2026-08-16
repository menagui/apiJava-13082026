package com.guilherme.api13_08_2026;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario salvar(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }
    
    
    public List<Usuario> buscarTodos() {
        return usuarios;
    }
    
    public Usuario buscarPorId(int id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }
    
    public Usuario atualizar(int id, Usuario usuarioAtualizado) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {

                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setIdade(usuarioAtualizado.getIdade());

                return usuario;
            }
        }

        return null;
    }
    
    public boolean deletar(int id) {

        return usuarios.removeIf(usuario -> usuario.getId() == id);
    }
    
    
    
}