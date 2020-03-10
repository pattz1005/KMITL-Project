/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

/**
 *
 * @author alok
 */
public class Mediator {
    AmericanSeller americanSeller;
    DollarConverter dollarConverter;
    FrenchBuyer frenchBuyer;
    SwedishBuyer swedishBuyer;
    
    void registerAmericanSeller(AmericanSeller aThis) {
        this.americanSeller = aThis;
    }

    boolean placeBid(float bid, String unitOfCurrency) {
        float amountInDollars = dollarConverter.convertCurrencyToDollars(bid, unitOfCurrency);
        return americanSeller.isBidAccepted(amountInDollars);
    }

    void registerDollarConverter(DollarConverter aThis) {
        this.dollarConverter = aThis;
    }

    void registerFrenchBuyer(FrenchBuyer aThis) {
        this.frenchBuyer = aThis;
    }

    void registerSwedishBuyer(SwedishBuyer aThis) {
        this.swedishBuyer = aThis;
    }
    
}
