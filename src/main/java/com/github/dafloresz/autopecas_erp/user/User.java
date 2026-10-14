package com.github.dafloresz.autopecas_erp.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table (name = "tb_user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@EntityListeners(AuditingEntityListener.class)
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9._-]+$",
            message = "Use apenas letras, números, ponto, underline ou hífen, com pelo menos uma letra”"
    )
    @Size(min = 6, max = 25)
    @Column(unique = true)
    private String username;

    @CPF
    @Column(unique = true)
    private String cpf;

    @NotNull
    private String password;
    private String name;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;

    @Embedded
    private Address address;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    public User() {
    }

    public User(String name, String username, String cpf, String password, String email, String phone, Address address) {
        this.name = name;
        this.username = username;
        this.cpf = cpf;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "User = " +
                "Login:" + username + '\'' +
                ", Name: " + name + '\'' +
                ", Email: " + email + '\'' +
                ", Phone: " + phone + '\'' +
                ", Address:" + address;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
