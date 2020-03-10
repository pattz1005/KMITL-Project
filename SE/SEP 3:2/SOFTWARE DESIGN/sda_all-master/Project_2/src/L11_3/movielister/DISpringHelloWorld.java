package L11_3.movielister;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DISpringHelloWorld {

    public static void main(String[] args) {
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new PropertiesBeanDefinitionReader(bf);
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println(s);
        reader.loadBeanDefinitions(
                new FileSystemResource(
                        new File(s + "/src/L11_3/hello.properties")));

        MessageService service = (MessageService) bf.getBean("service");
        service.execute();
    }

}
