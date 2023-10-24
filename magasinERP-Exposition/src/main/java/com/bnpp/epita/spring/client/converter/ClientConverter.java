package com.bnpp.epita.spring.client.converter;

import com.bnpp.epita.spring.domaine.Adresse;
import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.domaine.Role;
import com.bnpp.epita.spring.dto.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class ClientConverter {

    //clients
    public static ClientDTO convertirClientEnDTO (Client client){
        ClientDTO clientDTO = new ClientDTO();
        AdresseDTO adrDto=new AdresseDTO();

        //La partie client
        Optional.ofNullable(client).ifPresent((c)-> {
                    clientDTO.setId(c.getId());
                    clientDTO.setEmail(c.getEmail());
                    clientDTO.setPrenom(c.getPrenom());
                    clientDTO.setNom(c.getNom());
                    clientDTO.setDateNaissance(c.getDateNaissance());
                }
        );

        //Partie adresse
        Optional.ofNullable(client.getAdresse()).ifPresent( (adr) -> {
                    adrDto.setId(adr.getId());
                    adrDto.setRue(adr.getRue());
                    adrDto.setCodePostal(adr.getCodePostal());
                    adrDto.setVille(adr.getVille());
                    clientDTO.setAdresseDTO(adrDto);
                }
        );
        //OU
        //clientDTO.setId(client.getId());
        //clientDTO.setNom(client.getNom());
        //clientDTO.setPrenom(client.getPrenom());
        //clientDTO.setDateNaissance(client.getDateNaissance());
        //clientDTO.setEmail(client.getEmail());
        //clientDTO.setAdresseDTO(convertirAdresseEnDTO(client.getAdresse()));
        return clientDTO;
    }
    public static ClientCourtDTO convertirClientEnClientCourtDTO (Client c){
        ClientCourtDTO clientCourtDTO = new ClientCourtDTO();
        clientCourtDTO.setId(c.getId());
        clientCourtDTO.setNom(c.getNom());
        clientCourtDTO.setPrenom(c.getPrenom());
        clientCourtDTO.setEmail(c.getEmail());
        clientCourtDTO.setCodePostal(c.getAdresse().getCodePostal());
        clientCourtDTO.setVille(c.getAdresse().getVille());
        return clientCourtDTO;
    }
    public static Client convertirDTOenClient (ClientDTO dto){
        Client client = new Client();
        if(dto.getId() != null){
            client.setId(dto.getId());
        }
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setDateNaissance(dto.getDateNaissance());
        client.setEmail(dto.getEmail());
        client.setEmail(dto.getEmail());
        client.setAdresse(convertirDTOenAdresse(dto.getAdresseDTO()));
        List<Role> roles=dto.getRoles().stream().map(roleDTO -> convertRoleDtoDtoToRole(roleDTO)).toList();
        //List<Role> roles=dto.getRoles().stream().map(ClientConverter::convertRoleDtoDtoToRole).toList();
        return client;
    }

    private static Role convertRoleDtoDtoToRole(RoleDTO roleDTO) {
        return new Role(roleDTO.getId(),roleDTO.getNom());
    }

    public static Client convertirDTOenClientEntityViaModelMapper (ClientDTO dto){
        ModelMapper mapper = new ModelMapper();
        //TypeMap<ClientDTO, Client> propertyMapper = mapper.createTypeMap(ClientDTO.class, Client.class);
        //propertyMapper.addMapping(ClientDTO::getAdresseDTO, Client::setAdresse);
                      // besoin car adresse n'est pas nommée de la même façon dans dto et client
                      // il a fallu ajouter un addmapping .... TypeMap, etc ...
                      // mais il a compris de lui même grâce au @JsonProperty
        return mapper.map(dto,Client.class);
        // ModelMapper bien quand pas de modif entre entity et dto
    }

    private static AdresseDTO convertirAdresseEnDTO (Adresse a){
        AdresseDTO dto = new AdresseDTO();
        dto.setId(a.getId());
        dto.setRue(a.getRue());
        dto.setCodePostal(a.getCodePostal());
        dto.setVille(a.getVille());
        return dto;
    }
    private static Adresse convertirDTOenAdresse (AdresseDTO dto){
        Adresse adr = new Adresse();
        if(dto.getId() != null){
            adr.setId(dto.getId());
        }
        adr.setRue(dto.getRue());
        adr.setCodePostal(dto.getCodePostal());
        adr.setVille(dto.getVille());
        return adr;
    }



}
