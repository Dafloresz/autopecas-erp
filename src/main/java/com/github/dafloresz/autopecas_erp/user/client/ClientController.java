package com.github.dafloresz.autopecas_erp.user.client;

import com.github.dafloresz.autopecas_erp.user.client.dto.ClientPatchDTO;
import com.github.dafloresz.autopecas_erp.user.client.dto.ClientRequestDTO;
import com.github.dafloresz.autopecas_erp.user.client.dto.ClientResponseDTO;
import com.github.dafloresz.autopecas_erp.user.client.mapper.ClientMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping(value = "/api/auth/register")
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO request){
        Client client = clientMapper.toClient(request);
        client = clientService.save(client);
        ClientResponseDTO response = clientMapper.toClientResponseDTO(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping(value = "/api/clients/{id}")
    public ResponseEntity<ClientResponseDTO>  updateClient(@Valid @RequestBody ClientPatchDTO request, @PathVariable("id") Long id) {
        if(verifyOwnership(id)){
            Client client = clientMapper.toClientChange(request);
            client = clientService.update(id, client);
            ClientResponseDTO response = clientMapper.toClientResponseDTO(client);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    @GetMapping(value = "/api/clients/{id}")
    public ResponseEntity<ClientResponseDTO>  getClient(@PathVariable("id") Long id) {
        if(verifyOwnership(id)){
            var response = clientMapper.toClientResponseDTO(clientService.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    private boolean verifyOwnership(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        var client = clientService.findById(id);

        return authenticatedEmail.equals(client.getEmail());
    }

}
