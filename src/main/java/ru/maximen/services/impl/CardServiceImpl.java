package ru.maximen.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maximen.dao.CardDao;
import ru.maximen.dao.TransactionDao;
import ru.maximen.dao.UserDao;
import ru.maximen.entity.Card;
import ru.maximen.entity.Transaction;
import ru.maximen.services.CardService;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final TransactionDao transactionDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public String addCard(Card card){

        log.info("add card");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        card.setUser(userDao.findByLogin(authentication.getName()));


        if (card.getCardNumber().toString().length() != 16) {
            log.info("Incorrect card number");
            return "Incorrect card number";
        }

        if ((cardDao.getCardByCardNumber(card.getCardNumber()) == null)
                ||!(card.getCardNumber().equals(cardDao.getCardByCardNumber(card.getCardNumber()).getCardNumber()))) {
            cardDao.addCard(card);
            return "Card added";
        }

        log.info("Such a card already exists");
        return "Such a card already exists";
    }

    @Override
    @Transactional
    public void deleteCard(Long cardNumber){
        cardDao.deleteCard(cardNumber);
    }

    @Override
    @Transactional
    public Float getBalance(Long cardNumber){
        return cardDao.getCardByCardNumber(cardNumber).getBalance();
    }

    @Override
    @Transactional
    public String loadMoney(Long cardNumber, Float amount){

        Card card = cardDao.getCardByCardNumber(cardNumber);

        if (card == null) {
            return "Card ("+ cardNumber + ") not found";
        } else {
            card.setBalance(card.getBalance() + amount);
            cardDao.updateCard(card);

            Transaction transaction = new Transaction();
            transaction.setCardNumber(cardNumber);

            transaction.setDate(new Date());
            transaction.setAmount(amount);
            transaction.setBalance(cardDao.getCardByCardNumber(cardNumber).getBalance());
            transaction.setType("load");
            transactionDao.save(transaction);
        }
        return "Successful";
    }

    @Override
    @Transactional
    public String withdrawMoney(Long cardNumber, Float amount){
        Card card = cardDao.getCardByCardNumber(cardNumber);
        if (card == null) {
            return "Card (" + cardNumber + ") not found";
        } else if (card.getBalance() < amount) {
            return "Insufficient funds";
        } else {
            card.setBalance(card.getBalance() - amount);
            cardDao.updateCard(card);

            Transaction transaction = new Transaction();
            transaction.setCardNumber(cardNumber);

            transaction.setDate(new Date());
            transaction.setAmount(amount);
            transaction.setBalance(cardDao.getCardByCardNumber(cardNumber).getBalance());
            transaction.setType("withdraw");
            transactionDao.save(transaction);

            return "Successful";
        }
    }

    @Override
    @Transactional
    public String transferMoney(Long cardSenderNumber,Long cardRecipientNumber, Float amount){
        //Проверка на возможность сняттия средств и наличие карт
        Card card = cardDao.getCardByCardNumber(cardSenderNumber);
        if (cardDao.getCardByCardNumber(cardSenderNumber) == null) {
            return "Card (" + cardSenderNumber + ") not found";
        } else if (card.getBalance() < amount) {
            return "Insufficient funds";
        } else if (cardDao.getCardByCardNumber(cardRecipientNumber) == null) {
            return "Card (" + cardRecipientNumber + ") not found";
        }

        String status = withdrawMoney(cardSenderNumber, amount);
        if (status.equalsIgnoreCase("Successful")){
            status = loadMoney(cardRecipientNumber, amount);
            return status;
        } else return status;
    }



}
