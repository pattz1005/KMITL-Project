package Mediator_;

public interface Mediator {
    public void registerSwedishBuyer(SwedishBuyer swedishBuyer);

    public void registerFrenchBuyer(FrenchBuyer frenchBuyer) ;

    public void registerDollarConverter(DollarConverter dollarConverter) ;

    public void registerAmericanSeller(AmericanSeller americanSeller) ;

    public boolean placeBid(float bid, String unitOfCurrency) ;
}
