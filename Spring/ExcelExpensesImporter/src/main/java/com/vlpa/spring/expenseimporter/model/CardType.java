package com.vlpa.spring.expenseimporter.model;

public enum CardType {

    CREDIT("credit"),
    DEBIT("debit");

    private String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardType resolveCardType(String value) {
        for (CardType cardType : values()) {
            if (cardType.getValue().equalsIgnoreCase(value)) {
                return cardType;
            }
        }
        throw new RuntimeException(String.format("Card type '%s' is not supported"));
    }
}
