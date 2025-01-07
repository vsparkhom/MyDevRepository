package com.vlpa.spring.expenseimporter.model;

public enum Card {

    TdCredit(Bank.TD, CardType.CREDIT),
    TdDebit(Bank.TD, CardType.DEBIT),
    PcfCredit(Bank.PCF, CardType.CREDIT),
    CibcCredit(Bank.CIBC, CardType.CREDIT);

    private Bank bank;
    private CardType cardType;

    Card(Bank bank, CardType cardType) {
        this.bank = bank;
        this.cardType = cardType;
    }

    public Bank getBank() {
        return bank;
    }

    public CardType getCardType() {
        return cardType;
    }

    public static Card resolveCard(String bank, String cardType) {
        for (Card card : values()) {
            if (card.getBank().equals(Bank.resolveBank(bank)) && card.getCardType().equals(CardType.resolveCardType(cardType))) {
                return card;
            }
        }
        throw new RuntimeException(String.format("Card for bank '%s' and card type '%s' is not supported", bank, cardType));
    }
}
