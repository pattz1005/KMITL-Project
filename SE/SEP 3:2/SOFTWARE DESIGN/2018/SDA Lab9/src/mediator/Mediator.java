package mediator;

public class Mediator {

    private Buyer frenchBuyer;
    private Buyer swedishBuyer;
    private AmericanSeller americanSeller;
    private DollarConverter dollarConverter;

    public void registerFrenchBuyer(Buyer b) {
        this.frenchBuyer = b;
    }

    public void registerSwedishBuyer(Buyer b) {
        this.swedishBuyer = b;
    }

    public void registerAmericanSeller(AmericanSeller a) {
        this.americanSeller = a;
    }

    public void registerDollarConverter(DollarConverter d) {
        this.dollarConverter = d;
    }

    public boolean placeBid(float bid, String unitOfCurrency) {
        float dollars = dollarConverter.convertCurrencyToDollars(bid, unitOfCurrency);
        return americanSeller.isBidAccepted(dollars);
    }

}
