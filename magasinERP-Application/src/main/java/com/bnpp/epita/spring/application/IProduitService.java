package com.bnpp.epita.spring.application;

import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.exceptionsMetier.CrudEntityException;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProduitService {
    void createProduit(Produit p) throws CrudEntityException;
    void updateProduit (Produit p);

    List<Produit> afficherListeProduit ();
    List<Produit> findAll(Sort sort);
    Produit findProduit(Integer id);
}
