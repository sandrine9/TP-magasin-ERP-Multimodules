package com.bnpp.epita.spring.produit.api;

import com.bnpp.epita.spring.application.IProduitService;
import com.bnpp.epita.spring.produit.converter.ProduitConverter;
import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.dto.ProduitCourtDTO;
import com.bnpp.epita.spring.dto.ProduitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/produit")
public class ProduitController {

    @Autowired
    IProduitService produitService;
    @PostMapping
    void createProduit (@RequestBody ProduitDTO produitDTO){
        Produit produit = ProduitConverter.convertirDTOenProduit(produitDTO);
        produitService.createProduit(produit);
    }
    @PostMapping("/update")
    void updateProduit (@RequestBody ProduitDTO produitDTO){
        Produit produit = ProduitConverter.convertirDTOenProduit(produitDTO);
        produitService.updateProduit(produit);
    }
    @GetMapping("/{id}")
    ResponseEntity<ProduitDTO> afficherProduitById (@PathVariable Integer id){
        Produit produit = produitService.findProduit(id);
        ProduitDTO produitDTO = ProduitConverter.convertirProduitEnDTO(produit);
        return ResponseEntity.ok().body(produitDTO);
    }
    @GetMapping
    List<ProduitCourtDTO> afficherListeProduit(){
        List<Produit> listeProduit = produitService.afficherListeProduit();
        return (listeProduit.stream()
                .map(p -> ProduitConverter.convertirProduitEnProduitCourtDTO(p))
                .collect(Collectors.toList()));
    }
    @GetMapping("/all/{property}/{order}")
    List<ProduitCourtDTO> findAllSort(@PathVariable("property") String property, @PathVariable("order") String order){
        Sort s = null;
        if (order.equals("A")
        ) {
            s = Sort.by(property).ascending();
        } else if (order.equals("D")) {
            s = Sort.by(property).descending();
        }
        List<Produit> sortedList = produitService.findAll(s);
        //autre écriture possible. Mais ici on ne peut pas gérer de 3ème valeur dont autre pour lever exception
        //List<Produit> sortedList = produitService.findAll(order.equals("D") ? Sort.by(property).descending() : Sort.by(property).ascending());

        List<ProduitCourtDTO> listeDto= sortedList
                .stream()
                .map(produit -> ProduitConverter.convertirProduitEnProduitCourtDTO(produit))
                .collect(Collectors.toList());

        return listeDto;
    }

}
