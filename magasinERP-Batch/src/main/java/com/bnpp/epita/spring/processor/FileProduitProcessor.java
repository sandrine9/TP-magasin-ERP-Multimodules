package com.bnpp.epita.spring.processor;

import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.dto.ProduitCsv;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FileProduitProcessor implements ItemProcessor<ProduitCsv, Produit> {

    @Override
    public Produit process(ProduitCsv produitCsv) throws Exception {

        Produit produit=new Produit();
        produit.setNom(produitCsv.getNom());
        produit.setPrixHT(produitCsv.getPrixHT());
        produit.setPhotoURL(produitCsv.getPhotoURL());
        produit.setDescription(produitCsv.getDescription().length()>255 ?
                produitCsv.getDescription().substring(0,254)
                : produitCsv.getDescription());

       /*
        //avec public process(String[] lineCsv)    avec le CsvReader
        produit.setNom(lineCsv[0]);
        produit.setPrixHT(new BigDecimal(lineCsv[1]));
        produit.setDescription(lineCsv[2]);
        produit.setPhotoURL(lineCsv[3]);
         */
        return produit;
    }
}
