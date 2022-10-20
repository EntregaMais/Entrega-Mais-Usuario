package br.com.entrega_mais.usuario;

import br.com.entrega_mais.usuario.model.UsuarioModel;
import br.com.entrega_mais.usuario.repository.UsuarioRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
//É PREECISO QUE BANCO ESTEJA RODANDO
@SpringBootTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void init() {
        UsuarioModel user = new UsuarioModel();
        user.setPassword("test");
        user.setEmail("test@test.com");
        usuarioRepository.save(user);
    }
    @Test
    void findByEmailTest() {

        Optional<UsuarioModel> user = usuarioRepository.findByEmail("test@test.com");
        assertThat(user.isPresent(), Matchers.is(true));
        assertThat(user.get().getEmail(), Matchers.is("test@test.com"));
        assertThat(user.get().getPassword(), Matchers.is("test"));
    }

}
