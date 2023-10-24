package com.bnpp.epita.spring.infraBDD.messaging;


import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageInventaireDto;


public interface IInventaireMessaging {

    void send (MessageInventaireDto paylaod);


}
