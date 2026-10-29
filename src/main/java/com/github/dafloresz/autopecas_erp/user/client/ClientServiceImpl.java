package com.github.dafloresz.autopecas_erp.user.client;

import com.github.dafloresz.autopecas_erp.exception.ResourceNotFoundException;
import com.github.dafloresz.autopecas_erp.user.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Client save(Client client) {

        if(client.getEmail() == null) {
            throw new IllegalArgumentException("Client email must not be null");
        }

        if(client.getPassword() == null) {
            throw new IllegalArgumentException("Client password must not be null");
        }

        if(client.getCpf() != null){
            if(userRepository.existsByCpf(client.getCpf())) {
                throw new EntityExistsException("Client CPF already exists");
            }
        }

        if(client.getUsername() != null){
            if (userRepository.existsByUsername(client.getUsername())) {
                throw new EntityExistsException("Client username already exists");
            }
        }

        if(userRepository.existsByEmail(client.getEmail())) {
            throw new EntityExistsException("Client email already exists");
        }

       client.setPassword(passwordEncoder.encode(client.getPassword()));

        return clientRepository.save(client);

    }

    @Override
    public Client update(Long id, Client change) {
        var clientToUpdate = clientRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        if(change.getCpf() != null) {
            if(userRepository.existsByCpfAndIdNot(change.getCpf(), id)) {
                throw new EntityExistsException("Client CPF already exists");

            } else {
                clientToUpdate.setCpf(change.getCpf());
            }
        }

        if(change.getUsername() != null) {
            if(userRepository.existsByUsernameAndIdNot(change.getUsername(), id)) {
                throw new EntityExistsException("Username " + change.getUsername() + " already exists");

            } else {
                clientToUpdate.setUsername(change.getUsername());
            }

        }


        if(change.getEmail() != null) {
            if(userRepository.existsByEmailAndIdNot(change.getEmail(), id)) {
                throw new EntityExistsException("Email " + change.getEmail() + " already exists");

            } else {
                clientToUpdate.setEmail(change.getEmail());
            }

        }

        if(change.getName() != null) {
            clientToUpdate.setName(change.getName());
        }

        if(change.getPassword() != null) {
            clientToUpdate.setPassword(passwordEncoder.encode(change.getPassword()));
        }

        if(change.getPhone() != null) {
            clientToUpdate.setPhone(change.getPhone());
        }

        if(change.getAddress() != null) {
            clientToUpdate.setAddress(change.getAddress());
        }

        return clientRepository.save(clientToUpdate);

    }

    @Override
    public Client findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Client id must not be null");
        }

        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Client id must not be null");
        }

        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Client with id " + id + " not found");
        }

    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Client name must not be null");
        }

        var result = clientRepository.findByNameContainingIgnoreCase(name);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Client with name " + name + " not found");
        }

        return result;
    }
}
