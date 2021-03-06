
package example1;
import java.util.List;

public interface MovieFinder {
    
    public List<Movie> searchByMovieName(String name);
    public List<Movie> searchByGenre(String name);
    public List<Movie> searchByDirectorName(String name);
    public List<Movie> searchByYear(int year);
    public List<Movie> getAllMovies();

}
