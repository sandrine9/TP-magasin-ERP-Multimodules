package com.bnpp.epita.spring.application;

import com.bnpp.epita.spring.domaine.Devis;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface IDevisService {

    void createDevis(Devis d);

    Devis findById (Integer id);

    List<Devis> findAll(Sort s);

}
