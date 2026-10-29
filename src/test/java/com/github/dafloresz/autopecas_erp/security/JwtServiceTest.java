package com.github.dafloresz.autopecas_erp.security;

import com.github.dafloresz.autopecas_erp.user.User;
import com.github.dafloresz.autopecas_erp.user.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @Test
    public void shouldGenerateTokenWithCorrectEmail() {
        User client = new Client("Thiago", null, "1234567", "123", "thiago@fortnite.gov", "2199765432", null);

        UserDetailsImpl userDetails = new UserDetailsImpl(client);

        JwtService jwtService = new JwtService("uma-chave-de-teste-bem-longa-para-passar-no-minimo-de-bits", 3600000L);

        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertEquals(client.getEmail(), jwtService.extractUsername(token));

    }
}