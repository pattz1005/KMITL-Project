/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movielister;

import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alok
 */
public class ConcreteMovieFactory implements MovieFactory{
    private ApplicationContext context;
    
    public ConcreteMovieFactory(ApplicationContext context){
        this.context = context;
    }
    
    public Movie createMovie(String name, String director){
        Movie movie = (Movie) context.getBean("movie");
        movie.setMovieName(name);
        movie.setDirector(director);
        return movie;
    }
    
    public void setContext(ApplicationContext context){
        this.context = context;
    }
}
