import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example {

    public static void main(String[] args) {
        
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            
            Office office = new Office();
            office.setAddressone("One");
            office.setCity("Bangkok");
            office.setName("KMITL");
            session.save(office);
            
            Employee e1 = new Employee();
            e1.setFirstname("Jason");
            e1.setLastname("Statham");
            e1.setLocation("USA");
            e1.setOffice(office);
            
            Employee e2 = new Employee();
            e2.setFirstname("Virayut");
            e2.setLastname("Sandhu");
            e2.setLocation("Thailand");
            e2.setOffice(office);
            
            session.save(e1);
            session.save(e2);
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
