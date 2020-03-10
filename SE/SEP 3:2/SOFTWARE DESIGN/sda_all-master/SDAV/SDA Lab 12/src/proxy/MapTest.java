package proxy;

import junit.framework.*;
import java.util.Date;
import proxy.AbstractMap;
import proxy.Map;

public class MapTest extends TestCase {

    private String fileName;
    private final int COUNT = 10000;

    public MapTest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MapTest.class);
        return suite;
    }

    public void setUp() throws Exception {
        String sep = System.getProperty("file.separator");
        if (sep.equals("/")) {
            // assume we are on Unix.
            fileName = "/tmp/key_values";
        }
        else {
            fileName = "C:\\Users\\viray\\Documents\\Projects\\SDA Lab 12\\data\\data.properties";
        }
    }

    public void test1() throws Exception {
        AbstractMap map = new Map(fileName);
    }

    public void test2() throws Exception {
        String value = "Eric Dubuis";
        AbstractMap map = new Map(fileName);
        map.add("name", value);
        String result = map.find("name");
        assertEquals(value, result);
    }

    public void test3() throws Exception {
        // Performance test.
        String value = "Eric Dubuis";
        AbstractMap map = new Map(fileName);
        map.add("name", value);
        System.out.println("\nStarting loop at: " + new Date().toString());
        for (int i = 0; i < COUNT; i++) {
            map.find("name");
        }
        System.out.println("Finished loop at: " + new Date().toString());
    }

    public void tearDown() throws Exception {
        //
    }
}
