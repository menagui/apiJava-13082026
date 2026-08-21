package com.guilherme.api13_08_2026;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario paraEntity(UsuarioRequestDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        return usuario;
    }

    public UsuarioResponseDTO paraResponseDTO(Usuario usuario) {

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade()
        );
    }
    
    public void atualizarEntity(
            UsuarioRequestDTO dto,
            Usuario usuario) {

        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());
    }
}