package com.vlpa.spring.expenseimporter.model;

public enum CardType {

    CREDIT,
    DEBIT;

    public static CardType resolveCardType(String value) {
        for (CardType cardType : values()) {
            if (cardType.name().equalsIgnoreCase(value)) {
                return cardType;
            }
        }
        throw new RuntimeException(String.format("Card type '%s' is not supported", value));
    }
}
