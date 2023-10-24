package com.bnpp.epita.spring.application;

import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.exceptionsMetier.CrudEntityException;
import com.bnpp.epita.spring.infraBDD.messaging.IInventaireMessaging;
import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageInventaireDto;
import com.bnpp.epita.spring.infraBDD.data.IProduitRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProduitServiceImpl implements IProduitService{

    @Autowired
    IProduitRepository produitDao;

    @Autowired
    IInventaireMessaging messageProduit;

    //@Autowired
    //ObjectMapper mapper;

    @Override
    @Transactional
    public void createProduit(Produit p) throws CrudEntityException {
        // enregistrer produit en base :
        //if(p.getPrixHT().equals(0)){
        //    throw
        //}

        Produit newProduit = produitDao.save(p);
        if (newProduit==null){
            throw new CrudEntityException("le produit n'a pas pu etre cree");
        }

        //envoi evt pour incrémenter le stock :
        MessageInventaireDto dto = new MessageInventaireDto(newProduit.getId(), 1);
        messageProduit.send(dto);

        /*  version Sandrine
        String messageQueue = "ProductId="+newProduit.getId()+";"+"Qte=1"+";"+"Action=Product";
        jmsTemplate.send("ERP_Queue", session -> session.createTextMessage(messageQueue));
         */
    }
    public void updateProduit(Produit p) {
        produitDao.save(p);
    }

    @Override
    public List<Produit> afficherListeProduit() {
        return produitDao.findAll();
    }

    @Override
    public List<Produit> findAll(Sort sort) {
        return produitDao.findAll(sort);
    }

    @Override
    public Produit findProduit(Integer id) {
        return produitDao.findById(id).get();
    }
}
