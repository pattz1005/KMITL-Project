
package example1.movie;
import java.util.List;

public class MovieLister {
    
    public MovieFinder movieFinder;

    public void setmovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
    
    public List<Movie> searchByGenre(String name) {
        return this.movieFinder.searchByGenre(name);
    }
    
    public List<Movie> searchByMovieName(String name) {
        return this.movieFinder.searchByMovieName(name);
    }
    
    public List<Movie> searchByDirectorName(String name) {
        return this.movieFinder.searchByDirectorName(name);
    }
    
    public List<Movie> searchByYear(int year) {
        return this.movieFinder.searchByYear(year);
    }
    
    public List<Movie> getAllMovies() {
        return this.movieFinder.getAllMovies();
    }

    public void setMovieFinder(TabDelimitedMovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
