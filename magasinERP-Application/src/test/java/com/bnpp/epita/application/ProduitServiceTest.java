package com.bnpp.epita.application;

import com.bnpp.epita.spring.application.IProduitService;
import com.bnpp.epita.spring.application.ProduitServiceImpl;
import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.exceptionsMetier.CrudEntityException;
import com.bnpp.epita.spring.infraBDD.data.IProduitRepository;
import com.bnpp.epita.spring.infraBDD.messaging.IInventaireMessaging;
import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageInventaireDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProduitServiceImpl.class, IProduitRepository.class})
public class ProduitServiceTest {

    @MockBean
    IProduitRepository produitDao;
    @MockBean
    IInventaireMessaging messageProduit;
    @Autowired
    IProduitService service;

    Produit produitFake;
    Produit produitTest;
    @BeforeEach
    public void init(){
        produitFake=new Produit();
        produitFake.setId(85);

        produitTest=new Produit();
        produitTest.setDescription("lorem ipsum");

        when(produitDao.save(produitTest)).thenReturn(produitFake);
    }

    @Test
    public void createProduit() throws CrudEntityException {
        service.createProduit(produitTest);

        verify(messageProduit).send(argThat(new ArgumentMatcher<MessageInventaireDto>() {
            @Override
            public boolean matches(MessageInventaireDto messageInventaireDto) {
                return messageInventaireDto.getProductId()==produitFake.getId()
                        && messageInventaireDto.getQte()==1;
            }
        }));
    }

    @Test
    public void createNullProduct(){
        Exception exception = assertThrows(CrudEntityException.class, ()->service.createProduit(null));
        String message = "le produit n'a pas pu etre cree";
        assertTrue(message.equals(exception.getMessage()));
    }
}
