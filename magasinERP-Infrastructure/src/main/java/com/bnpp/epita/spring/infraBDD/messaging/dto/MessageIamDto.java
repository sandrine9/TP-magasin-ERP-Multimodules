package com.bnpp.epita.spring.infraBDD.messaging.dto;

public class MessageIamDto {

    private Integer idClient;
    private String blocType;
    private String action;
    private String payload;   //json

    public MessageIamDto() {
    }

    public MessageIamDto(Integer idClient, String blocType, String action, String payload) {
        this.idClient = idClient;
        this.blocType = blocType;
        this.action = action;
        this.payload = payload;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getBlocType() {
        return blocType;
    }

    public void setBlocType(String blocType) {
        this.blocType = blocType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
