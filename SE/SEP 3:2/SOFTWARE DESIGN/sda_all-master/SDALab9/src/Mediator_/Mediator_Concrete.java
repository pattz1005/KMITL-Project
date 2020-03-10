package Mediator_;

public class Mediator_Concrete implements Mediator {

    AmericanSeller americanSeller;
    SwedishBuyer swedishBuyer;
    FrenchBuyer frenchBuyer;
    DollarConverter dollarConverter;


    @Override
    public void registerSwedishBuyer(SwedishBuyer swedishBuyer) {
        this.swedishBuyer = swedishBuyer;
    }

    @Override
    public void registerFrenchBuyer(FrenchBuyer frenchBuyer) {
        this.frenchBuyer = frenchBuyer;
    }

    @Override
    public void registerDollarConverter(DollarConverter dollarConverter) {
        this.dollarConverter = dollarConverter;
    }

    @Override
    public void registerAmericanSeller(AmericanSeller americanSeller) {
        this.americanSeller = americanSeller;
    }

    @Override
    public boolean placeBid(float bid, String unitOfCurrency) {
        float inDollar = dollarConverter.convertCurrencyToDollars(bid, unitOfCurrency);
        return americanSeller.isBidAccepted(inDollar);
    }
}
