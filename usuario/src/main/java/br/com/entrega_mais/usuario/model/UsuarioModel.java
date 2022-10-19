package br.com.entrega_mais.usuario.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Usuario")
public class UsuarioModel{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //p que qnd fizer uma requisicao nao mostrar nem a senha encriptada por questao de seguranca
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Perfil> perfis;

}
