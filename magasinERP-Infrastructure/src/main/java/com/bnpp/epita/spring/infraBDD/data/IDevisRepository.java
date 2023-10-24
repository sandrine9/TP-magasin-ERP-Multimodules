package com.bnpp.epita.spring.infraBDD.data;

import com.bnpp.epita.spring.domaine.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDevisRepository extends JpaRepository<Devis, Integer> {
}
