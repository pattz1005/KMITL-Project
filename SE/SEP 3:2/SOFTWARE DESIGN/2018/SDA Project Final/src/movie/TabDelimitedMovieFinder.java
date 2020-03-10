
package movie;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabDelimitedMovieFinder implements MovieFinder {

    private ArrayList<Movie> movies = new ArrayList<>();
    private int idCounter = 1;

    public TabDelimitedMovieFinder(String filename) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                addMoive(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void addMoive(String newline){
       // Thor/Action/Thor is imprisoned on the planet Sakaar/Taika Waititi/2017
       // String movieName, String genre,String description, String directorName, int year
       String[] lst = newline.split("/");
       Movie newOne = new Movie(lst[0], lst[1], lst[2], lst[3], Integer.parseInt(lst[4]), this.idCounter);
       this.movies.add(newOne);
       this.idCounter++;
    }

    @Override
    public List<Movie> searchByMovieName(String name) {
        ArrayList<Movie> re_movies = new ArrayList<>();
        for(Movie movie: movies){
            if(movie.getMovieName().contains(name)){
                re_movies.add(movie);
            }
        }
        return re_movies;
    }

    @Override
    public List<Movie> searchByDirectorName(String name) {
        ArrayList<Movie> re_movies = new ArrayList<>();
        for(Movie movie: movies){
            if(movie.getDirectorName().contains(name)){
                re_movies.add(movie);
            }
        }
        return re_movies;
    }

    @Override
    public List<Movie> searchByYear(int year) {
        ArrayList<Movie> re_movies = new ArrayList<>();
        for(Movie movie: movies){
            if(year == movie.getYear()){
                re_movies.add(movie);
            }
        }
        return re_movies;
    }

    @Override
    public List<Movie> searchByGenre(String genre) {
        ArrayList<Movie> re_movies = new ArrayList<>();
        for(Movie movie: movies){
            if(movie.getGenre().equals(genre)){
                re_movies.add(movie);
            }
        }
        return re_movies;
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<Movie>(this.movies);
    }
}
