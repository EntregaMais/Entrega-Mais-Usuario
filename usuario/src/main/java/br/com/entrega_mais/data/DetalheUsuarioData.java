package br.com.entrega_mais.data;

import br.com.entrega_mais.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

//dados do usuario no padrão do springsecurity

public class DetalheUsuarioData implements UserDetails {

    private final Optional<UsuarioModel> usuario;

    public DetalheUsuarioData(Optional<UsuarioModel> usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO retornar direitinho
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new UsuarioModel()).getPassword(); //p evitar nullpointer exception se n tiver ele cria usuario e envia o atribudo vazio msm
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioModel()).getEmail();
    }

    //TODO controlar usuario ativo, bloqueado, inativo etc

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
