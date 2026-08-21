package com.guilherme.api13_08_2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveCriarUsuario() {

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Guilherme");
        request.setIdade(20);

        Usuario usuario = new Usuario();
        usuario.setNome("Guilherme");
        usuario.setIdade(20);

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1);
        usuarioSalvo.setNome("Guilherme");
        usuarioSalvo.setIdade(20);

        UsuarioResponseDTO responseEsperado =
                new UsuarioResponseDTO(1, "Guilherme", 20);

        when(usuarioMapper.paraEntity(request))
                .thenReturn(usuario);

        when(usuarioRepository.save(usuario))
                .thenReturn(usuarioSalvo);

        when(usuarioMapper.paraResponseDTO(usuarioSalvo))
                .thenReturn(responseEsperado);

        UsuarioResponseDTO resultado =
                usuarioService.criarUsuario(request);

        assertEquals(1, resultado.getId());
        assertEquals("Guilherme", resultado.getNome());
        assertEquals(20, resultado.getIdade());

        verify(usuarioMapper).paraEntity(request);
        verify(usuarioRepository).save(usuario);
        verify(usuarioMapper).paraResponseDTO(usuarioSalvo);
    }

    @Test
    void deveLancarErroQuandoUsuarioNaoExistir() {

        int id = 999;

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.buscarUsuario(id)
        );

        verify(usuarioRepository).findById(id);
        verifyNoInteractions(usuarioMapper);
    }

    @Test
    void deveBuscarUsuarioPorId() {

        int id = 1;

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Guilherme");
        usuario.setIdade(20);

        UsuarioResponseDTO responseEsperado =
                new UsuarioResponseDTO(1, "Guilherme", 20);

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.of(usuario));

        when(usuarioMapper.paraResponseDTO(usuario))
                .thenReturn(responseEsperado);

        UsuarioResponseDTO resultado =
                usuarioService.buscarUsuario(id);

        assertEquals(1, resultado.getId());
        assertEquals("Guilherme", resultado.getNome());
        assertEquals(20, resultado.getIdade());

        verify(usuarioRepository).findById(id);
        verify(usuarioMapper).paraResponseDTO(usuario);
    }

    @Test
    void deveAtualizarUsuario() {

        int id = 1;

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Guilherme Atualizado");
        request.setIdade(21);

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1);
        usuarioExistente.setNome("Guilherme");
        usuarioExistente.setIdade(20);

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(1);
        usuarioAtualizado.setNome("Guilherme Atualizado");
        usuarioAtualizado.setIdade(21);

        UsuarioResponseDTO responseEsperado =
                new UsuarioResponseDTO(
                        1,
                        "Guilherme Atualizado",
                        21
                );

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.of(usuarioExistente));

        when(usuarioRepository.save(usuarioExistente))
                .thenReturn(usuarioAtualizado);

        when(usuarioMapper.paraResponseDTO(usuarioAtualizado))
                .thenReturn(responseEsperado);

        UsuarioResponseDTO resultado =
                usuarioService.atualizarUsuario(id, request);

        assertEquals(1, resultado.getId());
        assertEquals("Guilherme Atualizado", resultado.getNome());
        assertEquals(21, resultado.getIdade());

        verify(usuarioRepository).findById(id);
        verify(usuarioMapper).atualizarEntity(request, usuarioExistente);
        verify(usuarioRepository).save(usuarioExistente);
        verify(usuarioMapper).paraResponseDTO(usuarioAtualizado);
    }

    @Test
    void deveLancarErroAoAtualizarUsuarioInexistente() {

        int id = 999;

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Novo Nome");
        request.setIdade(30);

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.atualizarUsuario(id, request)
        );

        verify(usuarioRepository).findById(id);

        verify(usuarioMapper, never())
                .atualizarEntity(any(), any());

        verify(usuarioRepository, never())
                .save(any());
    }

    @Test
    void deveDeletarUsuario() {

        int id = 1;

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Guilherme");
        usuario.setIdade(20);

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.of(usuario));

        usuarioService.deletarUsuario(id);

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void deveLancarErroAoDeletarUsuarioInexistente() {

        int id = 999;

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.deletarUsuario(id)
        );

        verify(usuarioRepository).findById(id);

        verify(usuarioRepository, never())
                .delete(any());
    }

    @Test
    void deveListarUsuarios() {

        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNome("Guilherme");
        usuario1.setIdade(20);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNome("Maria");
        usuario2.setIdade(22);

        UsuarioResponseDTO dto1 =
                new UsuarioResponseDTO(1, "Guilherme", 20);

        UsuarioResponseDTO dto2 =
                new UsuarioResponseDTO(2, "Maria", 22);

        when(usuarioRepository.findAll())
                .thenReturn(List.of(usuario1, usuario2));

        when(usuarioMapper.paraResponseDTO(usuario1))
                .thenReturn(dto1);

        when(usuarioMapper.paraResponseDTO(usuario2))
                .thenReturn(dto2);

        List<UsuarioResponseDTO> resultado =
                usuarioService.listarUsuarios();

        assertEquals(2, resultado.size());
        assertEquals("Guilherme", resultado.get(0).getNome());
        assertEquals("Maria", resultado.get(1).getNome());

        verify(usuarioRepository).findAll();
        verify(usuarioMapper).paraResponseDTO(usuario1);
        verify(usuarioMapper).paraResponseDTO(usuario2);
    }

    @Test
    void deveBuscarUsuariosPorNome() {

        String nome = "Guilherme";

        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNome("Guilherme");
        usuario1.setIdade(20);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNome("Guilherme");
        usuario2.setIdade(25);

        UsuarioResponseDTO dto1 =
                new UsuarioResponseDTO(1, "Guilherme", 20);

        UsuarioResponseDTO dto2 =
                new UsuarioResponseDTO(2, "Guilherme", 25);

        when(usuarioRepository.findByNome(nome))
                .thenReturn(List.of(usuario1, usuario2));

        when(usuarioMapper.paraResponseDTO(usuario1))
                .thenReturn(dto1);

        when(usuarioMapper.paraResponseDTO(usuario2))
                .thenReturn(dto2);

        List<UsuarioResponseDTO> resultado =
                usuarioService.buscarPorNome(nome);

        assertEquals(2, resultado.size());
        assertEquals("Guilherme", resultado.get(0).getNome());
        assertEquals("Guilherme", resultado.get(1).getNome());

        verify(usuarioRepository).findByNome(nome);
        verify(usuarioMapper).paraResponseDTO(usuario1);
        verify(usuarioMapper).paraResponseDTO(usuario2);
    }

}