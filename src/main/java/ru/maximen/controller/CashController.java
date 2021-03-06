package ru.maximen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maximen.services.CardService;

@RestController
@RequiredArgsConstructor
public class CashController {

    private final CardService cardService;

    @GetMapping("/getmoney/{cardNumber}")
    public String checkBalance(@PathVariable(value = "cardNumber") Long cardNumber){

        return cardService.getBalance(cardNumber).toString();
    }

    @PostMapping("/loadmoney/{cardNumber}/{amount}")
    public String loadMoney(@PathVariable(value = "cardNumber")Long cardNumber,
                            @PathVariable(value = "amount")Float amount){

        return cardService.loadMoney(cardNumber, amount);
    }

    @PostMapping("/withdrawmoney/{cardNumber}/{amount}")
    public String withdrawMoney(@PathVariable(value = "cardNumber")Long cardNumber,
                                @PathVariable(value = "amount")Float amount){
        return cardService.withdrawMoney(cardNumber, amount);
    }


    @PostMapping("/transfermoney/{cardSenderNumber}/{cardRecepientNumber}/{amount}")
    public String transferMoney(@PathVariable("cardSenderNumber") Long cardSenderNumber,
                                @PathVariable("cardRecepientNumber") Long cardRecepientNumber,
                                @PathVariable("amount") Float amount){
        System.out.println("call0");
        return cardService.transferMoney(cardSenderNumber,cardRecepientNumber, amount);
    }

}
