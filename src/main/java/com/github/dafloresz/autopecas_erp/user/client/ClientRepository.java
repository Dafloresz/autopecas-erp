package com.github.dafloresz.autopecas_erp.user.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameContainingIgnoreCase(String name);
    Optional<Client> findByEmailIgnoreCase(String email);
}
