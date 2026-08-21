package com.guilherme.api13_08_2026;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    void deveBuscarUsuarioPorId() throws Exception {

        UsuarioResponseDTO response =
                new UsuarioResponseDTO(
                        1,
                        "Guilherme",
                        20
                );

        when(usuarioService.buscarUsuario(1))
                .thenReturn(response);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Guilherme"))
                .andExpect(jsonPath("$.idade").value(20));
    }

    @Test
    void deveCriarUsuario() throws Exception {

        UsuarioResponseDTO response =
                new UsuarioResponseDTO(
                        1,
                        "Guilherme",
                        20
                );

        when(usuarioService.criarUsuario(any(UsuarioRequestDTO.class)))
                .thenReturn(response);

        String json = """
            {
                "nome": "Guilherme",
                "idade": 20
            }
            """;

        mockMvc.perform(
                        post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Guilherme"))
                .andExpect(jsonPath("$.idade").value(20));
    }

    @Test
    void deveRetornarBadRequestQuandoUsuarioForInvalido() throws Exception {

        String json = """
            {
                "nome": "",
                "idade": -5
            }
            """;

        mockMvc.perform(
                        post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("Nome é obrigatório"))
                .andExpect(jsonPath("$.idade").value("Idade não pode ser negativa"));

        verify(usuarioService, never())
                .criarUsuario(any(UsuarioRequestDTO.class));
    }

    @Test
    void deveRetornarNotFoundQuandoUsuarioNaoExistir() throws Exception {

        int id = 999;

        when(usuarioService.buscarUsuario(id))
                .thenThrow(new UsuarioNaoEncontradoException());

        mockMvc.perform(get("/usuarios/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarUsuario() throws Exception {

        int id = 1;

        mockMvc.perform(delete("/usuarios/" + id))
                .andExpect(status().isNoContent());

        verify(usuarioService).deletarUsuario(id);
    }

    @Test
    void deveAtualizarUsuario() throws Exception {

        int id = 1;

        UsuarioResponseDTO response =
                new UsuarioResponseDTO(
                        1,
                        "Guilherme Atualizado",
                        21
                );

        when(usuarioService.atualizarUsuario(
                eq(id),
                any(UsuarioRequestDTO.class)
        )).thenReturn(response);

        String json = """
            {
                "nome": "Guilherme Atualizado",
                "idade": 21
            }
            """;

        mockMvc.perform(
                        put("/usuarios/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Guilherme Atualizado"))
                .andExpect(jsonPath("$.idade").value(21));

        verify(usuarioService)
                .atualizarUsuario(
                        eq(id),
                        any(UsuarioRequestDTO.class)
                );
    }

}