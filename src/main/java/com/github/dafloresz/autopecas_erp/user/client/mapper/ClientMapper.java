package com.github.dafloresz.autopecas_erp.user.client.mapper;

import com.github.dafloresz.autopecas_erp.user.client.Client;
import com.github.dafloresz.autopecas_erp.user.client.dto.ClientPatchDTO;
import com.github.dafloresz.autopecas_erp.user.client.dto.ClientRequestDTO;
import com.github.dafloresz.autopecas_erp.user.client.dto.ClientResponseDTO;
import com.github.dafloresz.autopecas_erp.user.mapper.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    private final AddressMapper addressMapper;

    public ClientMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public ClientResponseDTO toClientResponseDTO(Client client) {
        var addressDTO = client.getAddress() != null ? addressMapper.toAddressDTO(client.getAddress()) : null;
        return new ClientResponseDTO(client.getId(), client.getUsername(), client.getName(), client.getEmail(), client.getPhone(), addressDTO);
    }

    public Client toClient(ClientRequestDTO request) {
        var address = request.address() != null ? addressMapper.toAddress(request.address()) : null;
        return new Client(request.name(), request.username(), request.cpf(), request.password(), request.email(), request.phone(), address);
    }

    public Client toClientChange(ClientPatchDTO patchDTO) {
        var client = new Client();
        if (patchDTO.email() != null) {
            client.setEmail(patchDTO.email());
        }

        if (patchDTO.password() != null) {
            client.setPassword(patchDTO.password());
        }

        if(patchDTO.name() != null) {
            client.setName(patchDTO.name());
        }

        if (patchDTO.username() != null) {
            client.setUsername(patchDTO.username());
        }

        if(patchDTO.cpf() != null) {
            client.setCpf(patchDTO.cpf());
        }

        if (patchDTO.phone() != null) {
            client.setPhone(patchDTO.phone());
        }

        if (patchDTO.address() != null) {
            client.setAddress(addressMapper.toAddress(patchDTO.address()));
        }

        return client;
    }
}
