package br.com.entrega_mais.usuario.repository;

import br.com.entrega_mais.usuario.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByEmail(String email);

}
