package com.bnpp.epita.application;

import com.bnpp.epita.spring.application.CalculService;
import com.bnpp.epita.spring.application.ICalcul;
import com.bnpp.epita.spring.application.ProduitServiceImpl;
import com.bnpp.epita.spring.dto.DevisLigneDTO;
import com.bnpp.epita.spring.infraBDD.data.IProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = {CalculService.class})   //pour que Spring charge l'interface dont on a besoin et permettre l'autowired
public class CalculServiceTest {
    @Autowired
    ICalcul calculService;
    @Test
    public void calculPrixTTC(){
        //ICalcul calculService = new CalculService();
        BigDecimal result=calculService.calculPrixTTC(new BigDecimal("10"));
        assertEquals(new BigDecimal("12"),result.setScale(0, RoundingMode.HALF_UP));
    }
}
