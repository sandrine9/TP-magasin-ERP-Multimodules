package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.infraBDD.messaging.dto.ClientDTO;
import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageIamDto;

import java.util.List;

public interface IClientMessaging {
    void send (ClientDTO payload);
    void send (MessageIamDto payload);

    //List<ClientDTO> consume(String message);
}
