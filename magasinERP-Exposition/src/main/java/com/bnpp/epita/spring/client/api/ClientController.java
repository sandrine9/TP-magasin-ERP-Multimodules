package com.bnpp.epita.spring.client.api;

import com.bnpp.epita.spring.application.IClientService;
import com.bnpp.epita.spring.client.converter.ClientConverter;
import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.dto.ClientCourtDTO;
import com.bnpp.epita.spring.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    @Autowired
    IClientService clientService;

    @PostMapping
    public void createClient(@RequestBody ClientDTO clientDTO){
        Client client = ClientConverter.convertirDTOenClient(clientDTO);
        clientService.createClient(client);
    }
    @GetMapping
    public List<ClientCourtDTO> afficherListeClient() {
        List<Client> listeClient = clientService.afficherListeClient();
        List<ClientCourtDTO> listeClientCourtDTO = listeClient.stream()
                                         .map(c -> ClientConverter.convertirClientEnClientCourtDTO(c))
                                         .collect(Collectors.toList());
        return listeClientCourtDTO;

    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> afficherClient(@PathVariable("id") Integer id){
        Client client = clientService.findById(id);
        ClientDTO clientDTO = ClientConverter.convertirClientEnDTO(client);
        return ResponseEntity.ok().body(clientDTO);
    }

}
