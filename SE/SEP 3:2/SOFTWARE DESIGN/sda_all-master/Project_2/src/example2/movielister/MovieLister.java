/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example2.movielister;
import java.util.List;

/**
 *
 * @author alok
 */
public class MovieLister implements Lister{
    private MovieFinder movieFinder;
    
    public MovieLister(MovieFinder movieFinder){
        this.movieFinder = movieFinder;
    }
    
    public void setMovieFinder(MovieFinder movieFinder){
        this.movieFinder = movieFinder;
        System.out.println("Finder switched to: "+movieFinder.getClass());
    }
    
    public void listMovieByName(String name){
        List movieList = this.movieFinder.findMoviesByName(name);
        System.out.println("---------------------------------------------------------------");
        for(Object movie:movieList){
            System.out.println(movie+"\n");
        }
        System.out.println("---------------------------------------------------------------");
    }
    public void listMoviesByDirector(String name){
        List movieList = this.movieFinder.findMoviesByDirector(name);
        System.out.println("---------------------------------------------------------------");
        for(Object movie:movieList){
            System.out.println(movie+"\n");
        }
        System.out.println("---------------------------------------------------------------");
    }
    public void listMoviesByReleaseDate(String name){
        List movieList = this.movieFinder.findMoviesByReleaseDate(name);
        System.out.println("---------------------------------------------------------------");
        for(Object movie:movieList){
            System.out.println(movie);
        }
        System.out.println("---------------------------------------------------------------");
    }
}
