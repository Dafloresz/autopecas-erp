package com.github.dafloresz.autopecas_erp.user.mapper;

import com.github.dafloresz.autopecas_erp.user.Address;
import com.github.dafloresz.autopecas_erp.user.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDTO toAddressDTO(Address address) {
        return new AddressDTO(address.getZipCode(),
                address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement());
    }

    public Address toAddress(AddressDTO addressDTO) {
        return new Address(addressDTO.zipCode(),
                addressDTO.state(),
                addressDTO.city(),
                addressDTO.neighborhood(),
                addressDTO.street(),
                addressDTO.number(),
                addressDTO.complement());
    }
}
