package Mediator2;
public class Mediator {
    private Buyer french;
    private Buyer swedish;
    private AmericanSeller seller;
    private DollarConverter convertor;

    public void registerAmericanSeller(AmericanSeller seller){
        this.seller = seller;
    }

    public void registerDollarConverter(DollarConverter convert){
        convertor = convert;
    }

    public void registerFrenchBuyer(Buyer French){
        this.french = French;
    }

    public void registerSwedishBuyer(Buyer swedish ){
        this.swedish = swedish;
    }

    public boolean placeBid(float bid, String unitOfCurrency ){
        float dollar = convertor.convertCurrencyToDollars(bid, unitOfCurrency);
        return seller.isBidAccepted(dollar);
    }
}
