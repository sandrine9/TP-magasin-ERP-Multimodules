package com.bnpp.epita.spring.application;

import com.bnpp.epita.spring.domaine.Devis;
import com.bnpp.epita.spring.infraBDD.data.IDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevisServiceImpl implements IDevisService{
    @Autowired
    IDevisRepository repository;

    @Override
    public void createDevis(Devis d) {
        repository.save(d);
    }

    @Override
    public Devis findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Devis> findAll(Sort s) {
        return repository.findAll(s);
    }


}
