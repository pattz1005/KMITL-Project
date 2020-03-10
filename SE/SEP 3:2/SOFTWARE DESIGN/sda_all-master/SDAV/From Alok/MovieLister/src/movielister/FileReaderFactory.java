/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movielister;

import java.io.BufferedReader;
import java.util.List;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alok
 */
public interface FileReaderFactory {
    public void setContext(ApplicationContext context);
    public List createList();
    public BufferedReader createBufferedReader();
}
