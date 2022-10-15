package br.com.entrega_mais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//                     {SecurityAutoConfiguration.class} //p n mais validar usuario e senha //n executar mais essa classe
@SpringBootApplication
public class UsuarioAutenticacaoJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioAutenticacaoJwtApplication.class, args);
    }

    //P que seja um componente da aplicação e poder usar ele nela
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
