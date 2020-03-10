package L11_3;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DIXMLSpringHelloWorld {

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println(s);
        XmlBeanFactory bf
                = new XmlBeanFactory(
                        new FileSystemResource(
                                new File(s + "/src/L11_3/hello.xml")));

        MessageService service = (MessageService) bf.getBean("service");
        service.execute();
    }

}
