package com.bnpp.epita.spring.infraBDD.messaging.converter;

import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.messaging.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IClientMapper {


    @Mapping(source = "adresse", target = "adresseDTO")
    ClientDTO clientEntityToDto(Client c);

    @Mapping(source = "adresseDTO", target = "adresse")
    Client clientDtoToCleintEntity(ClientDTO clientDTO);
}
