/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example2;

import java.io.BufferedReader;
import java.util.List;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alok
 */
public class ConcreteFileReaderFactory implements FileReaderFactory{
    private ApplicationContext context;
    
    public ConcreteFileReaderFactory(ApplicationContext context){
        this.context = context;
    }
    
    public void setContext(ApplicationContext context){
        this.context = context;
    }

    @Override
    public List createList() {
        return (List) context.getBean("list");
    }

    @Override
    public BufferedReader createBufferedReader() {
        return (BufferedReader) context.getBean("buffered_reader");
    }
}
