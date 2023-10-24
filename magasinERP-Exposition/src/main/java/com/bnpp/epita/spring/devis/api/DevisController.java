package com.bnpp.epita.spring.devis.api;

import com.bnpp.epita.spring.application.IDevisService;
import com.bnpp.epita.spring.devis.converter.DevisConverter;
import com.bnpp.epita.spring.domaine.Devis;
import com.bnpp.epita.spring.dto.DevisDTO;
import com.bnpp.epita.spring.dto.DevisLigneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/devis")
public class DevisController {
    @Autowired
    DevisConverter devisConverter;
    @Autowired
    IDevisService devisService;
    @PostMapping
    void createDevis (@RequestBody DevisDTO devisDTO){
        //convertir devisDTO en devis
        Devis devis = devisConverter.convertDevisDTOtoDevisEntity(devisDTO);
        //appel creation devis en BDD
        devisService.createDevis(devis);
    }
    @GetMapping("/{id}")
    DevisDTO findById (@PathVariable("id") Integer id){
        Devis devis = devisService.findById(id);
        return devisConverter.convertDevisToDevisDTO(devis);
    }
    @PostMapping("/ligne")   //pas utiliser, on passera par création/modif de devis pour ajouter ligne
    void createDevisLigne(@RequestBody DevisLigneDTO devisLigneDTO){

    }
}
