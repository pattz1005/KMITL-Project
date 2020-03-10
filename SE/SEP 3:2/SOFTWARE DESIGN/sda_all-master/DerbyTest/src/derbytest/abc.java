/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author nickytan
 */
public class abc {
    public abc() throws SQLException{
        
        boolean ctn = true;
        
        while (ctn==true){
        Scanner s = new Scanner(System.in);
        System.out.print("Add employee or office?(e/o):");
        String c = s.nextLine();
        
        if("e".equals(c) || "E".equals(c)){emp();}
       
        else if ("o".equals(c) || "O".equals(c)){office();}
            System.out.print("Continue?(y/n):");
            Scanner co = new Scanner(System.in);
            String conti = co.nextLine();
            if ("n".equals(conti)||"N".equals(conti)){
                ctn =false;
            }
            else if("y".equals(conti)||"Y".equals(conti)){
                ctn=true;
            }
        }
    }
    
public void emp() throws SQLException{
        Scanner sc =  new Scanner(System.in);
        System.out.print("Enter id:");
        int id  = sc.nextInt();
        
        Scanner sc1 =  new Scanner(System.in);
        System.out.print("Enter version:");
        int verison  = sc1.nextInt();
        
        Scanner sc2 =  new Scanner(System.in);
        System.out.print("Enter first name:");
        String fname = sc2.nextLine();
        
        Scanner sc3 =  new Scanner(System.in);
        System.out.print("Enter last name:");
        String lname = sc3.nextLine();
        
        Scanner sc4 =  new Scanner(System.in);
        System.out.print("Enter created date:");
        long cd = sc4.nextLong();
        
        Scanner sc5 =  new Scanner(System.in);
        System.out.print("Enter modified date:");
        long md = sc5.nextLong();
        
        Scanner sc6 =  new Scanner(System.in);
        System.out.print("Enter location:");
        String location = sc6.nextLine();
        
        Scanner sc7 =  new Scanner(System.in);
        System.out.print("Enter phone number:");
        String phone = sc7.nextLine();
        
        Scanner sc8 =  new Scanner(System.in);
        System.out.print("Enter office id:");
        int office_id = sc8.nextInt();
        
        Connection conn =  DriverManager.getConnection("jdbc:derby://localhost:1527/Employee","nicky","nicky");
        System.out.println("connected.");
        
        PreparedStatement emp  = conn.prepareStatement("insert into EMPLOYEE(ID,VERSION,FIRSTNAME,LASTNAME,CREATEDATE,MODIFIEDDATE,LOCATION,PHONENUMBER,OFFICE_ID)values(?,?,?,?,?,?,?,?,?)");
        emp.setInt(1, id);
        emp.setInt(2, verison);
        emp.setString(3, fname);
        emp.setString(4, lname);
        emp.setDate(5, new Date(cd));
        emp.setDate(6, new Date(md));
        emp.setString(7, location);
        emp.setString(8, phone);
        emp.setInt(9, office_id);
        
        int a = emp.executeUpdate();
        if(a>0){
            System.out.println("Employee Updated.");
        }
}

public void office() throws SQLException{
        Scanner sc =  new Scanner(System.in);
        System.out.print("Enter id:");
        int id  = sc.nextInt();
        
        Scanner sc4 =  new Scanner(System.in);
        System.out.print("Enter created date:");
        long cd = sc4.nextLong();
        
        Scanner sc5 =  new Scanner(System.in);
        System.out.print("Enter modified date:");
        long md = sc5.nextLong();
        
        Scanner sc1 =  new Scanner(System.in);
        System.out.print("Enter version:");
        int verison  = sc1.nextInt();
        
        Scanner sc2 =  new Scanner(System.in);
        System.out.print("Enter name:");
        String name = sc2.nextLine();
        
        Scanner sc3 =  new Scanner(System.in);
        System.out.print("Enter address one:");
        String address1 = sc3.nextLine();
        
        Scanner sc6 =  new Scanner(System.in);
        System.out.print("Enter address two:");
        String address2 = sc6.nextLine();
        
        Scanner sc7 =  new Scanner(System.in);
        System.out.print("Enter city:");
        String city = sc7.nextLine();
        
        Scanner sc8 =  new Scanner(System.in);
        System.out.print("Enter state:");
        String state = sc8.nextLine();
        
        Scanner sc9 =  new Scanner(System.in);
        System.out.print("Enter zipcode:");
        String zipcode = sc9.nextLine();
        
        Connection conn =  DriverManager.getConnection("jdbc:derby://localhost:1527/Employee","nicky","nicky");
        System.out.println("connected.");
        
        PreparedStatement ofi  = conn.prepareStatement("insert into OFFICE(ID_OFFICE,CREATEDDATE_OFFICE,MODIFIEDDATE_OFFICE,VERSION_OFFICE,NAME,ADDRESSONE,ADDRESSTWO,CITY,STATE,ZIPCODE)values(?,?,?,?,?,?,?,?,?,?)");
        
        ofi.setInt(1, id);
        ofi.setDate(2,new Date(cd));
        ofi.setDate(3,new Date(md));
        ofi.setInt(4,verison);
        ofi.setString(5, name);
        ofi.setString(6, address1);
        ofi.setString(7, address2);
        ofi.setString(8, city);
        ofi.setString(9, state);
        ofi.setString(10, zipcode);
        
        int b = ofi.executeUpdate();
        if(b>0){
            System.out.println("Office Updated.");
        }
        
        }
}