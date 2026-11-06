package com.github.dafloresz.autopecas_erp.user.client;

import java.util.List;

public interface ClientService {
    Client save(Client client);
    Client update(Long id, Client client);
    Client findById(Long id);
    void deleteById(Long id);

    List<Client> findAll();
    List<Client> findByName(String name);


}
