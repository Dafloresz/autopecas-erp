package com.github.dafloresz.autopecas_erp.security;

import com.github.dafloresz.autopecas_erp.user.User;
import com.github.dafloresz.autopecas_erp.user.client.Client;
import com.github.dafloresz.autopecas_erp.user.employee.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user instanceof Client) {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));

        } else if (user instanceof Employee) {
            return List.of(new SimpleGrantedAuthority("ROLE_" + ((Employee) user).getRole().name()));
        }

        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isEnabled() {
        if(user instanceof Client) {
            return true;

        } else if (user instanceof Employee && ((Employee) user).isActive()) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}
