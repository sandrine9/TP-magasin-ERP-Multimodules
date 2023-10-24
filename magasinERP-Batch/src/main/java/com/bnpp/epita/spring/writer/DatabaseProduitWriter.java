package com.bnpp.epita.spring.writer;

import com.bnpp.epita.spring.application.IProduitService;
import com.bnpp.epita.spring.domaine.Produit;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseProduitWriter implements ItemWriter<Produit> {

    @Autowired
    IProduitService produitService;

    @Override
    public void write(List<? extends Produit> list) throws Exception {
        //ici on enregistre un par un
        for (Produit p: list ) {
            System.out.println(p);
            //produitService.createProduit(p);     //Begin commit
        }
        //avec des implémentations de SpringBatch d'un DataItemWriter
        //Spring prend en charge le rollback sur le lot complet
        //en passant unitairement par les save de notre service métier, ce n'est pas possible


        // on pourrait aussi faire de l'enreg par lot
        // avec une nouvelle méthode dans IProduitService createProduitParLot qui prend une list<Produit> en param
        //  et qui fera des saveAll en bdd et des sendAll en fileMQ
        // saveAll fait des save successifs donc ça n'optimise pas vraiment

    }
}
