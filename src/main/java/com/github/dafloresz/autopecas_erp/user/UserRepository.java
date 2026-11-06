package com.github.dafloresz.autopecas_erp.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByUsernameAndIdNot(String username, Long id);
    boolean existsByCpfAndIdNot(String cpf, Long id);

}
