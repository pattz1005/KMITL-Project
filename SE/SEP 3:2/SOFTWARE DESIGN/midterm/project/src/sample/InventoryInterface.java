package sample;

public interface InventoryInterface {
	
	public void addMovie(Movie movie);
	public void sellMovie(String movieName)throws MatchNotFoundException;
	public void addCopy(String movieName, Integer NumberOfCopy ) throws MatchNotFoundException;
	public void changePrice(String movieName,Integer newPrice) throws MatchNotFoundException;
	public Integer findPriceByName(String movieName) throws MatchNotFoundException;
	public Integer findQuantityByName(String movieName) throws MatchNotFoundException;
	public Integer findQuantityByID(Integer movieID) throws MatchNotFoundException;
	public Integer findPriceByID(Integer movieID) throws MatchNotFoundException;
	public void saveState();
	public void getState();
}
