package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.infraBDD.messaging.dto.ClientDTO;

import java.util.List;

public class ReceiveClient {
    static List<ClientDTO> listClientDto;

    public static List<ClientDTO> getListClientDto() {
        return listClientDto;
    }

    public static void setListClientDto(List<ClientDTO> list) {
        listClientDto = list;
    }
}
