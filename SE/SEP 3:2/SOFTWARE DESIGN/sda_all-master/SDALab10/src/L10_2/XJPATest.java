package L10_2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XJPATest {

    public static void main(String[] args) {
        EntityManagerFactory EMFactory = Persistence.createEntityManagerFactory("SDALab10_2");
        EntityManager entitymanager = EMFactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        //Create Office
        Office office1 = new Office();
        PostalAddress poOffice = new PostalAddress("Lkhiag City", "2, Exebuvi Ave.", "47, Exebuvi Ave", "133377", "Khaj State");
        office1.setPostalAddress(poOffice);

        //Register office to DB
        entitymanager.persist(office1);

        //Create employees, put to DB
        Employee e1 = new Employee("Aqevet", "Bviqaruli",
                "Lkhiag City", "0011133335");
        e1.setOffice(office1);
        entitymanager.persist(e1);

        Employee e2 = new Employee("Huqqurit", "Mshvetli",
                "Girsun City", "0011133331");
        e2.setOffice(office1);
        entitymanager.persist(e2);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        EMFactory.close();
    }
}
