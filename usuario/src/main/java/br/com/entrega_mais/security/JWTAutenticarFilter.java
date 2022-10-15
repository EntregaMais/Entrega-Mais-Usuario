package br.com.entrega_mais.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.entrega_mais.data.DetalheUsuarioData;
import br.com.entrega_mais.model.UsuarioModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

//autenticar o usuario e gera token jwt
public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    //TODO melhorias
    public static final int TOKEN_EXPIRACAO = 600_000; //expiração fixa de 10min mas pode colocar no arquivo de configuração
    public static final String TOKEN_SENHA = "71ca1a4f-3c3d-44f3-a9da-02d493675b0a"; //guidgenerator.com //p desen mas n pode tá aqui aberta no codigo fonte //transportar p arquivo de config

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //TODO inserir a lista de permissões do usuário linha 45
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioModel usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), UsuarioModel.class); //ler o corpo da requisição e a classe p converter

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),
                    usuario.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        String token = JWT.create().
                                    withSubject(usuarioData.getUsername())
                                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                                    .sign(Algorithm.HMAC512(TOKEN_SENHA)); //assinar o token e criptografar ele

        response.getWriter().write(token); //registrando token no corpo da pag
        response.getWriter().flush();
    }
}















