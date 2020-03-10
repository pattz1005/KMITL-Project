/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example2.movielister;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alok
 */
public class TabDelimitedMovieFinder implements MovieFinder{
    private MovieFactory movieFactory;
    private FileReaderFactory fileReaderFactory;

    public TabDelimitedMovieFinder(MovieFactory movieFactory, FileReaderFactory fileReaderFactory){
        this.movieFactory = movieFactory;
        this.fileReaderFactory = fileReaderFactory;
    }
    
    public void setMovieFactory(MovieFactory movieFactory){
        this.movieFactory = movieFactory;
    }
    
    public void setFileReaderFactory(FileReaderFactory fileReaderFactory) {
        this.fileReaderFactory = fileReaderFactory;
    }
    
    public List readFile(){
        List movies = fileReaderFactory.createList();
        try{
            BufferedReader reader = fileReaderFactory.createBufferedReader();
            String line;
            while((line = reader.readLine())!=null){
                String[]attributes = line.split("\t", -1);
                Movie movie = movieFactory.createMovie(attributes[2], attributes[1]);
                movies.add(movie);
            }
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List findMoviesByName(String name) {
        List movies = this.readFile();
        List toReturn = fileReaderFactory.createList();
        for(Object object:movies){
            Movie movie = (Movie) object;
            if(movie.getMovieName().equalsIgnoreCase(name)){
                toReturn.add(object);
            }
        }
        return toReturn;
    }

    @Override
    public List findMoviesByDirector(String name) {
        List movies = this.readFile();
        List toReturn = fileReaderFactory.createList();
        for(Object object:movies){
            Movie movie = (Movie) object;
            if(movie.getDirector().equalsIgnoreCase(name)){
                toReturn.add(object);
            }
        }
        return toReturn;
    }

    @Override
    public List findMoviesByReleaseDate(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
