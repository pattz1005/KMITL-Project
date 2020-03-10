/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example2;
import java.util.Date;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alok
 */
public interface MovieFactory {
    public Movie createMovie(String name, String director);
    public void setContext(ApplicationContext context);
}
