package movie;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("application.xml");

        MovieFinder text = context.getBean("textDatabase", MovieFinder.class);
        MovieFinder real = context.getBean("realDatabase", MovieFinder.class);

        MovieLister lister = context.getBean("lister", MovieLister.class);
                
        Scanner scanner = new Scanner(System.in);
        
        String command = "";
        String search = "";
        boolean running = true;
        while (running) {
            System.out.println("**************************");
            System.out.println("1) List all movies");
            System.out.println("2) Search by movie name");
            System.out.println("3) Search by director name");
            System.out.println("4) Search by genre");
            System.out.println("5) Search by year");
            System.out.println("6) Change to Text Database");
            System.out.println("7) Change to Real Database");
            System.out.println("8) Quit");
            System.out.print("Input : ");

            command = scanner.nextLine();

            if (command.equals("1")) {
                displayMovies(lister.getAllMovies());
            } else if (command.equals("2")) {
                System.out.print("Input Movie name : ");
                search = scanner.nextLine();
                displayMovies(lister.searchByMovieName(search));
            } else if (command.equals("3")) {
                System.out.print("Input Director name : ");
                search = scanner.nextLine();
                displayMovies(lister.searchByDirectorName(search));
            } else if (command.equals("4")) {
                System.out.print("Input Genre : ");
                search = scanner.nextLine();
                displayMovies(lister.searchByGenre(search));
            } else if (command.equals("5")) {
                System.out.print("Input Year : ");
                search = scanner.nextLine();
                displayMovies(lister.searchByYear(Integer.parseInt(search)));
            } else if (command.equals("6")) {
                lister.setmovieFinder(text);
                System.out.println("Changed to Text Database Succesfull");
            } else if (command.equals("7")) {
                lister.setmovieFinder(real);
                System.out.println("Changed to Real Database Succesfull");
            } else if (command.equals("8")) {
                running = false;
            } else {
                System.out.println("Wrong Input");
            }
        }
        System.exit(0);
    }

    private static void displayMovies(List<Movie> movies) {
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

}
