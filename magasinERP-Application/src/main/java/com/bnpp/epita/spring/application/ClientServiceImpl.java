package com.bnpp.epita.spring.application;

import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.messaging.IClientMessaging;
import com.bnpp.epita.spring.infraBDD.messaging.IInventaireMessaging;
import com.bnpp.epita.spring.infraBDD.messaging.ReceiveClient;
import com.bnpp.epita.spring.infraBDD.messaging.converter.ClientDtoConverter;
import com.bnpp.epita.spring.infraBDD.messaging.dto.ClientDTO;
import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageIamDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService{


    @Autowired
    IInventaireMessaging messageService;
    @Autowired
    IClientMessaging clientMessaging;
    @Autowired
    ClientDtoConverter converter;
    @Autowired
    ObjectMapper mapper;
    @Override
    public void createClient(Client client) {

        try {
            // envoi d'un msg POST dans file IAM
            ClientDTO dto = converter.convertClientEntityToClientDto(client);
            String clientDtoToJson= null;
            clientDtoToJson = mapper.writeValueAsString(dto);
            MessageIamDto msgDto=new MessageIamDto(null,null,"CREATE", clientDtoToJson);
            clientMessaging.send(msgDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //ObjectMapper mapper = new ObjectMapper();
        //jmsTemplate.send("QUEUE_IAM_POST_CLIENT", session -> session.createTextMessage(clientDTO.toString()));

    }

    @Override
    public List<Client> afficherListeClient() {
        //findAll simple
        //return repository.findAll();

        //findAll avec tri (très utile quand remonte bcp de lignes et que le front ne peut en afficher que 10
        return null;
    }

    @Override
    @Transactional
    public Client findById(Integer id) {

        //Demande à l'IAM
        MessageIamDto dto=new MessageIamDto(id, "IDENTITY","GET","");
        clientMessaging.send(dto);

        //Réceptoin msg retour
        Optional<Client> optionalClient= ReceiveClient.getListClientDto()
                .stream()
                .filter(clientDto -> clientDto.getId()==id)
                .map(converter::convertClientDtoToCleintEntity)
                .findFirst();
        return optionalClient.get();

        /*
        Optional<Client> clientOptional=repository.findById(id);


        Client c = null;
        if(clientOptional.isPresent()){
            c=clientOptional.get();
            return c;
        }

         */



        //Ternaire
        //return clientOptional.isPresent() ? clientOptional.get() : null ;

        // envoi d'un msg GET ID dans file IAM
        //jmsTemplate.send("QUEUE_IAM_GET", session -> session.createTextMessage(id.toString()));

        // lecture msg retour dans file IAM
        //ClientDTO clientDto = (ClientDTO) jmsTemplate.receiveAndConvert("QUEUE_IAM_REPONSE");
        //return clientDTO;

    }
}
