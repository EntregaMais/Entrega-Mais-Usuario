package br.com.entrega_mais.usuario;

import br.com.entrega_mais.usuario.data.DetalheUsuarioData;
import br.com.entrega_mais.usuario.services.DetalheUsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.entrega_mais.usuario.model.UsuarioModel;
import br.com.entrega_mais.usuario.repository.UsuarioRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.Optional;


@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    private DetalheUsuarioServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new DetalheUsuarioServiceImpl(repository);
    }

    @Test
    public void testLoadUserByUsername() {
        // Cria um usuário para simular o retorno do repository
        UsuarioModel usuario = new UsuarioModel();
        usuario.setEmail("user1@example.com");
        usuario.setPassword("password1");
        // Configura o mock do repository para retornar o usuário acima
        when(repository.findByEmail("user1@example.com")).thenReturn(Optional.of(usuario));

        // Executa o método loadUserByUsername do serviço com o email "user1@example.com"
        UserDetails userDetails = service.loadUserByUsername("user1@example.com");

        // Verifica se o retorno é um objeto DetalheUsuarioData
        Assertions.assertTrue(userDetails instanceof DetalheUsuarioData);
        // Verifica se os detalhes do usuário são os esperados
        assertEquals("user1@example.com", userDetails.getUsername());
        assertEquals("password1", userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        // Configura o mock do repository para retornar um usuário vazio
        when(repository.findByEmail("user1@example.com")).thenReturn(Optional.empty());

        // Executa o método loadUserByUsername do serviço com o email "user1@example.com"

        service.loadUserByUsername("user1@example.com");
    }

}
