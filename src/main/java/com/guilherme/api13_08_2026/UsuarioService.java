package com.guilherme.api13_08_2026;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;

	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {

	    this.usuarioRepository = usuarioRepository;
	    this.usuarioMapper = usuarioMapper;
	}
	
	public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO) {
		
		Usuario usuario = usuarioMapper.paraEntity(usuarioDTO);
		Usuario usuarioNovo = usuarioRepository.save(usuario);
		 
	    return usuarioMapper.paraResponseDTO(usuarioNovo);
	}
    
	public List<UsuarioResponseDTO> listarUsuarios() {
	    
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarios
				.stream()
				.map(usuario -> usuarioMapper.paraResponseDTO(usuario))
				.toList();
	    
	}
    
	public UsuarioResponseDTO buscarUsuario(int id) {

		 Usuario usuario = buscarEntidadePorId(id);
		
		
		return usuarioMapper.paraResponseDTO(usuario);
	}
	
	public List<UsuarioResponseDTO> buscarPorNome(String nome) {
	    return 
	    		usuarioRepository
	    		.findByNome(nome)
	    		.stream()
	    		.map(usuario -> usuarioMapper.paraResponseDTO(usuario))
	    		.toList();
	}
    
	public void deletarUsuario(int id) {
	    Usuario usuario = buscarEntidadePorId(id);
	    
	    usuarioRepository.delete(usuario);
	    
	}
    
    public UsuarioResponseDTO atualizarUsuario(int id, UsuarioRequestDTO usuarioAtualizadoDTO) {
        
    	Usuario usuario = buscarEntidadePorId(id);
        
        
    	usuarioMapper.atualizarEntity(usuarioAtualizadoDTO, usuario);
        
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
  
        return usuarioMapper.paraResponseDTO(usuarioAtualizado);
    }
    
    
    private Usuario buscarEntidadePorId(int id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }
}