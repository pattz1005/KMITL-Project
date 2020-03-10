package sample;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Inventory implements InventoryInterface {
    private ArrayList<Movie> movieCollection = new ArrayList<Movie>();
    private Memento memento = new Memento();
    private InventoryDecorator invent;
    private String CommandFileName = "Command.ser";
    private Integer numberOfState = 0, timeToSave = 3;

    public ArrayList<Movie> getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(ArrayList<Movie> movieCollection) {
        this.movieCollection = movieCollection;
    }

    public void addMovie(Movie movie) {
        movieCollection.add(movie);
        if (++numberOfState == timeToSave) {
            this.saveState();
            numberOfState = 0;
        }
    }

    /**
     * sellMovie(String movieName) method minimize the count of movie
     * by 1 if movie is present in inventory else throws
     * exception.
     */

    public void sellMovie(String movieName) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {

            if (movie.getName().equals(movieName) && movie.getQuantity() > 0) {
                movie.changeQuantity(-1);
                if (++numberOfState == timeToSave) {
                    this.saveState();
                    numberOfState = 0;
                }
                return;
            }

        }
        throw new MatchNotFoundException("No Match Found");
    }

    public void addCopy(String movieName, Integer NumberOfCopy) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {
            if (movie.getName().equals(movieName)) {
                movie.changeQuantity(NumberOfCopy);
                if (++numberOfState == timeToSave) {
                    this.saveState();
                    numberOfState = 0;
                }
                return;
            }
        }
        throw new MatchNotFoundException("No Match Found");
    }

    public void changePrice(String movieName, Integer newPrice) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {
            if (movie.getName().equals(movieName)) {
                movie.setPrice(newPrice);
                if (++numberOfState == timeToSave) {
                    this.saveState();
                    numberOfState = 0;
                }
                return;
            }
        }
        throw new MatchNotFoundException("No Match Found");
    }

    public Integer findPriceByName(String movieName) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {
            if (movie.getName().equals(movieName)) {
                return movie.getPrice();
            }
        }
        throw new MatchNotFoundException("No Match Found");
    }

    public Integer findQuantityByName(String movieName) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {
            if (movie.getName().equals(movieName)) {
                return movie.getQuantity();
            }
        }
        throw new MatchNotFoundException("No Match Found");
    }

    public Integer findQuantityByID(Integer movieID) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {
            if (movie.getUniqueID().equals(movieID)) {
                return movie.getQuantity();
            }
        }
        throw new MatchNotFoundException("No Match Found");
    }

    public Integer findPriceByID(Integer movieID) throws MatchNotFoundException {
        for (Movie movie : movieCollection) {
            if (movie.getUniqueID().equals(movieID)) {
                return movie.getPrice();
            }
        }
        throw new MatchNotFoundException("No Match Found");
    }

    /**
     * This method saves the state of inventory to file
     * and delete the commnadfile.
     */

    public void saveState() {
        memento.saveState(movieCollection);
        File file = new File(CommandFileName);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method get the previous state of inventory from file
     * and also runs command to take inventory to original state
     */

    public void getState() {
        invent.getState();
        movieCollection = (invent.getInvent().getMovieCollection());
    }
}