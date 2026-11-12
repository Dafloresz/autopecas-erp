package com.github.dafloresz.autopecas_erp.user.client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client save(Client client);
    Client update(Long id, Client client);
    Client findById(Long id);

    void activate(Long id);
    void deactivate(Long id);

    Client findByEmail(String email);
    List<Client> findByName(String name);


}
