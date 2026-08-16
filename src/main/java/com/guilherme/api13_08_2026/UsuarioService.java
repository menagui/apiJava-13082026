package com.guilherme.api13_08_2026;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

    
	public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
	
	public Usuario criarUsuario(Usuario usuario) {
	    return usuarioRepository.salvar(usuario);
	}
    
	public List<Usuario> listarUsuarios() {
	    return usuarioRepository.buscarTodos();
	}
    
	public Usuario buscarUsuario(int id) {

	    Usuario usuario = usuarioRepository.buscarPorId(id);

	    if (usuario == null) {
	        throw new UsuarioNaoEncontradoException();
	    }

	    return usuario;
	}
    
	public void deletarUsuario(int id) {
	    boolean deletou = usuarioRepository.deletar(id);
	    
	    if (!deletou) {
	    	throw new UsuarioNaoEncontradoException();
	    }
	    
	}
    
    public Usuario atualizarUsuario(int id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.atualizar(id, usuarioAtualizado);
        
        if (usuario == null) {
	        throw new UsuarioNaoEncontradoException();
	    }

	    return usuario;
    }
}