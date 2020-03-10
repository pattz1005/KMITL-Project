package helloworld;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

public class DIXMLSpringHelloWorld {

  public static void main(String[] args) {
    XmlBeanFactory bf =
      new XmlBeanFactory(
        new FileSystemResource(
          new File("C:\\Users\\alok\\Documents\\NetBeansProjects\\lab11\\src\\helloworld\\hello.xml")));

    MessageService service = (MessageService) bf.getBean("service");
    service.execute();
  }

}
