package com.bnpp.epita.spring.reader;

import com.bnpp.epita.spring.dto.ProduitCsv;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class FileProduitReader implements ItemReader<ProduitCsv> {

    /*
    @Autowired
    CSVReader csvReader;          // avec le CsvReader, le retour est String[]
     */

    Iterator<ProduitCsv> iterator;

    public FileProduitReader(CsvToBean<ProduitCsv> csvToBean) {
        this.iterator = csvToBean.iterator();
    }

    @Override
    public ProduitCsv read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!iterator.hasNext()){
            return null;
        }
        return iterator.next();
    }

    /*   cette méthode en tableau marche bien
    @Override
    public String[] read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //CSVReader reader=new CSVReader(new FileReader("produitsData.csv"));
        //on peut faire un bean de ce constructeur pour avec le nom du fichier csv dans la configuration (JobConfiguration)
        // + autowired pour l'utiliser

        return csvReader.readNext();
    }*/
}
