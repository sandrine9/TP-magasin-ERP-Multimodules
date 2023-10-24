package com.bnpp.epita.spring.infraBDD.messaging.converter;

import com.bnpp.epita.spring.domaine.Adresse;
import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.messaging.dto.AdresseDTO;
import com.bnpp.epita.spring.infraBDD.messaging.dto.ClientDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoConverter {

    public Adresse convertAdressDtoToAdressEntity(AdresseDTO adresseDto){
        Adresse adresse=new Adresse();
        adresse.setCodePostal(adresseDto.getCodePostal());
        adresse.setRue(adresseDto.getRue());
        adresse.setVille(adresseDto.getVille());
        return adresse;
    }


    public ClientDTO convertClientEntityToClientDto(Client client){
        IClientMapper  mapper = Mappers.getMapper( IClientMapper.class );
        return mapper.clientEntityToDto(client);
        //ou avec Mapstruct
        //return IClientMapper.INSTANCE.clientEntityToDto(client);
    }
    public Client convertClientDtoToCleintEntity(ClientDTO clientDTO){
        IClientMapper  mapper = Mappers.getMapper( IClientMapper.class );
        return mapper.clientDtoToCleintEntity(clientDTO);
    }


}
