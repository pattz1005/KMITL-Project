/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author nickytan
 */
public class createEmp_Office {
    
public static void main(String[] args) {
    EntityManagerFactory emf;
    emf = Persistence.createEntityManagerFactory("persistenceDemoPU");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Employee e1 = new Employee();
    e1.setId(Long.valueOf(497));
    e1.setVersion(992);
    e1.setFirstname("Ian");
    e1.setLastname("nicky");
    e1.setCreatedate(new Date(1560000000000L));
    e1.setModifieddate(new Date(1560000000000L));
    e1.setLocation("Bangkok");
    e1.setPhonenumber("95453453");
    e1.setOfficeId(BigInteger.valueOf(5));
    em.persist(e1);
    
    Office o = new Office();
    o.setIdOffice(Long.valueOf(543));
    o.setCreateddateOffice(new Date(1560000000000L));
    o.setModifieddateOffice(new Date(1560000000000L));
    o.setVersionOffice(3);
    o.setName("HelloKitty");
    o.setAddressone("Chalongkrung");
    o.setAddresstwo("1");
    o.setCity("Bangkok");
    o.setState("IDK");
    o.setZipcode("10520");
    em.persist(o);


    em.getTransaction().commit();

    em.close();
    emf.close();
}
}
