package ru.maximen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.maximen.entity.Card;
import ru.maximen.entity.Transaction;
import ru.maximen.entity.User;
import ru.maximen.services.CardService;
import ru.maximen.services.TransactionService;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final TransactionService transactionService;

    @GetMapping(value = "/add/{cardNumber}/{pin}/{balance}/{expiredDate}/{cvc}")
    public String addNewCard(
            @PathVariable(value = "cardNumber") Long cardNumber,
            @PathVariable(value = "pin") Long pin,
            @PathVariable(value = "balance") Float balance,
            @PathVariable(value = "expiredDate") String expiredDate,
            @PathVariable(value = "cvc") Long cvc
            ){
        Card card = new Card(cardNumber, pin, balance, expiredDate, new User(), cvc);

        return cardService.addCard(card);
    }

    @DeleteMapping(value = "/delete/{cardNumber}")
    public String deleteCard(@PathVariable(value = "cardNumber") Long cardNumber){
        cardService.deleteCard(cardNumber);
        return "Delete";
    }

    @GetMapping("/gettranshist/{cardNumber}/{startDate}/{endDate}")
    public List<Transaction> getTransactionHistory(
            @PathVariable("cardNumber") Long cardNumber,
            @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm") Date startDate,
            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm") Date endDate
            ){
        return transactionService.GetTransactionHistory(cardNumber,startDate,endDate);
    }

}
