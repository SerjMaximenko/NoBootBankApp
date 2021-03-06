package ru.maximen.services;

import ru.maximen.entity.Card;

public interface CardService {

    String addCard(Card card);

    void deleteCard(Long cardNuber);

    Float getBalance(Long cardNumber);

    String loadMoney(Long cardNumber, Float amount);

    String withdrawMoney(Long cardNumber, Float amount);

    String transferMoney(Long cardSenderNumber,Long cardRecipientNumber, Float amount);
}
