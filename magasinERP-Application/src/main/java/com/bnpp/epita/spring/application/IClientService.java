package com.bnpp.epita.spring.application;

import com.bnpp.epita.spring.domaine.Client;

import java.util.List;

public interface IClientService {
    void createClient(Client c);
    List<Client> afficherListeClient();
    Client findById(Integer id);
}
