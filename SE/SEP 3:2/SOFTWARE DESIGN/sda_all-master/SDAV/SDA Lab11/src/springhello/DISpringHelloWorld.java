package springhello;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

public class DISpringHelloWorld {

    public static void main(String[] args) {
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new PropertiesBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(
                new FileSystemResource(
                        new File("hello.properties")));

        MessageService service = (MessageService) bf.getBean("service");
        service.execute();
    }

}
