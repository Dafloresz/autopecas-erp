package com.github.dafloresz.autopecas_erp.user;

public interface UserService {
    User findByUsername(String username);
    User findByCpf(String cpf);
    User findByEmail(String email);
}
