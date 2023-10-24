package com.bnpp.epita.spring.configuration;

import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.dto.ProduitCsv;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Configuration
@Primary
public class FileConfiguration {

    /*
    @Bean
    public CSVReader csvReader() throws FileNotFoundException{
        return new CSVReader(new FileReader("produitsData.csv"));
    }
     */

    @Bean
    public CsvToBean<ProduitCsv> csvTobean() throws FileNotFoundException {
        return new CsvToBeanBuilder<ProduitCsv>(new FileReader("produitsData.csv"))
                .withType(ProduitCsv.class)
                .build();
    }

    //Writer avec implémentation JsonItemWriter de SpringBatch
    @Bean
    public JsonFileItemWriter<Produit> jsonFileItemWriter(){
        return new JsonFileItemWriterBuilder<Produit>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new ClassPathResource("produits.json"))
                .name("produitJsonFileItemWriter")
                .build();
    }
}
