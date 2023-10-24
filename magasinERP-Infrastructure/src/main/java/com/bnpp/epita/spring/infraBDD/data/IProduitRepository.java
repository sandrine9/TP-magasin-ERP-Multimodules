package com.bnpp.epita.spring.infraBDD.data;

import com.bnpp.epita.spring.domaine.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduitRepository extends JpaRepository<Produit, Integer> {
}
