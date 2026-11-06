package com.github.dafloresz.autopecas_erp.user.client;

import com.github.dafloresz.autopecas_erp.user.Address;
import com.github.dafloresz.autopecas_erp.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_client")
@DiscriminatorValue("CLIENT")
public class Client extends User {

    public Client() {
    }

    public Client(String name, String username, String cpf, String password, String email, String phone, Address address) {
        super(name, username, cpf, password, email, phone, address);
    }
}
