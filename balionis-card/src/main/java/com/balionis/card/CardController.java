package com.balionis.card;

import com.balionis.card.security.CardSecurityFacade;
import com.balionis.card.repository.CardRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardApp.class);

    @Autowired
    CardSecurityFacade securityFacade;

    @Autowired
    CardRepository repository;

    @Secured("ROLE_USER")
    @RequestMapping(path = "/withdraw", method = RequestMethod.GET)
    @ResponseBody
    public CardResponse withdraw(@RequestParam(value="amount", defaultValue="-1") double amount) throws CardException {

        String cardName = securityFacade.getCurrentCardName();

        logger.debug("withdraw: cardName={}, amount={}", cardName, amount);

        if (amount <= 0) {
            throw new CardException(cardName, -1, "withdraw amount must be positive");
        }

        CardRequest req = new CardRequest(cardName, amount * -1);
        double balance = repository.update(req);

        logger.debug("withdraw: cardName={}, balance={}", cardName, balance);

        return new CardResponse(cardName, balance, CardResponse.Status.SUCCESS, null);

    }

    @Secured("ROLE_USER")
    @RequestMapping(path = "/topup", method = RequestMethod.GET)
    @ResponseBody
    public CardResponse topup(@RequestParam(value="amount", defaultValue="-1") double amount) throws CardException {

        String cardName = securityFacade.getCurrentCardName();

        logger.debug("topup: cardName={}, amount={}", cardName, amount);

        if (amount <= 0) {
            throw new CardException(cardName, -1, "top-up amount must be positive");
        }

        CardRequest req = new CardRequest(cardName, amount);
        double balance = repository.update(req);

        logger.debug("topup: cardName={}, balance={}", cardName, balance);

        return new CardResponse(cardName, balance, CardResponse.Status.SUCCESS, null);

    }

    @ExceptionHandler(CardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CardResponse handleException(CardException exc)  {

        logger.info("handleException: cardName={}, reason={}", exc.getCardName(), exc.getMessage(), exc);

        CardResponse response = new CardResponse(exc.getCardName(), exc.getBalance(), CardResponse.Status.FAILURE, exc.getMessage());
        return response;
    }

}