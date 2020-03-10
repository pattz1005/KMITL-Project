
package example1.movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GenRecords {

    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                                    .configure("example1/hibernate.cfg.xml")
                                    .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            Movie m1 = new Movie("The Shawshank Redemption", "Crime", 
                    "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                    "Frank Darabont", 1994);
            Movie m2 = new Movie("The Godfather", "Crime",
                    "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                    "Francis Ford Coppola", 1972);
            Movie m3 = new Movie("The Dark Knight", "Action",
                    "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                    "Christopher Nolan", 2008);
            Movie m4 = new Movie("12 Angry Men", "Crime",
                    "A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.",
                    "Sidney Lumet", 1957);
            Movie m5 = new Movie("Fight Club", "Drama",
                    "An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soapmaker, forming an underground fight club that evolves into something much, much more.",
                    "David Fincher", 1999);
            
            session.beginTransaction();
            
            session.save(m1);
            session.save(m2);
            session.save(m3);
            session.save(m4);
            session.save(m5);
            
            session.getTransaction().commit();
            
        } finally {
            factory.close();
        }
        
    }
}
