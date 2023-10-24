package com.bnpp.epita.spring.infraBDD.messaging.dto;

public class RoleDTO {
    private Integer id;

    private String nom;

    public RoleDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public RoleDTO() {
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
}
