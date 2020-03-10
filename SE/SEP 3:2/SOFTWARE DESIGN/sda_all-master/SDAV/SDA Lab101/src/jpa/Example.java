package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Example {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SDA_Lab10PU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        // Create Office
        Office office = new Office();
        office.setAddressone("one");
        office.setCity("Bangkok");
        
        entitymanager.persist(office);
        
        Employee e1 = new Employee();
        e1.setFirstname("Jason");
        e1.setLastname("Statham");
        e1.setLocation("USA");
        e1.setOfficeId(office);
        
        Employee e2 = new Employee();
        e2.setFirstname("Virayut");
        e2.setLastname("Sandhu");
        e2.setLocation("Thailand");
        e2.setOfficeId(office);
        
        entitymanager.persist(e1);
        entitymanager.persist(e2);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
    }
}
