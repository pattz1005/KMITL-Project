
package example1;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseMovieFinder implements MovieFinder {
    
    private SessionFactory factory;
    
    public DatabaseMovieFinder(String filename) {
        this.factory = new Configuration()
                            .configure(filename)
                            .buildSessionFactory();
    }

    @Override
    public List<Movie> searchByMovieName(String name) {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        List<Movie> result = session.createQuery("from Movie m where m.movieName like '%" +
                                                name + "%'").getResultList();
        
        session.getTransaction().commit();
        
        return result; 
    }

    @Override
    public List<Movie> searchByGenre(String name) {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        List<Movie> result = session.createQuery("from Movie m where m.genre like '%" +
                                                name + "%'").getResultList();
        
        session.getTransaction().commit();
        
        return result; 
    }

    @Override
    public List<Movie> searchByDirectorName(String name) {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        List<Movie> result = session.createQuery("from Movie m where m.directorName like '%" +
                                                name + "%'").getResultList();
        
        session.getTransaction().commit();
        
        return result; 
    }

    @Override
    public List<Movie> searchByYear(int year) {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        List<Movie> result = session.createQuery("from Movie m where m.year = " +
                                                String.valueOf(year)).getResultList();
        
        session.getTransaction().commit();
        
        return result; 
    }

    @Override
    public List<Movie> getAllMovies() {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        List<Movie> result = session.createQuery("from Movie").getResultList();
        
        session.getTransaction().commit();
        
        return result;
    }
    
    public void cleanUp() {
        System.out.println("Closing Factory");
        this.factory.close();
    }
    
}

