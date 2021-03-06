package ru.maximen.dao;

import ru.maximen.entity.Card;

public interface CardDao {

    void addCard(Card card);
    void deleteCard(Long cardNumber);
    Card getCardByCardNumber(Long cardNumber);
    void updateCard(Card card);
}

