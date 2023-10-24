package com.bnpp.epita.spring.dto;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class ProduitCsv {

    @CsvBindByName
    private String nom;
    @CsvBindByName
    private BigDecimal prixHT;
    @CsvBindByName
    private String description;
    @CsvBindByName(column="photo")
    private String photoURL;

    public ProduitCsv(String nom, BigDecimal prixHT, String description, String photoURL) {
        this.nom = nom;
        this.prixHT = prixHT;
        this.description = description;
        this.photoURL = photoURL;
    }

    public ProduitCsv() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(BigDecimal prixHT) {
        this.prixHT = prixHT;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
