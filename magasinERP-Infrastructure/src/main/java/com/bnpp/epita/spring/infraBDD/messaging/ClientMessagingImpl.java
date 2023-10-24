package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.infraBDD.messaging.dto.ClientDTO;
import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageIamDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientMessagingImpl implements IClientMessaging {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ObjectMapper mapper;
    @Override
    public void send(ClientDTO payload) {

        try {
            final String json=mapper.writeValueAsString(payload);
            jmsTemplate.send("QUEUE_ERP_TO_IAM",
                    session -> session.createTextMessage(json));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void send(MessageIamDto payload) {
        try {
            final String json=mapper.writeValueAsString(payload);
            jmsTemplate.send("QUEUE_ERP_TO_IAM",
                    session -> session.createTextMessage(json));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @JmsListener(destination = "QUEUE_IAM_TO_ERP")
    @Override
    public List<ClientDTO> consume(String message) {
        List<ClientDTO> listClientDto;
        try {
            listClientDto=mapper.readerForListOf(ClientDTO.class).readValue(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return listClientDto;

    }
     */

    /*
    @Override
    public ClientDTO readMessage() {
        ClientDTO clientDto = (ClientDTO) jmsTemplate.receiveAndConvert("QUEUE_IAM_REPONSE");
        return clientDto;
    }
    */
}
