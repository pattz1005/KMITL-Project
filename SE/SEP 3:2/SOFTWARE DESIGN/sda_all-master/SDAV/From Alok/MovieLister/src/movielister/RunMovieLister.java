/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movielister;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

/**
 *
 * @author alok
 */
public class RunMovieLister {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Lister lister = (Lister) context.getBean("lister");
        MovieFinder fileFinder = (MovieFinder) context.getBean("file_finder");
        MovieFinder databaseFinder = (MovieFinder) context.getBean("database_finder");
        String currentFinder = "file";
        String out = "---------------------------------------------------------------\nr: Switch movie finder\nn: Search movie by name\nd: Search movie by director\nq: quit";
        while(true){
            System.out.println(out);
            Scanner sc=new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("r")){
                if(currentFinder=="file"){
                    lister.setMovieFinder(databaseFinder);
                    currentFinder = "database";
                }
                else if(currentFinder=="database"){
                    lister.setMovieFinder(fileFinder);
                    currentFinder = "file";
                }
            }
            else if(input.equalsIgnoreCase("n")){
                System.out.print("Enter movie name: ");
                input = sc.nextLine();
                lister.listMovieByName(input);
                System.out.println("");
            }
            else if(input.equalsIgnoreCase("d")){
                System.out.print("Enter director name: ");
                input = sc.nextLine();
                lister.listMoviesByDirector(input);
                System.out.println("");
            }
            else if(input.equalsIgnoreCase("q")){
                break;
            }
        }
    }
}
