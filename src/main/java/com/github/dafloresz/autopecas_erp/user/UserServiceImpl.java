package com.github.dafloresz.autopecas_erp.user;

import com.github.dafloresz.autopecas_erp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findByUsername(String username) {
        if(username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findByCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("Cpf cannot be null");
        }

        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        return  userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
