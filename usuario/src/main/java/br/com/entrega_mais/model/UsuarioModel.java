package br.com.entrega_mais.model;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Usuario")
public class UsuarioModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //p que qnd fizer uma requisicao nao mostrar nem a senha encriptada por questao de seguranca
    private String password;

}
