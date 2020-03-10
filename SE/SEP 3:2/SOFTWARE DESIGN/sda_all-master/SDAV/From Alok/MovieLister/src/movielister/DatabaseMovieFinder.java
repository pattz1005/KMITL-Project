/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movielister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author alok
 */
public class DatabaseMovieFinder implements MovieFinder{
    private EntityManagerFactory factory;
    private EntityManager em;
    
    public DatabaseMovieFinder(){
        this.factory = Persistence.createEntityManagerFactory("MovieListerPU");
        this.em = factory.createEntityManager();
    }

    @Override
    public List findMoviesByName(String name) {
        try {
            List movies = em.createNamedQuery("Movie_1.findByMovieName").setParameter("movieName", name).getResultList();
            return movies;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List findMoviesByDirector(String name) {
        try {
            List movies = em.createNamedQuery("Movie_1.findByDirector").setParameter("director", name).getResultList();
            return movies;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List findMoviesByReleaseDate(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
