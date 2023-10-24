package com.bnpp.epita.spring.infraBDD.messaging.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ClientDTO {

    private Integer id;
    private String nom;
    private String prenom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private String email;
    @JsonProperty("adresse")    // pour que dans le json, il s'appelle adresse et non adresseDTO
    private AdresseDTO adresseDTO;
    private String password;
    private List<RoleDTO> roles;


    public ClientDTO() {
    }

    public ClientDTO(Integer id, String nom, String prenom, LocalDate dateNaissance, String email,
                     AdresseDTO adresseDTO, String password, List<RoleDTO> roles) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.adresseDTO = adresseDTO;
        this.password = password;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AdresseDTO getAdresseDTO() {
        return adresseDTO;
    }

    public void setAdresseDTO(AdresseDTO adresseDTO) {
        this.adresseDTO = adresseDTO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return  id
                +";"+ nom
                +";" + prenom
                +";" + dateNaissance
                +";"+email
                + ";"+adresseDTO ;
    }
}
