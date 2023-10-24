package com.bnpp.epita.spring.configuration;

import com.bnpp.epita.spring.domaine.Produit;
import com.bnpp.epita.spring.dto.ProduitCsv;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {



    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    ItemReader<ProduitCsv> reader;
    @Autowired
    ItemProcessor<ProduitCsv, Produit> processor;
    @Autowired
    ItemWriter<Produit> dataWriter;
    @Autowired
    JsonFileItemWriter<Produit> jsonWriter;

    @Bean
    public Job jobCreateProduit(){
        return jobBuilderFactory
                .get("jobCreateProduit"+ LocalDateTime.now())
                .start(stepCreateProduit())
                .build();
    }

    public Step stepCreateProduit(){
        return stepBuilderFactory
                .get("step CSV")
                .<ProduitCsv, Produit>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(jsonWriter)
                .writer(dataWriter)
                .build();
    }


}
