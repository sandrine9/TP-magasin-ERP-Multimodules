package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageInventaireDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.QueueReceiver;

@Service
public class InventaireMessagingImpl implements IInventaireMessaging {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void send(MessageInventaireDto msg) {
        try{
            final String produitToJson=mapper.writeValueAsString(msg);
            jmsTemplate.send("QUEUE_ERP_INVENTAIRE",
                    session -> session.createTextMessage(produitToJson));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }


}
