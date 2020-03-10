
package example1;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
    
    @Column(name="movie_name")
    private String movieName;
    
    @Column(name="genre")
    private String genre;
    
    @Column(name="description")
    private String description;
    
    @Column(name="director_name")
    private String directorName;
    
    @Column(name="pub_year")
    private int year;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    public Movie() {
        
    }

    public Movie(String movieName, String genre, String description, 
            String directorName, int year) {
        this.movieName = movieName;
        this.genre = genre;
        this.description = description;
        this.directorName = directorName;
        this.year = year;
    }
    
    public Movie(String movieName, String genre, String description, 
            String directorName, int year, int id) {
        this.movieName = movieName;
        this.genre = genre;
        this.description = description;
        this.directorName = directorName;
        this.year = year;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "****************************************\n" +
                "Id : " + this.id + "\n" +
                "Movie Name : " + this.movieName + "\n" +
                "Director Name : " + this.directorName + "\n" +
                "Genre : " + this.genre + "\n" +
                "Year : " + String.valueOf(this.year) + "\n" +
                "Description : " + this.description;
    }
    
}
