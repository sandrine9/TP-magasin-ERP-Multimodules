package com.bnpp.epita.spring.infraBDD.messaging.dto;

public class MessageInventaireDto {

    private Integer productId;
    private int qte;

    public MessageInventaireDto(Integer productId, int qte) {
        this.productId = productId;
        this.qte = qte;
    }

    public MessageInventaireDto() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
