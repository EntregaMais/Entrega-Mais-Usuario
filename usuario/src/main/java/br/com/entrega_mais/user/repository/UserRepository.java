package br.com.entrega_mais.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.entrega_mais.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
}
